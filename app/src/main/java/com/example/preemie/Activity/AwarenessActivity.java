package com.example.preemie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import com.example.preemie.R;
public class AwarenessActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        startActivity(new Intent(AwarenessActivity.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.awareness_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
