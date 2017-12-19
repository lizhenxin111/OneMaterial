package androidlib.image.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.widget.ImageView;

import androidlib.image.configure.BaseConfigure;
import androidlib.net.HttpGet.HttpRunnable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lizhenxin on 17-11-24.
 */

public class ImageLoader implements ILoader {

    private static Handler mHandler = new Handler(){

    };


    public static BaseConfigure.ConfigureBuilder with(Context context) {
        return new BaseConfigure.ConfigureBuilder();
    }

    @Override
    public void download(final BaseConfigure configure) {
        LoaderHelper.getHttpRequest().execute(new HttpRunnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap;
                    String url = configure.getUrl();
                    if (configure.getCache() == null) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url(url).build();
                        Response response = client.newCall(request).execute();

                        //压缩图片
                        if (configure.getInSampleSize() != 1) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = configure.getInSampleSize();
                            bitmap = BitmapFactory.decodeStream(response.body().byteStream(), new Rect(), options);
                        } else {
                            bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        }

                    } else {
                        if (configure.getCache().get(url) == null) {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url(url).build();
                            Response response = client.newCall(request).execute();

                            //压缩图片
                            if (configure.getInSampleSize() != 1) {
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inSampleSize = configure.getInSampleSize();
                                bitmap = BitmapFactory.decodeStream(response.body().byteStream(), new Rect(), options);
                            } else {
                                bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                            }
                            configure.getCache().add(url, bitmap);
                        } else {
                            bitmap = configure.getCache().get(url);
                        }
                    }
                    setImage(configure.getTarget(), bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setImage(final ImageView view, final Bitmap bmp) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                view.setImageBitmap(bmp);
            }
        });
    }
}
