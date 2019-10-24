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

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    Context context;
    ArrayList<ContactM> contactMS;
    ArrayList<String> selectlist = new ArrayList();
    ImageView deletebtn;

    public ContactAdapter(Context context, ArrayList<ContactM> contactMS, ImageView deletebtn) {
        this.context = context;
        this.contactMS = contactMS;
        this.deletebtn = deletebtn;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.sub.setText(contactMS.get(position).getSubject());
        holder.msg.setText(contactMS.get(position).getMessage());
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Contact_Us.class);
                intent.putExtra("contactdata", contactMS.get(position));
                ((Contact_management) context).startActivityForResult(intent, 123);

            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("DELETE");
        builder.setMessage("ARE YOU WANT TO DELETE");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelperProject helperProject = new DBHelperProject(context);
                helperProject.contact_delete(contactMS.get(position).getId());
                ((Contact_management) context).refreshContact();
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

        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (selectlist.contains(contactMS.get(position).getId())) {
                        holder.box.setChecked(false);
                        selectlist.remove(contactMS.get(position).getId());
                    } else {
                        selectlist.add(contactMS.get(position).getId());
                        holder.box.setChecked(true);
                        String models = TextUtils.join(",", selectlist);
                        Log.e("selctedIds>>", "==" + models);

                    }
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactMS.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sub, msg;
        LinearLayout contact;
        CheckBox box;
        ImageView del;

        public MyViewHolder(View itemView) {
            super(itemView);
            contact = (LinearLayout) itemView.findViewById(R.id.contactlayout);
            sub = (TextView) itemView.findViewById(R.id.subjectrow);
            msg = (TextView) itemView.findViewById(R.id.messagerow);
            del = (ImageView) itemView.findViewById(R.id.contactdelete);
            box = (CheckBox) itemView.findViewById(R.id.contactcheckbox);
            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    {
                        builder.setTitle("DELETE");
                        builder.setMessage("ARE YOU SURE FOR DELETE SELECTED ITEMS");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelperProject dbHelperProject = new DBHelperProject(context);

                                String models = TextUtils.join(",", selectlist);
                                dbHelperProject.contact_delete(models);
                                ((Contact_management) context).refreshContact();


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
