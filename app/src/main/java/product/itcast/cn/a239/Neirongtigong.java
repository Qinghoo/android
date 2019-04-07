package product.itcast.cn.a239;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Neirongtigong extends Activity implements View.OnClickListener
{
    Button student ;  //为表student添加数据；
    Button cencle;
    ContentResolver resolver;
    final static String CONTENT_URI="content://product.itcast.cn.a239.conyentprovider/";

    String[] names = {"a","b","c","d","e","f","g","h","i"};//姓名；
    int[] balance = {1, 2, 3, 4, 5, 6, 7, 8, 9}; //金额；


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charushuju);
        init();

        File file = new File("data/data/com.feicui.contentprovider/databases/DB");
        if(file.exists())  //如果文件已经存在则删除；
        {
            file.delete();
        }
    }

    private void init()
    {
        student = (Button) findViewById(R.id.charu_btn);
        cencle = (Button) findViewById(R.id.cencle6_btn);


        student.setOnClickListener(this);
        cencle.setOnClickListener(this);


        resolver = getContentResolver();  //获取ContentResolver对象；
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.charu_btn:
                for(int i=0; i<names.length; i++)
                {
                    ContentValues values=new ContentValues();
                    values.put("name", names[i]);
                    values.put("balance", balance[i]);
                    resolver.insert(Uri.parse(CONTENT_URI+"account"), values);
                }
                //由插入界面返回登录界面
                Intent intent_charu_to_main = new Intent(Neirongtigong.this,main_Activity.class) ;    //切换Resetpwd Activity至Login Activity
                startActivity(intent_charu_to_main);
                finish();
                break;
            case R.id.cencle6_btn:
                Intent intent1 = new Intent(Neirongtigong.this,main_Activity.class) ;    //切换Resetpwd Activity至Login Activity
                startActivity(intent1);
                finish();
                break;

            default:
                break;
        }
    }
}
