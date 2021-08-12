package ir.psoft.psoftdrawer;
/**
 * Created by Ravi on 29/07/15.
 */

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


public class FragmentDrawer extends Fragment {


//    private ActionBarDrawerToggle mDrawerToggle;
    public DrawerLayout mDrawerLayout;
    private PDrawerConfig pDrawerConfig;
    private DrawerMenuCell drawermenucell;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        drawermenucell = new DrawerMenuCell(inflater.getContext());
        return drawermenucell;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar, PDrawerConfig pDrawerConfig) {
        this.pDrawerConfig = pDrawerConfig;
        drawermenucell.setConfig(pDrawerConfig);
        mDrawerLayout = drawerLayout;
//        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//              //  getActivity().invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//              //  getActivity().invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
////                toolbar.setAlpha(1 - slideOffset / 2);
//            }
//        };
//        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mDrawerToggle.syncState();
//            }
//        });


    }

    public DrawerMenuCell getLayout() {
        return drawermenucell;
    }
}