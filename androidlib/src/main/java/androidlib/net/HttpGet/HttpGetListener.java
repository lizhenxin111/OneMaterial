package androidlib.net.HttpGet;

/**
 * Created by lizhenxin on 17-11-19.
 */

public interface HttpGetListener {
    void onSuccess(String result);
    void onFail(String error);
}
