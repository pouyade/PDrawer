package ir.psoft.psoftdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ir.psoft.psoftdrawer.helper.ColorUtilies;
import ir.psoft.psoftdrawer.helper.MD5Helper;
import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;


/**
 * Created by pouyadark on 11/15/18.
 */

public class NavMenuItem extends FrameLayout implements View.OnClickListener {
    private final PDrawerConfig config;
    NavDrawerItem navDrawerItem;
    private TextView txttitle;
    private ImageView imgicon;
    private NavMenuItemClickInterface onNavMenuitemclick;
    private TextView txtButton;

    public NavMenuItem( Context context,PDrawerConfig config) {
        super(context);
        this.config=config;
        init();
    }
    public void init(){
//        setBackground();
        setBackgroundColor(0xffffffff);
        setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,50));

         imgicon= new ImageView(getContext());
        addView(imgicon, LayoutHelper.createFrame(25,25,Gravity.CENTER_VERTICAL|(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT),10,5,10,5));

        txttitle = new TextView(getContext());
        txttitle.setTypeface(config.getTypeFace());
        txttitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        txttitle.setGravity(this.config.getIsRtl()?(Gravity.RIGHT|Gravity.CENTER_VERTICAL):(Gravity.LEFT|Gravity.CENTER_VERTICAL));

        txttitle.setTextColor(0xff000000);
        addView(txttitle, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT,Gravity.TOP|(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT),(config.getIsRtl()?10:45),5,(config.getIsRtl()?45:10),5));

        txtButton = new TextView(getContext());
        txtButton.setTypeface(config.getTypeFace());
        txtButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        txtButton.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        txtButton.setTextColor(0xffffffff);
        txtButton.setVisibility(GONE);
        txtButton.setPadding(AndroidUtilities.dp2,AndroidUtilities.dp1,AndroidUtilities.dp2,AndroidUtilities.dp1);
        addView(txtButton, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT,Gravity.CENTER_VERTICAL|(config.getIsRtl()?Gravity.LEFT:Gravity.RIGHT),10,5,10,5));


        View line = new View(getContext());
        line.setBackgroundColor(config.getDeviderColor());
//        addView(line, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,1,Gravity.BOTTOM));
        setOnClickListener(this);
    }

    public void setNavItem(final NavDrawerItem navDrawerItem) {
        this.navDrawerItem=navDrawerItem;
        if(navDrawerItem.getDrawble()==null){
            if(navDrawerItem.isCachDrawbleUrl()) {
                final String filepath = getContext().getApplicationContext().getFilesDir().getAbsolutePath();
                final String filename = MD5Helper.md5(navDrawerItem.getDrawbleUrl()) + ".png";
                if (new File(filepath + "/" + filename).exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(filepath + "/" + filename);
                    Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                    if ((!String.valueOf(navDrawerItem.getCustomiconcolor()).equals("-1")) && (config.getIconColor() != 0 || navDrawerItem.getCustomiconcolor() != 0)) {
                        drawable = ColorUtilies.changeColor(drawable, navDrawerItem.getCustomiconcolor() != 0 ? navDrawerItem.getCustomiconcolor() : config.getIconColor());
                    }
                    imgicon.setImageDrawable(drawable);
                } else {
                    Picasso.get().load(navDrawerItem.getDrawbleUrl()).transform(
                            navDrawerItem.getTransformation() != null ? navDrawerItem.getTransformation() : new NoTransformation()
                    ).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            saveBitmap(bitmap, filename, filepath);
                            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                            if ((!String.valueOf(navDrawerItem.getCustomiconcolor()).equals("-1")) && (config.getIconColor() != 0 || navDrawerItem.getCustomiconcolor() != 0)) {
                                drawable = ColorUtilies.changeColor(drawable, navDrawerItem.getCustomiconcolor() != 0 ? navDrawerItem.getCustomiconcolor() : config.getIconColor());
                            }
                            imgicon.setImageDrawable(drawable);

                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
                }
            }else{
                Picasso.get().load(navDrawerItem.getDrawbleUrl()).transform(
                        navDrawerItem.getTransformation() != null ? navDrawerItem.getTransformation() : new NoTransformation()
                ).into(imgicon);
            }
        }else {
            Drawable drawble = navDrawerItem.getDrawble();
            if (config.getIconColor() != 0 || navDrawerItem.getCustomiconcolor() != 0) {
                drawble = ColorUtilies.changeColor(drawble, navDrawerItem.getCustomiconcolor() != 0 ? navDrawerItem.getCustomiconcolor() : config.getIconColor());
            }
            imgicon.setImageDrawable(drawble);
        }
        txttitle.setText(navDrawerItem.getTitle());
        txtButton.setTag(navDrawerItem.getId());
        if(navDrawerItem.getCustombotton()!=null){
            txtButton.setVisibility(VISIBLE);
            txtButton.setTextColor(navDrawerItem.getCustombottontextcolor());
            txtButton.setText(navDrawerItem.getCustombotton());
            if(navDrawerItem.getCustombottonradiusdp()!=0){
                GradientDrawable gradientDrawable=new GradientDrawable();
                gradientDrawable.setColor(navDrawerItem.getCustombottonbackcolor());
                gradientDrawable.setCornerRadius(AndroidUtilities.dp(navDrawerItem.getCustombottonradiusdp()));
                txtButton.setBackgroundDrawable(gradientDrawable);
            }else{
                txtButton.setBackgroundColor(navDrawerItem.getCustombottonbackcolor());
            }
            txtButton.setOnClickListener(navDrawerItem.getCustombottonevent());
        }else{
            txtButton.setVisibility(GONE);
        }
    }

    private void saveBitmap(Bitmap bmp,String filename,String filepath) {
        try  {
            FileOutputStream out = new FileOutputStream(filepath + "/" + filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnNavMenuitemclick(NavMenuItemClickInterface onNavMenuitemclick) {
        this.onNavMenuitemclick = onNavMenuitemclick;
    }

    @Override
    public void onClick(View view) {
        if(onNavMenuitemclick!=null){
            this.onNavMenuitemclick.onDrawerItemSelected(navDrawerItem);
        }
    }
    public void setSelected(boolean selected){
        this.setBackgroundColor(selected?0x77E0E0E0:0xffffffff);
    }

    private class NoTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            return source;
        }

        @Override
        public String key() {
            return "normal";
        }
    }
}
