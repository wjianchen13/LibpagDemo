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
import com.example.libpagdemo.test4.TestActivity4;
import com.example.libpagdemo.test5.TestActivity5;

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

    /**
     * 加载插件基础使用
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TestActivity1.class));
    }

    /**
     * PAGFile设置到多个不同的PAGView
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, TestActivity2.class));
    }

    /**
     * load bytes 每次加载不同的PAGFile对象
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, TestActivity3.class));
    }

    /**
     * asFile 重新解析成byte
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, TestActivity4.class));
    }

    /**
     * PAGFile.LoadAsync 异步解析
     * @param v
     */
    public void onTest5(View v) {
        startActivity(new Intent(this, TestActivity5.class));
    }

}