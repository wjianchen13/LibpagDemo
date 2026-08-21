package com.example.libpagdemo.test10;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.libpagdemo.R;
import com.example.libpagdemo.pag_loader.PAGFileViewTarget1;

import org.libpag.PAGFile;
import org.libpag.PAGText;
import org.libpag.PAGView;

/**
 * 加载assets目录下的文件
 */
public class TestActivity10 extends AppCompatActivity {

    private static final Option<String> PAG_MEMORY_KEY =
            Option.memory("com.example.libpagdemo.pag_memory_key");
    private static final Uri PAG_ASSET_URI = Uri.parse("file:///android_asset/pag_replacement_text.pag");

    private PAGView pagView;
    private PAGView pagView2;
    private PAGView pagView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test10);
        pagView = findViewById(R.id.pag_view);
        pagView2 = findViewById(R.id.pag_view2);
        pagView3 = findViewById(R.id.pag_view3);
    }

    public void onTest1(View v) {
        loadPag(v, pagView, "replacement test11", "pag_view_1");
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest2(View v) {
        loadPag(v, pagView2, "replacement test22", "pag_view_2");
    }

    /**
     * Test edit text.
     */
    void testEditText(String text, PAGFile pagFile, PAGView pagView) {
        if (pagFile == null || pagView == null || pagFile.numTexts() <= 0) return;
        PAGText textData = pagFile.getTextData(0);
        textData.text = text;
        pagFile.replaceText(0, textData);
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest3(View v) {
        loadPag(v, pagView3, "replacement test33", "pag_view_3");
    }

    private void loadPag(View triggerView, PAGView targetView, String text, String viewKey) {
        Glide.with(this)
                .as(PAGFile.class)
                .load(PAG_ASSET_URI)
                .apply(new RequestOptions()
                        .set(PAG_MEMORY_KEY, viewKey + ":" + text)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(new PAGFileViewTarget1(targetView, pagFile -> testEditText(text, pagFile, targetView)));
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
        if (pagView2 != null) {
            pagView2.stop();
            pagView2.freeCache();
        }
        if (pagView3 != null) {
            pagView3.stop();
            pagView3.freeCache();
        }
    }
}
