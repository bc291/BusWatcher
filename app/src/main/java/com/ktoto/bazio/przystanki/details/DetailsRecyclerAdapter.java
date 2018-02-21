package com.ktoto.bazio.przystanki.details;

        import android.graphics.Color;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.ktoto.bazio.przystanki.DisplayList;
        import com.ktoto.bazio.przystanki.R;
        import com.ktoto.bazio.przystanki.dane.getFromDelays;

        import java.util.ArrayList;


public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.RecyclerViewHolder> {
    ArrayList<getFromDelays>  arrayList = new ArrayList<>();
    public boolean isSynchronizing=false;

    DetailsRecyclerAdapter(ArrayList<getFromDelays> arrayList, boolean syncing)
    {
        isSynchronizing=syncing;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_details, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        getFromDelays getFromDelays = arrayList.get(position);
        holder.headSignText.setText(getFromDelays.getHeadsign());
        holder.estimatedTimeText.setText(getFromDelays.getEstimatedTime());
        holder.routeIdText.setText(Integer.toString(getFromDelays.getRouteId()));





        if(isSynchronizing)  {
            holder.itemView.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(!isSynchronizing) {





            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#e1e1e1"));
            }

            // holder.imageView.setImageResource(R.drawable.ic_play);
        }



        //else if(!isSynchronizing) holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    //nizej ma byc static
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public Button aButton;

        TextView headSignText, estimatedTimeText, routeIdText;
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            headSignText = itemView.findViewById(R.id.headSignText);
            estimatedTimeText = itemView.findViewById(R.id.estimatedTimeText);
            routeIdText = itemView.findViewById(R.id.routeIdText);
            imageView = itemView.findViewById(R.id.imgBus);

        }


    }


}
