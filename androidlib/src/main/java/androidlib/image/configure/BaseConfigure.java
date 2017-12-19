package androidlib.image.configure;


import android.graphics.Bitmap;
import android.widget.ImageView;

import androidlib.image.cache.IBaseCache;
import androidlib.image.loader.LoaderHelper;

/**
 * Created by lizhenxin on 17-11-24.
 */

public class BaseConfigure {

    private String url;
    private ImageView target;
    private Bitmap bitmap;
    private IBaseCache cache;
    private int inSampleSize = 1;       //压缩倍数，相当于BitmapFactory.Option的inSampleSize。为1时不压缩
    private ImageView.ScaleType scaleType;

    public BaseConfigure() {
    }

    public void execute(BaseConfigure configure) {
        LoaderHelper.getLoader().download(configure);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getTarget() {
        return target;
    }

    public void setTarget(ImageView target) {
        this.target = target;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public IBaseCache getCache() {
        return cache;
    }

    public void setCache(IBaseCache cache) {
        this.cache = cache;
    }

    public int getInSampleSize() {
        return inSampleSize;
    }

    public void setInSampleSize(int inSampleSize) {
        this.inSampleSize = inSampleSize;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }


    public static class ConfigureBuilder {
        private BaseConfigure mBaseConfigure = new BaseConfigure();

        public ConfigureBuilder url(String url) {
            mBaseConfigure.setUrl(url);
            return this;
        }

        public ConfigureBuilder cache(IBaseCache cache) {
            mBaseConfigure.setCache(cache);
            return this;
        }

        public void into(ImageView targetView) {
            mBaseConfigure.setTarget(targetView);
            mBaseConfigure.execute(mBaseConfigure);
        }

        public ConfigureBuilder compress(int inSampleSize) {
            mBaseConfigure.setInSampleSize(inSampleSize);
            return this;
        }

        public ConfigureBuilder scaleTpye(ImageView.ScaleType scaleType) {
            mBaseConfigure.setScaleType(scaleType);
            return this;
        }
    }
}
