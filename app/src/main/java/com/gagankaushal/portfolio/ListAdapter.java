package com.gagankaushal.portfolio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    Context context;
    ArrayList<PortfolioM> list;
    int type=0;
    public ListAdapter(Context context,ArrayList<PortfolioM> list,int type)

    {
        this.context=context;
        this.list=list;
        this.type=type;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycletext,null,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
    holder.tittle1.setText(list.get(position).getTittle());
    holder.tittle2.setText(list.get(position).getTittle());
    holder.description1.setText(list.get(position).getDesc());
    holder.description2.setText(list.get(position).getDesc());
    if (type==0){
        holder.grid.setVisibility(View.VISIBLE);
        holder.list.setVisibility(View.GONE);
    }else {
        holder.grid.setVisibility(View.GONE);
        holder.list.setVisibility(View.VISIBLE);
    }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView1,imageView2;
        TextView tittle1,description1,tittle2,description2;
        LinearLayout list,grid;
        public MyViewHolder(View itemView) {
            super(itemView);
            tittle1=(TextView)itemView.findViewById(R.id.protext);
            tittle2=(TextView)itemView.findViewById(R.id.protext1);
            imageView1=(ImageView)itemView.findViewById(R.id.image1);
            imageView2=(ImageView)itemView.findViewById(R.id.image2);
            description1=(TextView)itemView.findViewById(R.id.prodescripition);
            description2=(TextView)itemView.findViewById(R.id.prodescripition1);
            list=(LinearLayout)itemView.findViewById(R.id.listV);
            grid=(LinearLayout)itemView.findViewById(R.id.gridV);
        }
    }
}
