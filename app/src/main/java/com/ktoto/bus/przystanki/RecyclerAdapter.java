package com.ktoto.bus.przystanki;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktoto.bus.przystanki.dane.getFromPrzystanki;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>  {
    ArrayList<getFromPrzystanki>  arrayList = new ArrayList<>();
    private ArrayList<getFromPrzystanki> mFilteredList;
    RecyclerAdapter(ArrayList<getFromPrzystanki> arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

            getFromPrzystanki getFromPrzystanki = arrayList.get(position);

            holder.name.setText(getFromPrzystanki.getStopDesc());
            holder.id1.setText(Integer.toString(getFromPrzystanki.getStopId()));
            //  holder.id2.setText(Integer.toString(getFromStops.getStopId2()));


            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#2196f3"));
                holder.imageView.setImageResource(R.drawable.ic_bus);
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#bbdefb"));
                holder.imageView.setImageResource(R.drawable.ic_tram2);
            }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void updateList(ArrayList<getFromPrzystanki> itemEntities) {
        arrayList = itemEntities;
        notifyDataSetChanged();
    }

   public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        TextView name, id1, id2;
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
           name = itemView.findViewById(R.id.textView1);
            id1 = itemView.findViewById(R.id.textView2);
          //  id2 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imgBus);

        }
    }
   }
