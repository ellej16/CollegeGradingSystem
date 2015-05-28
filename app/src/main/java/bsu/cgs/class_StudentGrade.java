package bsu.cgs;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link class_StudentGrade.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link class_StudentGrade#newInstance} factory method to
 * create an instance of this fragment.
 */
public class class_StudentGrade extends Fragment {


    private static String clasName;
    private static int pos;

    private OnFragmentInteractionListener mListener;

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static class_StudentGrade newInstance(String cname,int section,int position) {
        class_StudentGrade fragment = new class_StudentGrade();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER,section);
        clasName = cname;
        pos = position;
        fragment.setArguments(args);
        return fragment;
    }

    public class_StudentGrade() {
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
        View grdSaveView = inflater.inflate(R.layout.fragment_class_student_grade, container
                , false);
        createFields();
        return grdSaveView;
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

    public void createFields()
    {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onGradeSave(int section, String cname, String grade);
    }

}
