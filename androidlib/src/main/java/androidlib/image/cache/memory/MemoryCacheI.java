package androidlib.image.cache.memory;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.io.InputStream;

import androidlib.image.cache.IBaseCache;
import androidlib.image.utils.StringUtils;


/**
 * Created by lizhe on 2017/10/9.
 */

public class MemoryCacheI implements IBaseCache {
    final LruCache<String, Bitmap> mMemoryCache;
    final LruCache<String, InputStream> memoryCache;

    public MemoryCacheI() {
        int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = memorySize / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
        memoryCache = new LruCache<String, InputStream>(cacheSize);
    }

    @Override
    public void add(String imageUrl, Bitmap bmp) {
        mMemoryCache.put(StringUtils.toHex(imageUrl), bmp) ;
    }

    public void add(String link, InputStream inputStream){
        memoryCache.put(StringUtils.toHex(link), inputStream);
    }

    @Override
    public Bitmap get(String imageUrl) {
        return mMemoryCache.get(StringUtils.toHex(imageUrl));
    }

    public InputStream get(String link, int i){
        return memoryCache.get(StringUtils.toHex(link));
    }

    @Override
    public void clear() {
        mMemoryCache.evictAll();
    }

    @Override
    public int size() {
        return mMemoryCache.size();
    }

    @Override
    public void close() {
    }
}
