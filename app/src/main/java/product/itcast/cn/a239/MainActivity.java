package product.itcast.cn.a239;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {                 //登录界面活动

    public int pwdresetFlag=0;
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮
    private Button mCancleButton;                     //注销按钮
    private CheckBox mRememberCheck;
    private Boolean auto_isCheck = false;
    private String userNameValue,passwordValue;


    private SharedPreferences login_sp;
   // private String userNameValue,passwordValue;

    private UserdataManager mUserDataManager;         //用户数据管理类


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //通过id找到相应的控件
        mAccount = (EditText) findViewById(R.id.login_input_username);
        mPwd = (EditText) findViewById(R.id.login_input_password);
        mRegisterButton = (Button) findViewById(R.id.zhuce_tv);
        mLoginButton = (Button) findViewById(R.id.login_btn);
       mCancleButton = (Button) findViewById(R.id.login_btn_cancle);

       // mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);

        mRememberCheck = (CheckBox) findViewById(R.id.remember_pwd);

        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        auto_isCheck = mRememberCheck.isChecked();

        mRememberCheck.setChecked(false);

        if (login_sp.getBoolean("mRememberCheck", false)) {
            //设置默认是自动登录状态
            mAccount.setText(name);
            mPwd.setText(pwd);
            Log.e("自动恢复保存的账号密码", "自动恢复保存的账号密码");
            mRememberCheck.setChecked(false);
            Intent intent = new Intent(MainActivity.this, main_Activity.class);
            MainActivity.this.startActivity(intent);
            Toast.makeText(getApplicationContext(), "已自动登录", Toast.LENGTH_SHORT);
           Log.e("自动登陆", "自动登陆");


        }



        mRegisterButton.setOnClickListener(mListener);                      //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mLoginButton.setOnClickListener(mListener);
        mCancleButton.setOnClickListener(mListener);

        if (mUserDataManager == null) {
            mUserDataManager = new UserdataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
    }



    OnClickListener mListener = new OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.zhuce_tv:                            //登录界面的注册按钮
                    Intent intent_Login_to_Register = new Intent(MainActivity.this,Register.class) ;    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.login_btn:                              //登录界面的登录按钮
                    login();
                    break;
                case R.id.login_btn_cancle:                             //登录界面的取消按钮
                    finish();
                    break;

            }
        }
    };

    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = mPwd.getText().toString().trim();
            auto_isCheck = mRememberCheck.isChecked();
            SharedPreferences.Editor editor =login_sp.edit();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //返回1说明用户名和密码均正确
                //保存用户名和密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);
                editor.putBoolean("auto_isCheck", auto_isCheck);

                //是否记住密码
                if(mRememberCheck.isChecked()){
                    editor.putBoolean("mRememberCheck", true);
                }else{
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();
                Log.e("测试", "测试");

                Intent intent = new Intent(MainActivity.this,main_Activity.class) ;    //切换Login Activity至User Activity
                Bundle bundle=new Bundle();
                bundle.putString("key",userName);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                Toast.makeText(this, getString(R.string.login_success),Toast.LENGTH_SHORT).show();//登录成功提示
            }else if(result==0){
                Toast.makeText(this, "登录失败",Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }


    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this,"用户名不能空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserdataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }

}

