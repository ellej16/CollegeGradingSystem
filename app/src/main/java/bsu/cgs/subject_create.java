package bsu.cgs;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;

import bsu.cgs.Models.*;
import bsu.cgs.Models.Subject;


public class subject_create extends Fragment {
    //TODO fix param shits
    private static final String ARG_SECTION_NUMBER = "section_number";

    private OnFragmentInteractionListener mListener;
    EditText tbSubCode;
    EditText tbSubDesc;
    Button btnSave;
    Toast toast;


    public static subject_create newInstance(int sectionNumber) {
        subject_create fragment = new subject_create();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    public subject_create() {
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
        View subCreateView = inflater.inflate(R.layout.fragment_subject_create, container, false);

        tbSubCode = (EditText) subCreateView.findViewById(R.id.tbSubjCode);
        tbSubDesc = (EditText) subCreateView.findViewById(R.id.tbSubjDesc);
        btnSave = (Button) subCreateView.findViewById(R.id.btnSubjSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
        return subCreateView;
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
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void Save()
    {
        Subject sub;
        if(tbSubCode.getText().length()==0 || tbSubDesc.getText().length() ==0 )
        {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "No fields should be empty!";
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {

            sub = new Select()
                    .from(Subject.class)
                    .where("subjcode=?", tbSubCode.getText())
                    .orderBy("RANDOM()")
                    .executeSingle();
            if (sub != null) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Subject code already exists!";
                int duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {

                Subject subject = new Subject();
                subject.description = tbSubDesc.getText().toString();
                subject.subjcode = tbSubCode.getText().toString();
                subject.save();
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Subject saved!";
                int duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                mListener.onSaveSubject(getArguments().getInt(ARG_SECTION_NUMBER));
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onSaveSubject(int section);
    }

}
