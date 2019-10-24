package com.gagankaushal.portfolio;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectFragment extends android.support.v4.app.Fragment {

   RecyclerView rv;
    ArrayList<PortfolioM> list1;
    ListAdapter adapter;
    Boolean islist=false;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.projects,container,false);
        LinearLayoutManager manager=new GridLayoutManager(getActivity(),2);
        context=getActivity();



        ((Home)context).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(islist)
                {
                    ((Home)context).imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.grid));
                    islist=false;
                    LinearLayoutManager manager=new GridLayoutManager(getActivity(),2);
                    rv.setLayoutManager(manager);
                    adapter=new ListAdapter(getActivity(),list1,0);
                    rv.setAdapter(adapter);
                }else
                {
                    ((Home)context).imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.list));
                    islist=true;
                    LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                    rv.setLayoutManager(manager);
                    adapter=new ListAdapter(getActivity(),list1,1);
                    rv.setAdapter(adapter);
                }
            }
        });

      list1=new ArrayList();
     rv=(RecyclerView)view.findViewById(R.id.recycleview);
     adapter=new ListAdapter(getActivity(),list1,0);
     rv.setAdapter(adapter);
     rv.setLayoutManager(manager);

     adapter.notifyDataSetChanged();
     for(int i=0;i<=10;i++)
     {
         PortfolioM portfolioM= new PortfolioM("","description","des","But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself");
        list1.add(portfolioM);
     }




        return view;
    }


}
