package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.NewsResponse;
import com.example.yuer.myoschina.bean.PostResponse;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Yuer on 2017/5/2.
 */

public class PostRVAdapter extends RecyclerView.Adapter<PostRVAdapter.ViewHolder>  {

    Context context;
    List<PostResponse.PostListBean> postlist;
    public PostRVAdapter(Context context,List<PostResponse.PostListBean> postlist) {
        this.context = context;
        this.postlist = postlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(PostRVAdapter.ViewHolder holder, int position) {

        //绑定数据

        PostResponse.PostListBean posts = postlist.get(position);

        String pubTime = posts.getPubDate(); //转换成 xx小时前
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


//        int port= Integer.parseInt(posts.getPortrait());
//        String url = posts.getPortrait();

        if (posts.getPortrait().equals("https://www.oschina.net/img/portrait.gif"))
        {
            ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
            int color1=colorGenerator.getRandomColor();
            Drawable textDrawable= TextDrawable.builder()
                    .beginConfig()
                    .width(120)
                    .height(120)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(posts.getAuthor().substring(0,1),color1);
            holder.tag.setImageDrawable(textDrawable);

        }else
        {
            holder.tag.setImageURI(posts.getPortrait());

//            Picasso.with(context).load(i)
//                    .placeholder(R.color.colorPrimary)
//                    .into(holder.tag);
        }



        holder.author.setText("@"+posts.getAuthor());
        holder.title.setText(posts.getTitle());
//        holder.tag.setImageResource(posts.get);
        holder.comment.setText(posts.getAnswerCount()+"");
    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView tag;
//        CircleImageView tag;
        SimpleDraweeView tag;
        TextView title;
        TextView content;
        TextView author;
        TextView time;
        TextView comment;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
//            tag = (ImageView) itemView.findViewWithTag(R.id.image_item_picture_yuan);
//            tag = (CircleImageView) itemView.findViewWithTag(R.id.image_item_picture_yuan);
            tag = (SimpleDraweeView) itemView.findViewById(R.id.image_item_picture_yuan);
            title = (TextView) itemView.findViewById(R.id.tv_item_post_title);
//            content = itemView.findViewById(R.id.tv)
            author = (TextView) itemView.findViewById(R.id.tv_item_post_author);
            time = (TextView) itemView.findViewById(R.id.tv_item_post_time);
            comment = (TextView) itemView.findViewById(R.id.tv_post_comment_count);
            item = (LinearLayout) itemView;
        }
    }
}
