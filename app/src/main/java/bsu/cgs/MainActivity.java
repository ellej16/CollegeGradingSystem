package bsu.cgs;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bsu.cgs.Models.*;

//TODO fix titlebar
public class MainActivity extends ActionBarActivity
        implements
        Subject.OnFragmentInteractionListener,
        subject_create.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks
        {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private FragmentManager fragmentManager;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    //Subject Section
            public void onAddSubj(int section)
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,subject_create.newInstance(section))
                        .commit();
            }
            public void onEditSubj(int section)
            {

            }
            public void onSaveSubject(int section)
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,Subject.newInstance(section))
                        .commit();
            }
            //end of Subject Section
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        fragmentManager = getSupportFragmentManager();
        Fragment frag = null;
        switch(position)
        {
            case 0:
                frag = Class.newInstance(position);
                break;
            case 1:
                frag = Subject.newInstance(position);
                break;
            case 2:
                frag = Student.newInstance(position);
                break;
            case 3:
                //todo: user settings
                break;
            case 4:
                //todo: logout
                break;

        }
        fragmentManager.beginTransaction()
                .replace(R.id.container,frag)
                .commit();


    }

    public void onSectionAttached(int number) {
        String[] titles = getResources().getStringArray(R.array.NavDrawerParts);
        mTitle = "CGS: "+ titles[number];

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    // not used
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
            public static PlaceholderFragment newInstance(int sectionNumber) {
                PlaceholderFragment fragment = new PlaceholderFragment();
                Bundle args = new Bundle();
                args.putInt(ARG_SECTION_NUMBER, sectionNumber);

                fragment.setArguments(args);

                return fragment;
            }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
