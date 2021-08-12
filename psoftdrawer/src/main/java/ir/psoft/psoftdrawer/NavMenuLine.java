package ir.psoft.psoftdrawer;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ir.psoft.psoftdrawer.helper.ColorUtilies;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;

/**
 * Created by pouyadark on 11/15/18.
 */

public class NavMenuLine extends View {

    public NavMenuLine( Context context) {
        super(context);
        init();
    }
    public void init(){
        setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,1));
        setBackgroundColor(0xbbd0d0d0);
    }
}
