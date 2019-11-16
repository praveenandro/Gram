package thangam.photostudio;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button login;
    private EditText username, password;
TextView t1;
    private CheckBox showpass;
    private String get_username, get_passwor;
    private SQLiteDatabase sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addressing();
t1=(TextView)findViewById(R.id.logt1);

        Typeface tf =  Typeface.createFromAsset(Login.this.getAssets(),"fff.ttf");
        t1.setTypeface(tf);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                get_username = username.getText().toString();
                get_passwor = password.getText().toString();
                if ((get_username.length() > 0) & (password.length() > 0)) {
                    if (veryfying()) {
                        Intent next = new Intent(Login.this,
                                Home.class);
                        startActivity(next);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Username or password is not valid",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Enter username and password", Toast.LENGTH_SHORT)
                            .show();
                }

            }

        });
        showpass.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                }
            }
        });



    }

    protected void addressing() {
        // TODO Auto-generated method stub
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.loge1);
        password = (EditText) findViewById(R.id.loge2);

        showpass = (CheckBox) findViewById(R.id.show_pass);
        sq = openOrCreateDatabase("user", MODE_PRIVATE, null);
    }

    protected boolean veryfying() {
        // TODO Auto-generated method stub
        Cursor cr = sq.rawQuery(
                "select password from userdetail where name='"
                        + get_username + "';", null);
        String checking_pass = null;
        if ((cr != null) & (cr.moveToFirst())) {
            checking_pass = cr.getString(0);
            return (checking_pass.equals(get_passwor));
        }
        return false;

    }





    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        username.setText("");
        password.setText("");
    }
}

