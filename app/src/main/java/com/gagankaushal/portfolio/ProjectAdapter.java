package com.gagankaushal.portfolio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {
    Context context;
    ArrayList<PortfolioM> list;
    ArrayList<String> arrayList = new ArrayList();
    ImageView deletePbtn;

    public ProjectAdapter(Context context, ArrayList<PortfolioM> list, ImageView deletePbtn) {
        this.context = context;
        this.list = list;
        this.deletePbtn = deletePbtn;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTittle());
        holder.desc.setText(list.get(position).getDesc());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update_project_layout);
                WindowManager.LayoutParams layoutParam = new WindowManager.LayoutParams();
                layoutParam.copyFrom(dialog.getWindow().getAttributes());
                layoutParam.height = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParam.width = WindowManager.LayoutParams.MATCH_PARENT;
                final ImageView projectupdateback = (ImageView) dialog.findViewById(R.id.updateprojectback);
                final EditText title = (EditText) dialog.findViewById(R.id.EuseraddT);
                final EditText desc = (EditText) dialog.findViewById(R.id.EuseraddD);
                final Button save = (Button) dialog.findViewById(R.id.Esave);
                title.setText(list.get(position).getTittle());
                desc.setText(list.get(position).getDesc());
                projectupdateback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ProjectManagement.class);
                        context.startActivity(intent);

                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHelperProject dbHelper = new DBHelperProject(context);
                        dbHelper.update(list.get(position).getid(), title.getText().toString(), desc.getText().toString());
                        ((ProjectManagement) context).refreshProjectData();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setAttributes(layoutParam);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("DELETE");
                builder.setMessage("ARE YOU SURE TO DELETE");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelperProject dbHelper = new DBHelperProject(context);
                        dbHelper.delete(list.get(position).getid());
                        ((ProjectManagement) context).refreshProjectData();

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

        holder.pbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (arrayList.contains(list.get(position).getid())) {
                        holder.pbox.setChecked(false);
                        arrayList.remove(list.get(position).getid());
                    } else {
                        arrayList.add(list.get(position).getid());
                        holder.pbox.setChecked(true);
                        String models = TextUtils.join(",", arrayList);
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
        TextView desc, title;
        ImageView img, delete;
        LinearLayout layout;
        CheckBox pbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.userupdate);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            img = (ImageView) itemView.findViewById(R.id.userImg);
            title = (TextView) itemView.findViewById(R.id.userMtext);
            desc = (TextView) itemView.findViewById(R.id.userMtext1);
            pbox = (CheckBox) itemView.findViewById(R.id.projectcheckbox);

            deletePbtn.setOnClickListener(new View.OnClickListener() {
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

                                String models = TextUtils.join(",", arrayList);
                                dbHelperProject.delete(models);
                                ((ProjectManagement) context).refreshProjectData();


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
