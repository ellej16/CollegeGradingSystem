package bsu.cgs;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bsu.cgs.Models.*;
import bsu.cgs.Models.Student;

import com.activeandroid.query.Select;


public class student_create extends Fragment {



    private static final String ARG_SECTION_NUMBER = "section_number";

    EditText studNum;
    EditText studName;
    Button btnSave;
    Toast toast;
    public static student_create newInstance(int sectionNumber)
    {
        student_create fragment = new student_create();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        return fragment;
    }

    public student_create() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        //TODO : remove all empty if statements , unused imports and etc.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View createStudView =inflater.inflate(R.layout.fragment_student_create, container, false);

        studName = (EditText) createStudView.findViewById(R.id.tbStudName);
        studNum = (EditText) createStudView.findViewById(R.id.tbSnum);
        btnSave = (Button) createStudView.findViewById(R.id.btnStudSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        return createStudView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void save()
    {
        Student stud;
        if(studName.getText().length()==0 || studNum.getText().length() ==0 )
        {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "No fields should be empty!";
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {

            stud = new Select()
                    .from(Student.class)
                    .where("snum=?", studNum.getText())
                    .orderBy("RANDOM()")
                    .executeSingle();
            if (stud != null) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Subject code already exists!";
                int duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
            } else
            {
                Student newStud = new Student();
                newStud.studName = studName.getText().toString();
                newStud.studNum = studNum.getText().toString();
                newStud.save();
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Student added!";
                studNum.setText("");
                studName.setText("");
                int duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }
    }


}
