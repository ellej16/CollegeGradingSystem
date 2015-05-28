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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import bsu.cgs.Models.*;

//TODO fix titlebar
public class MainActivity extends ActionBarActivity
        implements
        Subject.OnFragmentInteractionListener,
        subject_create.OnFragmentInteractionListener,
        Student.OnFragmentInteractionListener,
        Class.OnFragmentInteractionListener,
        class_create.OnFragmentInteractionListener,
        class_subject_select.OnFragmentInteractionListener,
        class_mng_students.OnFragmentInteractionListener,
        class_manage_criteria.OnFragmentInteractionListener,
        class_mng.OnFragmentInteractionListener,
        class_masterList.OnFragmentInteractionListener,
        class_StudentGrade.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks
        {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private FragmentManager fragmentManager;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    //class section
            public void onGradeSave(int section, String cname, String grade)
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,class_masterList.newInstance(cname,section,grade))
                        .commit();
            }
            public void onStudentGradeSelect(String cname,int section,int position)
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,class_StudentGrade
                                .newInstance(cname,section,position))
                        .commit();
            }
            public void onClassSelect(String cname,int section)
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,class_masterList.newInstance(cname,section))
                        .commit();
            }
            public void onSaveCriteria(int section,List<String> criteria)
            {
                List<Criterion> crits = new ArrayList<Criterion>();
                if(criteria.isEmpty())
                {
                    Context context = getApplicationContext();
                    String text = "No criteria saved!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container,class_create.newInstance(section,crits,0))
                            .commit();
                }
                else
                {
                    for(String crit : criteria)
                    {
                        String[] parts = crit.split(":");
                        String text = crit+" "+parts[1]+" "+parts[2];
                        int duration = Toast.LENGTH_SHORT;

                        Criterion newCrit = new Criterion();
                        newCrit.CriterName = parts[0];
                        newCrit.percentage = Double.parseDouble(parts[1]);
                        newCrit.items = Integer.parseInt(parts[2]);
                        newCrit.save();
                        crits.add(newCrit);
                    }
                    Context context = getApplicationContext();
                    String text = "Criteria saved!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container,class_create.newInstance(section,crits,1))
                            .commit();

                }
            }

            public void onStudentsSelected(int section,List<String> students) //@class_mng_studs
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,class_create.newInstance(section,students))
                        .commit();
            }
            public void onSelectSubj(String subjcode , int section)
            {

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,class_create.newInstance(section,subjcode))
                        .commit();
            }
            public void onBtnClick(int section, int type)
            {
                Fragment frag = null;
                switch(type)
                {
                    case 0:
                        frag = class_create.newInstance(section);
                        break;
                    case 1:
                        frag = class_mng.newInstance(section);
                        break;
                    case 2:
                        //frag = class_edit.newInstance(section);
                        break;

                }
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,frag)
                        .commit();
            }
            public void onSelectOption(int section, int type)
            {
                Fragment frag = null;
                switch(type)
                {
                    case 0:
                        frag = class_subject_select.newInstance(section);
                        break;
                    case 1:
                        frag = class_mng_students.newInstance(section);
                        break;
                    case 2:
                        frag = class_manage_criteria.newInstance(section);
                        break;
                }
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,frag)
                        .commit();
            }


    //end of class section
    //Student section
            public void onAddNewStuds(int section)
            {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,student_create.newInstance(section))
                        .commit();

            }
    //End of student section
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
