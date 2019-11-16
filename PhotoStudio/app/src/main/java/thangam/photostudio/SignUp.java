package thangam.photostudio;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {
    private EditText name,address, phone, email, pass, c_pass;
    private Button signup;
TextView t1;
    private SQLiteDatabase sq;
    private String g_email, g_pass, g_cpass, g_address, g_name, g_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Initial();
        t1=(TextView)findViewById(R.id.sit1);

        Typeface tf =  Typeface.createFromAsset(SignUp.this.getAssets(),"fff.ttf");
        t1.setTypeface(tf);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                getValues();
                validate();
                Intent i=new Intent(SignUp.this,Login.class);
                startActivity(i);
            }

        });

    }
    protected void validate() {
        if ((g_name.length() > 0) & (g_address.length() > 0)
                & (g_phone.length() > 0) & (g_email.length() > 0)
                & (g_cpass.length() > 0) & (g_pass.length() > 0)) {
            // TODO Auto-generated method stub

            if (!isValidName(g_name)) {
                name.setError("Please enter full name");
            }
            else if (!isValidPhone(g_phone)) {
                phone.setError("This not valid Phone number");
            }
             else if (!isValidPassword(g_pass)) {
                pass.setError("Password minimum length 6 digit");
            } else if (!g_pass.equals(g_cpass)) {
                c_pass.setError("Confirmation password is not match");
            }else {
                addDetails();
                Toast.makeText(getApplicationContext(),
                        "Sucess", Toast.LENGTH_SHORT).show();
                finish();
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    "You can't leave fields empty.", Toast.LENGTH_SHORT).show();
        }

    }

    private void addDetails() {
        // TODO Auto-generated method stub
        sq.execSQL("insert into userdetail values('"+g_name+"','"+g_address+"','"+g_phone+"','"+g_email+"','"+g_pass+"');");
    }

    protected void getValues() {
        // TODO Auto-generated method stub
        g_name = name.getText().toString();
        g_address =address.getText().toString();
        g_phone = phone.getText().toString();
        g_email = email.getText().toString();
        g_pass = pass.getText().toString();
        g_cpass = c_pass.getText().toString();
    }

    private void Initial() {
        // TODO Auto-generated method stub
        name = (EditText) findViewById(R.id.sige1);
        address = (EditText) findViewById(R.id.sige2);
        phone = (EditText) findViewById(R.id.sige3);
        signup = (Button) findViewById(R.id.signup_b);
        email = (EditText) findViewById(R.id.sige4);
        pass = (EditText) findViewById(R.id.sige5);
        c_pass = (EditText) findViewById(R.id.sige6);

        sq = openOrCreateDatabase("user", MODE_PRIVATE, null);
        sq.execSQL("create table if not exists userdetail(name text,address text,phone text,email text,password text);");

    }


    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    private boolean isValidName(String name) {
        if (name != null && name.length() >= 6) {
            return true;
        }
        return false;
    }

    private boolean isValidPhone(String phone) {
        if (phone != null && phone.length() >= 10) {
            return true;
        }
        return false;
    }



}
