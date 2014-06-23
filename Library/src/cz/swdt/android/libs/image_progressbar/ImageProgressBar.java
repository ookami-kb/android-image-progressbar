package cz.swdt.android.libs.image_progressbar;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by ookami.kb on 23.06.14.
 * mailto:ookami.kb@gmail.com
 */
public class ImageProgressBar extends RelativeLayout {
    private ImageView grayImage, colorImage;
    int progress;
    int initialHeight;

    public ImageProgressBar(Context context) {
        this(context, null);
    }

    public ImageProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_progress_bar, this, true);

        grayImage = (ImageView) findViewById(R.id.gray);
        colorImage = (ImageView) findViewById(R.id.color);
    }

    public void setDrawable(Drawable d) {
        colorImage.setImageDrawable(d);
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        Drawable gray = d.getConstantState().newDrawable().mutate();
        gray.setColorFilter(f);
        grayImage.setImageDrawable(gray);
        initialHeight = gray.getIntrinsicHeight();
    }

    public void setProgress(int progress) {
        if (progress > 100) progress = 100;
        else if (progress < 0) progress = 0;
        this.progress = progress;
        if (grayImage.getLayoutParams() != null) {
            grayImage.getLayoutParams().height = initialHeight - (initialHeight * progress / 100);
            grayImage.requestLayout();
        }
    }
}
