package product.itcast.cn.a239;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider{
    AccountDao dbutil;
    static UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    final static String AUTHORITY="product.itcast.cn.a239.conyentprovider";  //在清单文件注册时的authorities属性值；

    final static int URI_ACCOUNT=1;
  //  final static int URI_SALARY=2;
    final static int URI_ACCOUNT_WHICH=3;
//    final static int URI_SALARY_WHICH=4;

    static
    {
        //为UriMatcher注册2个Uri；
        uriMatcher.addURI(AUTHORITY, "account", URI_ACCOUNT);
        uriMatcher.addURI(AUTHORITY, "account/#", URI_ACCOUNT_WHICH);
    }
    @Override
    public boolean onCreate()
    {
        dbutil = new AccountDao(getContext());
        return false;
    }
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        int code = uriMatcher.match(uri);
        switch(code)
        {
            case URI_ACCOUNT:
                dbutil.query("account"); //查询student表下的所有数据；
                break;
          /*  case URI_SALARY:
                dbutil.queryAll("salary"); //查询salary表下的所有数据；
                break;*/
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        return null;
    }

    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    //  插入数据；
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        int code=uriMatcher.match(uri); //获取在上面static代码块中对应的code值；
        long insertID=0;
        switch (code)
        {
            case URI_ACCOUNT:
                insertID=dbutil.insert("account", values); //往student表中插入数据；
                break;
            /*case URI_SALARY:
                insertID=dbutil.insert("salary", values); //往salary表中插入数据；
                break;*/
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        return ContentUris.withAppendedId(uri, insertID);
    }

    //  删除数据；
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        int code=uriMatcher.match(uri);
        switch (code)
        {
            case URI_ACCOUNT:       //删除整个表的数据；
                System.out.println("---------selection----" + selection);
                dbutil.delete("account", selection, selectionArgs);
                break;
            case URI_ACCOUNT_WHICH: //删除表中指定的一个数据；
            {
                long id = ContentUris.parseId(uri);
                String whereClause = "id=" + id;
                if(selection != null && selection.equals("")) //如果原来的selection语句存在，则拼接语句；
                {
                    whereClause = selection + " and " + whereClause;
                }
                dbutil.delete("account", whereClause, selectionArgs);
                break;
            }
           /* case URI_SALARY:       //删除整个表的数据；
                dbutil.delete("salary", selection, selectionArgs);
                break;*/
           /* case URI_SALARY_WHICH://删除表中指定的一个数据；
            {
                long id2 = ContentUris.parseId(uri);
                String whereClause2 = "id=" + id2;
                if(selection != null && selection.equals("")) //如果原来的selection语句存在，则拼接语句；
                {
                    whereClause2 = selection + " and " + whereClause2;
                }
                dbutil.delete("salary", whereClause2, selectionArgs);
                break;
            }*/
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        return 0;
    }

    //更新数据；
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs)
    {
        int code=uriMatcher.match(uri);
        switch (code)
        {
            case URI_ACCOUNT:

                dbutil.update("account", values, selection);
                break;
           /* case URI_SALARY:
                dbutil.update("salary", values, selection);
                break;*/
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        return 0;
    }

}
