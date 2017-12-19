package androidlib.image.cache;

import android.graphics.Bitmap;

/**
 * Created by lizhe on 2017/10/9.
 */

public interface IBaseCache {
    void add(String imageUrl, Bitmap bmp);
    Bitmap get(String imageUrl);
    void clear();
    int size();
    void close();
}
