package org.peace.allinone.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/9/7.
 */
public class MyFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppLogger.e("frag1 oncreate");
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        AppLogger.e("frag1 create view");
        return inflater.inflate(R.layout.frag_my, container, false);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AppLogger.e("frag onSave");
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getView();
        menu.add(0, 1, 0, "Test");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(1).setEnabled(!menu.findItem(1).isEnabled());
    }

    @OnClick(R.id.start_btn) public void onClick(View v) {
        getActivity().invalidateOptionsMenu();
    }

    static int i = 1;

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.content, new MyFragment3())
                .addToBackStack("frag" + i++)
                .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
