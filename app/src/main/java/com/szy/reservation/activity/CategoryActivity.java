package com.szy.reservation.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.szy.reservation.R;
import com.szy.reservation.bean.BonusBean;

import java.util.ArrayList;


/**
 * �ҵĲ���
 * 
 * @author qht
 * @created 2015-8-11
 * @version 1.0
 */

public class CategoryActivity extends AppCompatActivity   {
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private RecyclerView mRecyclerView;
	private RecyclerViewAdapter mAdapter;
	ArrayList<BonusBean> list = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_activity);
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
		toolbar.setTitle(getIntent().getStringExtra("title"));
		setSupportActionBar(toolbar);
		// getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(CategoryActivity.this));
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
				.resetViewBeforeLoading(true)
				.showImageOnLoading(R.mipmap.ic_launcher).build();
		BonusBean b = new BonusBean();
		b.setUrl("drawable://" + R.drawable.goods);
		b.setTitle("南山能源羽毛球管");
		b.setAddress("南山区前海路309号");
		b.setPrice("Y 45");
		BonusBean b2 = new BonusBean();
		b2.setUrl("drawable://" + R.drawable.goods);
		b2.setTitle("南山能源羽毛球管");
		b2.setAddress("南山区前海路309号");
		b2.setPrice("Y 45");
		BonusBean b3 = new BonusBean();
		b3.setUrl("drawable://" + R.drawable.goods);
		b3.setTitle("南山能源羽毛球管");
		b3.setAddress("南山区前海路309号");
		b3.setPrice("Y 45");
		list.add(b);
		list.add(b2);
		list.add(b3);
		list.add(b3);
		list.add(b3);
		list.add(b3);
		list.add(b3);

       /* list.add(b);
        list.add(b);
        list.add(b);
        list.add(b);
        list.add(b);*/


		mRecyclerView = (RecyclerView)
				findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
		mAdapter = new RecyclerViewAdapter();
		mRecyclerView.setAdapter(mAdapter);
	}

	public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

		private ArrayList<BonusBean> pictures;

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
					R.layout.home_list_item, parent, false);
			return new ViewHolder(v);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onBindViewHolder(final ViewHolder listItemView,
									 final int position) {

			final BonusBean list = pictures.get(position);
			listItemView.title.setText(list.getTitle());
			listItemView.address.setText(list.getAddress());

			listItemView.price.setText(list.getPrice() + "元");

			imageLoader.displayImage(list.getUrl(), listItemView.url, options);

			listItemView.commit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					Intent activity_intent = new Intent(CategoryActivity.this,
							VenueDetailActivity.class);
                  /*  Bundle bundle = new Bundle();
                    bundle.putSerializable("data", list);
                    activity_intent.putExtras(bundle);*/
					startActivity(activity_intent);


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

		private TextView title, address, price, commit;
		private ImageView url;
		private LinearLayout bg;
		private RelativeLayout lp;

		public ViewHolder(View convertView) {
			super(convertView);

			title = (TextView) convertView.findViewById(R.id.title);
			address = (TextView) convertView.findViewById(R.id.address);
			commit = (TextView) convertView.findViewById(R.id.commit);
			price = (TextView) convertView.findViewById(R.id.price);
			url = (ImageView) convertView.findViewById(R.id.url);
			bg = (LinearLayout) convertView.findViewById(R.id.bg);
			lp = (RelativeLayout) convertView.findViewById(R.id.lr);

		}
	}


}
