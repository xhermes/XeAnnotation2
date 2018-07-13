package xeno.xeannotation2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import xeno.xeannotation2.annotation.Find;
import xeno.xeannotation2.BaseActivity;

public class MainActivity extends BaseActivity {

    @Find()
    private TextView mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mTestView = (TextView) findViewById(R.id.test);
        if (mTestView != null)
            Log.i("xeno", mTestView.getText().toString());
        else
            Log.i("xeno", "error");
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }


}
