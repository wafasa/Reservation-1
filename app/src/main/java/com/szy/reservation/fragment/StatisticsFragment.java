package com.szy.reservation.fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.szy.reservation.customview.RoundProgressBar;

import java.util.ArrayList;

public class StatisticsFragment extends BaseFragment {

	private static final String TAG = "StatisticsFragment";
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private RecyclerView mRecyclerView;
	private RecyclerViewAdapter mAdapter;
	ArrayList<BonusBean> list = new ArrayList<>();

	public static StatisticsFragment newInstance() {
		StatisticsFragment statisticsFragment = new StatisticsFragment();

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
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
				.resetViewBeforeLoading(true)
				.showImageOnLoading(R.mipmap.ic_launcher).build();

		BonusBean b3 = new BonusBean();
		b3.setUrl("drawable://" + R.drawable.goods);
		b3.setTitle("南山能源羽毛球管");
		b3.setAddress("南山区前海路309号");
		b3.setYd("300");
		b3.setWyd("188");
		b3.setPrice("Y 45");
		BonusBean b2 = new BonusBean();
		b2.setUrl("drawable://" + R.drawable.goods);
		b2.setTitle("南山能源羽毛球管");
		b2.setAddress("南山区前海路309号");
		b2.setYd("600");
		b2.setWyd("88");
		b2.setPrice("Y 45");

		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
		list.add(b3);
		list.add(b2);
       /* list.add(b);
        list.add(b);
        list.add(b);
        list.add(b);
        list.add(b);*/


		mRecyclerView = (RecyclerView) view
				.findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mAdapter = new RecyclerViewAdapter();
		mRecyclerView.setAdapter(mAdapter);
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
					R.layout.statistic_list_item, parent, false);
			return new ViewHolder(v);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onBindViewHolder(final ViewHolder listItemView,
									 final int position) {

			final BonusBean list = pictures.get(position);
			listItemView.title.setText(list.getTitle());
			listItemView.address.setText(list.getAddress());

			listItemView.yd.setText(list.getYd());
			listItemView.wyd.setText(list.getWyd());
			imageLoader.displayImage(list.getUrl(), listItemView.url, options);
			if(list.getYd().equals("300")){
				listItemView.rpb.setProgress((int)(300.0/488*100));
			}else{
				listItemView.rpb.setProgress((int)(600.0/688*100));
			}

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

		private TextView title, address,yd,wyd;
		private ImageView url;
		private RoundProgressBar rpb;
		private LinearLayout bg;
		private RelativeLayout lp;

		public ViewHolder(View convertView) {
			super(convertView);

			title = (TextView) convertView.findViewById(R.id.title);
			address = (TextView) convertView.findViewById(R.id.address);
			rpb = (RoundProgressBar) convertView.findViewById(R.id.roundProgressBar2);
			yd = (TextView) convertView.findViewById(R.id.yy);
			wyd = (TextView) convertView.findViewById(R.id.wy);
			url = (ImageView) convertView.findViewById(R.id.url);
			bg = (LinearLayout) convertView.findViewById(R.id.bg);
			lp = (RelativeLayout) convertView.findViewById(R.id.lr);

		}
	}


}
