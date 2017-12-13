package com.hoanganhtuan95ptit.fillter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang Anh Tuan on 3/10/2017.
 */

abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> list;
    protected Activity activity;
    protected LayoutInflater inflater;

    BaseAdapter(Activity activity) {
        this.activity = activity;
        this.list = new ArrayList<>();
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public synchronized void add(T t) {
        list.add(t);
        sort();
        int position = list.indexOf(t);
        notifyItemInserted(position);
    }

    public synchronized void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public synchronized void addPosition(T t, int position) {
        list.add(position, t);
        sort();
        position = list.indexOf(t);
        notifyItemInserted(position);
    }

    public synchronized void removerPosition(int position) {
        T t = list.get(position);
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, list.size());
    }

    public synchronized void updatePosition(T t, int position) {
        list.set(position, t);
        notifyItemChanged(position);
        notifyItemRangeChanged(position, list.size());
    }

    public ArrayList<T> getData() {
        return (ArrayList<T>) list;
    }

    synchronized void sort() {
    }

}
