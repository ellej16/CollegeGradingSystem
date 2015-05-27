package bsu.cgs;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Class extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String ARG_SECTION_NUMBER = "section_number";

    Button btnClsCreate;
    Button btnClsMng;
    Button btnClsEdit;
    public static Class newInstance(int sectionNumber) {
        Class fragment = new Class();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);

        return fragment;
    }
    public Class() {
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
        View classView =inflater.inflate(R.layout.fragment_class, container, false);
        btnClsCreate = (Button) classView.findViewById(R.id.btnClsCreate);
        btnClsMng = (Button) classView.findViewById(R.id.btnClsMng);
        btnClsEdit = (Button) classView.findViewById(R.id.btnClsEdit);

        btnClsMng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBtnClick(getArguments().getInt(ARG_SECTION_NUMBER),1);
            }
        });

        btnClsCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBtnClick(getArguments().getInt(ARG_SECTION_NUMBER),0);
            }
        });

        btnClsEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBtnClick(getArguments().getInt(ARG_SECTION_NUMBER),2);
            }
        });

        return classView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SubjectListener");
        }
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onBtnClick(int section,int type);
    }

}
