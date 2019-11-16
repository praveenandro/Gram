package com.admin.flubbes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<ListItem> listItems;
    Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final ListItem listItem = listItems.get(i);
        viewHolder.textHead.setText(listItem.getHead());
        viewHolder.textDesc.setText(listItem.getDesc());

        Picasso.with(context)
        .load(listItem.getImage())
        .into(viewHolder.imageView);

        viewHolder.bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);

                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        listItem.getHead()+"\nTo view latest updates Download: //play.google.com/store/apps/details?id=com.admin.flubbes");
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textHead,textDesc;
        public ImageView imageView;
        public Button bShare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textHead=(TextView)itemView.findViewById(R.id.idTitle);
            textDesc=(TextView)itemView.findViewById(R.id.idDescription);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            bShare=(Button)itemView.findViewById(R.id.idShare);
        }
    }
}
