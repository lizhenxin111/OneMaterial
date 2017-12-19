package com.lzx.onematerial.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzx.onematerial.R;
import com.lzx.onematerial.activity.ArticleActivity;
import com.lzx.onematerial.activity.ImageViewActivity;
import com.lzx.onematerial.adapter.SearchResultListAdapter;
import com.lzx.onematerial.entity.search.Search;
import com.lzx.onematerial.entity.search.SearchItem;
import com.lzx.onematerial.entity.search.SearchOtherItem;
import com.lzx.onematerial.entity.search.SearchOtherParent;
import com.lzx.onematerial.entity.search.SearchResult;
import com.lzx.onematerial.entity.search.SearchImageItem;
import com.lzx.onematerial.listener.OnSearchListener;
import com.lzx.onematerial.utils.ApiUtil;
import com.lzx.onematerial.utils.MyApp;

import java.util.ArrayList;
import java.util.List;

import androidlib.activity.WebActivity;
import androidlib.net.HttpGet.HttpGetListener;
import androidlib.utils.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    private String FRAGMENT_CATEGORY;
    private int SEARCH_PAGE_NUM = 1;
    private String SEARCH_CATEGORY;
    private String SEARCH_CONTENT;
    private boolean hasMore = true;

    private View mContextView;
    private RecyclerView mListView;
    private TwinklingRefreshLayout mRefresh;

    private SearchResultListAdapter mAdapter;
    private List<SearchItem> mValues = new ArrayList<>();

    private OnSearchListener mOnSearchListener = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContextView = inflater.inflate(R.layout.fragment_search_result, container, false);
        //mLoading.show();

        mAdapter = new SearchResultListAdapter(mValues ,getContext());
        mListView = mContextView.findViewById(R.id.search_result_list);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setOnItemClickListener(new SearchResultListAdapter.OnItemClickLsitener() {
            @Override
            public void onClick(View view, SearchItem searchItem) {
                onListItemClick(view, searchItem);
            }
        });
        mListView.setAdapter(mAdapter);

        mRefresh = mContextView.findViewById(R.id.search_refresh);
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadmore(true);
        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                addData(SEARCH_PAGE_NUM++, SEARCH_CATEGORY, SEARCH_CONTENT);
            }
        });
        return mContextView;
    }

    private void onListItemClick(View view, final SearchItem searchItem) {
        MyApp.getMainRequest().getString(ApiUtil.getSearchContentUrl(searchItem.getCategory(), searchItem.getContent_id()), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                if (FRAGMENT_CATEGORY.equals("hp")) {
                    SearchImageItem item = gson.fromJson(result , SearchResult.class).getData();
                    Bundle bundle = new Bundle();
                    bundle.putString("imageUrl", item.getImg_url());
                    bundle.putString("author", item.getPic_info());
                    bundle.putString("date", item.getPost_date());
                    bundle.putString("content", item.getForward());
                    bundle.putString("webUrl", item.getShare_url());
                    bundle.putString("wordsAuthor", item.getWords_info());
                    //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), null, "shareImage");
                    ActivityUtils.newActivity(getContext(), ImageViewActivity.class, bundle);
                } else {
                    SearchOtherParent parent = gson.fromJson(result, SearchOtherParent.class);
                    SearchOtherItem item = parent.getData();
                    Log.d("URL", item.getWeb_url() + "  " + item.getCategory() + "  " + item.getCommentnum() + "     " + item.getTitle());
                    Bundle bundle = new Bundle();
                    bundle.putString(WebActivity.NEED_TOOLBAR, "true");
                    bundle.putString(WebActivity.PAGE_TITLE, item.getTitle());
                    bundle.putString(WebActivity.PAGE_IMAGEURL, searchItem.getCover());
                    bundle.putString(WebActivity.PAGE_URL, item.getWeb_url());
                    bundle.putString(WebActivity.PAGE_SUBTITLE, searchItem.getSubtitle());
                    bundle.putString(WebActivity.PAGE_AUTHOR, item.getAuthor_list().get(0).getUser_name());
                    //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), null, "shareIitle");
                    ActivityUtils.newActivity(getContext(), ArticleActivity.class, bundle);
                }
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setOnSearchListener(OnSearchListener onSearchListener) {
        mOnSearchListener = onSearchListener;
    }

    /**
     * 添加数据
     */
    public void addData(final int page, String category, String content) {
        if (mOnSearchListener != null) {
            mOnSearchListener.onSearch();
        }
        FRAGMENT_CATEGORY = category;
        if (!content.equals(SEARCH_CONTENT)) {     //content不相同，说明更换了搜索词
            hasMore = true;
        }

        if (hasMore) {
            SEARCH_CATEGORY = category;
            SEARCH_CONTENT = content;
            MyApp.getMainRequest().getString(ApiUtil.getSearchUrl(category, content, page), new HttpGetListener() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    Search search = gson.fromJson(result, Search.class);
                    List<SearchItem> list = search.getData().getList();
                    if (list.size() == 0) {       //加载更多的数据为0时，设置标记为为false禁止加载更多
                        hasMore = false;
                        Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                    } else {
                        mValues.addAll(list);
                    }
                    mAdapter.notifyDataSetChanged();
                    mRefresh.finishLoadmore();
                    if (mOnSearchListener != null) {
                        mOnSearchListener.onSearchComplete();
                    }
                }

                @Override
                public void onFail(String error) {

                }
            });
        }
    }


    public void releaseData() {
        hasMore = true;
        if (mValues.size() != 0) {
            mValues.clear();
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
