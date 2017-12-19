package com.lzx.onematerial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzx.onematerial.R;
import com.lzx.onematerial.entity.search.SearchItem;

import java.util.List;

import androidlib.image.loader.ImageLoader;

/**
 * Created by lizhenxin on 17-11-29.
 */

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.ResultHolder> {

    private List<SearchItem> mValues;
    private Context mContext;

    private OnItemClickLsitener mOnListItemClickListener = null;

    public SearchResultListAdapter(List<SearchItem> mValues, Context mContext) {
        this.mValues = mValues;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickLsitener onListItemClickListener) {
        mOnListItemClickListener = onListItemClickListener;
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultHolder holder, final int position) {
        ImageLoader.with(mContext).url(mValues.get(position).getCover()).into(holder.image);
        //Glide.with(mContext).load(mValues.get(position).getCover()).into(holder.image);
        holder.title.setText(mValues.get(position).getTitle());
        holder.subtitle.setText(mValues.get(position).getSubtitle());

        if (mOnListItemClickListener != null) {
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnListItemClickListener.onClick(v, mValues.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnItemClickLsitener {
        void onClick(View view, SearchItem searchItem);
    }

    public static class ResultHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title, subtitle;
        public ViewGroup root;
        public ResultHolder(View view) {
            super(view);
            root = view.findViewById(R.id.search_item_root);
            image = view.findViewById(R.id.search_item_image);
            title = view.findViewById(R.id.search_item_title);
            subtitle = view.findViewById(R.id.search_item_subtitle);
        }
    }
}
