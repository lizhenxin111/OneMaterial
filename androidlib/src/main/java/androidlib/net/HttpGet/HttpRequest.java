package androidlib.net.HttpGet;

import android.os.Handler;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lizhenxin on 17-11-19.
 */

public class HttpRequest {

    private int mCorePoolSize = 5;
    private int mMaxPoolSize = 10;
    private int mKeepAliveTime = 5;
    private int mBlockSize = 10;

    private ThreadPoolExecutor mExecutor;

    private Handler mHandler;

    public HttpRequest() {
        mExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaxPoolSize, mKeepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(mBlockSize));
        mHandler = new Handler();
    }

    public HttpRequest(int mCorePoolSize, int mMaxPoolSize, int mKeepAliveTime, int mBlockSize, TimeUnit unit) {
        mExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaxPoolSize, mKeepAliveTime, unit, new ArrayBlockingQueue<Runnable>(mBlockSize));
        mHandler = new Handler();
    }

    private static class LazyLoader {
        public static final HttpRequest HTTP_REQUEST_SINGLE_INSTANCE = new HttpRequest();
    }

    public static HttpRequest getInstance() {
        return LazyLoader.HTTP_REQUEST_SINGLE_INSTANCE;
    }

    /**
     * 执行网络操作
     * @param httpRunnable
     */
    public void execute(HttpRunnable httpRunnable) {
        mExecutor.execute(httpRunnable);
    }

    public void shutdown() {
        if (!mExecutor.isShutdown()) {
            mExecutor.shutdown();
        }
    }

    public void shuutdownNow() {
        if (!mExecutor.isShutdown()) {
            mExecutor.shutdownNow();
        }
    }

    /**
     * 从网络中获取字符串
     * @param link              链接
     * @param httpGetListener   回调接口
     */
    public void getString(final String link, final HttpGetListener httpGetListener) {
        mExecutor.execute(new HttpRunnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(link)
                            .build();
                    response = client.newCall(request).execute();
                    postData(response.body().string(), httpGetListener, response.isSuccessful());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (response != null) {
                        response.close();
                    }
                }
            }
        });
    }

    private void postData(final String result, final HttpGetListener httpGetListener, final boolean isSuccessful) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (isSuccessful) {
                    httpGetListener.onSuccess(result);
                } else {
                    httpGetListener.onFail(result);
                }
            }
        });
    }
}
