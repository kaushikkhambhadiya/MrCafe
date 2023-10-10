package com.conestoga.mrcafe;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<DataModel> items;
    private ProductActivity mActivity;

    public ProductAdapter(ArrayList<DataModel> data, ProductActivity activity) {
        this.items = data;
        this.mActivity = activity;
        this.mInflater = LayoutInflater.from(mActivity);
    }

    public void addItem(DataModel result) {
        items.add(result);
    }

    public void setInflater(LayoutInflater layoutInflater){
        this.mInflater =layoutInflater;
    }

    public void replaceItems(ArrayList<DataModel> newItems) {
        this.items.clear();
        for(DataModel item: newItems)
            this.items.add(item);
    }

    public void insertItem(DataModel item) {
        items.add(0, item);
    }

    public void clearItems(){
        items.clear();
    }

    public void AddResults(ArrayList<DataModel> result) {
        items.addAll(result);
    }

    public DataModel getItemsAt(int position){
        return  items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataModel model = items.get(position);
        MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
        Picasso.with(mActivity)
                .load(model.getImage())
                .placeholder(R.drawable.slide1)
                .into(messageViewHolder.imageViewIcon);
        messageViewHolder.textViewName.setText(model.getName());
        messageViewHolder.textViewPrice.setText("$" + model.getPrice());
        messageViewHolder.textViewDesc.setText(model.getDescription());
    }

    @Override
    public int getItemViewType(int position) {
        return  super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootCategoryView = mInflater.inflate(R.layout.row_item, parent, false);
        return new MessageViewHolder(rootCategoryView, this);
    }

    private class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageViewIcon;
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewDesc;
        private CardView cardView;

        private MessageViewHolder(View itemView, ProductAdapter adapter) {
            super(itemView);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final int pos = getAdapterPosition();
            if (pos >= 0) {
//                Toast.makeText(mActivity, items.get(pos).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity,DisplayActivity.class);
                intent.putExtra("data",items.get(pos));
                mActivity.startActivity(intent);
            }
        }
    }
}
