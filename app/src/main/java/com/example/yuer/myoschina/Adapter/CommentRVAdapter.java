package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.DongdanDetailActivity;
import com.example.yuer.myoschina.bean.NewsCommentResponse;
import com.example.yuer.myoschina.bean.ZuixinResponse;
import com.example.yuer.myoschina.widget.ImageLoad;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Yuer on 2017/5/8.
 */

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.ViewHolder>{
    //1. 数据源 2.item布局
    Context context;
    List<NewsCommentResponse.CommentListBean> commentList;
    public CommentRVAdapter(Context context,List<NewsCommentResponse.CommentListBean> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CommentRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_comment,parent,false);
        CommentRVAdapter.ViewHolder vh = new CommentRVAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CommentRVAdapter.ViewHolder holder, int position) {
        //绑定数据

        NewsCommentResponse.CommentListBean comments = commentList.get(position);
        String pubTime = comments.getPubDate(); //转换成 xx小时前
        SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Date dateNow=new Date();
        try {
            date=a.parse(pubTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=Calendar.getInstance();
        Calendar calendarNow=Calendar.getInstance();
        calendar.setTime(date);
        calendarNow.setTime(dateNow);
        int years=calendarNow.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
        int months=calendarNow.get(Calendar.MONTH)-calendar.get(Calendar.MONTH);
        int days=calendarNow.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_MONTH);
        int hours=calendarNow.get(Calendar.HOUR_OF_DAY)-calendar.get(Calendar.HOUR_OF_DAY);
        int minutes=calendarNow.get(Calendar.MINUTE)-calendar.get(Calendar.MINUTE);
        int seconds=calendarNow.get(Calendar.SECOND)-calendar.get(Calendar.SECOND);
        if (years>0)
        {
            holder.time.setText(years+"年前");
        }
        else
        {
            if (months>0)
            {
                holder.time.setText(months+"月前");
            }
            else
            {
                if (days>0)
                {
                    holder.time.setText(days+"日前");
                }
                else
                {
                    if (hours>0)
                    {
                        holder.time.setText(hours+"小时前");
                    }
                    else
                    {
                        if (minutes>0)
                        {
                            holder.time.setText(minutes+"分钟前");
                        }
                        else
                        {
                            holder.time.setText("刚刚");
                        }
                    }
                }
            }
        }

//        holder.tag.setImageURI(comments.getCommentPortrait());


        if (comments.getCommentPortrait().equals("https://www.oschina.net/img/portrait.gif"))
        {
            ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
            int color1=colorGenerator.getRandomColor();
            Drawable textDrawable= TextDrawable.builder()
                    .beginConfig()
                    .width(120)
                    .height(120)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(comments.getCommentAuthor().substring(0,1),color1);
            holder.tag.setImageDrawable(textDrawable);

        }else
        {
//                            imageHead.setImageURI(dongDanDetailResponse.getPortrait());
            Picasso.with(context).load(comments.getCommentPortrait())
                    .placeholder(R.color.colorPrimary)
                    .into(holder.tag);
        }














        holder.author.setText(comments.getCommentAuthor());
        String content=comments.getContent();
        holder.body.setText(Html.fromHtml(content));
         //赞还没写


//        holder.time.setText(pubTime);
//        if (pubTime.equals("昨天")){  //判断 是今天以前的  让隐藏
//            holder.tag.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        SimpleDraweeView tag;
        CircleImageView tag;
        TextView body;
        TextView author;
        TextView time;
        TextView thumb;  //赞
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            tag = (CircleImageView) itemView.findViewById(R.id.image_item_picture_yuan);
//            content = itemView.findViewById(R.id.tv)
            body=(TextView) itemView.findViewById(R.id.tv_comment_body);
            author=(TextView) itemView.findViewById(R.id.tv_item_comment_author);
            time = (TextView) itemView.findViewById(R.id.tv_item_comment_time);
            thumb = (TextView) itemView.findViewById(R.id.thumb_count);
            item = (LinearLayout) itemView;
        }
    }
}
