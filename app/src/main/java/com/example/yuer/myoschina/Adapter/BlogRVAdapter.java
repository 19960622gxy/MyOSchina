package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.BlogResponse;
import com.example.yuer.myoschina.bean.NewsResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuer on 2017/5/2.
 */

public class BlogRVAdapter extends RecyclerView.Adapter<BlogRVAdapter.ViewHolder> {
    //1. 数据源 2.item布局
    Context context;
    List<BlogResponse.BloglistBean> blogList;
    public BlogRVAdapter(Context context, List<BlogResponse.BloglistBean> blogList) {
        this.context = context;
        this.blogList=blogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_blog,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BlogRVAdapter.ViewHolder holder, int position) {


        //绑定数据

        BlogResponse.BloglistBean blogs = blogList.get(position);
        String pubTime = blogs.getPubDate(); //转换成 xx小时前
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
            holder.tagjin.setVisibility(View.GONE);
        }
        else
        {
            if (months>0)
            {
                holder.time.setText(months+"月前");
                holder.tagjin.setVisibility(View.GONE);
            }
            else
            {
                if (days>0)
                {
                    holder.time.setText(days+"日前");
                    holder.tagjin.setVisibility(View.GONE);
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


        holder.author.setText("@"+blogs.getAuthor());
        holder.title.setText(blogs.getTitle());
        holder.comment.setText(blogs.getCommentCount()+"");
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tagjin;
        ImageView tagyuan;
        ImageView tagjian;
        TextView title;
        TextView content;
        TextView author;
        TextView time;
        TextView comment;
        LinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            tagjin = (ImageView) itemView.findViewById(R.id.image_item_tagjin);
            tagyuan = (ImageView) itemView.findViewById(R.id.image_item_tagyuan);
            tagjian = (ImageView) itemView.findViewById(R.id.image_item_tagjian);

            title = (TextView) itemView.findViewById(R.id.tv_item_blog_title);
//            content = itemView.findViewById(R.id.tv)
            author=(TextView) itemView.findViewById(R.id.tv_item_blog_author);
            time = (TextView) itemView.findViewById(R.id.tv_item_blog_time);
            comment = (TextView) itemView.findViewById(R.id.tv_blog_comment_count);
            item = (LinearLayout) itemView;
        }
    }
}
