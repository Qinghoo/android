package product.itcast.cn.a239;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Neirongfangwen extends Activity implements View.OnClickListener{
    ContentResolver resolver;
    Button insert, delete, update, query,cenclefamgwen_main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fangwenshuju);
        resolver=getContentResolver();
        init();
    }
    public void send1(View view){
        Intent intent=new Intent();
        intent.setAction("insertwoshi239");
        sendBroadcast(intent);
}
    public void send2(View view){
        Intent intent=new Intent();
        intent.setAction("detele woshi239");
        sendBroadcast(intent);
    }
    public void send3(View view){
        Intent intent=new Intent();
        intent.setAction("update woshi239");
        sendBroadcast(intent);
    }
    public void send4(View view){
        Intent intent=new Intent();
        intent.setAction("query woshi239");
        sendBroadcast(intent);
    }
    private void init()
    {
        insert = (Button)findViewById(R.id.insert_btn);
        delete = (Button)findViewById(R.id.delete_btn);
        update = (Button)findViewById(R.id.update_btn);
        query = (Button)findViewById(R.id.query_btn);
        cenclefamgwen_main = (Button)findViewById(R.id.cenclefamgwen_main);

        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
        cenclefamgwen_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.insert_btn:
              //  insertData(); //插入数据；
                Intent intent = new Intent();
                intent.setAction("com.insert");
                intent.putExtra("name", "插入value广播");
                sendBroadcast(intent);
                Toast.makeText(getApplicationContext(), "发送广播成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_btn:
            //    deleteData();        //删除整个表里的内容；
          // deleteCodeData("student", " name='a'");  //删除student表中name为a的数据；
           //     deleteCodeData("student/2", null); //删除student表中id为2的数据；(后面是int型数据时默认的就是指id);
                Intent intent1 = new Intent();
                intent1.setAction("com.lijin");
                intent1.putExtra("name", "删除value广播");
                sendBroadcast(intent1);
                Toast.makeText(getApplicationContext(), "发送广播成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_btn:
                //updateData(); //更新数据；
                Intent intent2 = new Intent();
                intent2.setAction("com.lijin");
                intent2.putExtra("name", "更新value广播");
                sendBroadcast(intent2);
                Toast.makeText(getApplicationContext(), "发送广播成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_btn:
               // queryData();  //查询数据；
                Intent intent3 = new Intent();
                intent3.setAction("com.lijin");
                intent3.putExtra("name", "查询value广播");
                sendBroadcast(intent3);
                Toast.makeText(getApplicationContext(), "发送广播成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cenclefamgwen_main:
                // queryData();  //查询数据；
                Intent intent20 = new Intent(Neirongfangwen.this,main_Activity.class);
                startActivity(intent20);
                finish();
                break;
        }

    }

    private void updateData()
    {
        ContentValues values = new ContentValues();
        values.put("money", 9999);
        resolver.update(Uri.parse("content://product.itcast.cn.a239.conyentprovider/account"), values, "name='d'", null);
    }

    private void deleteData()
    {
        resolver.delete(Uri.parse("content://product.itcast.cn.a219.conyentprovider/account"), null, null);
    }

    private void deleteCodeData(String table, String where)
    {
        resolver.delete(Uri.parse("content://product.itcast.cn.a219.conyentprovider/" + table), where, null);
    }

    private void queryData()
    {
        resolver.query(Uri.parse("content://product.itcast.cn.a219.conyentprovider/account"), null, null, null, null);
       // resolver.query(Uri.parse("content://com.feicui.contentprovider/salary"), null, null, null, null);
    }

    private void insertData()
    {
        ContentValues values1=new ContentValues();
        values1.put("name", "j");
        values1.put("number", 10);
        resolver.insert(Uri.parse("content://product.itcast.cn.a219.conyentprovider/account"), values1);
        ContentValues values2=new ContentValues();
        values2.put("name", "k");
        values2.put("number", 11);
        resolver.insert(Uri.parse("content://product.itcast.cn.a219.conyentprovider/account"), values2);

        ContentValues values3=new ContentValues();
        values3.put("name", "j");
        values3.put("money", 2232);
        resolver.insert(Uri.parse("product.itcast.cn.a219.conyentprovider/account"), values3);
        ContentValues values4=new ContentValues();
        values4.put("name", "k");
        values4.put("money", 2233);
        resolver.insert(Uri.parse("product.itcast.cn.a219.conyentprovider/account"), values4);
    }

}
