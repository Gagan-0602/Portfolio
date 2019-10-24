package com.gagankaushal.portfolio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.MyViewHolder> {
    Context context;
    ArrayList<RateM> list;
    ArrayList<String> arrayList=new ArrayList();
    ImageView deleterate;

    public RateAdapter(Context context, ArrayList<RateM> list,ImageView deleterate) {
        this.context = context;
        this.list = list;
        this.deleterate=deleterate;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_us_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            holder.bar.setRating(Float.parseFloat(list.get(position).getRate()));

        } catch (Exception e) {
        }
        holder.ratemsg.setText(list.get(position).getMsg());

        holder.delbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("DELETE");
                builder.setMessage("ARE YOU WANT TO DELETE");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       DBHelperUser dbHelperUser=new DBHelperUser(context);
                       dbHelperUser.delete_feedback(list.get(position).getId());
                        ((Rate_Management)context).refreshrate();
                    }

                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        holder.Rbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (arrayList.contains(list.get(position).getId())) {
                        holder.Rbox.setChecked(false);
                     arrayList.remove(list.get(position).getId());


                    } else {
                       arrayList.add(list.get(position).getId());
                        holder.Rbox.setChecked(true);
                        String models = TextUtils.join(",",arrayList);
                        Log.e("selctedIds>>", "==" + models);

                    }
                } catch (Exception e) {

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RatingBar bar;
        TextView ratemsg;
        ImageView delbar;
        CheckBox Rbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            bar = (RatingBar) itemView.findViewById(R.id.RratingBar);
            ratemsg = (TextView) itemView.findViewById(R.id.ratemsgrow);
            delbar=(ImageView)itemView.findViewById(R.id.ratedelete);
            Rbox=(CheckBox)itemView.findViewById(R.id.rateuscheckbox);


            deleterate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    {
                        builder.setTitle("DELETE");
                        builder.setMessage("ARE YOU SURE FOR DELETE SELECTED ITEMS");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelperUser dbHelperUser = new DBHelperUser(context);

                                String models = TextUtils.join(",",arrayList);
                                dbHelperUser.delete_feedback(models);
                                ((Rate_Management)context).refreshrate();



                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    }
                }

            });
        }
    }
}
