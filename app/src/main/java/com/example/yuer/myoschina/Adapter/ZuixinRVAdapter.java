package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.BigImageActivity;
import com.example.yuer.myoschina.activity.DongdanDetailActivity;
import com.example.yuer.myoschina.activity.NewsDetailActivity;
import com.example.yuer.myoschina.bean.NewsResponse;
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

public class ZuixinRVAdapter extends RecyclerView.Adapter<ZuixinRVAdapter.ViewHolder> {
    //1. 数据源 2.item布局
    Context context;
    List<ZuixinResponse.TweetlistBean> zuixinList;
    public ZuixinRVAdapter(Context context, List<ZuixinResponse.TweetlistBean> zuixinList) {
        this.context = context;
        this.zuixinList = zuixinList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_zuixin,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据

        final ZuixinResponse.TweetlistBean zuixins = zuixinList.get(position);
        String pubTime = zuixins.getPubDate(); //转换成 xx小时前
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



        if (zuixins.getPortrait().equals("https://www.oschina.net/img/portrait.gif"))
        {
            ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
            int color1=colorGenerator.getRandomColor();
            Drawable textDrawable= TextDrawable.builder()
                    .beginConfig()
                    .width(120)
                    .height(120)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(zuixins.getAuthor().substring(0,1),color1);
            holder.tag.setImageDrawable(textDrawable);

        }else
        {
            holder.tag.setImageURI(zuixins.getPortrait());
        }



        holder.author.setText(zuixins.getAuthor());
        final String content=zuixins.getBody();
        holder.body.setText(Html.fromHtml(content));
        holder.comment.setText(zuixins.getCommentCount()+"");

        String image=zuixins.getImgSmall();
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
                intent.putExtra("imageBig",zuixins.getImgBig());
                context.startActivity(intent);
            }
        });




        //点击监听  跳转到对应详情页
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //检测
//                    Toast.makeText(context,"点击了"+news.getId(),Toast.LENGTH_SHORT).show();

                //跳转
                Intent intent=new Intent(context,DongdanDetailActivity.class);
                //携带id参数
                intent.putExtra("dongDanId",zuixins.getId());  //传递给下一个activity
                intent.putExtra("commentCount",zuixins.getCommentCount());
                intent.putExtra("imagesmall",zuixins.getImgSmall());
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
        return zuixinList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView tag;
        TextView content;
        TextView body;
        TextView author;
        TextView time;
        TextView comment;
        ImageLoad imageLoad;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            tag = (SimpleDraweeView) itemView.findViewById(R.id.image_item_picture_yuan);
//            content = itemView.findViewById(R.id.tv)
            body=(TextView) itemView.findViewById(R.id.tv_zuixin_body);
            author=(TextView) itemView.findViewById(R.id.tv_item_zuixin_author);
            time = (TextView) itemView.findViewById(R.id.tv_item_zuixin_time);
            comment = (TextView) itemView.findViewById(R.id.tv_zuixin_comment_count);
            item = (LinearLayout) itemView;
            imageLoad=(ImageLoad) itemView.findViewById(R.id.imageLoad);
        }
    }
}