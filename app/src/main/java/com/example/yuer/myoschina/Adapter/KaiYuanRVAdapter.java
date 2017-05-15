package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.KaiYuanResponse;
import com.example.yuer.myoschina.bean.PostResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Yuer on 2017/5/2.
 */

public class KaiYuanRVAdapter extends RecyclerView.Adapter<KaiYuanRVAdapter.ViewHolder>  {

    Context context;
    List<KaiYuanResponse.SoftwareTypesBean> kaiyuanList;
    public KaiYuanRVAdapter(Context context,  List<KaiYuanResponse.SoftwareTypesBean> kaiyuanList) {
        this.context = context;
        this.kaiyuanList = kaiyuanList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_kaiyuan,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(KaiYuanRVAdapter.ViewHolder holder, int position) {

        //绑定数据

        KaiYuanResponse.SoftwareTypesBean kaiyuans = kaiyuanList.get(position);

        holder.name.setText(kaiyuans.getName());


    }

    @Override
    public int getItemCount() {
        return kaiyuanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.soft_name);
            item = (LinearLayout) itemView;
        }
    }
}
