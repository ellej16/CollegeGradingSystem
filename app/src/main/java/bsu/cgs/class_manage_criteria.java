package bsu.cgs;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bsu.cgs.Models.Criterion;


public class class_manage_criteria extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";

    private OnFragmentInteractionListener mListener;


    private ListView critListview;
    private ArrayAdapter critAdapter;

    public List<String> critList =  new ArrayList<String>();

    Button addCrit;
    Button remCrit;
    Button saveCrits;

    EditText tbCritName;
    EditText tbPercent;
    EditText tbRecs;

    View critManageView;

    Toast toast;
    CharSequence text;

    public static class_manage_criteria newInstance(int sectionNumber) {
        class_manage_criteria fragment = new class_manage_criteria();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    public class_manage_criteria() {
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
       critManageView = inflater.inflate(R.layout.fragment_class_manage_criteria,
                container, false);


        tbCritName = (EditText) critManageView.findViewById(R.id.tbCritName);
        tbPercent = (EditText) critManageView.findViewById(R.id.tbPercent);
        tbRecs = (EditText) critManageView.findViewById(R.id.tbRecCount);




        addCrit = (Button) critManageView.findViewById(R.id.btnAddCrit);
        addCrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dagdagCrit();
            }
        });
        remCrit = (Button) critManageView.findViewById(R.id.btnRemCrit);
        remCrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCrit();
            }
        });
        saveCrits = (Button) critManageView.findViewById(R.id.btnCritSave);
        saveCrits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        critListview = (ListView) critManageView.findViewById(R.id.critList);
        critListview.setAdapter(critAdapter);
        critAdapter = new ArrayAdapter<String>( getActivity()
        , android.R.layout.simple_list_item_multiple_choice,android.R.id.text1,critList);


        return critManageView;
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

   public void dagdagCrit()
   {
       if(tbRecs.getText().length()==0
               || tbPercent.getText().length()==0
               || tbCritName.getText().length()==0)
       {
           Context context = getActivity().getApplicationContext();
           text = "No fields should be empty!";
           int duration = Toast.LENGTH_SHORT;

           toast = Toast.makeText(context, text, duration);
           toast.show();
       }
       else if((Integer.parseInt(tbRecs.getText().toString()) <= 0 ||
               Double.parseDouble(tbPercent.getText().toString())<=0))
       {
           Context context = getActivity().getApplicationContext();
           text = "Number of records/Percentage should not be 0!";
           int duration = Toast.LENGTH_SHORT;

           toast = Toast.makeText(context, text, duration);
           toast.show();

       }
       else
       {

           critList.add(tbCritName.getText().toString()+
                   ":"+tbPercent.getText().toString()+":"+tbRecs.getText().toString());
           critAdapter.notifyDataSetChanged();
           critListview = (ListView) critManageView.findViewById(R.id.critList);
           critListview.setAdapter(critAdapter);
           critAdapter.notifyDataSetChanged();

           Context context = getActivity().getApplicationContext();

           text = "Criterion added!";
           int duration = Toast.LENGTH_SHORT;


           toast = Toast.makeText(context, text, duration);
           toast.show();


       }

   }

    public void removeCrit()
    {
        SparseBooleanArray studList  = critListview.getCheckedItemPositions();
        for(int i = 0; i < critListview.getCount(); i++)
            if (studList.get(i))
            {
                critList.remove(i);
            }
        critAdapter.notifyDataSetChanged();
        critListview = (ListView) critManageView.findViewById(R.id.critList);
        critListview.setAdapter(critAdapter);
        critAdapter.notifyDataSetChanged();


        Context context = getActivity().getApplicationContext();
        text = "Criteria removed!";
        int duration = Toast.LENGTH_SHORT;
        critAdapter.notifyDataSetChanged();
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void save()
    {

        mListener.onSaveCriteria(getArguments().getInt(ARG_SECTION_NUMBER),critList);
    }
    public interface OnFragmentInteractionListener {

        public void onSaveCriteria(int section, List<String> criteria);
    }

}
