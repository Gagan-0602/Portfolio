package com.gagankaushal.portfolio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.join;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserM> userArrayList;
    ArrayList<String> strinselectedIds = new ArrayList<>();
    ImageView deleteBtn;

    public UserAdapter(Context context, ArrayList<UserM> userArrayList,ImageView deleteBtn) {
        this.context = context;
        this.userArrayList = userArrayList;
        this.deleteBtn=deleteBtn;
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final UserAdapter.MyViewHolder holder, final int position) {
        holder.username.setText(userArrayList.get(position).getName());
        holder.useremail.setText(userArrayList.get(position).getEmail());
        holder.userlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserUpdate.class);
                intent.putExtra("data", userArrayList.get(position));
                ((UserManagement) context).startActivityForResult(intent, 123);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                {
                    builder.setTitle("DELETE");
                    builder.setMessage("ARE YOU SURE FOR DELETE");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DBHelperUser dbHelperUser = new DBHelperUser(context);
                            dbHelperUser.delete_user(userArrayList.get(position).getId());
                            ((UserManagement) context).refresh();
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


        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (strinselectedIds.contains(userArrayList.get(position).getId())) {
                        holder.box.setChecked(false);
                        strinselectedIds.remove(userArrayList.get(position).getId());


                    } else {
                        strinselectedIds.add(userArrayList.get(position).getId());
                        holder.box.setChecked(true);
                        String models = TextUtils.join(",",strinselectedIds);
                        Log.e("selctedIds>>", "==" + models);

                    }
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, useremail;
        ImageView imageView;
        CheckBox box;
        LinearLayout userlinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.userdelete);
            username = (TextView) itemView.findViewById(R.id.usertextrow);
            useremail = (TextView) itemView.findViewById(R.id.usertextrow1);
            userlinearLayout = (LinearLayout) itemView.findViewById(R.id.usertextbox);
            box = (CheckBox) itemView.findViewById(R.id.usercheckbox);


            deleteBtn.setOnClickListener(new View.OnClickListener() {
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

                                String models = TextUtils.join(",",strinselectedIds);
                                dbHelperUser.delete_user(models);
                                ((UserManagement)context).refresh();



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
