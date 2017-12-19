package androidlib.activity;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by lizhenxin on 17-11-15.
 */

public class BaseFragment extends Fragment {
    /*****************Toast********************/
    protected void toastShort(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void toastLong(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

}
