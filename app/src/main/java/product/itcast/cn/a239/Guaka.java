package product.itcast.cn.a239;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Guaka extends Activity {

	private ImageView mImageView;
	private Bitmap alterbitmap;
	Button cenclegk_main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guaka);
		mImageView = (ImageView) findViewById(R.id.imgv);
		cenclegk_main = (Button) findViewById(R.id.cenclegk_main);
		cenclegk_main.setOnClickListener(mListener);

		//从资源文件中解析一张bitmap
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guagua);
		alterbitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//创建一个canvas对象
		Canvas canvas = new Canvas(alterbitmap);
		//创建画笔对象
		Paint paint = new Paint();
		//为画笔设置颜色
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
		//创建Matrix对象
		Matrix matrix = new Matrix();
		//在alterBitmap上画图
		canvas.drawBitmap(bitmap, matrix, paint);
		//设置ImageView的背景
		mImageView.setImageBitmap(alterbitmap);
		mImageView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				try {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						//Toast.makeText(Guaka.this, "手指触下",Toast.LENGTH_SHORT).show();
						break;
					case MotionEvent.ACTION_MOVE:
						//Toast.makeText(Guaka.this, "手指移动("+event.getX()+","+event.getY()+")", Toast.LENGTH_SHORT).show();
						int x = (int) event.getX();
						int y = (int) event.getY();
						for(int i=-10;i<10;i++){
							for(int j=-10;j<10;j++){
								if(Math.sqrt((i*i)+(j*j))<=10){
									alterbitmap.setPixel(x+i, y+j, Color.TRANSPARENT);
								}
							}
						}
						mImageView.setImageBitmap(alterbitmap);
						break;
					case MotionEvent.ACTION_UP:
						//Toast.makeText(Guaka.this, "手指松开", Toast.LENGTH_SHORT).show();
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;//消费掉该触摸事件
			}
		});

	}
	View.OnClickListener mListener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.cenclegk_main:                            //商品展示
					Intent intent1 = new Intent(Guaka.this,main_Activity.class) ;
					startActivity(intent1);
					finish();
					break;
			}
		}
	};
}
