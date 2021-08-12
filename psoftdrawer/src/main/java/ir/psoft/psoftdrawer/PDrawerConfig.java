package ir.psoft.psoftdrawer;

import android.graphics.Typeface;
import android.view.View;

import java.lang.reflect.Type;

/**
 * Created by pouyadark on 2/20/19.
 */

public class PDrawerConfig {
    private boolean isRtl = false;
    private boolean DrawerEnabled = true;
    private int DrawerWidthdp= 300;
    private int toolbarColor=0;
    private int icon_colors=0;
    private int title_color=0xffffffff;
    private int sub_title_color=0x99ffffff;
    private View drawerHeaderView = null;
    private boolean CustomControledNavClickes = false;
    private int deviderColor = 0xbbd0d0d0;
    private Typeface typeface;

    public PDrawerConfig() {
        this.typeface = Typeface.DEFAULT;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public int getTitleColor() {
        return title_color;
    }

    public void setTitleColor(int title_color) {
        this.title_color = title_color;
    }

    public int getSubTitlecolor() {
        return sub_title_color;
    }

    public void setSubTitleColor(int sub_title_color) {
        this.sub_title_color = sub_title_color;
    }

    public PDrawerConfig setToolbarColor(int toolbarColor) {
        this.toolbarColor = toolbarColor;
        return this;
    }

    public void setDeviderColor(int deviderColor) {
        this.deviderColor = deviderColor;
    }

    public View getDrawerHeaderView() {
        return drawerHeaderView;
    }

    public PDrawerConfig setDrawerHeaderView(View drawerHeaderView) {
        this.drawerHeaderView = drawerHeaderView;
        return this;
    }

    public boolean isCustomControledNavClickes() {
        return CustomControledNavClickes;
    }

    public void setCustomControledNavClickes(boolean customControledNavClickes) {
        CustomControledNavClickes = customControledNavClickes;
    }

    public int getToolbarColor() {
        return toolbarColor;
    }

    public int getDrawerWidthdp() {
        return DrawerWidthdp;
    }

    public boolean getIsRtl() {
        return isRtl;
    }

    public PDrawerConfig isRtl(boolean b) {
        isRtl=b;
        return this;
    }

    public PDrawerConfig setIconColors(int colors) {
        this.icon_colors=colors;
        return this;
    }

    public int getIconColor() {
        return icon_colors;
    }

    public int getDeviderColor() {
        return deviderColor;
    }

    public Typeface getTypeFace() {
        return this.typeface;
    }
}
