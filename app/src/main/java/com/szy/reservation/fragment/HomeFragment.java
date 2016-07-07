package com.szy.reservation.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jorge.circlelibrary.ImageCycleView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.szy.reservation.R;
import com.szy.reservation.activity.CategoryActivity;
import com.szy.reservation.activity.VenueDetailActivity;
import com.szy.reservation.bean.BonusBean;
import com.szy.reservation.customview.FullyLinearLayoutManager;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";
    private Activity mActivity;
    ImageCycleView imageCycleView;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    ArrayList<BonusBean> list = new ArrayList<>();
    private static ScrollView sv;
    private RelativeLayout tjc,yyg,zhty,jsf,jjg,gefc,wqg,qb;
    private boolean isFirst = true;
    private static int svX = 0;
    private static int svY = 0;


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();

        return homeFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .showImageOnLoading(R.mipmap.ic_launcher).build();
        sv = (ScrollView) view.findViewById(R.id.sv);
        /** 找到轮播控件*/
        imageCycleView = (ImageCycleView) view.findViewById(R.id.cycleView);
        /**装在数据的集合 文字描述*/
        ArrayList<String> imageDescList = new ArrayList<>();
        /**装在数据的集合 图片地址*/
        ArrayList<String> urlList = new ArrayList<>();
        /**添加数据*/

        urlList.add("drawable://" + R.drawable.page1);
        urlList.add("drawable://" + R.drawable.page2);
        urlList.add("drawable://" + R.drawable.page3);
        urlList.add("drawable://" + R.drawable.page4);

     /*   urlList.add("http://bbsdown10.cnmo.com/attachments/201308/06/091441rn5ww131m0gj55r0.jpg");
        urlList.add("http://kuoo8.com/wall_up/hsf2288/200801/2008012919460743597.jpg");
        urlList.add("http://d.3987.com/taiqiumein_141001/007.jpg");
        urlList.add("http://kuoo8.com/wall_up/hsf2288/200807/2008071115370276173.jpg");*/
        imageDescList.add("");
        imageDescList.add("");
        imageDescList.add("");
        imageDescList.add("");

        initCarsuelView(imageDescList, urlList);

        tjc =  (RelativeLayout)view.findViewById(R.id.tjc);
        yyg =  (RelativeLayout)view.findViewById(R.id.yyg);
        zhty =  (RelativeLayout)view.findViewById(R.id.zhty);
        jsf =  (RelativeLayout)view.findViewById(R.id.jsf);
        jjg =  (RelativeLayout)view.findViewById(R.id.jjg);
        gefc =  (RelativeLayout)view.findViewById(R.id.gefc);
        wqg =  (RelativeLayout)view.findViewById(R.id.wqg);
        qb =  (RelativeLayout)view.findViewById(R.id.qb);
        tjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","田径场");
                startActivity(activity_intent);
            }
        });
        yyg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","游泳馆");
                startActivity(activity_intent);
            }
        });
        zhty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","综合体育");
                startActivity(activity_intent);
            }
        });
        jsf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","健身房");
                startActivity(activity_intent);
            }
        });
        jjg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","击剑馆");
                startActivity(activity_intent);
            }
        });
        gefc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","高尔夫场");
                startActivity(activity_intent);
            }
        });
        wqg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","网球馆");
                startActivity(activity_intent);
            }
        });
        qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_intent = new Intent(getActivity(),
                        CategoryActivity.class);

                activity_intent.putExtra("title","全部");
                startActivity(activity_intent);
            }
        });

        return view;
    }

    /**
     * 初始化轮播图
     */
    public void initCarsuelView(ArrayList<String> imageDescList, ArrayList<String> urlList) {
        LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (getScreenHeight(getActivity()) * 2.5 / 10));
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

    public static void turnUP() {
        sv.scrollTo(svX,svY);


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        svX =  sv.getScrollX();
        svY =  sv.getScrollY();
    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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


        mRecyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //view加载完成时回调

        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                if (isFirst) {
                    sv.fullScroll(ScrollView.FOCUS_UP);

                }


            }
        });
        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isFirst = false;
                return false;
            }
        });
       /* sv.setScrollViewListener(new ObservableScrollView.ScrollViewListener(){
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                        int oldx, int oldy) {

            }

        });*/




    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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

                    Intent activity_intent = new Intent(getActivity(),
                            VenueDetailActivity.class);
                  /*  Bundle bundle = new Bundle();
                    bundle.putSerializable("data", list);
                    activity_intent.putExtras(bundle);*/
                    startActivity(activity_intent);
                   // Toast.makeText(getActivity(),"预订",Toast.LENGTH_LONG).show();

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
