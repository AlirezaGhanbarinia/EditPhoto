package com.hoanganhtuan95ptit.fillter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hoang Anh Tuan on 12/3/2017.
 */

class FilterAdapter extends BaseAdapter<FilterModel> {

    private int positionSelect = 0;

    private OnItemFilterClickedListener onItemFilterClickedListener;

    FilterAdapter(Activity activity) {
        super(activity);
    }

    void setOnItemFilterClickedListener(OnItemFilterClickedListener onItemFilterClickedListener) {
        this.onItemFilterClickedListener = onItemFilterClickedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final FilterModel filterModel = list.get(position);

        Picasso.with(activity)
                .load(filterModel.getImg())
                .into(viewHolder.iv);

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

                if (onItemFilterClickedListener != null)
                    onItemFilterClickedListener.onItemFilterClicked(filterModel);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv;
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.iv);
            item = view.findViewById(R.id.item);
        }
    }

    public interface OnItemFilterClickedListener {
        void onItemFilterClicked(FilterModel filterModel);
    }
}
