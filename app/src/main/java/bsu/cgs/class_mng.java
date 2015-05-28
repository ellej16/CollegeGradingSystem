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
import android.widget.TextView;


import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import bsu.cgs.Models.*;
import bsu.cgs.Models.Class;

public class class_mng extends Fragment implements AbsListView.OnItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";




    private OnFragmentInteractionListener mListener;


    private AbsListView mListView;

    private ListAdapter mAdapter;

    public static class_mng newInstance(int sectionNumber) {
        class_mng fragment = new class_mng();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public class_mng() {
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
        View classMngView = inflater.inflate(R.layout.fragment_class_mng, container, false);

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

            mListener.onClassSelect(mListView.getItemAtPosition(position).toString().split(":")[0]
                    ,getArguments()
                    .getInt(ARG_SECTION_NUMBER));
        }
    }


    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public List<String> getContent()
    {
        List<String> content = new ArrayList<String>();

        List<Class> classes;
        classes = new Select()
                .from(Class.class)
                .orderBy("cName ASC")
                .execute();

        for(Class hue : classes)
        {
            content.add(hue.cName+":"+hue.cSubject.description);
        }
        return content;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onClassSelect(String cName,int section);
    }

}
