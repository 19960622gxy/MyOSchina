package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.yuer.myoschina.activity.BigImageActivity;
import com.example.yuer.myoschina.bean.RemenResponse;
import com.example.yuer.myoschina.bean.ZuixinResponse;
import com.example.yuer.myoschina.widget.ImageLoad;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class RemenRVAdapter extends RecyclerView.Adapter<RemenRVAdapter.ViewHolder> {
    //1. 数据源 2.item布局
    Context context;
    List<RemenResponse.TweetlistBean>  remenList;
    public RemenRVAdapter(Context context,List<RemenResponse.TweetlistBean>  remenList) {
        this.context = context;
        this.remenList = remenList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_remen,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据

        final RemenResponse.TweetlistBean remens = remenList.get(position);
        String pubTime = remens.getPubDate(); //转换成 xx小时前
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



        if (remens.getPortrait().equals("https://www.oschina.net/img/portrait.gif"))
        {
            ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
            int color1=colorGenerator.getRandomColor();
            Drawable textDrawable= TextDrawable.builder()
                    .beginConfig()
                    .width(120)
                    .height(120)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(remens.getAuthor().substring(0,1),color1);
            holder.tag.setImageDrawable(textDrawable);

        }else
        {
            holder.tag.setImageURI(remens.getPortrait());
        }








        holder.author.setText(remens.getAuthor());
        String content=remens.getBody();
        holder.body.setText(Html.fromHtml(content));
        holder.comment.setText(remens.getCommentCount()+"");

        String image=remens.getImgSmall();
        //清空子view
        for (int i = 0; i <holder.imageLoad.getChildCount() ; i++) {
            holder.imageLoad.removeView(holder.imageLoad.getChildAt(i));
        }
               //加载图片
        if (image== null) {
            holder.imageLoad.setVisibility(View.GONE);


        } else {
            holder.imageLoad.setVisibility(View.VISIBLE);
            holder.imageLoad.setImages(image);
        }

        //点击小图展示大图
        holder.imageLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"你点击了小图",Toast.LENGTH_SHORT).show();
                //跳转
                Intent intent=new Intent(context,BigImageActivity.class);
                //携带id参数
                intent.putExtra("imageBig",remens.getImgBig());
                context.startActivity(intent);
            }
        });


//        holder.time.setText(pubTime);
//        if (pubTime.equals("昨天")){  //判断 是今天以前的  让隐藏
//            holder.tag.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return remenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView tag;
        TextView content;
        TextView body;
        TextView author;
        TextView time;
        TextView comment;
        LinearLayout item;
        ImageLoad imageLoad;

        public ViewHolder(View itemView) {
            super(itemView);
            tag = (SimpleDraweeView) itemView.findViewById(R.id.image_item_picture_yuan);
//            content = itemView.findViewById(R.id.tv)
            body=(TextView) itemView.findViewById(R.id.tv_remen_body);
            author=(TextView) itemView.findViewById(R.id.tv_item_remen_author);
            time = (TextView) itemView.findViewById(R.id.tv_item_remen_time);
            comment = (TextView) itemView.findViewById(R.id.tv_remen_comment_count);
            item = (LinearLayout) itemView;
            imageLoad=(ImageLoad) itemView.findViewById(R.id.imageLoadRemen);
        }
    }
}