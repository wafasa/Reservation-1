package com.szy.reservation.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.szy.reservation.R;


public class GuideActivity extends Activity {

	private ViewPager viewPager;
	private int[] imgs = { R.drawable.guide_01, R.drawable.guide_02, R.drawable.guide_03
			  };
	private int index = 0;
	private ViewPagerAdapter adapter;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_user_guide);
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(GuideActivity.this));
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
				.resetViewBeforeLoading(true)
				.showImageOnLoading(R.mipmap.ic_launcher).build();
		viewPager = (ViewPager) findViewById(R.id.welcome_pager);
		adapter = new ViewPagerAdapter(imgs);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new PageChangeListener());
	}



	private class PageChangeListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
		//	Toast.makeText(GuideActivity.this, arg0 + "", Toast.LENGTH_LONG).show();
			index = arg0;
		}

	}


	  class ViewPagerAdapter extends PagerAdapter {
		private int[] list  ;

		public ViewPagerAdapter(int[] list) {
			this.list = list;
		}

		@Override
		public int getCount() {

			if (list != null && list.length > 0) {
				return list.length;
			} else {
				return 0;
			}
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			ImageView iv = new ImageView(GuideActivity.this);
			String imageUri = "drawable://" +list[position]; //  drawable文件
			imageLoader.displayImage(imageUri,iv, options);
			//iv.setImageResource(list[position]);
			if(position==2){
				iv.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(getApplicationContext(),
								MainActivity.class));
						finish();
					}
				});
			}
			container.addView(iv);
			return iv;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

	}


}
