package androidlib.net.HttpGet;

import java.util.concurrent.TimeUnit;

/**
 * Created by lizhenxin on 17-11-24.
 */

public class ThreadPoolConfigure {
    int mCorePoolSize = -1;
    int mMaxPoolSize = -1;
    int mKeepAliveTime = -1;
    int mBlockSize = -1;
    TimeUnit mTimeUnit = null;

    public ThreadPoolConfigure() {
    }

    public int getCorePoolSize() {
        return mCorePoolSize;
    }

    public void setCorePoolSize(int mCorePoolSize) {
        this.mCorePoolSize = mCorePoolSize;
    }

    public int getMaxPoolSize() {
        return mMaxPoolSize;
    }

    public void setMaxPoolSize(int mMaxPoolSize) {
        this.mMaxPoolSize = mMaxPoolSize;
    }

    public int getKeepAliveTime() {
        return mKeepAliveTime;
    }

    public void setKeepAliveTime(int mKeepAliveTime) {
        this.mKeepAliveTime = mKeepAliveTime;
    }

    public int getBlockSize() {
        return mBlockSize;
    }

    public void setBlockSize(int mBlockSize) {
        this.mBlockSize = mBlockSize;
    }

    public TimeUnit getTimeUnit() {
        return mTimeUnit;
    }

    public void setTimeUnit(TimeUnit mTimeUnit) {
        this.mTimeUnit = mTimeUnit;
    }

    public boolean isEffective() {
        return mCorePoolSize != -1 && mMaxPoolSize != -1 && mKeepAliveTime != -1 && mBlockSize != -1 && mTimeUnit != null;
    }

    public static class Builder {
        private ThreadPoolConfigure configure;

        public Builder corePoolSize(int size) {
            configure.setCorePoolSize(size);
            return this;
        }
    }
}
