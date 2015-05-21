package bsu.cgs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.query.Select;

import bsu.cgs.Models.User;


public class Signup extends Activity {

    EditText supEdit;
    EditText supPw;
    EditText supCPw;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        alertDialog = new AlertDialog.Builder(this).create();

        supPw = (EditText)findViewById(R.id.usrPw);
        supCPw = (EditText)findViewById(R.id.usrPwCf);
        supEdit = (EditText)findViewById(R.id.usrNme);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean validateInput()
    {
        //checks if username is not yet taken

         User user;
        user = new Select()
               .from(User.class)
               .where("username= ?", supEdit.getText())
               .orderBy("RANDOM()")
               .executeSingle();
        if(user!=null)
            return false;
        else
            return true;

    }

    public void sendMessage(View view)
    {
        if(validateInput())
        {
            if(supPw.getText().toString().contentEquals(supCPw.getText().toString())) {
                User user =  new User();
                user.uname = supEdit.getText().toString();
                user.pword = supPw.getText().toString();
                user.save();
                Intent intent = new Intent(this, Signin.class);
                startActivity(intent);
            }
            else
            {
                alertDialog.setTitle("Password mismatch");
                alertDialog.setMessage("Password field and Confirm password field does not match");

                alertDialog.show();

            }

        }
        else
        {
            alertDialog.setTitle("Account already exists");
            alertDialog.setMessage("Said username is already in use." +
                    "please change or go back to Sign in");

            alertDialog.show();

        }
    }

}
