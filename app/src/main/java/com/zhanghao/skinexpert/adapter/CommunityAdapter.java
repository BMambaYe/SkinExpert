package com.zhanghao.skinexpert.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.skinexpert.Activity.BeautifulActivity;
import com.zhanghao.skinexpert.Activity.CommentActivity;
import com.zhanghao.skinexpert.Activity.HotSelectActivity;
import com.zhanghao.skinexpert.Activity.ProductDetailActivity;
import com.zhanghao.skinexpert.Activity.UserInfoActivity;
import com.zhanghao.skinexpert.R;
import com.zhanghao.skinexpert.beans.CommunityListViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */

public class CommunityAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CommunityListViewBean.DataBean.ListBean> allList = new ArrayList<>();
    private PopupWindow popupWindow;

    public CommunityAdapter(Context context, List<CommunityListViewBean.DataBean.ListBean> allList) {
        this.context = context;
        this.allList = allList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return allList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.communitylistview, null);
            mHolder = new ViewHolder();
            mHolder.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            mHolder.iv_content = (ImageView) convertView.findViewById(R.id.iv_content);
            mHolder.iv_like = (ImageView) convertView.findViewById(R.id.iv_like);
            mHolder.iv_comment = (ImageView) convertView.findViewById(R.id.iv_comment);
            mHolder.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            mHolder.tv_userskin = (TextView) convertView.findViewById(R.id.tv_userskin);
            mHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            mHolder.tv_like_count = (TextView) convertView.findViewById(R.id.tv_like_count);
            mHolder.tv_comment_count = (TextView) convertView.findViewById(R.id.tv_comment_count);
            mHolder.tv_all = (TextView) convertView.findViewById(R.id.tv_all);
            mHolder.tv_usercomment_count = (TextView) convertView.findViewById(R.id.tv_usercomment_count);
            mHolder.tv_all1 = (TextView) convertView.findViewById(R.id.tv_all1);
            mHolder.ll_hot = (LinearLayout) convertView.findViewById(R.id.ll_hot);
            mHolder.ll_tags = (LinearLayout) convertView.findViewById(R.id.ll_tags);
            mHolder.ll_right = (LinearLayout) convertView.findViewById(R.id.ll_right);
            mHolder.ll_comment = (LinearLayout) convertView.findViewById(R.id.ll_comment);
            mHolder.ll_product = (LinearLayout) convertView.findViewById(R.id.ll_product);
            mHolder.community_more_iv = (ImageView) convertView.findViewById(R.id.community_more_iv);
            mHolder.community_comment_iv = (ImageView) convertView.findViewById(R.id.community_comment_iv);
            mHolder.community_like_cb = (CheckBox) convertView.findViewById(R.id.community_like_cb);
            mHolder.ll_community_user = (LinearLayout) convertView.findViewById(R.id.ll_community_user);
            mHolder.ll_comment_all = (LinearLayout) convertView.findViewById(R.id.ll_comment_all);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(allList.get(position).getUser().getHeadface()).into(mHolder.iv_head);
        mHolder.tv_username.setText(allList.get(position).getUser().getNick());
        mHolder.tv_userskin.setText(allList.get(position).getUser().getSkinText());
        Picasso.with(context).load(allList.get(position).getImage().get(0)).into(mHolder.iv_content);
        mHolder.tv_content.setText(allList.get(position).getContent().replaceAll(" ", ""));
        mHolder.tv_like_count.setText(allList.get(position).getLikedCount() + "");
        mHolder.tv_comment_count.setText(allList.get(position).getCommentCount() + "");
        mHolder.tv_usercomment_count.setText(allList.get(position).getCommentCount() + "");
        final List<CommunityListViewBean.DataBean.ListBean.ProductBean> products = allList.get(position).getTags_product();
        mHolder.ll_product.removeAllViews();
        if (products.size() > 0) {
            for (int i = 0; i < products.size(); i++) {
                View view = inflater.inflate(R.layout.community_product, null);
                TextView product_tv = (TextView) view.findViewById(R.id.product_tv);
                ImageView product_iv = (ImageView) view.findViewById(R.id.product_iv);
                Picasso.with(context).load(products.get(i).getImage()).into(product_iv);
                product_tv.setText(products.get(i).getName());
                mHolder.ll_product.addView(view);
                mHolder.ll_product.setBackgroundColor(0xfff0f0f0);
                final int finalI = i;
                mHolder.ll_product.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        intent.putExtra("id", products.get(finalI).getObjectId());
                        context.startActivity(intent);
                    }
                });
            }
        }
        final List<CommunityListViewBean.DataBean.ListBean.TagsBean> tags = allList.get(position).getTags();
        mHolder.ll_tags.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            View view = inflater.inflate(R.layout.community_content_tags, null);
            Button btn_tags = (Button) view.findViewById(R.id.btn_tags);
            btn_tags.setText(tags.get(i).getName());
            final int finalI = i;
            btn_tags.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BeautifulActivity.class);
                    intent.putExtra("id", tags.get(finalI).getTag_id());
                    intent.putExtra("tagname", tags.get(finalI).getName());
                    context.startActivity(intent);
                }
            });
            mHolder.ll_tags.addView(view);
        }
        List<CommunityListViewBean.DataBean.ListBean.CommentsBean> comments = allList.get(position).getComments();
        mHolder.ll_comment.removeAllViews();
        for (int i = 0; i < comments.size(); i++) {
            View view = inflater.inflate(R.layout.community_content_comment, null);
            TextView tv_comment_user = (TextView) view.findViewById(R.id.tv_comment_user);
            TextView tv_jiange = (TextView) view.findViewById(R.id.tv_jiange);
            TextView tv_user_content = (TextView) view.findViewById(R.id.tv_user_content);
            tv_comment_user.setText(comments.get(i).getNick());
            tv_user_content.setText(comments.get(i).getContent());
            mHolder.ll_comment.addView(view);
        }
        /**
         * 各种监听事件
         */
        mHolder.ll_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HotSelectActivity.class);
                context.startActivity(intent);
            }
        });
        mHolder.community_like_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "+1美肤家基金", Toast.LENGTH_SHORT).show();
            }
        });
        mHolder.community_comment_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("id", allList.get(position).getId());
                context.startActivity(intent);
            }
        });
        mHolder.iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                intent.putExtra("uid", allList.get(position).getUser().getUid());
                intent.putExtra("userskin", allList.get(position).getUser().getSkinText());
                context.startActivity(intent);
            }
        });
        mHolder.ll_community_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                intent.putExtra("uid", allList.get(position).getUser().getUid());
                intent.putExtra("userskin", allList.get(position).getUser().getSkinText());
                context.startActivity(intent);
            }
        });
        mHolder.community_more_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View shareView = LayoutInflater.from(context).inflate(R.layout.community_share, null);
                //设置popupWindow焦点
                shareView.setFocusable(true);
                shareView.setFocusableInTouchMode(true);
                //创建popupWindow
                popupWindow = new PopupWindow(shareView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                //点击popupWindow以外隐藏
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
                //popupWindow动画
                popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
                //popupWindow以外的透明度
                WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
                params.alpha =0.5f;
                ((Activity) context).getWindow().setAttributes(params);
                //显示popupWindow
                popupWindow.showAtLocation( ((Activity) context).findViewById(R.id.activity_main), Gravity.BOTTOM, 0, 0);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        closePopupWindow();
                    }
                });
                Button share_btn_cancel= (Button) shareView.findViewById(R.id.share_btn_cancel);
                share_btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closePopupWindow();
                    }
                });
                Button share_btn_report= (Button) shareView.findViewById(R.id.share_btn_report);
                share_btn_report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"举报成功",Toast.LENGTH_SHORT).show();
                        closePopupWindow();
                    }
                });

            }
        });
        mHolder.ll_comment_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("id", allList.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView iv_head;
        TextView tv_username;
        TextView tv_userskin;
        LinearLayout ll_hot;
        ImageView iv_content;
        TextView tv_content;
        LinearLayout ll_tags;
        ImageView iv_like;
        TextView tv_like_count;
        ImageView iv_comment;
        TextView tv_comment_count;
        LinearLayout ll_right;
        TextView tv_all;
        TextView tv_usercomment_count;
        TextView tv_all1;
        LinearLayout ll_comment;
        LinearLayout ll_product;
        ImageView community_more_iv;
        ImageView community_comment_iv;
        CheckBox community_like_cb;
        LinearLayout ll_community_user;
        LinearLayout ll_comment_all;
    }
    private void closePopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
            params.alpha = 1f;
            ((Activity) context).getWindow().setAttributes(params);
        }
    }

}
