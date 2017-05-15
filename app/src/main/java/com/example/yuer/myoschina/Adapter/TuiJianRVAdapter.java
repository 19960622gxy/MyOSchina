package com.example.yuer.myoschina.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.KaiYuanResponse;
import com.example.yuer.myoschina.bean.TuiJianResponse;

import java.util.List;


/**
 * Created by Yuer on 2017/5/2.
 */

public class TuiJianRVAdapter extends RecyclerView.Adapter<TuiJianRVAdapter.ViewHolder>  {

    Context context;
    List<TuiJianResponse.ProjectlistBean>  tuijianList;
    public TuiJianRVAdapter(Context context,  List<TuiJianResponse.ProjectlistBean>  tuijianList) {
        this.context = context;
        this.tuijianList = tuijianList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuijian,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(TuiJianRVAdapter.ViewHolder holder, int position) {

        //绑定数据

        TuiJianResponse.ProjectlistBean tuijians = tuijianList.get(position);

        holder.name.setText(tuijians.getName());
        holder.descrip.setText(tuijians.getDescription());


    }

    @Override
    public int getItemCount() {
        return tuijianList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView descrip;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tuijian_name);
            descrip = (TextView) itemView.findViewById(R.id.tuijian_descrip);
            item = (LinearLayout) itemView;
        }
    }
}
