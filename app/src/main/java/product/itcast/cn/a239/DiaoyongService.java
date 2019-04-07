package product.itcast.cn.a239;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DiaoyongService extends Activity {

    private IService iService;
    private MyConn conn;
    private Button no66;
    private Button no56;
    private Button no76;
    private Button no86;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaoyong219fuwu);
        no56 = (Button) findViewById(R.id.no56);
        no66= (Button) findViewById(R.id.no66);
        no76=(Button) findViewById(R.id.no76);
        no66.setOnClickListener(mListener);
        no56.setOnClickListener(mListener);
        no76.setOnClickListener(mListener);
        no86=(Button) findViewById(R.id.no86);
        no86.setOnClickListener(mListener);
    }
    View.OnClickListener mListener = new View.OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.no56:                            //xiugai

                    Intent service = new Intent();
                    service.setAction("forIMyAidllnterface");
                    conn = new MyConn();
                    //System.out.println("diaoyongle ");
                    bindService(service, conn, BIND_AUTO_CREATE);

                    break;
                case R.id.no66:                            //xiugai
                    try {
                        if (iService != null) {
                            iService.callALiPayService();
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                case R.id.no86:                            //xiugai

                    Intent intent1 = new Intent(DiaoyongService.this,main_Activity.class) ;
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.no76:                            //xiugai
                    if (conn != null) {
                        unbindService(conn);

                        Toast.makeText(getApplicationContext(), "jiechu", Toast.LENGTH_SHORT).show();
                        conn = null;
                        iService = null;
                    }
                    break;

            }
        }
    };
    public void bind(View view) {
        conn = new MyConn();
        //bindService(service, conn, BIND_AUTO_CREATE);
    }

    public void call(View view) {
        try {
            if (iService != null) {
                iService.callALiPayService();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iService = IService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    public void unbind(View view) {
        if (conn != null) {
            unbindService(conn);

            Toast.makeText(getApplicationContext(), "jiechu", Toast.LENGTH_SHORT).show();
            conn = null;
            iService = null;
        }
    }
}
