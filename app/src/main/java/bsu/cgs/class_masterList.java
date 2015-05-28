package bsu.cgs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import bsu.cgs.Models.*;
import bsu.cgs.Models.Class;
import bsu.cgs.Models.Student;

public class class_masterList extends Fragment implements AbsListView.OnItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";


    private static View classMngView;


    private OnFragmentInteractionListener mListener;

    private static String cname;
    private static List<String> grades = new ArrayList<String>();

    private static List<String> content;
    private static  AbsListView mListView;

    private static ArrayAdapter mAdapter;

    public static class_masterList newInstance(String name,int sectionNumber) {
        class_masterList fragment = new class_masterList();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        cname = name;
        grades = new ArrayList<String>();
        return fragment;
    }

    public static class_masterList newInstance(String name,int sectionNumber, String grade) {
        class_masterList fragment = new class_masterList();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        cname = name;
        grades.add(grade);
        setGrade();
        return fragment;
    }



    public class_masterList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, getContent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         classMngView = inflater.inflate(R.layout.fragment_class_mng, container, false);

        // Set the adapter
        mListView = (AbsListView) classMngView.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return classMngView;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {

            mListener.onStudentGradeSelect(
                    mListView.getItemAtPosition(position).toString().split(":")[0]
                    ,getArguments().getInt(ARG_SECTION_NUMBER)
                    ,position);
        }
    }


    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public static void setGrade()
    {
        for(String grade : grades)
        {
            String[] parts = grade.split(":");
            String gr = parts[1];
            int pos  = Integer.parseInt(parts[0]);

            String newcon = content.get(pos).toString()+ ":"+gr;
            content.set(pos,newcon);
             mAdapter.notifyDataSetChanged();
            mListView = (AbsListView) classMngView.findViewById(R.id.critList);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }
    public List<String> getContent()
    {
        content = new ArrayList<String>();
        Class classes;
        classes = new Select()
                .from(Class.class)
                .where("cName = ?",cname)
                .orderBy("RANDOM()")
                .executeSingle();
        for (Student stud : classes.students) {
                content.add("(" + stud.studNum + ") " + stud.studName);
            }


        return content;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onStudentGradeSelect(String cName,int section,int position);
    }

}
