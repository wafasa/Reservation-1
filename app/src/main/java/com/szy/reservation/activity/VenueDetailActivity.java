package com.szy.reservation.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jorge.circlelibrary.ImageCycleView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.szy.reservation.R;
import com.szy.reservation.bean.VenueBean;

import java.util.ArrayList;


/**
 * �ҵĲ���
 * 
 * @author qht
 * @created 2015-8-11
 * @version 1.0
 */

public class VenueDetailActivity extends AppCompatActivity   {
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private RecyclerView mRecyclerView;
	private RecyclerViewAdapter mAdapter;
	ImageCycleView imageCycleView;
	ArrayList<VenueBean> list = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.venue_detail_activity);
		init();
		initEvents();

	}

	private void initEvents() {
		// TODO Auto-generated method stub

		 
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private void init() {
		// TODO Auto-generated method stub

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("场馆详情");
		setSupportActionBar(toolbar);
		// getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(VenueDetailActivity.this));
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
				.resetViewBeforeLoading(true)
				.showImageOnLoading(R.mipmap.ic_launcher).build();
		/** 找到轮播控件*/
		imageCycleView = (ImageCycleView)  findViewById(R.id.cycleView);
		/**装在数据的集合 文字描述*/
		ArrayList<String> imageDescList = new ArrayList<>();
		/**装在数据的集合 图片地址*/
		ArrayList<String> urlList = new ArrayList<>();
		/**添加数据*/

		urlList.add("drawable://" + R.drawable.page1);
		urlList.add("drawable://" + R.drawable.page2);
		urlList.add("drawable://" + R.drawable.page3);
		urlList.add("drawable://" + R.drawable.page4);


		imageDescList.add("");
		imageDescList.add("");
		imageDescList.add("");
		imageDescList.add("");

		initCarsuelView(imageDescList, urlList);


		VenueBean b = new VenueBean();
		b.setTime("今天");
		b.setNumber("剩余:30");
		b.setDate("3月22日");
		VenueBean b2 = new VenueBean();
		b2.setTime("周三");
		b2.setNumber("剩余:20");
		b2.setDate("3月23日");
		VenueBean b3 = new VenueBean();
		b3.setTime("周四");
		b3.setNumber("剩余:40");
		b3.setDate("3月24日");
		VenueBean b4 = new VenueBean();
		b4.setTime("周五");
		b4.setNumber("剩余:10");
		b4.setDate("3月25日");
		VenueBean b5 = new VenueBean();
		b5.setTime("周六");
		b5.setNumber("剩余:23");
		b5.setDate("3月26日");
		VenueBean b6 = new VenueBean();
		b6.setTime("周日");
		b6.setNumber("剩余:3");
		b6.setDate("3月27日");

		list.add(b);
		list.add(b2);
		list.add(b3);
		list.add(b4);
		list.add(b5);
		list.add(b6);




		mRecyclerView = (RecyclerView)
				findViewById(R.id.recyclerview);
		LinearLayoutManager lm = new LinearLayoutManager(VenueDetailActivity.this);
		lm.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(lm);
		mAdapter = new RecyclerViewAdapter();
		mRecyclerView.setAdapter(mAdapter);
	}

	/**
	 * 初始化轮播图
	 */
	public void initCarsuelView(ArrayList<String> imageDescList, ArrayList<String> urlList) {
		LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (getScreenHeight(VenueDetailActivity.this) * 2.5 / 10));
		imageCycleView.setLayoutParams(cParams);
		ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
			@Override
			public void onImageClick(int position, View imageView) {
				/**实现点击事件*/
			}

			@Override
			public void displayImage(String imageURL, ImageView imageView) {
				/**在此方法中，显示图片，可以用自己的图片加载库，也可以用本demo中的（Imageloader）*/
				imageLoader.displayImage(imageURL, imageView);
			}
		};
		/**设置数据*/
		imageCycleView.setImageResources(imageDescList, urlList, mAdCycleViewListener);
		imageCycleView.startImageCycle();
	}


	/**
	 * 得到屏幕的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		if (null == context) {
			return 0;
		}
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		return dm.heightPixels;
	}



	public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

		private ArrayList<VenueBean> pictures;

		private int lastPosition = -1;
		private int temp;

		public RecyclerViewAdapter() {

			pictures = list;

		}

		private void setAnimation(View viewToAnimate, int position) {
			if (position > lastPosition) {
				Animation animation = AnimationUtils.loadAnimation(
						viewToAnimate.getContext(), R.anim.item_bottom_in);
				viewToAnimate.startAnimation(animation);
				lastPosition = position;
			}
		}

		@Override
		public void onViewDetachedFromWindow(ViewHolder holder) {
			super.onViewDetachedFromWindow(holder);

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.detail_list_item, parent, false);
			return new ViewHolder(v);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onBindViewHolder(final ViewHolder listItemView,
									 final int position) {

			final VenueBean list = pictures.get(position);
			listItemView.time.setText(list.getTime());
			listItemView.date.setText(list.getDate());

			listItemView.number.setText(list.getNumber());
			if(position==0){
				listItemView.number.setBackgroundResource(R.drawable.commit_bg_pressed);
				listItemView.number.setTextColor(getResources().getColor(R.color.white));
			}else{
				listItemView.number.setBackgroundResource(R.drawable.commit_bg);
				listItemView.number.setTextColor(getResources().getColor(R.color.black));
			}
			listItemView.number.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

                  /*  Intent activity_intent = new Intent(getActivity(),
                            WithdrawDetailRecordActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", list);
                    activity_intent.putExtras(bundle);
                    startActivity(activity_intent);*/


				}
			});

			setAnimation(listItemView.bg, position);


		}

		@Override
		public int getItemCount() {

			return pictures.size();
		}

     /*   public void loadFirst() {
            page = 1;
            loadData();
        }

        public void loadNextPage() {
            page++;
            LogUtils.i("GolobalBuy==page", page + "");
            loadData();

        }

        private void loadData() {
            final Handler handler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    if (mPullToRefreshRecyclerView != null) {
                        mPullToRefreshRecyclerView.onRefreshComplete();
                    }
                    switch (msg.arg1) {
                        case WHAT_DID_LOAD_DATA:
                            if (page == 1) {
                                setViewValue();

                            } else {

                            }

                            break;

                        default:
                            break;
                    }
                }

            };

            url = *//* Constant.TRANSACTION_DETAIL *//*Constant.BASE_SERVER_URL
                    + "&userId=" + MemberDataMgr.getUserId() + "&token="
                    + MemberDataMgr.getToken() + "&pageNum=" + page
                    + "&pageSize=" + PAGE_SIZE + "&" + "dataType=" + "list"
                    + "&" + "key=2502";

            LogUtils.i("myprize", "JSON==>" + url);

            StringRequest stringRequest = new StringRequest(url,
                    new Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            LogUtils.i("myprize", "JSON==>" + response);
                            // TODO Auto-generated method stub
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<WithdrawalRecordRootBean*//*
                                                                                                 * LinkedList
																								 * <
																								 * WithdrawalRecordBean
																								 * >
																								 *//*>() {
                            }.getType();

                            withdrawalRecordRootBean = gson.fromJson(response,
                                    type);
                            // withdrawalRecordBeans = gson.fromJson(json,
                            // type);
                            withdrawalRecordBeans = withdrawalRecordRootBean.data;
                            if (page == 1) {

                                RecyclerViewAdapter.this.pictures.clear();
                                RecyclerViewAdapter.this.temp = 0;
                                if (withdrawalRecordBeans.size() == 0) {
                                    initNoData();
                                    return;
                                }
                            }

                            for (int i = 0; i < withdrawalRecordBeans.size(); i++) {
                                RecyclerViewAdapter.this.pictures
                                        .add(withdrawalRecordBeans.get(i));
                            }

                            RecyclerViewAdapter.this.temp = 0;
                            notifyDataSetChanged();

                            Message msg = new Message();
                            msg.arg1 = WHAT_DID_LOAD_DATA;
                            msg.what = WHAT_DID_LOAD_DATA;
                            handler.sendMessage(msg);
                        }
                    }, new ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    initNetErro();
                    ToastUtil.show(WithdrawActivity.this,
                            "访问出现错误，请检查您的网络环境");
                }
            });

            RequestManager.getInstance(WithdrawActivity.this).addRequest(
                    stringRequest, WithdrawActivity.this);

        }*/

	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView time, date, number;
		private LinearLayout bg;

		public ViewHolder(View convertView) {
			super(convertView);

			time = (TextView) convertView.findViewById(R.id.time);
			date = (TextView) convertView.findViewById(R.id.date);
			number = (TextView) convertView.findViewById(R.id.number);
			bg = (LinearLayout) convertView.findViewById(R.id.bg);

		}
	}


}
