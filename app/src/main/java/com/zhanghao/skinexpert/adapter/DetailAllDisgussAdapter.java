package com.zhanghao.skinexpert.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.Activity.CommentActivity;
import com.zhanghao.skinexpert.Activity.UserInfoActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.DetailAllDisgussBean;
import com.zhanghao.skinexpert.view.PercentLinearLayout;

import java.util.List;

/**
 * Created by 黑曼巴ye on 2016/12/23.
 */

public class DetailAllDisgussAdapter extends BaseAdapter {
    private Context context;
    private List<DetailAllDisgussBean.DataBean.ListBean> datalist;
    private LayoutInflater inflater;
    private PopupWindow popupWindow;

    public DetailAllDisgussAdapter(Context context, List<DetailAllDisgussBean.DataBean.ListBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder =null;
        if (convertView==null){
            mHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_detail_all_disguss,null);
            mHolder.img_head= (ImageView) convertView.findViewById(R.id.img_detail_alldisguss_item_header);
            mHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_detail_alldisguss_item_name);
            mHolder.tv_creattime= (TextView) convertView.findViewById(R.id.tv_item_alldisguss_creattime);
            mHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_detail_alldisguss_item_comment);
            mHolder.tv_likecount= (TextView) convertView.findViewById(R.id.tv_detail_alldisguss_item_likecount);
            mHolder.img_sandian= (ImageView) convertView.findViewById(R.id.img_alldisguss_item_sandian);
            mHolder.img_pinlun= (ImageView) convertView.findViewById(R.id.img_alldisguss_item_pinlun);

            mHolder.ll_pinlun_container= (LinearLayout) convertView.findViewById(R.id.ll_item_all_disguss_punluncontainer);
            convertView.setTag(mHolder);
        }else {
            mHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(datalist.get(position).getUser().getHeadface()).into(mHolder.img_head);
        //点击头像进入userinfo
        mHolder.img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                intent.putExtra("uid", datalist.get(position).getUser().getUid());
                intent.putExtra("userskin", datalist.get(position).getUser().getSkinText());
                context.startActivity(intent);
            }
        });
        //点击分享这个帖子;
        mHolder.img_sandian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
        mHolder.img_pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CommentActivity.class);
                intent.putExtra("id",datalist.get(position).getId());
                context.startActivity(intent);

            }
        });


        mHolder.tv_name.setText(datalist.get(position).getUser().getNick());
        //// TODO: 2016/12/23  设置时间判断
        mHolder.tv_creattime.setText(datalist.get(position).getCreateTime());
        mHolder.tv_content.setText(datalist.get(position).getContent());
        mHolder.tv_likecount.setText(datalist.get(position).getLikedCount()+"");
        List<DetailAllDisgussBean.DataBean.ListBean.CommentBean> comments = datalist.get(position).getComments();
        int count =comments.size()>=3 ? 3:comments.size();
        mHolder.ll_pinlun_container.removeAllViews();
        for (int i = 0; i <count ; i++) {
            DetailAllDisgussBean.DataBean.ListBean.CommentBean commentBean = comments.get(i);
            TextView tv=new TextView(context);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv.setText(commentBean.getNick()+" :" +commentBean.getContent());
            LinearLayout.LayoutParams params=new PercentLinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            mHolder.ll_pinlun_container.addView(tv);
        }
        return convertView;
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_window_share, null);
        TextView tv_share = (TextView) contentView.findViewById(R.id.tv_pop_win_share);
        tv_share.setText("分享这个帖子");
        //设置popupWindow焦点
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        //创建popupWindow
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //点击popupWindow以外隐藏
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        //popupWindow动画
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        //popupWindow以外的透明度
        WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.alpha = 0.7f;
        ((Activity) context).getWindow().setAttributes(params);
        //显示popupWindow
        popupWindow.showAtLocation(((Activity) context).findViewById(R.id.activity_product_detail), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(popupDismissListener);
        Button popupBtn = (Button) contentView.findViewById(R.id.btn_popu);
        popupBtn.setOnClickListener(dismissPopupListener);
        //TODO 分享
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
            WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
            params.alpha = 1f;
            ((Activity) context).getWindow().setAttributes(params);
        }
    }

    class ViewHolder{
        ImageView img_head,img_sandian,img_pinlun;
        TextView tv_name,tv_creattime,tv_content,tv_likecount;
        LinearLayout ll_pinlun_container;
    }
}
