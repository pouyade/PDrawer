package ir.psoft.psoftdrawer.helper;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/**
 * Created by pouyadark on 2/20/19.
 */

public class ColorUtilies {
    public static Drawable changeColor(Drawable drawable, int Color){
        PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
        drawable.setColorFilter(Color,mMode);
        return drawable;
    }
}
