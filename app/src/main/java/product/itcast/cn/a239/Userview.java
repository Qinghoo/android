package product.itcast.cn.a239;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Userview extends Activity{

    private TextView tv_name,tv_password,tv_password1;
    private Button back_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userview);
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        String password1 = intent.getStringExtra("password1");
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_password=(TextView) findViewById(R.id.tv_password);
        tv_password1=(TextView) findViewById(R.id.tv_password1);
        back_btn= (Button) findViewById(R.id.back_btn);
        tv_name.setText("用户名："+name);
        tv_password.setText("密码："+password);
        tv_password1.setText("新密码："+password1);



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.back_btn){
               /* {Intent intent_Resetpwd_to_Login = new Intent(Userview.this,Resetpwd.class) ;    //切换Resetpwd Activity至Login Activity
                    startActivity(intent_Resetpwd_to_Login);*/
                    finish();}
            }
        });

}
}
