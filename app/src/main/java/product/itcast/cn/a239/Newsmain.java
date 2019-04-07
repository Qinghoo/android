package product.itcast.cn.a239;

import java.io.ByteArrayInputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

public class Newsmain extends Activity {
    private ListView lv_news;
    private LinearLayout loading;
    private List<NewsInfo> newsInfos;
    private Button cenclenew_main;

    private class NewsAdapter extends BaseAdapter {
        public int getCount() {
            return newsInfos.size();
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(Newsmain.this, R.layout.news_item,
                    null);
            SmartImageView siv = (SmartImageView) view
                    .findViewById(R.id.siv_icon);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_description = (TextView) view
                    .findViewById(R.id.tv_description);
            TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
            NewsInfo newsInfo = newsInfos.get(position);
            siv.setImageUrl(newsInfo.getIconPath(), R.drawable.ic_launcher,
                    R.drawable.ic_launcher);
            tv_title.setText(newsInfo.getTitle());
            tv_description.setText(newsInfo.getDescription());
            int type = newsInfo.getType();
            switch (type) {
                case 1:
                    tv_type.setText("评论:" + newsInfo.getComment());
                    break;
                case 2:
                    tv_type.setTextColor(Color.RED);
                    tv_type.setText("专题");
                    break;
                case 3:
                    tv_type.setTextColor(Color.BLUE);
                    tv_type.setText("LIVE");
                    break;
            }
            return view;
        }
        //条目对象
        public Object getItem(int position) {
            return null;
        }
        //条目id
        public long getItemId(int position) {
            return 0;
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        lv_news = (ListView) findViewById(R.id.lv_news);
        cenclenew_main = (Button) findViewById(R.id.cenclenew_main);
        loading = (LinearLayout) findViewById(R.id.loading);
        cenclenew_main.setOnClickListener(mListener);
        fillData2();

    }
    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cenclenew_main:                            //商品展示
                    Intent intent1 = new Intent(Newsmain.this,main_Activity.class) ;
                    startActivity(intent1);
                    finish();
                    break;
            }
        }
    };
    //使用AsyncHttpClient访问网络
    private void fillData2() {
        //创建AsyncHttpClient实例
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //使用GET方式请求
        asyncHttpClient.get(Newsmain.this.getString(R.string.serverurl),
                new AsyncHttpResponseHandler() {
                    public void onSuccess(String content) {
                        //访问成功
                        super.onSuccess(content);
                        //将字符串转换成Byte数组
                        byte[] bytes = content.getBytes();
                        //将Byte数组转换成输入流
                        ByteArrayInputStream bais = new ByteArrayInputStream(
                                bytes);
                        //调用NewsInfoService工具类解析xml文件
                        newsInfos = NewsInfoService.getNewsInfos(bais);
                        if (newsInfos == null) {
                            // 解析失败 弹出toast
                            Toast.makeText(Newsmain.this,
                                    "解析失败", 0).show();
                        } else {
                            // 更新界面.
                            loading.setVisibility(View.INVISIBLE);
                            lv_news.setAdapter(new NewsAdapter());
                        }
                    }
                    //请求失败
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                        Log.e("error",Newsmain.this.getString(R.string.serverurl));
                        Log.e("error", error.toString());
                        Toast.makeText(Newsmain.this, "请求失败", 0).show();
                    }
                });
    }

}