package com.szy.reservation.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.szy.reservation.R;
import com.szy.reservation.customview.MyTabWidget;
import com.szy.reservation.fragment.HomeFragment;
import com.szy.reservation.fragment.StatisticsFragment;
import com.szy.reservation.fragment.UserCenterFragment;
import com.szy.reservation.utils.ConstantValues;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends AppCompatActivity implements
        MyTabWidget.OnTabSelectedListener {

    private MyTabWidget mTabWidget;
    private HomeFragment mHomeFragment;
    private StatisticsFragment mStatisticsFragment;
    private UserCenterFragment mUserCenterFragment;
    private int mIndex = ConstantValues.HOME_FRAGMENT_INDEX;
    private FragmentManager mFragmentManager;

    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    private static final String TAG = "MainActivity";
    private Set<String> set = new HashSet<String>();

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 6002:
                    setAlias();
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlias();
        init();
        initEvents();


    }

    private void setAlias(){
        set.add("szqg");
        JPushInterface.setAliasAndTags(this, "szqg", set,
                new TagAliasCallback() {

                    @Override
                    public void gotResult(int code, String arg1,
                                          Set<String> arg2) {
                        // TODO Auto-generated method stub
                        switch (code) {
                            case 0:
                                // 建议这里往 SharePreference
                                // 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                                SharedPreferences.Editor sharedata1 = getSharedPreferences(
                                        "Jpush", MODE_PRIVATE).edit();
                                sharedata1.putInt("pushCode", 2);
                                sharedata1.commit();

                                break;
                            case 6002:
                                // 延迟 60 秒来调用 Handler 设置别名
                                mHandler.sendEmptyMessageDelayed(code,
                                        1000 * 60);
                                break;
                            default:
                                Log.i("JPush", "Jpush status: " + code);// 状态
                        }

                        Log.i("JPush", "Jpush status: " + code);// 状态
                    }

                });
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        mTabWidget = (MyTabWidget) findViewById(R.id.tab_widget);

    }

    private void initEvents() {
        mTabWidget.setOnTabSelectedListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        onTabSelected(mIndex);
        mTabWidget.setTabsDisplay(this, mIndex);
      //  mTabWidget.setIndicateDisplay(this, 2, true);
        JPushInterface.onResume(MainActivity.this);
    }

    @Override
    public void onTabSelected(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case ConstantValues.HOME_FRAGMENT_INDEX:
                if (null == mHomeFragment) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.center_layout, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                     HomeFragment.turnUP();
                }
                break;

            case ConstantValues.STATISTICS_FRAGMENT_INDEX:
                if (null == mStatisticsFragment) {
                    mStatisticsFragment = new StatisticsFragment();
                    transaction.add(R.id.center_layout, mStatisticsFragment);
                } else {
                    transaction.show(mStatisticsFragment);
                }
                break;
            case ConstantValues.USERCENTER_FRAGMENT_INDEX:
                if (null == mUserCenterFragment) {
                    mUserCenterFragment = new UserCenterFragment();
                    transaction.add(R.id.center_layout, mUserCenterFragment);
                } else {
                    transaction.show(mUserCenterFragment);
                }
                break;

            default:
                break;
        }
        mIndex = index;
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }

        if (null != mStatisticsFragment) {
            transaction.hide(mStatisticsFragment);
        }
        if (null != mUserCenterFragment) {
            transaction.hide(mUserCenterFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 自己记录fragment的位置,防止activity被系统回收时，fragment错乱的问题
        // super.onSaveInstanceState(outState);
        outState.putInt("index", mIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // super.onRestoreInstanceState(savedInstanceState);
        mIndex = savedInstanceState.getInt("index");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }






    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(MainActivity.this);
    }

}
