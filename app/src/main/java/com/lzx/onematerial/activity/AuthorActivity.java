package com.lzx.onematerial.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzx.onematerial.MVP.authorMVP.AuthorPresenter;
import com.lzx.onematerial.MVP.authorMVP.IAuthorView;
import com.lzx.onematerial.R;
import com.lzx.onematerial.adapter.AuthorWorksAdapter;
import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.listener.OnListItemClickListener;
import com.lzx.onematerial.service.MusicService;
import com.lzx.onematerial.utils.ImageManager;
import com.lzx.onematerial.utils.NetworkUtil;

import java.util.List;

import androidlib.activity.BaseActivity;
import androidlib.activity.WebActivity;
import androidlib.image.loader.ImageLoader;
import androidlib.utils.ActivityUtils;

import static com.lzx.onematerial.utils.MyApp.getContext;

public class AuthorActivity extends BaseActivity implements IAuthorView {
    public static final String AUTHOR_ID = "AUTHOR_ID";

    private boolean isRegisted = false;

    private ImageView mImage;
    private TextView mName, mSummary, mDesc;
    private LinearLayout mContainer;
    private TwinklingRefreshLayout mRefresh;
    private AuthorPresenter mPresenter;

    private RecyclerView mList;
    private AuthorWorksAdapter mAdapter;

    private MusicService.MusicControlBinder binder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MusicControlBinder) service;
            isRegisted = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isRegisted = false;
        }
    };

    @Override
    protected void initViews(Bundle saveBundleInstance) {
        setContentView(R.layout.activity_author);
        initComponent();
        setSlideBack(true);
        mList = findViewById(R.id.author_work_list);
        mList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initComponent() {
        mImage = findViewById(R.id.author_detail_image);
        mName = findViewById(R.id.author_detail_name);
        //mSummary = (TextView) findViewById(R.id.author_detail_summary);
        mDesc = findViewById(R.id.author_detail_desc);
        //mContainer = (LinearLayout) findViewById(R.id.author_works_list);
        mRefresh = findViewById(R.id.author_refresh);
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadmore(true);
        mRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.setAuthorWorks(getBundle().getString(AUTHOR_ID), mPresenter.getPageNum());
            }
        });
        mRefresh.setAutoLoadMore(false);
        mRefresh.setOverScrollRefreshShow(false);
    }

    @Override
    protected void initData() {
        Log.d("AUTHOR", "initData");
        mPresenter = new AuthorPresenter(this);
        mPresenter.setAuthorDetail(getBundle().getString(AUTHOR_ID));
        mPresenter.setAuthorWorks(getBundle().getString(AUTHOR_ID), mPresenter.getPageNum());
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void setAuthors(List<AuthorProfile> list) {
    }

    @Override
    public void setAuthorDetails(AuthorProfile profile) {
        Log.d("AUTHOR", "setAuthorDetails");
        ImageLoader.with(this).url(profile.getWeb_url()).into(mImage);
        mName.setText(profile.getUser_name());
        mDesc.setText(profile.getDesc());
    }

    @Override
    public void setAuthorWorks(final List<ContentItem> list) {
        mAdapter = new AuthorWorksAdapter(this, list);
        mAdapter.setOnClickListener(new OnListItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(WebActivity.PAGE_TITLE, list.get(position).getTitle());
                bundle.putString(WebActivity.PAGE_IMAGEURL, list.get(position).getImg_url());
                bundle.putString(WebActivity.PAGE_URL, list.get(position).getShare_url());
                bundle.putString(WebActivity.PAGE_SUBTITLE, list.get(position).getForward());
                bundle.putString(WebActivity.PAGE_AUTHOR, list.get(position).getAuthor().getUser_name());
                bundle.putString(WebActivity.NEED_TOOLBAR, "true");
                //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AuthorActivity.this, dayItem.getTitle(), "shareIitle");
                ActivityUtils.newActivity(AuthorActivity.this, ArticleActivity.class, bundle);
            }
        });
        mList.setAdapter(mAdapter);
        if (mRefresh != null) {
            mRefresh.finishLoadmore();
        }
    }

    /*if ("8".equals(item.getCategory())) {       //电台
        final DayRadio dayRadio = new DayRadio(this);
        dayRadio.setParams(item.getTitle(), item.getAuthor().getUser_name(), item.getAudio_url(), item.getImg_url(), item.getShare_url());
        dayRadio.setOnToggleClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null){
                    switch (binder.getState()){
                        case MusicService.MEDIAPLAYER_NULL:
                            binder.init(item.getAudio_url(), item.getTitle(), item.getImg_url(), item.getShare_url());
                            NotificationUtil.showToast(AuthorActivity.this, getResources().getString(R.string.loading), Toast.LENGTH_SHORT);
                            dayRadio.setToggleImage(R.mipmap.bg_load);
                            binder.setOnPlayerPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    binder.play();
                                }
                            });
                            binder.setOnStateChangeListener(new MusicService.OnStateChangeListehner() {
                                @Override
                                public void onChanged() {
                                    dayRadio.setToggleImage(binder.isPlaying() ? R.mipmap.bg_pause : R.mipmap.bg_play);
                                }
                            });
                            break;
                        case MusicService.MEDIAPLAYER_PLAYING:
                            binder.pause();
                            break;
                        case MusicService.MEDIAPLAYER_PAUSING:
                            binder.play();
                            break;
                        default:
                    }
                }
            }
        });
        dayRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tag", "radio");
                bundle.putString(WebActivity.PAGE_URL, item.getShare_url());
                bundle.putString(WebActivity.PAGE_TITLE, item.getTitle());
                bundle.putString(WebActivity.NEED_TOOLBAR, "true");
                ActivityUtils.newActivity(getContext(), ArticleActivity.class, bundle);
            }
        });
        dayRadio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDownloadImageLongClick(v, item.getImg_url());
                return true;
            }
        });
        mContainer.addView(dayRadio, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    } else {        //其他项目
        final DayItem dayItem = new DayItem(this);
        dayItem.setCategory(item.getCategory());
        dayItem.setPicByUrl(item.getImg_url());
        dayItem.setTitle(item.getTitle());
        dayItem.setAuthor(item.getAuthor().getUser_name());
        dayItem.setAbstracts(item.getForward());
        dayItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(WebActivity.PAGE_URL, item.getShare_url());
                bundle.putString(WebActivity.PAGE_TITLE, item.getTitle());
                bundle.putString(WebActivity.NEED_TOOLBAR, "true");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AuthorActivity.this, dayItem.getTitle(), "shareIitle");
                ActivityUtils.newActivity(AuthorActivity.this, ArticleActivity.class, bundle, options);
            }
        });
        dayItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDownloadImageLongClick(v, item.getImg_url());
                return true;
            }
        });
        mContainer.addView(dayItem, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }*/

    private void onDownloadImageLongClick(View view, final String imageUrl) {
        final PopupMenu menu = new PopupMenu(getContext(), view, Gravity.CENTER);
        menu.getMenuInflater().inflate(R.menu.image_long_click, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.save_image){
                    if (new NetworkUtil().getNetworkState() != NetworkUtil.NONE){
                        int result = new ImageManager(AuthorActivity.this).saveImage(imageUrl);
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

}