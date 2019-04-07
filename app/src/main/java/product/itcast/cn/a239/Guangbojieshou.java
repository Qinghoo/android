package product.itcast.cn.a239;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Guangbojieshou extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getExtras().getString("name");
        Log.i("4164010239", "接收到:"+name);
      //  Log.i("Guangbojieshou", "接收到:"+name);


    }}

