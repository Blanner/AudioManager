package bll.scg.de.audiomanager.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Simon C. Gorissen on 31.08.2016.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener
{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        selectItem(position);
    }

    private void selectItem(int position)
    {
        //TODO link to corresponding activity
    }
}

