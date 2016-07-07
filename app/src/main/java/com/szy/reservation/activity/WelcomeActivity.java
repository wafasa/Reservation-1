package com.szy.reservation.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.szy.reservation.R;

import cn.jpush.android.api.JPushInterface;


public class WelcomeActivity extends Activity {

	private AlphaAnimation start_anima;
	private View view;
	private int curVersionCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);

		initView();
		initData();

	}

	private void initView() {
		// TODO Auto-generated method stub

	}

	private void initData() {
		start_anima = new AlphaAnimation(0.6f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redirectTo();
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(WelcomeActivity.this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(WelcomeActivity.this);
	}

	protected void redirectTo() {

		SharedPreferences sharedata = getSharedPreferences("version", 0);
		int befor_VersionCode = sharedata.getInt("versionCode", 0);
		if (getCurrentVersion() <= befor_VersionCode) {
			startActivity(new Intent(getApplicationContext(),
					MainActivity.class));
			/*overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);*/
			finish();
		} else {
			Editor sharedata1 = getSharedPreferences("version", MODE_PRIVATE)
					.edit();
			sharedata1.putInt("versionCode", getCurrentVersion());
			sharedata1.commit();
			startActivity(new Intent(getApplicationContext(),
					GuideActivity.class));
			/*overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);*/
			finish();
		}

	}

	private int getCurrentVersion() {
		try {
			PackageInfo info = this.getPackageManager().getPackageInfo(
					this.getPackageName(), 0);

			curVersionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
			return 0;
		}
		return curVersionCode;
	}

}
