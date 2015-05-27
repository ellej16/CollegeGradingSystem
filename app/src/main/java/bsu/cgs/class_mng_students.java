package bsu.cgs;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import bsu.cgs.Models.*;
import bsu.cgs.Models.Student;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link class_mng_students.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link class_mng_students#newInstance} factory method to
 * create an instance of this fragment.
 */
public class class_mng_students extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static final String ARG_SECTION_NUMBER = "section_number";


    private ListView mListView;
    private Button saveStuds;
    private ListAdapter mListAdapter;



    public static class_mng_students newInstance(int sectionNumber)
    {
        class_mng_students fragment = new class_mng_students();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }


    public class_mng_students() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mngStudsView =inflater.inflate(R.layout.fragment_class_mng_students, container, false);
        mListView = (ListView) mngStudsView.findViewById(R.id.studentList);
        saveStuds = (Button) mngStudsView.findViewById(R.id.btnStSelSave);
        saveStuds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save();
            }
        });
        mListAdapter = new ArrayAdapter<String>( getActivity(),
                android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, getContent());

        mListView.setAdapter(mListAdapter);
        return mngStudsView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public List<String> getContent()
    {
        List<String> content = new ArrayList<String>();
        List<Student> studs;
        studs = new Select()
                .from(Student.class)
                .orderBy("sname ASC")
                .execute();
        for(Student stud : studs)
        {
            content.add(stud.studName+":"+stud.studNum);
        }
        return content;
    }

    public void save()
    {
        List<String> studs = new ArrayList<String>();
        SparseBooleanArray studList  = mListView.getCheckedItemPositions();
        for(int i = 0; i < mListView.getCount(); i++)
            if (studList.get(i))
                studs.add(mListView.getItemAtPosition(i).toString());

        mListener.onStudentsSelected(getArguments().getInt(ARG_SECTION_NUMBER),studs);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onStudentsSelected(int section,List<String> studs);
    }

}
