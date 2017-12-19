package com.lzx.onematerial.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzx.onematerial.R;
import com.lzx.onematerial.entity.topic.banner.TopicItem;
import com.lzx.onematerial.listener.OnListItemClickListener;
import com.lzx.onematerial.utils.DensityUtil;
import com.lzx.onematerial.utils.ImageManager;
import com.lzx.onematerial.utils.MyApp;

import java.util.List;

import androidlib.image.loader.ImageLoader;

/**
 * Created by lizhenxin on 17-11-22.
 */

public class QAListAdapter extends RecyclerView.Adapter<QAListAdapter.Holder> {

    private List<TopicItem> mList;
    private ImageManager mImageManager;
    private Activity mActivity;

    private OnListItemClickListener mOnItemClickListener = null;

    public QAListAdapter(List<TopicItem> list, ImageManager imageManager, Activity activity) {
        this.mList = list;
        this.mImageManager = imageManager;
        this.mActivity = activity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.title.setText(mList.get(position).getTitle());

        //图片高度为250dp
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = DensityUtil.dip2px(MyApp.getContext(), 200);
        params.width = DensityUtil.dip2px(MyApp.getContext(), 300);
        holder.imageView.setLayoutParams(params);
        //项目宽度为300dp
        ViewGroup.LayoutParams params1 = holder.root.getLayoutParams();
        //params1.width = DensityUtil.dip2px(MyApp.getContext(), 300);
        params1.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        holder.root.setLayoutParams(params1);

        //mImageManager.setImageFromUrl(mActivity, holder.imageView, mList.get(position).getCover(), false);
        ImageLoader.with(mActivity).url(mList.get(position).getCover()).cache(MyApp.getCache()).into(holder.imageView);
        if (mOnItemClickListener != null) {
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnListItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public static class Holder extends RecyclerView.ViewHolder{
        ViewGroup root;
        ImageView imageView;
        TextView title;
        public Holder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.topic_item_root);
            imageView = itemView.findViewById(R.id.topic_imageview);
            title = itemView.findViewById(R.id.topic_title);
        }
    }
}
