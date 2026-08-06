package com.example.libpagdemo.test2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libpagdemo.R;

import org.libpag.PAGFile;
import org.libpag.PAGText;
import org.libpag.PAGView;

/**
 *
 */
public class TestActivity2 extends AppCompatActivity {

    private String mPagUrl = "https://files.applecompare.com/api/direct/kQNu9ptR";
    private PAGView pagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        pagView = findViewById(R.id.pag_view);
    }

    public void onTest1(View v) {
        PAGFile pagFile1 = PAGFile.Load(getAssets(), "pag_replacement_text.pag");
        testEditText(pagFile1, pagView);
        pagView.setComposition(pagFile1);
        pagView.setRepeatCount(0);
        pagView.play();
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest2(View v) {


    }

    /**
     * Test edit text.
     */
    void testEditText(PAGFile pagFile, PAGView pagView) {
        if (pagFile == null || pagView == null || pagFile.numTexts() <= 0) return;
        PAGText textData = pagFile.getTextData(0);
        textData.text = "replacement test11";
        pagFile.replaceText(0, textData);
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest3(View v) {

    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest4(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pagView != null) {
            pagView.stop();
            pagView.freeCache();
        }
    }
}
