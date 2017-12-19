package com.lzx.onematerial.fragment.navigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzx.onematerial.MVP.authorMVP.AuthorPresenter;
import com.lzx.onematerial.MVP.authorMVP.IAuthorView;
import com.lzx.onematerial.R;
import com.lzx.onematerial.activity.AuthorActivity;
import com.lzx.onematerial.adapter.AuthorListAdapter;
import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.listener.OnListItemClickListener;

import java.util.List;

import androidlib.activity.LazyFragment;
import androidlib.utils.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorFragment extends LazyFragment implements IAuthorView {

    private View mContextView;
    private RecyclerView mList;
    private AuthorListAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContextView = inflater.inflate(R.layout.fragment_category, container, false);
        //mContainer = (LinearLayout) mContextView.findViewById(R.id.category_root);

        mList = mContextView.findViewById(R.id.author_list);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));

        return mContextView;
    }

    @Override
    protected void fetchData() {
        AuthorPresenter presenter = new AuthorPresenter(this);
        presenter.setHotAuthors();
    }


    @Override
    public void setAuthors(final List<AuthorProfile> list) {
        /*for (final AuthorProfile author :
                list) {
            AuthorItem authorItem = new AuthorItem(getContext());
            authorItem.setAuthorImage(getContext(), author.getWeb_url());
            authorItem.setAuthorName(author.getUser_name());
            authorItem.setAuthorWeName(author.getWb_name());
            authorItem.setAuthorDesc(author.getDesc());
            authorItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AuthorActivity.AUTHOR_ID, author.getUser_id());
                    ActivityUtils.newActivity(getActivity(), AuthorActivity.class, bundle);
                }
            });
            mContainer.addView(authorItem);
        }*/
        mAdapter = new AuthorListAdapter(getContext(), list);
        mList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnListItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(AuthorActivity.AUTHOR_ID, list.get(position).getUser_id());
                ActivityUtils.newActivity(getActivity(), AuthorActivity.class, bundle);
            }
        });
    }

    @Override
    public void setAuthorDetails(AuthorProfile profile) {

    }

    @Override
    public void setAuthorWorks(List<ContentItem> list) {

    }
}