package cz.swdt.android.image_progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import cz.swdt.android.libs.image_progressbar.ImageProgressBar;

public class MyActivity extends Activity {
    private Handler mHandler = new Handler();
    private ImageProgressBar progressBar;
    private int mProgressStatus;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        progressBar = (ImageProgressBar) findViewById(R.id.progress);
        progressBar.setDrawable(getResources().getDrawable(R.drawable.ic_launcher));

        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();
    }
}
