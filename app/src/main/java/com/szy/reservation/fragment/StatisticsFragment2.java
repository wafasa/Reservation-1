package com.szy.reservation.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szy.reservation.R;
import com.szy.reservation.customview.PagerSlidingTabStrip;

public class StatisticsFragment2 extends BaseFragment {

	private static final String TAG = "StatisticsFragment";
	private TextView mTitleTv;
	private PagerSlidingTabStrip mPagerSlidingTabStrip;
	private ViewPager mViewPager;

	public static StatisticsFragment2 newInstance() {
		StatisticsFragment2 statisticsFragment = new StatisticsFragment2();

		return statisticsFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_statistics, container,
				false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initViews(View view) {
		mPagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
		mPagerSlidingTabStrip.setViewPager(mViewPager);
		mPagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				//colorChange(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		initTabsValue();
	}





	/**
	 * 颜色加深处理
	 *
	 * @param RGBValues
	 *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
	 *            Android中我们一般使用它的16进制，
	 *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
	 *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
	 *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
	 * @return
	 */
	private int colorBurn(int RGBValues) {
		int alpha = RGBValues >> 24;
		int red = RGBValues >> 16 & 0xFF;
		int green = RGBValues >> 8 & 0xFF;
		int blue = RGBValues & 0xFF;
		red = (int) Math.floor(red * (1 - 0.1));
		green = (int) Math.floor(green * (1 - 0.1));
		blue = (int) Math.floor(blue * (1 - 0.1));
		return Color.rgb(red, green, blue);
	}






	/**
	 * mPagerSlidingTabStrip默认值配置
	 *
	 */
	private void initTabsValue() {
		// 底部游标颜色
		mPagerSlidingTabStrip.setIndicatorColor(Color.WHITE);
		mPagerSlidingTabStrip.setUnderlineColor(Color.parseColor("#4081DA"));
		// tab的分割线颜色
		mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
		// tab背景
		mPagerSlidingTabStrip.setBackgroundColor(Color.parseColor("#4081DA"));
		// tab底线高度
		mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				6, getResources().getDisplayMetrics()));
		// 游标高度
		mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				5, getResources().getDisplayMetrics()));
		// 选中的文字颜色
		mPagerSlidingTabStrip.setSelectedTextColor(Color.WHITE);
		// 正常文字颜色
		mPagerSlidingTabStrip.setTextColor(Color.WHITE);
	}



	/* ***************FragmentPagerAdapter***************** */
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "分类", "主页", "热门推荐", "热门收藏", "本月热榜", "今日热榜", "专栏", "随机" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return SuperAwesomeCardFragment.newInstance(position,TITLES[position]);
		}

	}

























	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public String getFragmentName() {
		return TAG;
	}

}
