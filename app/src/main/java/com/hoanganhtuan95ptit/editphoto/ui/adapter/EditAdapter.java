package com.hoanganhtuan95ptit.editphoto.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoanganhtuan95ptit.editphoto.R;
import com.hoanganhtuan95ptit.editphoto.model.EditType;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hoang Anh Tuan on 11/29/2017.
 */

public class EditAdapter extends BaseAdapter<EditType> {

    private OnItemEditPhotoClickedListener onItemEditPhotoClickedListener;

    public EditAdapter(Activity activity) {
        super(activity);
    }

    public void setOnItemEditPhotoClickedListener(OnItemEditPhotoClickedListener onItemEditPhotoClickedListener) {
        this.onItemEditPhotoClickedListener = onItemEditPhotoClickedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_edit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final EditType type = list.get(position);

        Picasso.with(activity)
                .load(type.VALUE)
                .into(viewHolder.iv);
        viewHolder.tv.setText(type.name());

        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemEditPhotoClickedListener.onItemEditPhotoClicked(type);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemEditPhotoClickedListener{
        void onItemEditPhotoClicked(EditType type);
    }
}
