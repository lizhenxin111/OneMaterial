package com.lzx.onematerial.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzx.onematerial.R;
import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.listener.OnListItemClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lizhenxin on 17-12-12.
 */

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorListAdapter.MyHolder> {
    private List<AuthorProfile> mList;
    private Context mContext;

    private OnListItemClickListener mOnListItemClickListener = null;
    public void setOnItemClickListener(OnListItemClickListener onItemClickListener) {
        mOnListItemClickListener = onItemClickListener;
    }

    public AuthorListAdapter(Context context, List<AuthorProfile> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getWeb_url()).into(holder.header);
        holder.name.setText(mList.get(position).getUser_name());
        holder.summary.setText(mList.get(position).getSummary());
        if (mOnListItemClickListener != null) {
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnListItemClickListener.onClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        CircleImageView header;
        TextView name;
        TextView summary;
        ConstraintLayout parent;
        public MyHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.author_image);
            name = itemView.findViewById(R.id.author_name);
            summary = itemView.findViewById(R.id.author_summary);
            parent = itemView.findViewById(R.id.author_constraint);
        }
    }
}