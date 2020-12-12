package com.haytech.haytechstyles.multibutton;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListAdapter<T>.ViewHolder> {

    private List<T> items;

    @Nullable
    private OnItemClickListener<T> clickListener;

    @Nullable
    private OnItemLongClickListener<T> longClickListener;

    public BaseListAdapter(@NonNull List<T> items,
                           @Nullable OnItemClickListener<T> clickListener,
                           @Nullable OnItemLongClickListener<T> longClickListener) {
        this.items = items;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public final void setClickListener(@Nullable OnItemClickListener<T> clickListener) {
        this.clickListener = clickListener;
        notifyDataSetChanged();
    }

    public final void setLongClickListener(@Nullable OnItemLongClickListener<T> longClickListener) {
        this.longClickListener = longClickListener;
        notifyDataSetChanged();
    }

    public final void addItems(@NonNull List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public final void addItem(@NonNull T item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    public final void removeItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public final void removeItem(int itemPosition) {
        if (itemPosition < this.items.size() && itemPosition >= 0) {
            this.items.remove(itemPosition);
            notifyDataSetChanged();
        }
    }

    public final void removeItem(T item) {
//        int itemPosition = 0;
//        for (int i = 0; i < this.items.size(); i++) {
//            if (this.items.get(i) == item)
//                itemPosition = i;
//        }
        this.items.remove(this.items.indexOf(item));
        notifyDataSetChanged();
    }

    protected final T getItem(int position) {
        return items.get(position);
    }

    protected final List<T> getItems() {
        return items;
    }

    public final void setItems(@NonNull List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private void onItemClick(T item, int position) {
        if (clickListener != null) {
            clickListener.onItemClick(item, position);
        }
    }

    private void onLongItemClick(T item, int position) {
        if (longClickListener != null) {
            longClickListener.onItemLongClick(item, position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(T item, int position);
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnLongClickListener {

        private T item;

        public ViewHolder(View v) {
            super(v);
        }

        private void bind(T item) {
            this.item = item;

            if (clickListener != null) {
                itemView.setOnClickListener(this);
            }

            if (longClickListener != null) {
                itemView.setOnLongClickListener(this);
            }

            onBind(item);
        }

        protected abstract void onBind(T item);

        @NonNull
        protected T getItem() {
            return item;
        }

        @Override
        public void onClick(View v) {
            onItemClick(item, getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onLongItemClick(item, getLayoutPosition());
            return true;
        }
    }
}