package androidlib.image.cache;

import android.content.Context;
import android.graphics.Bitmap;

import androidlib.image.cache.disk.DiskCacheI;
import androidlib.image.cache.memory.MemoryCacheI;


/**
 * Created by lizhe on 2017/10/11.
 */

public class DoubleCache implements IBaseCache {
    private IBaseCache mMemoryCache;
    private IBaseCache mDiskCache;

    public DoubleCache(Context context, String diskCacheName, int maxDiskCacheSize) {
        mMemoryCache = new MemoryCacheI();
        mDiskCache = new DiskCacheI(context, diskCacheName, maxDiskCacheSize);
    }

    @Override
    public void add(String imageUrl, Bitmap bmp) {
        mMemoryCache.add(imageUrl, bmp);
        mDiskCache.add(imageUrl, bmp);
    }

    @Override
    public Bitmap get(String imageUrl) {
        Bitmap bitmap = mMemoryCache.get(imageUrl);
        if (bitmap == null){
            bitmap = mDiskCache.get(imageUrl);
        }
        return bitmap;
    }

    @Override
    public void clear() {
        clearMemoryCache();
        clearDiskCache();
    }

    public void clearMemoryCache() {
        mMemoryCache.clear();
    }

    public void clearDiskCache(){
        mDiskCache.clear();
    }

    public void close(){
        mMemoryCache.close();
        if (mDiskCache != null){
            mDiskCache.close();
        }
    }



    public int size(){
        return (mDiskCache.size() + mMemoryCache.size()) / 1024;
    }
}
