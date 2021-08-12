package ir.psoft.psoftdrawer;


import androidx.fragment.app.Fragment;

import ir.psoft.psoftactionbar.Ui.PActionbar;

/**
 * Created by pouyadark on 5/2/19.
 */

public abstract class PDrawerFragment extends Fragment {
    public abstract String getToolbarTitle();
    public abstract void createToolbar(PActionbar toolbar);
}
