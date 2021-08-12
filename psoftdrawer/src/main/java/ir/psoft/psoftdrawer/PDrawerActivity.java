package ir.psoft.psoftdrawer;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.psoft.psoftactionbar.Ui.PActionbar;
import ir.psoft.psoftdrawer.actionbarrtlizer.ActionBarRtlizer;
import ir.psoft.psoftdrawer.actionbarrtlizer.RtlizeEverything;
import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;

public class PDrawerActivity extends AppCompatActivity {
    public FragmentDrawer drawerFragment;
//    private Toolbar mToolbar;
    public PActionbar mToolbar;
    private HashMap<Integer,PDrawerFragment> fragmentHashMap;
    private HashMap<Integer,NavDrawerItem> navDrawerItemHashMap;
    public NavDrawerItem currentNavDrawerItem;
    public PDrawerConfig config;
    private ActionBarRtlizer rtlizer;
    private NavigationDrawerAdapter adapter;
    private LinearLayout toolbarContainer;
    private View.OnClickListener drawermenuclicklistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_automenu_layout);
        CreateToolbar();
        CreateDrawerMenu();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(config.getIsRtl()){
            rtilize();
        }
        return true;
    }
    private void CreateToolbar() {
        toolbarContainer = findViewById(R.id.container_toolbar);
        mToolbar=new PActionbar(this);
        mToolbar.setTypeFace(config.getTypeFace());
        if(config.getToolbarColor()!=0){
            mToolbar.setBackgroundColor(config.getToolbarColor());
        }
        if(config.getIconColor()!=0){
            mToolbar.setTitleColor(config.getTitleColor());
            mToolbar.setSubTitleColor(config.getSubTitlecolor());
            mToolbar.setForeGroundColor(config.getIconColor());
        }
        mToolbar.setDrawerMenuRtl(config.getIsRtl());
        mToolbar.showDrawerMenuicon(true);
        drawermenuclicklistener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (drawerFragment.mDrawerLayout.isDrawerOpen(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT)) {
                    drawerFragment.mDrawerLayout.closeDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
                } else {
                    drawerFragment.mDrawerLayout.openDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
                }
            }
        };
        mToolbar.setOnIconClick(drawermenuclicklistener);
        toolbarContainer.addView(mToolbar);
    }

    private void CreateDrawerMenu() {
        View drawerview= findViewById(R.id.fragment_navigation_drawer);
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) drawerview.getLayoutParams();
        layoutParams.width = AndroidUtilities.dp(config.getDrawerWidthdp());
        layoutParams.gravity = config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT;
        drawerview.setLayoutParams(layoutParams);
        //fragments
        fragmentHashMap = new HashMap<>();
        navDrawerItemHashMap = new HashMap<>();
        adapter = new NavigationDrawerAdapter(this, getDrawerItems());
        adapter.setpDrawerConfig(config);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), null,config);
        drawerFragment.getLayout().setAdapter(adapter, new NavMenuItemClickInterface() {
            @Override
            public void onDrawerItemSelected(NavDrawerItem navDrawerItem) {
                handleDrawerItemSelected(navDrawerItem);
            }
        });



    }
    public void updateDrawerItems(){
        adapter.setData(getDrawerItems());
    }

    private void handleDrawerItemSelected(NavDrawerItem navDrawerItem) {
        if(currentNavDrawerItem!=null&&currentNavDrawerItem==navDrawerItem){
            drawerFragment.mDrawerLayout.closeDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
            return;
        }
        currentNavDrawerItem=navDrawerItem;
        setTitle(navDrawerItem.getTitle());
        if(!navDrawerItemHashMap.containsKey(navDrawerItem.getId())){
            navDrawerItemHashMap.put(navDrawerItem.getId(),navDrawerItem);
        }
        adapter.setSelected(navDrawerItem.getId());
        if(!config.isCustomControledNavClickes()){
            if(navDrawerItem.getFragment()!=null){
                if(fragmentHashMap.containsKey(navDrawerItem.getId())){
                    showFragment(fragmentHashMap.get(navDrawerItem.getId()));
                    drawerFragment.mDrawerLayout.closeDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
                    return;
                }else{
                    PDrawerFragment fragment= navDrawerItem.getFragment();
                    fragmentHashMap.put(navDrawerItem.getId(),fragment);
                    showFragment(fragment);
                    drawerFragment.mDrawerLayout.closeDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
                    return;
                }
            }
        }
        drawerFragment.mDrawerLayout.closeDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
        DrawerSelectedNavItem(navDrawerItem);
    }


    protected void DrawerSelectedNavItem(NavDrawerItem navDrawerItem) {

    }


    public void selectitem(int id){
        if(!navDrawerItemHashMap.containsKey(id)){
            List<NavDrawerItem> items = getDrawerItems();
            for(int i=0;i<items.size();i++){
                if(items.get(i).getId()==id){
                    navDrawerItemHashMap.put(items.get(i).getId(),items.get(i));
                    handleDrawerItemSelected(items.get(i));
                    return;
                }
            }
        }else{
            handleDrawerItemSelected(navDrawerItemHashMap.get(id));
        }
    }
    public void showFragment(PDrawerFragment fragment){
        mToolbar.reset();
        Bundle arg = customArguments();
        if(arg!=null){
            fragment.setArguments(arg);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
        if(fragment.getToolbarTitle()!=null){
            mToolbar.setTitle(fragment.getToolbarTitle());
        }
        fragment.createToolbar(mToolbar);
    }



    public void setConfig(PDrawerConfig config) {
        this.config=config;
    }

    public Bundle customArguments(){
        return null;
    }
    protected void rtilize() {
        rtlizer = new ActionBarRtlizer(this,"toolbar");
        ViewGroup actionBarView = rtlizer.getActionBarView();
        ViewGroup homeView = (ViewGroup)rtlizer.getHomeView();

        rtlizer.flipActionBarUpIconIfAvailable(homeView);
        RtlizeEverything.rtlize(actionBarView);
        RtlizeEverything.rtlize(homeView);
    }
    public void setTitle(String title){
        mToolbar.setTitle(title);
    }
    public List<NavDrawerItem> getDrawerItems() {
        return new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        if(drawerFragment.mDrawerLayout.isDrawerOpen(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT)){
            drawerFragment.mDrawerLayout.closeDrawer(config.getIsRtl()?Gravity.RIGHT:Gravity.LEFT);
            return;
        }
        super.onBackPressed();
    }
}
