package com.zhanghao.skinexpert.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.FundRedemptionBean;
import com.zhanghao.skinexpert.utils.Constant;
import com.zhanghao.skinexpert.utils.NetWorkRequest;
import com.zhanghao.skinexpert.utils.SQLiteHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SkinFundRedemption extends AppCompatActivity {
    private Button btnBack;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewHolderHead viewHolderHead;
    private Context context;
    private String activeTitle;
    private String url;
    private MyAdapter adapter;
    private String description;
    private PopupWindow popupWindow;
    private SQLiteHelper sqliteHelper;
    private SQLiteDatabase db;
    private List<FundRedemptionBean.DataBean.ListBean> datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_fund_redemption);
        context = SkinFundRedemption.this;

        initView();
        initData();
        setOnClick();
    }


    private void initData() {
        sqliteHelper = new SQLiteHelper(context);
        db = sqliteHelper.getReadableDatabase();
    }


    private void initView() {
        btnBack = (Button) findViewById(R.id.skin_fund_redemption_btn_back);
        listView = (ListView) findViewById(R.id.skin_fund_redemption_lv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.skin_fund_redemption_swipeRefreshLayout);

         adapter = new MyAdapter(datalist);
        listView.addHeaderView(getHeadView());
        listView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_red),
                getResources().getColor(R.color.refresh_red1),
                getResources().getColor(R.color.refresh_red2),
                getResources().getColor(R.color.refresh_red3));
        swipeRefreshLayout.setProgressViewOffset(false, 0, 85);
        //初次加载自动刷新
        setAutoRefresh();

        //刷新的监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

               setViewData();
            }

        });
        /**
         * 解决滑动冲突问题
         */
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(listView != null && listView.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
            }});

    }

    private void setViewData() {
        NetWorkRequest.addJSONRequest(context, Constant.SKIN_FUND_REDEMPTION_URL_GET, new NetWorkRequest.RequestCallBack() {
            @Override
            public void success(Object result) {
                JSONObject jsonObject = (JSONObject) result;
                JSONObject jsonObject2 = null;
                try {
                    jsonObject2=jsonObject.getJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getData(jsonObject2);
                viewHolderHead.text1ViewHead.setText(activeTitle);
                viewHolderHead.text2ViewHead.setText(description);
                adapter.notifyDataSetChanged();
                //停止刷新
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void fail(String result) {

            }
        });
    }

    private void setAutoRefresh() {
        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                setViewData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getData(JSONObject jsonObject) {
        try {

            activeTitle = jsonObject.getString("activeTitle");
            description = jsonObject.getString("description");
            url = jsonObject.getString("url");
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObjectData = jsonArray.getJSONObject(i);
                FundRedemptionBean.DataBean.ListBean listBean = new FundRedemptionBean.DataBean.ListBean();
                listBean.setPid((Integer) jsonObjectData.get("pid"));
                listBean.setTitle(jsonObjectData.getString("title"));
                listBean.setImage((String) jsonObjectData.getJSONArray("image").get(0));
                listBean.setDescription(jsonObjectData.getString("description"));
                listBean.setMaxDiscount(jsonObjectData.getInt("maxDiscount"));
                listBean.setStock(jsonObjectData.getInt("stock"));
                listBean.setBrandName(jsonObjectData.getString("brandName"));
                listBean.setBrandIcon(jsonObjectData.getString("brandIcon"));
                listBean.setBeginDateText(jsonObjectData.getString("beginDateText"));
                listBean.setBrandEnglishName(jsonObjectData.getString("brandEnglishName"));
                datalist.add(listBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private View getHeadView() {
        viewHolderHead = new ViewHolderHead();
        View view = LayoutInflater.from(context).inflate(R.layout.skin_fund_redemption_lv_item_head,null);
        viewHolderHead.text1ViewHead = (TextView) view.findViewById(R.id.skin_fund_redemption_lv_item_head_txt1);
        viewHolderHead.text2ViewHead = (TextView) view.findViewById(R.id.skin_fund_redemption_lv_item_head_txt2);
        viewHolderHead.linearLayout = (LinearLayout) view.findViewById(R.id.skin_fund_redemption_lv_item_head_ll);
        viewHolderHead.text1ViewHead.setText(activeTitle);
        viewHolderHead.text2ViewHead.setText(description);
        viewHolderHead.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至福利抢购说明界面
                Intent intent = new Intent(context,FundBuyInstructionsActivity.class);
                intent.putExtra("instructionUrl",url);
                startActivity(intent);
            }
        });
        return view;

    }


    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    class MyAdapter extends BaseAdapter{
        List<FundRedemptionBean.DataBean.ListBean> dataList ;

        public MyAdapter(List<FundRedemptionBean.DataBean.ListBean> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.skin_fund_redemption_lv_item_body,parent,false);
                viewHolder = new ViewHolder();

                viewHolder.imageicon = (ImageView) convertView.findViewById(R.id.skin_fund_redemption_lv_item_icon);
                viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.skin_fund_redemption_lv_item_title);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.skin_fund_redemption_lv_item_img);
                viewHolder.imgLike = (ImageView) convertView.findViewById(R.id.skin_fund_redemption_lv_item_imglike);
                viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.skin_fund_redemption_lv_item_ll);
                viewHolder.textView1 = (TextView) convertView.findViewById(R.id.fund_redemption_lv_item_txt1);
                viewHolder.textView2 = (TextView) convertView.findViewById(R.id.fund_redemption_lv_item_txt2);
                viewHolder.textView3 = (TextView) convertView.findViewById(R.id.fund_redemption_lv_item_txt3);
                viewHolder.textView4 = (TextView) convertView.findViewById(R.id.fund_redemption_lv_item_txt4);
                viewHolder.imageShare = (ImageView) convertView.findViewById(R.id.fund_redemption_lv_item_share);
                viewHolder.btnBuy = (Button) convertView.findViewById(R.id.fund_redemption_lv_item_buy);

                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (dataList.get(position).getBrandIcon()!=null&&!"".equals(dataList.get(position).getBrandIcon())){
                Picasso.with(context).load(dataList.get(position).getBrandIcon()).into(viewHolder.imageicon);
            }
            if (dataList.get(position).getImage()!=null&&!"".equals(dataList.get(position).getImage())){
                Picasso.with(context).load(dataList.get(position).getImage()).into(viewHolder.imageView);
            }
            viewHolder.txtTitle.setText(dataList.get(position).getBrandName()+" "+dataList.get(position).getBrandEnglishName());

            viewHolder.textView1.setText(dataList.get(position).getMaxDiscount()+"00基金+1元");
            viewHolder.textView2.setText(dataList.get(position).getTitle());
            viewHolder.textView3.setText(dataList.get(position).getDescription());
            viewHolder.textView4.setText("已有"+dataList.get(position).getStock()+"人抢到");
            viewHolder.btnBuy.setText(dataList.get(position).getBeginDateText());
            //检索数据库，判断是否喜欢产品
            int pid = dataList.get(position).getPid();
            final int id = pid;
            searchSQLite(pid,viewHolder.imgLike);
            viewHolder.imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    //点击喜欢
                   Cursor cursor = searchSQLite(id,viewHolder.imgLike);
                   if (cursor==null){
                       ContentValues values = new ContentValues();
                       values.put("product_id",id);
                       values.put("product_brand",dataList.get(position).getBrandName());
                       int index= dataList.get(position).getTitle().indexOf(dataList.get(position).getBrandEnglishName());
                       String productName= dataList.get(position).getTitle().substring(index+1,dataList.get(position).getTitle().length());
                       values.put("product_name",productName);
                       //TODO
                       values.put("product_pic","");//需要图片地址
                       db.insert(SQLiteHelper.table_wanted,null,values);
                       viewHolder.imgLike.setBackgroundResource(R.mipmap.heart_detail_want);
                   }else {
                       db.delete(SQLiteHelper.table_wanted,"product_id=?",new String[]{""+id});
                       viewHolder.imgLike.setBackgroundResource(R.mipmap.like_icon);
                   }
                }
            });
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击查看产品详情
                }
            });
            viewHolder.imageShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //分享界面
                    initPopupWindow();
                }
            });
            viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击抢购
                }
            });
            return convertView;
        }
    }
    //查询数据库
    private Cursor searchSQLite(int pid,ImageView imgview) {
        Cursor cursor = db.query(SQLiteHelper.table_wanted,null,"product_id=?",new String[]{""+pid},null,null,null);
        Log.i("RockTest:","测试点:"+cursor.getCount());
        if (cursor!=null&&cursor.getCount()>0){
            imgview.setBackgroundResource(R.mipmap.heart_detail_want);
            return cursor;
        }else {
            imgview.setBackgroundResource(R.mipmap.like_icon);
            return null;
        }
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_window_share, null);
        //设置popupWindow焦点
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        //创建popupWindow
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //点击popupWindow以外隐藏
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        //popupWindow动画
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        //popupWindow以外的透明度
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        //显示popupWindow
        popupWindow.showAtLocation(findViewById(R.id.skin_fund_redemption_swipeRefreshLayout), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(popupDismissListener);
        Button popupBtn = (Button) contentView.findViewById(R.id.btn_popu);
        popupBtn.setOnClickListener(dismissPopupListener);

    }


    View.OnClickListener dismissPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closePopupWindow();
        }
    };

    PopupWindow.OnDismissListener popupDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            closePopupWindow();
        }
    };

    private void closePopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        }
    }

    class ViewHolder{
        ImageView imageicon;
        TextView txtTitle;
        ImageView imageView ;
        ImageView imgLike;
        LinearLayout linearLayout;
        TextView  textView1;
        TextView  textView2;
        TextView  textView3;
        TextView  textView4;
        ImageView imageShare;
        Button btnBuy;
    }
    class ViewHolderHead{
        TextView text1ViewHead;
        TextView text2ViewHead;
        LinearLayout linearLayout;
    }


}
