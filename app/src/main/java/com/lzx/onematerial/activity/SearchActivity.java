package com.lzx.onematerial.activity;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.lzx.onematerial.R;
import com.lzx.onematerial.adapter.MainViewPagerAdapter;
import com.lzx.onematerial.fragment.SearchResultFragment;
import com.lzx.onematerial.listener.OnSearchListener;
import com.lzx.onematerial.ui.MyViewPager;
import com.lzx.onematerial.utils.MyApp;

import java.util.ArrayList;
import java.util.List;

import androidlib.activity.BaseActivity;
import androidlib.ui.SimpleLoading;

public class SearchActivity extends BaseActivity implements OnSearchListener{

    private List<SearchResultFragment> mFragmentList = new ArrayList<>();


    private SearchView mSearchView;
    private TabLayout mTabs;
    private MyViewPager mPager;
    private SimpleLoading mLoading;

    @Override
    protected void initViews(Bundle saveBundleInstance) {
        setContentView(R.layout.activity_search);
        mLoading = new SimpleLoading(SearchActivity.this);

        initFragments();

        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        mPager = findViewById(R.id.serach_vp);
        mPager.setCancelScroll(true);
        mPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), mFragmentList));

        mTabs = findViewById(R.id.search_tabs);

        mSearchView = findViewById(R.id.search_box);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setSubmitButtonEnabled(true);
    }

    private void initFragments() {
        if (mFragmentList.size() == 0) {
            for (int i = 0; i < 6; i++) {
                SearchResultFragment fragment = new SearchResultFragment();
                fragment.setOnSearchListener(this);
                mFragmentList.add(fragment);
            }
        }
    }

    @Override
    protected void initData() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int position = mTabs.getSelectedTabPosition();
                search(position);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 5) {
                    toastShort("暂未开放");
                } else {
                    mPager.setCurrentItem(tab.getPosition());
                    if (mSearchView.getQuery().toString() != null && !"".equals(mSearchView.getQuery().toString().trim())){
                        search(tab.getPosition());
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mFragmentList.get(tab.getPosition()).releaseData();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected void initListeners() {

    }

    private String getCategory(int position) {
        switch (position) {
            case 0:
                return "hp";
            case 1:
                return "reading";
            case 2:
                return "music";
            case 3:
                return "movie";
            case 4:
                return "radio";
            case 5:
                return "author";
        }
        return "";
    }

    private void search(int position) {
        mFragmentList.get(position).addData(0, getCategory(position), mSearchView.getQuery().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (SearchResultFragment frament:
             mFragmentList) {
            frament.releaseData();
        }
    }

    @Override
    public void onSearch() {
        mLoading.show();
    }

    @Override
    public void onSearchComplete() {
        mLoading.dismiss();
    }
}
