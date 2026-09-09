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
import com.example.libpagdemo.test10.TestActivity10;
import com.example.libpagdemo.test11.TestActivity11;
import com.example.libpagdemo.test2.TestActivity2;
import com.example.libpagdemo.test3.TestActivity3;
import com.example.libpagdemo.test4.TestActivity4;
import com.example.libpagdemo.test5.TestActivity5;
import com.example.libpagdemo.test6.TestActivity6;
import com.example.libpagdemo.test7.TestActivity7;
import com.example.libpagdemo.test8.TestActivity8;
import com.example.libpagdemo.test9.TestActivity9;

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

    /**
     * 使用Glide 设置不同的key，达到内存缓存的效果
     * @param v
     */
    public void onTest6(View v) {
        startActivity(new Intent(this, TestActivity6.class));
    }

    /**
     * 使用Glide 设置不同的key，达到磁盘缓存和内存缓存的效果
     * @param v
     */
    public void onTest7(View v) {
        startActivity(new Intent(this, TestActivity7.class));
    }

    /**
     * 使用PAGFileViewTarget1方式加载
     * @param v
     */
    public void onTest8(View v) {
        startActivity(new Intent(this, TestActivity8.class));
    }


    /**
     * 加载assets目录下的文件，替换文字
     * @param v
     */
    public void onTest9(View v) {
        startActivity(new Intent(this, TestActivity9.class));
    }

    /**
     * 加载assets目录下的文件，替换图片
     * @param v
     */
    public void onTest10(View v) {
        startActivity(new Intent(this, TestActivity10.class));
    }

    /**
     * 测试项目资源
     * @param v
     */
    public void onTest11(View v) {
        startActivity(new Intent(this, TestActivity11.class));
    }

}