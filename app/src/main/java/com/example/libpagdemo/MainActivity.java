package com.example.libpagdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.libpagdemo.databinding.ActivityMainBinding;
import com.example.libpagdemo.test1.TestActivity1;
import com.example.libpagdemo.test2.TestActivity2;
import com.example.libpagdemo.test3.TestActivity3;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onTest1(View v) {
        startActivity(new Intent(this, TestActivity1.class));
    }

    public void onTest2(View v) {
        startActivity(new Intent(this, TestActivity2.class));
    }

    public void onTest3(View v) {
        startActivity(new Intent(this, TestActivity3.class));
    }

}