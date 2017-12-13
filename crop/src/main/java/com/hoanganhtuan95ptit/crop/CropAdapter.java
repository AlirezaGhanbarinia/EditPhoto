package com.hoanganhtuan95ptit.crop;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Hoang Anh Tuan on 11/30/2017.
 */

class CropAdapter extends BaseAdapter<CropModel> {

    private int positionSelect = 0;

    private OnItemCropClickedListener onItemCropClickedListener;

    CropAdapter(Activity activity) {
        super(activity);
    }

    void setOnItemCropClickedListener(OnItemCropClickedListener onItemCropClickedListener) {
        this.onItemCropClickedListener = onItemCropClickedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_crop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final CropModel cropModel = list.get(position);

        Picasso.with(activity)
                .load(cropModel.getImg())
                .into(viewHolder.iv);
        viewHolder.tv.setText(cropModel.getName());

        if (position == positionSelect)
            viewHolder.item.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorSelect));
        else
            viewHolder.item.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary));

        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == positionSelect) return;

                int oldP = positionSelect;
                positionSelect = position;

                notifyItemChanged(oldP);
                notifyItemChanged(position);

                onItemCropClickedListener.onItemCropClicked(cropModel.getType());
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.iv);
            tv = view.findViewById(R.id.tv);
            item = view.findViewById(R.id.item);
        }
    }

    public interface OnItemCropClickedListener {
        void onItemCropClicked(CropModel.Type type);
    }
}
