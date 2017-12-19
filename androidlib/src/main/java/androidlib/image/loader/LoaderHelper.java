package androidlib.image.loader;

import java.util.concurrent.TimeUnit;

import androidlib.net.HttpGet.HttpRequest;

/**
 * Created by lizhenxin on 17-11-24.
 */

public class LoaderHelper {
    private static ILoader iLoader = null;

    public static ILoader getLoader(){
        if (iLoader == null) {
            iLoader = new ImageLoader();
        }
        return iLoader;
    }

    private static HttpRequest httpRequest = null;

    public static HttpRequest getHttpRequest() {
        if (httpRequest == null) {
            httpRequest = new HttpRequest(10, 20, 5, 10, TimeUnit.SECONDS);
        }
        return httpRequest;
    }
}
