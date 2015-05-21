package bsu.cgs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.query.Select;

import bsu.cgs.Models.User;


public class Signin extends Activity {

    EditText tbUname;
    EditText tbPw;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        alertDialog = new AlertDialog.Builder(this).create();

        tbUname = (EditText) findViewById(R.id.usrNme);
        tbPw = (EditText) findViewById(R.id.usrPw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
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
    public boolean VerifyLogin()
    {
        User user;
        user = new Select()
                .from(User.class)
                .where("username= ?", tbUname.getText())
                .orderBy("RANDOM()")
                .executeSingle();
        if(user!=null)
            if(user.pword.contentEquals(tbPw.getText().toString()))
                return true;
            else
            {
                alertDialog.setTitle("Incorrect Password");
                alertDialog.setMessage("Your password does not match the one in the database. " +
                        "please enter again");
                return false;
            }
        else
        {
            alertDialog.setTitle("Account does not exist");
            alertDialog.setMessage("Login details does not currently exist. please head to Signup");
            return false;
        }

    }


    public void sendMsgSignIn(View view)
    {
        if(VerifyLogin())
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            alertDialog.show();
        }

    }

    public void sendMsgSignUp(View view)
    {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

}
