package product.itcast.cn.a239;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class ALiPayService extends Service {
    private static final String TAG = "ALiPayService";
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "绑定219");
        Toast.makeText(this,"绑定219",Toast.LENGTH_LONG).show();
        return new MyBinder();
    }
    private class MyBinder extends IService.Stub {
        @Override
        public void callALiPayService() {
            methodInService();
        }
    }
    private void methodInService() {
        Log.v(TAG, "开始操作");
    }
    @Override
    public void onCreate() {
        Log.v(TAG, "调用219成功");
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        Log.v(TAG, "关闭219程序");
        super.onDestroy();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "取消操作");
        return super.onUnbind(intent);
    }
}
