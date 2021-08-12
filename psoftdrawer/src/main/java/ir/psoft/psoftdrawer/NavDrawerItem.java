package ir.psoft.psoftdrawer;

import android.graphics.drawable.Drawable;

import android.view.View;

import com.squareup.picasso.Transformation;


/**
 * Created by Pouya on 12/15/2015.
 */
public class NavDrawerItem {
    private int id;
    private boolean showNotify;
    private String title;
    private boolean haveseperator;
    private Drawable drawble;
    private String notifyText;
    private PDrawerFragment fragment;
    private Transformation transformation;
    private int customiconcolor=0;
    private String drawbleUrl;
    private boolean cachDrawbleUrl=true;
    private String custombotton = null;
    private int custombottontextcolor = 0xffffffff;
    private int custombottonbackcolor = 0x00000000;
    private int custombottonradiusdp=0;
    private View.OnClickListener custombottonevent = null;

    public String getCustombotton() {
        return custombotton;
    }

    public int getCustombottontextcolor() {
        return custombottontextcolor;
    }

    public int getCustombottonbackcolor() {
        return custombottonbackcolor;
    }

    public int getCustombottonradiusdp() {
        return custombottonradiusdp;
    }

    public View.OnClickListener getCustombottonevent() {
        return custombottonevent;
    }

    public NavDrawerItem() {

    }

    public void setCustombotton(String custombotton,
                                int custombottontextcolor,
                                int custombottonbackcolor,
                                int custombottonradiusdp,
                                View.OnClickListener custombottonevent) {
        this.custombotton = custombotton;
        this.custombottontextcolor = custombottontextcolor;
        this.custombottonbackcolor = custombottonbackcolor;
        this.custombottonradiusdp = custombottonradiusdp;
        this.custombottonevent = custombottonevent;
    }

    public boolean isCachDrawbleUrl() {
        return cachDrawbleUrl;
    }

    public void setCachDrawbleUrl(boolean cachDrawbleUrl) {
        this.cachDrawbleUrl = cachDrawbleUrl;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }

    public void setCustomiconcolor(int customiconcolor) {
        this.customiconcolor = customiconcolor;
    }

    public String getDrawbleUrl() {
        return drawbleUrl;
    }


    public NavDrawerItem(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public NavDrawerItem(int id,String title, Drawable drawble) {
        this.id = id;
        this.title = title;
        this.drawble = drawble;

    }
    public NavDrawerItem(int id,String title, Drawable drawble,PDrawerFragment fragment,int customiconcolor) {
        this.id = id;
        this.fragment = fragment;
        this.title = title;
        this.drawble = drawble;
        this.customiconcolor=customiconcolor;
    }
    public NavDrawerItem(int id,String title, Drawable drawble,PDrawerFragment fragment) {
        this.id = id;
        this.fragment = fragment;
        this.title = title;
        this.drawble = drawble;
    }
    public NavDrawerItem(int id,String title, Drawable drawble,Boolean haveseperator) {
        this.id = id;
        this.title = title;
        this.haveseperator=haveseperator;
        this.drawble = drawble;

    }
    public NavDrawerItem(int id,String title, Drawable drawble,PDrawerFragment fragment,Boolean haveseperator,int customiconcolor) {
        this.id = id;
        this.fragment = fragment;
        this.customiconcolor = customiconcolor;
        this.title = title;
        this.haveseperator=haveseperator;
        this.drawble = drawble;
    }
    public NavDrawerItem(int id,String title, Drawable drawble,PDrawerFragment fragment,Boolean haveseperator) {
        this.id = id;
        this.fragment = fragment;
        this.title = title;
        this.haveseperator=haveseperator;
        this.drawble = drawble;
    }
    public NavDrawerItem(int id, String title, Drawable drawble, PDrawerFragment fragment, Boolean showNotify, String notifyText) {
        this.id = id;
        this.showNotify = showNotify;
        this.notifyText = notifyText;
        this.fragment = fragment;
        this.title = title;
        this.drawble = drawble;
    }
    public NavDrawerItem(int id, String title, Drawable drawble, Boolean showNotify, String notifyText) {
        this.id = id;
        this.showNotify = showNotify;
        this.notifyText = notifyText;
        this.title = title;
        this.drawble = drawble;
    }
    public NavDrawerItem(int id, String title, Drawable drawble, PDrawerFragment fragment, Boolean haveseperator, Boolean showNotify, String notifyText) {
        this.id = id;
        this.showNotify = showNotify;
        this.notifyText = notifyText;
        this.fragment = fragment;
        this.title = title;
        this.haveseperator=haveseperator;
        this.drawble = drawble;
    }

    public boolean isShowNotify() {
        return showNotify;
    }
    public boolean isHaveseperator(){return haveseperator;}
    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }
    public void setHaveseperator(boolean haveseperators){
        this.haveseperator=haveseperators;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFragment(PDrawerFragment fragment) {
        this.fragment = fragment;
    }

    public Drawable getDrawble() {
        return drawble;
    }
    public void setDrawble(Drawable s){
        this.drawble=s;
    }

    public PDrawerFragment getFragment() {
        return fragment;
    }

    public int getId() {
        return id;
    }

    public int getCustomiconcolor() {
        return customiconcolor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDrawbleUrl(String drawbleUrl) {
        this.drawbleUrl=drawbleUrl;
    }


}