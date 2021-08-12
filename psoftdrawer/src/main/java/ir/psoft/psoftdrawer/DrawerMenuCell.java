package ir.psoft.psoftdrawer;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import ir.psoft.psoftlayoutlib.helper.LayoutHelper;


/**
 * Created by pouyadark on 11/16/18.
 */

public class DrawerMenuCell extends LinearLayout {
    private RecyclerView recyclerView;

    private NavMenuItemClickInterface drawerListener;
    private LinearLayout placeholder;
    private PDrawerConfig config;


    public void setAdapter(NavigationDrawerAdapter adapter,NavMenuItemClickInterface drawerListener) {
       recyclerView.setAdapter(adapter);
       this.drawerListener=drawerListener;
       adapter.setNavMenuItemListener(drawerListener);
    }



    public DrawerMenuCell( Context context) {
        super(context);
        init();
    }

    public void setConfig(PDrawerConfig config) {
        this.config = config;
        if(config.getDrawerHeaderView()!=null){
            placeholder.addView(config.getDrawerHeaderView());
        }
    }

    private void init() {
        setLayoutParams(LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
        setOrientation(VERTICAL);
        setBackgroundColor(0xffffffff);
        placeholder = new LinearLayout(getContext());
        addView(placeholder,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT, Gravity.TOP));
        recyclerView =new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addView(recyclerView,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT, Gravity.TOP));

    }


}
