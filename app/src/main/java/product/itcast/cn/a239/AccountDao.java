package product.itcast.cn.a239;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class AccountDao {
    private MyHelper helper;
    public AccountDao(Context context){
        helper=new MyHelper(context);
    }
    public void insert(Account account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",account.getName());
        values.put("balance",account.getBalance());
        long id=db.insert("account",null,values);
        account.setId(id);
        db.close();
    }
    public int delete(long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("account","_id=?",new String[]{id+""});
        db.close();
        return count;
    }
    public int update(Account account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",account.getName());
        values.put("balance",account.getBalance());
        int count=db.update("account",values,"_id=?",new String[]{account.getId()+""});
        db.close();
        return count;
    }
    public List<Account> queryAll(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.query("account",null,null,null,null,null,"balance DESC");
        List<Account> list=new ArrayList<Account>();
        while (c.moveToNext()){
            long id=c.getLong(c.getColumnIndex("_id"));
            String name=c.getString(1);
            int balance=c.getInt(2);
            list.add(new Account(id,name,balance));
        }
        c.close();
        db.close();
        return list;
    }
    public long insert(String table, ContentValues values)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        long code=db.insert(table, null, values); //code为插入数据的行数；
        System.out.println(  table + "  "+code);
        db.close();
        return code;
    }

    //查询表中的所有与数据；
    public void query(String table)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        System.out.println("table ---" + table);
        if("account".equals(table))
        {
            String sql = "select * from account";
            Cursor cursor = db.rawQuery(sql, null);
            while(cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String balance = cursor.getString(cursor.getColumnIndex("balance"));
                System.out.println(table + ", id=" + id + ", name=" + name + ", balance=" + balance);
            }
        }
      /*  else if("salary".equals(table))
        {
            String sql = "select * from salary";
            Cursor cursor = db.rawQuery(sql, null);
            while(cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String money = cursor.getString(cursor.getColumnIndex("money"));
                System.out.println(table + ", id=" + id + ", name=" + name + ", money=" + money);
            }
        }*/
        db.close();
    }

    //删除由whereClause指定的数据；
    public void delete(String table, String whereClause, String[] whereArgs )
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.delete(table, whereClause, whereArgs);
        System.out.println("whereClause---" + whereClause);
        db.close();
    }

    //更新数据库，把符合whereClause条件的数据更改为values；
    public void update(String table, ContentValues values, String whereClause)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.update(table, values, whereClause, null);
        db.close();
    }
}

