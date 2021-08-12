package ir.psoft.psoftdrawer;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private Context context;
    private int selected=-1;
    private PDrawerConfig pDrawerConfig;
    private NavMenuItemClickInterface navMenuItemListener;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> items) {
        this.context = context;
        this.data = items;
    }

    public PDrawerConfig getpDrawerConfig() {
        return pDrawerConfig;
    }

    public void setpDrawerConfig(PDrawerConfig pDrawerConfig) {
        this.pDrawerConfig = pDrawerConfig;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position )!= null) {
            return 0;
        }
        return 1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            NavMenuItem nav = new NavMenuItem(context,pDrawerConfig);
            nav.setOnNavMenuitemclick(navMenuItemListener);
            return new MyViewHolder(nav);
        }else if(viewType==1){
            return new MyViewHolder(new NavMenuLine(context));
        }
        return new MyViewHolder(new View(context)) ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(holder.getItemViewType()==0) {
            NavDrawerItem current = data.get(position);
            ((NavMenuItem) holder.itemView).setNavItem(current);
            ((NavMenuItem) holder.itemView).setSelected(selected==current.getId());
        }else if(holder.getItemViewType()==1){

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setNavMenuItemListener(NavMenuItemClickInterface navMenuItemListener) {
        this.navMenuItemListener = navMenuItemListener;
    }
    public void setData( List<NavDrawerItem> items){
        this.data=items;
        notifyDataSetChanged();
    }
    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}