package com.haytech.haytechstyles.multibutton;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.haytech.haytechstyles.R;

import java.util.List;

public class MultiButtonAdapter extends BaseListAdapter<ItemStyleModel> {

    private Context context;
    private ItemStyleModel selectedItemStyle;
    private int selectedItemPosition;

    public MultiButtonAdapter(Context context, @NonNull List<ItemStyleModel> items, @Nullable OnItemClickListener<ItemStyleModel> clickListener, @Nullable OnItemLongClickListener<ItemStyleModel> longClickListener) {
        super(items, clickListener, longClickListener);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_multi_button, parent, false));
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }

    public void setSelectedItemStyle(ItemStyleModel selectedItemStyle) {
        this.selectedItemStyle = selectedItemStyle;
        notifyDataSetChanged();
    }

    public void setSelectedItemPosition(int selectedItemPosition) {
        this.selectedItemPosition = selectedItemPosition;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends ViewHolder {
        private TextView buttonItemLabel;

        public MyViewHolder(View view) {
            super(view);
            buttonItemLabel = (AppCompatTextView) itemView.findViewById(R.id.tv_buttonItemLabel);
        }


        @Override
        protected void onBind(ItemStyleModel item) {
            item.getBuilder().setPosition(getLayoutPosition());
            if (selectedItemPosition == getLayoutPosition()) {
//                item.getBuilder().setSelectedItem(true);
//                ItemStyleModel itemStyleModel = selectedItemStyle.getBuilder()
//                        .setLabelText(item.getLabelText())
//                        .setSelectedItem(true)
//                        .build();
//                setItemStyle(itemStyleModel);
                if (selectedItemStyle.getLabelText() != null && selectedItemStyle.getLabelText().isEmpty()) {
                    ItemStyleModel itemStyleModel = selectedItemStyle.getBuilder()
                            .setLabelText(item.getLabelText())
                            .setSelectedItem(true)
                            .build();
                    setItemStyle(itemStyleModel);
                } else
                    setItemStyle(selectedItemStyle);
            } else {
//                itemStyleUpdate(item, item);
                item.getBuilder().setSelectedItem(false).update(item);
                setItemStyle(item);

            }
        }

        private void setItemStyle(ItemStyleModel item) {
            if (item.getLabelText() != null)
                buttonItemLabel.setText(item.getLabelText());
            buttonItemLabel.setTextColor(item.getLabelTextColor());
            if (item.getLabelTextSize() != 0)
                buttonItemLabel.setTextSize(item.getLabelTextSize());
            buttonItemLabel.setVisibility(item.getLabelVisibility());
            if (item.getLabelTypeface() != null)
                buttonItemLabel.setTypeface(item.getLabelTypeface());
            buttonItemLabel.setPadding(
                    dp2px(context, item.getLabelPaddingLeft()),
                    dp2px(context, item.getLabelPaddingTop()),
                    dp2px(context, item.getLabelPaddingRight()),
                    dp2px(context, item.getLabelPaddingBottom())
            );
            buttonItemLabel.setBackgroundColor(item.getLabelBackgroundColor());

            itemView.setBackgroundResource(item.getLabelBackgroundResource());
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
            if (item.getWidth() == 0)
                params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            else
                params.width = item.getWidth();
            if (item.getWidth() == 0)
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            else
                params.height = item.getHeight();
            itemView.setLayoutParams(params);
            itemView.setPadding(
                    dp2px(context, item.getPaddingLeft()),
                    dp2px(context, item.getPaddingTop()),
                    dp2px(context, item.getPaddingRight()),
                    dp2px(context, item.getPaddingBottom())
            );
            itemView.setBackgroundColor(item.getBackgroundColor());
            itemView.setBackground(item.getBackgroundDrawable());
//                itemView.set(item.getMarginLeft());
//                itemView.set(item.getMarginTop());
//                itemView.set(item.getMarginRight());
//                itemView.set(item.getMarginBottom());
        }

        private ItemStyleModel itemStyleUpdate(ItemStyleModel oldItem, ItemStyleModel newItem) {
            oldItem.getBuilder()
                    .setSelectedItem(newItem.isSelectedItem())
//                    .setLabelText(newItem.getLabelText())
                    .setLabelTextColor(newItem.getLabelTextColor())
                    .setLabelTextSize(newItem.getLabelTextSize())
                    .setLabelVisibility(newItem.getLabelVisibility())
                    .setLabelPaddingLeft(newItem.getLabelPaddingLeft())
                    .setLabelPaddingTop(newItem.getLabelPaddingTop())
                    .setLabelPaddingRight(newItem.getLabelPaddingRight())
                    .setLabelPaddingBottom(newItem.getLabelPaddingBottom())
                    .setWidth(newItem.getWidth())
                    .setHeight(newItem.getHeight())
                    .setMarginLeft(newItem.getMarginLeft())
                    .setMarginTop(newItem.getMarginTop())
                    .setMarginRight(newItem.getMarginRight())
                    .setMarginBottom(newItem.getMarginBottom())
                    .setBackgroundDrawable(newItem.getBackgroundDrawable())
                    .update(oldItem);
            return oldItem;
        }

        @NonNull
        @Override
        protected ItemStyleModel getItem() {
            return super.getItem();
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            selectedItemPosition = getLayoutPosition();
            notifyDataSetChanged();
        }

        @Override
        public boolean onLongClick(View v) {
            selectedItemPosition = getLayoutPosition();
            notifyDataSetChanged();
            return super.onLongClick(v);
        }
    }
}
