package jumbotail.singularity.vegigate.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Objects;

import jumbotail.singularity.vegigate.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView incorrect;
    Animation animation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
//        startActivity(intent);
//        finish();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_phone);
        incorrect = (TextView) findViewById(R.id.login_incorrect);

        incorrect.setVisibility(View.GONE);

        username.setText("jumbotail");
        password.setText("sagatu2016");

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void login(View v)
    {
        if(Objects.equals(username.getText().toString().toUpperCase(), "JUMBOTAIL") && Objects.equals(password.getText().toString().toUpperCase(), "SAGATU2016"))
        {
            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            incorrect.setVisibility(View.VISIBLE);
            incorrect.startAnimation(animation);
        }

    }

}
