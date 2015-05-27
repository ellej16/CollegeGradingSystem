package bsu.cgs;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import bsu.cgs.Models.*;
import bsu.cgs.Models.Class;
import bsu.cgs.Models.Student;
import bsu.cgs.Models.Subject;


public class class_create extends Fragment
{

    //todo: if selectStuds is clicked AGAIN it should retain selection
    //todo: if selectCrit is clicked AGAIN ti should retain selection

    private OnFragmentInteractionListener mListener;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SUBJ_CODE = "subject_code";

    //booleans for dynamic shit
    private static boolean subSelected = false;


    Subject subj = null; //implements subj
    ArrayList<Criterion>  criteria = null; //implements addcriteria

    ArrayList<Student> students = null; //implements addstudent
    private static List<String> selStuds = new ArrayList<String>();
    Button btnMngStuds;
    Button btnMngCrit;
    Button btnSave;

    TextView lblSubjCode;
    TextView lblSubjName;
    TextView lblStudCount;
    TextView lblCritCount;

    EditText tbCname;

    public static class_create newInstance(int sectionNumber) {
        class_create fragment = new class_create();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        subSelected = false;
        selStuds.clear();
        return fragment;
    }


    public static class_create newInstance(int sectionNumber , String subjcode) {
        class_create fragment = new class_create();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_SUBJ_CODE, subjcode);
        fragment.setArguments(args);
        subSelected = true;

        return fragment;
    }


    public static class_create newInstance(int sectionNumber , List<String> students) {
        class_create fragment = new class_create();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        selStuds = students;
        fragment.setArguments(args);

        return fragment;
    }




    public class_create() {
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
        View classCreateView =inflater.inflate(R.layout.fragment_class_create, container, false);

        btnMngStuds = (Button) classCreateView.findViewById(R.id.btnMngStuds);
        btnMngStuds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSelectOption(getArguments().getInt(ARG_SECTION_NUMBER),1);
            }
        });

        btnMngCrit = (Button) classCreateView.findViewById(R.id.btnMngCrit);
        btnMngCrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSelectOption(getArguments().getInt(ARG_SECTION_NUMBER),2);
            }
        });

        btnSave = (Button) classCreateView.findViewById(R.id.btnCcrtSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        lblSubjCode = (TextView) classCreateView.findViewById(R.id.lblSubjCode);
        lblSubjName= (TextView) classCreateView.findViewById(R.id.lblSelSubj);
        if(subSelected)
        {
            lblSubjCode.setText(getArguments().getString(ARG_SUBJ_CODE).split(":")[0]);
            lblSubjName.setText(getArguments().getString(ARG_SUBJ_CODE).split(":")[1]);
        }

        lblSubjName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSelectOption(getArguments().getInt(ARG_SECTION_NUMBER),0);
            }
        });
        lblStudCount= (TextView) classCreateView.findViewById(R.id.lblStudCount);
        if(!selStuds.isEmpty())
            lblStudCount.setText("There are currently "+selStuds.size() + "students in this class");

        else
            lblStudCount.setText("There are currently 0 students in this class");


        lblCritCount= (TextView) classCreateView.findViewById(R.id.lblCrit);
        tbCname = (EditText) classCreateView.findViewById(R.id.tbCname);




        return classCreateView;
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

    public void save()
    {/*
        if() //if requirements are not met
        {
            //TODO: communicating between fragments?
        }
        else //else go g
        {


            Class qclass;
            qclass = new Select()
                .from(Class.class)
                .where("cName=? AND cSubject.desc = ?",tbCname.getText(),lblSubjName.getText())
                .orderBy("RANDOM()")
                .executeSingle();
            if(qclass!=null)
            {

            }
            else
            {
                Class newClass = new Class();
                newClass.cName = tbCname.getText().toString();

                //todo:criteriaaaa
            }
        }
        */
    }

    public interface OnFragmentInteractionListener {

        public void onSelectOption(int section , int type);
    }

}
