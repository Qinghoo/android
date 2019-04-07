package product.itcast.cn.a239;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_Activity extends AppCompatActivity {
    private Button mReturnButton;
    private Button shuiguoview;
    private Button pwdxiugai;
    private Button no6;
    private Button no5;
    private Button no7;
    private Button no8;
    private Button no9;
    private Button no10;
    Bundle bundle;
    //private Button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mReturnButton = (Button)findViewById(R.id.returnback);
        pwdxiugai = (Button) findViewById(R.id.xiugai_btn);
        no5 = (Button) findViewById(R.id.no5);
        no6 = (Button) findViewById(R.id.no6);
        no7=(Button) findViewById(R.id.no7);
        no8=(Button) findViewById(R.id.no8);
        no9=(Button) findViewById(R.id.no9);
        no10=(Button) findViewById(R.id.no10);
        Intent in=this.getIntent();
        bundle=in.getExtras();


        pwdxiugai.setOnClickListener(mListener);
        no6.setOnClickListener(mListener);
        no5.setOnClickListener(mListener);
        no7.setOnClickListener(mListener);
        no8.setOnClickListener(mListener);
        no9.setOnClickListener(mListener);
        no10.setOnClickListener(mListener);

    }
    View.OnClickListener mListener = new View.OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.xiugai_btn:                            //xiugai
                    Intent intent3 = new Intent(main_Activity.this,Resetpwd.class) ;
                    intent3.putExtras(bundle);
                    startActivity(intent3);
                    finish();
                    break;
                case R.id.no5:                            //商品展示
                    Intent intent1 = new Intent(main_Activity.this,viewMainActivity.class) ;

                    startActivity(intent1);
                    finish();
                    break;
                case R.id.no6:                            //6章实例
                    Intent intent4 = new Intent(main_Activity.this,Neirongtigong.class) ;

                    startActivity(intent4);
                    finish();
                    break;

                case R.id.no7:                            //fangwenshuju
                    Intent intent2 = new Intent(main_Activity.this,Neirongfangwen.class) ;

                    startActivity(intent2);
                    finish();
                    break;
                case R.id.no8:                            //fangwenfuwu
                    Intent intent5 = new Intent(main_Activity.this,DiaoyongService.class) ;

                    startActivity(intent5);
                    finish();
                    break;
              case R.id.no9:                            //新闻客户端
                    Intent intent6 = new Intent(main_Activity.this,Newsmain.class) ;

                    startActivity(intent6);
                    finish();
                    break;
                     case R.id.no10:                            //刮刮卡
                   Intent intent8 = new Intent(main_Activity.this,Guaka.class) ;

                    startActivity(intent8);
                    finish();
                    break;


            }
        }
    };
    public void back_to_login(View view) {
        //setContentView(R.layout.login);
       /* Intent intent3 = new Intent(main_Activity.this,MainActivity.class);

        startActivity(intent3);*/
        finish();

    }
}
