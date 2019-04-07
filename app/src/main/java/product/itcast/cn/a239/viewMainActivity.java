package product.itcast.cn.a239;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class viewMainActivity extends AppCompatActivity {
    private List<Account> list;
    private AccountDao dao;
    private EditText nameET;
    private EditText balanceET;
    private MyAdapter adapter;
    private ListView accountLV;
    private Button cencle_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件

        initView();
        cencle_main=(Button) findViewById(R.id.cencle_main);
        dao = new AccountDao(this);
        // 从数据库查询出所有数据
        list = dao.queryAll();
        adapter = new MyAdapter();
        accountLV.setAdapter(adapter);
        cencle_main.setOnClickListener(mListener);


    }
    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cencle_main:                            //商品展示
                    Intent intent1 = new Intent(viewMainActivity.this,main_Activity.class) ;
                    startActivity(intent1);
                    finish();
                    break;
            }
        }
    };

        private void initView(){
        accountLV=(ListView) findViewById(R.id.accountLV);

        nameET=(EditText) findViewById(R.id.nameET);
        balanceET=(EditText) findViewById(R.id.balanceET);
        accountLV.setOnItemClickListener(new MyOnItewClickListener());
    }
    public void add(View v){
        String name=nameET.getText().toString().trim();
        String balance=balanceET.getText().toString().trim();
        Account a=new Account(name,balance.equals("")?0
        :Integer.parseInt(balance));
        dao.insert(a);
        list.add(a);
        adapter.notifyDataSetChanged();




        accountLV.setSelection(accountLV.getCount()-1);
        nameET.setText("");
        balanceET.setText("");

    }
    private class MyAdapter extends BaseAdapter{
        public int getCount(){
            return list.size();

        }
        public Object getItem(int position){
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View item = convertView!=null?convertView:View.inflate(getApplicationContext(),R.layout.item,null);
            TextView idTV=(TextView) item.findViewById(R.id.idTV);
            TextView nameTV=(TextView) item
                    .findViewById(R.id.nameTV);
            TextView balance=(TextView) item.findViewById(R.id.balanceTV);
            final Account a=list.get(position);
            idTV.setText(a.getId()+"");
            nameTV.setText(a.getName());
            balance.setText(a.getBalance()+"");
            ImageView upIV=(ImageView) item.findViewById(R.id.upIV);
            ImageView downIV=(ImageView) item.findViewById(R.id.downIV);
            ImageView deleteIV=(ImageView) item.findViewById(R.id.deleteIV);
            upIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a.setBalance(a.getBalance()+1);
                    notifyDataSetChanged();
                    dao.update(a);
                }
            });
            downIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a.setBalance(a.getBalance()-1);
                    notifyDataSetChanged();
                    dao.update(a);
                }
            });
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                            list.remove(a);
                            dao.delete(a.getId());
                            notifyDataSetChanged();
                        }
                    };
                    AlertDialog.Builder builder=new AlertDialog.Builder(viewMainActivity.this);
                    builder.setTitle("确定要删除吗");
                    builder.setPositiveButton("确定",listener);
                    builder.setNegativeButton("取消",null);
                    builder.show();
                }
            });
            return item;
        }
    }
    private class MyOnItewClickListener implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?>parent,View view,int position,long id){
            Account a = (Account) parent.getItemAtPosition(position);

            Toast.makeText(getApplicationContext(), a.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}








