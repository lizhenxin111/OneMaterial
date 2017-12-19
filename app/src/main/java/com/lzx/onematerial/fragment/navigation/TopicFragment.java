package com.lzx.onematerial.fragment.navigation;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzx.onematerial.R;
import com.lzx.onematerial.activity.ArticleActivity;
import com.lzx.onematerial.adapter.QAListAdapter;
import com.lzx.onematerial.entity.topic.banner.TopicItem;
import com.lzx.onematerial.MVP.topicMVP.ITopicView;
import com.lzx.onematerial.MVP.topicMVP.TopicPresenter;
import com.lzx.onematerial.listener.OnListItemClickListener;
import com.lzx.onematerial.utils.ApiUtil;
import com.lzx.onematerial.utils.ImageManager;
import com.lzx.onematerial.utils.NetworkUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidlib.activity.LazyFragment;
import androidlib.activity.WebActivity;
import androidlib.utils.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends LazyFragment implements ITopicView{

    private Banner mBanner;
    private ImageLoader mBannerLoader = new ImageLoader() {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }

    };
    private LinearLayout mRootView;     //items的容器view
    private RecyclerView mQAListView;       //问答专题的listview
    private View mContextView;      //layout的view
    private TwinklingRefreshLayout mRefresh;

    private TopicPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContextView = inflater.inflate(R.layout.fragment_topic, container, false);
        initViews(mContextView);

        return mContextView;
    }

    public void initViews(View view) {
        mBanner = view.findViewById(R.id.topic_banner);
        mBanner.setImageLoader(mBannerLoader);

        mRefresh = view.findViewById(R.id.topic_refresh);
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadmore(true);
        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.setNormalTpoic(TopicPresenter.getLastItemId());
                mRefresh.finishLoadmore();
            }
        });
        mRefresh.setAutoLoadMore(false);
        mRefresh.setOverScrollRefreshShow(false);

        mRootView = view.findViewById(R.id.topic_root);
        initQAView(view);
    }


    /**
     * 加载banner数据
     * @param itemList
     */
    @Override
    public void setBanner(final List<TopicItem> itemList) {
        List<String> linkList = new ArrayList<>();
        for (TopicItem topicItem :
                itemList) {
            linkList.add(topicItem.getCover());
        }
        mBanner.setImages(linkList);
        mBanner.setDelayTime(3500);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String category = itemList.get(position).getCategory();
                if ("14".equals(category)) {    //卖东西
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri uri = Uri.parse(itemList.get(position).getLink_url());
                    intent.setData(uri);
                    startActivity(intent);
                } else if ("11".equals(category)) {     //文章
                    setTopicItemClick(ApiUtil.getTopicCoverUrl(itemList.get(position).getContent_id()), itemList.get(position).getTitle());
                }
            }
        });
        mBanner.start();
    }

    /**
     * 加载专题数据
     * @param itemList
     */
    @Override
    public void setNormalTipics(List<TopicItem> itemList) {
        for (final TopicItem topic :
                itemList) {
            com.lzx.onematerial.ui.TopicItem topicItem = new com.lzx.onematerial.ui.TopicItem(getActivity());
            topicItem.setImage(topic.getCover(), new ImageManager(getActivity()));
            topicItem.setTitle(topic.getTitle());
            topicItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTopicItemClick(ApiUtil.getTopicCoverUrl(topic.getContent_id()), topic.getTitle());
                }
            });
            topicItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onDownloadImageLongClick(v, topic.getCover());
                    return true;
                }
            });
            mRootView.addView(topicItem);
        }
    }

    /**
     * 加载问答专题的数据
     * @param itemList
     */
    @Override
    public void setQATopics(final List<TopicItem> itemList) {
        QAListAdapter adapter = new QAListAdapter(itemList, new ImageManager(getActivity()), getActivity());
        adapter.setOnItemClickListener(new OnListItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                setTopicItemClick(ApiUtil.getTopicCoverUrl(itemList.get(position).getContent_id()), itemList.get(position).getTitle());
            }
        });
        mQAListView.setAdapter(adapter);
    }

    private void setTopicItemClick(String pageUrl, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(WebActivity.PAGE_URL, pageUrl);
        bundle.putString(WebActivity.PAGE_TITLE, title);
        ActivityUtils.newActivity(getContext(),  ArticleActivity.class, bundle);
    }

    /**
     * 初始化问答专题的RecyclerView
     * @param view
     */
    private void initQAView(View view) {
        mQAListView = view.findViewById(R.id.topic_qa_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mQAListView.setLayoutManager(layoutManager);
    }

    private void onDownloadImageLongClick(View view, final String imageUrl) {
        final PopupMenu menu = new PopupMenu(getContext(), view, Gravity.CENTER);
        menu.getMenuInflater().inflate(R.menu.image_long_click, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.save_image){
                    if (new NetworkUtil().getNetworkState() != NetworkUtil.NONE){
                        int result = new ImageManager(getActivity()).saveImage(imageUrl);
                        String message = null;
                        if (result == ImageManager.IMAGE_EXIST){
                            message = getContext().getString(R.string.image_exist);
                        }else {
                            message = getContext().getString(R.string.save_complete);
                        }
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
        menu.show();
    }

    @Override
    protected void fetchData() {
        mPresenter = new TopicPresenter(this);
        mPresenter.setBanner();
        mPresenter.setQATopics();
        mPresenter.setNormalTpoic(TopicPresenter.getLastItemId());
        mRefresh.finishRefreshing();
    }
}
