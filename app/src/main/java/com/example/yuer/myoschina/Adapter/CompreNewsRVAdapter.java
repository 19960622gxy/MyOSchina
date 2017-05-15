package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.NewsDetailActivity;
import com.example.yuer.myoschina.bean.NewsResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class CompreNewsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int TYPE_HEAD=0;
    private int TYPE_NOMAL=1;
    //1. 数据源 2.item布局
    Context context;
    List<NewsResponse.NewslistBean> newslist;
    public CompreNewsRVAdapter(Context context, List<NewsResponse.NewslistBean> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        if (viewType == 0){
            //加载 轮播图（头布局）
            HeadViewHolder headViewHolder=new HeadViewHolder(mHeadView);
            return headViewHolder;
        }
        else
        {
            //加载新闻item

            View view = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
            NewsViewHolder vh = new NewsViewHolder(view);
            return vh;
        }

    }
    private  View mHeadView;
    //设置头布局
    public void setHead(View view)
    {
        mHeadView=view;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEAD)
        {

        }
        else {
            //绑定数据

            final NewsResponse.NewslistBean news = newslist.get(position-1);
            NewsViewHolder newsViewHolder= (NewsViewHolder) holder;


            String pubTime = news.getPubDate(); //转换成 xx小时前
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
                newsViewHolder.time.setText(years+"年前");
                newsViewHolder.tag.setVisibility(View.GONE);
            }
            else
            {
                if (months>0)
                {
                    newsViewHolder.time.setText(months+"月前");
                    newsViewHolder.tag.setVisibility(View.GONE);
                }
                else
                {
                    if (days>0)
                    {
                        newsViewHolder.time.setText(days+"日前");
                        newsViewHolder.tag.setVisibility(View.GONE);
                    }
                    else
                    {
                        if (hours>0)
                        {
                            newsViewHolder.time.setText(hours+"小时前");
                        }
                        else
                        {
                            if (minutes>0)
                            {
                                newsViewHolder.time.setText(minutes+"分钟前");
                            }
                            else
                            {
                                newsViewHolder.time.setText("刚刚");
                            }
                        }
                    }
                }
            }


            newsViewHolder.author.setText("@"+news.getAuthor());
            newsViewHolder.title.setText(news.getTitle());
            newsViewHolder.comment.setText(news.getCommentCount()+"");


            //点击监听  跳转到对应详情页
            newsViewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //检测
//                    Toast.makeText(context,"点击了"+news.getId(),Toast.LENGTH_SHORT).show();

                    //跳转
                    Intent intent=new Intent(context, NewsDetailActivity.class);
                    //携带id参数
                    intent.putExtra("newsId",news.getId());  //传递给下一个activity
                    intent.putExtra("commentCount",news.getCommentCount());
                    context.startActivity(intent);
                }
            });



        }

//        holder.time.setText(pubTime);
//        if (pubTime.equals("昨天")){  //判断 是今天以前的  让隐藏
//            holder.tag.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        int size=newslist.size();
        if (mHeadView!=null)
        {
            size++;
        }
        return size;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView tag;
        TextView title;
//        TextView newscontent;
        TextView author;
        TextView time;
        TextView comment;
        LinearLayout item;
        public NewsViewHolder(View itemView) {
            super(itemView);
            tag = (ImageView) itemView.findViewById(R.id.image_item_tag);
            title = (TextView) itemView.findViewById(R.id.tv_item_title);
//            content = itemView.findViewById(R.id.tv)
//            newscontent=(TextView)itemView.findViewById(R.id.news_content);
            author=(TextView) itemView.findViewById(R.id.tv_item_author);
            time = (TextView) itemView.findViewById(R.id.tv_item_time);
            comment = (TextView) itemView.findViewById(R.id.tv_comment_count);
            item = (LinearLayout) itemView;//设置整个一项item
        }
    }

    //多加一个viewholder  放头布局 轮播图
    public class HeadViewHolder extends RecyclerView.ViewHolder
    {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    //拿到描述类型
    @Override
    public int getItemViewType(int position) {
        //获取item类型
        //0 head
        if (position==0&&mHeadView!=null)
        {
            return TYPE_HEAD;
        }else {
            return TYPE_NOMAL;
        }
    }
}