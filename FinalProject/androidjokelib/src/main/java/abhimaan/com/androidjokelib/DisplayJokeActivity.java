package abhimaan.com.androidjokelib;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJokeActivity extends Activity
{
    public static final String DISPLAY_TEXT = "display_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_joke);
            ((TextView) findViewById(R.id.text)).setText(getIntent().getStringExtra(DISPLAY_TEXT));
        }
}
