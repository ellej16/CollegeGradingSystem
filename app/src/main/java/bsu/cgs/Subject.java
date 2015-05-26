package bsu.cgs;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Subject.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Subject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Subject extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_SECTION_NUMBER = "section_number";

    private int mParam;
    Button AddSub;

    private OnFragmentInteractionListener mListener;



    public static Subject newInstance(int sectionNumber) {
        Subject fragment = new Subject();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);

        return fragment;
    }

    public Subject() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_SECTION_NUMBER);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View subjectView = inflater.inflate(R.layout.fragment_subject, container, false);
        AddSub = (Button) subjectView.findViewById(R.id.btnAddSubj);
        AddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddSubj(getArguments().getInt(ARG_SECTION_NUMBER));
            }
        });
        return subjectView;
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
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }


    public void EditSubj()
    {
        mListener.onEditSubj(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onAddSubj(int sectNum);
        public void onEditSubj(int sectNum);
    }





}
