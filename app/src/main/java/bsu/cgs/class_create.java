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


public class class_create extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String ARG_SECTION_NUMBER = "section_number";


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
        lblSubjName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSelectOption(getArguments().getInt(ARG_SECTION_NUMBER),0);
            }
        });
        lblStudCount= (TextView) classCreateView.findViewById(R.id.lblStudCount);
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
    {}

    public interface OnFragmentInteractionListener {

        public void onSelectOption(int section , int type);
    }

}
