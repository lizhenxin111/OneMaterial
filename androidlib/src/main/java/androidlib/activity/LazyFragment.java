package androidlib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by lizhenxin on 17-11-15.
 * 可以懒加载的Fragment
 * 参考：http://www.jianshu.com/p/c5d29a0c3f4c
 */

public abstract class LazyFragment extends BaseFragment {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    private boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    private boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    protected abstract void fetchData();
}
