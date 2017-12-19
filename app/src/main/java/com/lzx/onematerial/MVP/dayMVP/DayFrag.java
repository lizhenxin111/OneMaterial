package com.lzx.onematerial.MVP.dayMVP;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzx.onematerial.R;
import com.lzx.onematerial.activity.ArticleActivity;
import com.lzx.onematerial.activity.ImageViewActivity;
import com.lzx.onematerial.activity.MainActivity;
import com.lzx.onematerial.listener.ToolbarDateListener;
import com.lzx.onematerial.service.MusicService;
import com.lzx.onematerial.ui.DayItem;
import com.lzx.onematerial.ui.DayPic;
import com.lzx.onematerial.ui.DayRadio;
import com.lzx.onematerial.utils.ImageManager;
import com.lzx.onematerial.utils.NetworkUtil;
import com.lzx.onematerial.utils.NotificationUtil;

import androidlib.activity.WebActivity;
import androidlib.ui.SimpleLoading;
import androidlib.utils.ActivityUtils;

/**
 * Created by lizhe on 2017/6/3.
 */

public class DayFrag extends Fragment implements IDayFrag, View.OnClickListener {


    private boolean isRegisted = false;

    private PopupWindow shareList;
    private View view;
    private TwinklingRefreshLayout mRefreshLayout;
    private LinearLayout layout;

    private SimpleLoading mLoading;

    private int mCurrentDay;

    private String CURRENT_DATE = null;

    private ToolbarDateListener mToolbarDateListener = null;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day, container, false);
        getActivity().bindService(new Intent(getActivity(), MusicService.class), connection, getActivity().BIND_AUTO_CREATE);
        layout = view.findViewById(R.id.fd_root_layout);
        mRefreshLayout = view.findViewById(R.id.day_refresh);
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadmore(false);
        //mRefreshLayout.setFloatRefresh(true);
        //mRefreshLayout.setEnableOverScroll(true);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                removeViews();
                updateData(mCurrentDay);
                //在removeLoading()中结束
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoading = new SimpleLoading(getActivity());
        mToolbarDateListener = MainActivity.INSTANCE;       //控制toolbar的日期显示
    }

    public void removeViews() {
        LinearLayout root = view.findViewById(R.id.fd_root_layout);
        if (root.getChildCount() != 0) {
            root.removeAllViews();
        }
    }

    public void updateData(int day) {
        mCurrentDay = day;
        DayPresenter dayPresenter = new DayPresenter(this);
        dayPresenter.getAllItem(day);
    }


    @Override
    public void setContentItem(String category, final String picUrl, final String title, final String author, final String abstracts,
                               final String contentUrl, String audioUrl, final String contentTag, int id) {
        final DayItem dayItem = new DayItem(getActivity());
        dayItem.setId(id);
        dayItem.setCategory(category);
        dayItem.setPicByUrl(picUrl);
        dayItem.setTitle(title);
        dayItem.setAuthor(author);
        dayItem.setAbstracts(abstracts);
        dayItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(WebActivity.PAGE_TITLE, title);
                bundle.putString(WebActivity.PAGE_IMAGEURL, picUrl);
                Log.d("URL", picUrl);
                bundle.putString(WebActivity.PAGE_URL, contentUrl);
                bundle.putString(WebActivity.PAGE_SUBTITLE, abstracts);
                bundle.putString(WebActivity.PAGE_AUTHOR, author);
                bundle.putString(WebActivity.NEED_TOOLBAR, "true");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), dayItem.getTitle(), "shareIitle");
                ActivityUtils.newActivity(getContext(), ArticleActivity.class, bundle, options);
            }
        });
        /*dayItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDownloadImageLongClick(v, picUrl);
                return true;
            }
        });*/
        layout.addView(dayItem, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void setPic(final String picUrl, final String picAuthor, final String words, final String wordsAuthor, final String url) {
        final DayPic dayPic = new DayPic(getActivity());
        dayPic.setId(0);
        dayPic.setPicByUrl(picUrl);
        dayPic.setPicAuthor(picAuthor);
        dayPic.setWords(words);
        dayPic.setWordsAuthor(wordsAuthor);
        dayPic.setUrl(url);
        dayPic.setDate(getDate());
        dayPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("imageUrl", picUrl);
                bundle.putString("author", picAuthor);
                bundle.putString("content" , words);
                bundle.putString("webUrl", url);
                bundle.putString("date", getDate());
                bundle.putString("wordsAuthor", wordsAuthor);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), dayPic.getPic(), "shareImage");
                ActivityUtils.newActivity(getContext(), ImageViewActivity.class, bundle, options);
            }
        });
        dayPic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //showPopupWindow(v);
                return true;
            }
        });
        layout.addView(dayPic, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void setRadio(final String title, final String author, final String imageUrl, final String audioUrl, final String articleUrl) {

        final DayRadio dayRadio = new DayRadio(getActivity());
        dayRadio.setParams(title, author, audioUrl, imageUrl, articleUrl);
        dayRadio.setOnToggleClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null){
                    switch (binder.getState()){
                        case MusicService.MEDIAPLAYER_NULL:
                            binder.init(audioUrl, title, imageUrl, articleUrl);
                            NotificationUtil.showToast(getActivity(), getResources().getString(R.string.loading), Toast.LENGTH_SHORT);
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
                bundle.putString(WebActivity.PAGE_TITLE, title);
                bundle.putString(WebActivity.PAGE_IMAGEURL, imageUrl);
                bundle.putString(WebActivity.PAGE_URL, articleUrl);
                bundle.putString(WebActivity.PAGE_SUBTITLE, title);
                bundle.putString(WebActivity.PAGE_AUTHOR, author);
                bundle.putString(WebActivity.NEED_TOOLBAR, "true");
                ActivityUtils.newActivity(getContext(), ArticleActivity.class, bundle);
            }
        });
        dayRadio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDownloadImageLongClick(v, imageUrl);
                return true;
            }
        });
        layout.addView(dayRadio, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void setDate(String date) {
        mToolbarDateListener.setDate(date.substring(0, 10));
    }

    @Override
    public String getDate() {
        return mToolbarDateListener.getDate();
    }

    @Override
    public void showLoading() {
        mLoading.show();
    }

    /**
     * 加载网络数据结束
     */
    @Override
    public void removeLoading() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefreshing();
        }
        if (mLoading != null) {
            mLoading.dismiss();
        }
    }

    /*private void showPopupWindow(View v){
        View shareListView = LayoutInflater.from(getActivity()).inflate(R.layout.share_list, null);

        shareListView.findViewById(R.id.share_to_sina).setOnClickListener(this);

        shareList = new PopupWindow(shareListView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        shareList.setBackgroundDrawable(new ColorDrawable(0x00000000));
        shareList.setOutsideTouchable(true);
        //shareList.setAnimationStyle(R.style.shareWindowAnim);
        //shareList.update();
        shareList.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }*/

    private void closePopupWindow(){
        shareList.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_to_sina:
                closePopupWindow();
                break;
            default:
        }
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
    public void onDestroy() {
        super.onDestroy();
        if (isRegisted) {
            getActivity().unbindService(connection);
        }
        CURRENT_DATE = null;
    }
}
