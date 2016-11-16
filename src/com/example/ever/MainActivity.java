package com.example.ever;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	private ViewPager mPager;
	private List<View> mList;
	private LinearLayout mLL;
	private List<ImageView> mList2;
	private int pic[] = new int[] { R.drawable.a, R.drawable.b, R.drawable.c };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mPager = (ViewPager) findViewById(R.id.viewpage);
		mLL = (LinearLayout) findViewById(R.id.ll);
		mList = new ArrayList<View>();
		mList2 = new ArrayList<ImageView>();
		for (int i = 0; i < pic.length; i++) {
			View v = View.inflate(this, R.layout.item_view_layout, null);
			ImageView image = (ImageView) v.findViewById(R.id.image);
			image.setImageResource(pic[i]);
			// 定义一个图片做为圆点
			ImageView mImageView = new ImageView(MainActivity.this);
			// 给该圆点设置背景选择
			mImageView.setBackgroundResource(R.drawable.select_shape);

			// 设置圆点间的距离
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			params.setMargins(10, 0, 10, 0);
			mImageView.setLayoutParams(params);
			// 将圆点放入集合中
			mList2.add(mImageView);
			// 将圆点放入布局中
			mLL.addView(mImageView);
			mList.add(v);
		}
		mPager.setAdapter(new MyAdapter());
		// 设置第一个页面为默认
		mPager.setCurrentItem(0);
		// 设置第一个圆点为默认展示
		mList2.get(0).setSelected(true);
		mPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < mList2.size(); i++) {
					if (arg0 == i) {
						mList2.get(i).setImageResource(
								R.drawable.shapeselect_layout);
					} else {
						mList2.get(i).setImageResource(
								R.drawable.shapedisselect_layout);
					}
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mList.get(position));
			return mList.get(position);
		}
	}

}
