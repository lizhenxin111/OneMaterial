package com.lzx.onematerial.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzx.onematerial.R;
import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.listener.OnListItemClickListener;
import com.lzx.onematerial.utils.DeviceUtil;

import java.util.List;

/**
 * Created by lizhenxin on 17-12-12.
 */

public class AuthorWorksAdapter extends RecyclerView.Adapter<AuthorWorksAdapter.MyHolder> {

    private List<ContentItem> mList;
    private Context mContext;

    private OnListItemClickListener mOnListItemClickListener = null;
    public void setOnClickListener(OnListItemClickListener onClickListener) {
        mOnListItemClickListener = onClickListener;
    }

    public AuthorWorksAdapter(Context context, List<ContentItem> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item_normal, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        //Glide.with(mContext).asBitmap().load(mList.get(position).getImg_url()).into(holder.pic);
        Glide.with(mContext).asBitmap().load(mList.get(position).getImg_url()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                float picWidth = resource.getWidth();
                float picHeight = resource.getHeight();
                int viewWidth = new DeviceUtil((Activity) mContext).getWidthPx() - new DeviceUtil().dip2px(mContext, 32);
                int viewHeight = (int) (picHeight / picWidth * viewWidth);
                Matrix matrix = new Matrix();
                matrix.postScale(viewWidth/picWidth, viewHeight/picHeight);
                holder.pic.setImageBitmap(Bitmap.createBitmap(resource, 0, 0, (int) picWidth, (int) picHeight, matrix, true));
                /*Palette palette = Palette.from(resource).generate();
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (swatch != null) {
                    holder.title.setTextColor(swatch.getBodyTextColor());
                }*/
            }
        });
        holder.title.setText(mList.get(position).getTitle());
        holder.subtitle.setText(mList.get(position).getForward());
        holder.category.setText(mList.get(position).getCategory());
        holder.author.setText(mList.get(position).getAuthor().getUser_name());

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

    public static class MyHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parent;
        ImageView pic;
        TextView title, subtitle, category, author;
        public MyHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.day_item_root);
            pic = itemView.findViewById(R.id.day_item_pic);
            title = itemView.findViewById(R.id.day_item_title);
            subtitle = itemView.findViewById(R.id.day_item_abstract);
            category = itemView.findViewById(R.id.day_item_category);
            author = itemView.findViewById(R.id.day_item_author);
        }
    }
}
