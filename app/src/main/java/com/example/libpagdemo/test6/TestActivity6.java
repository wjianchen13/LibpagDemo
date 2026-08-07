package com.example.libpagdemo.test6;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.libpagdemo.R;

import org.libpag.PAGFile;
import org.libpag.PAGText;
import org.libpag.PAGView;

import java.io.File;

/**
 * 使用Glide 设置不同的key，达到内存缓存的效果
 */
public class TestActivity6 extends AppCompatActivity {

    private String mPagUrl = "https://files.applecompare.com/api/direct/kQNu9ptR";
    private PAGView pagView;
    private PAGView pagView2;
    private PAGView pagView3;
    private int requestId1;
    private int requestId2;
    private int requestId3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test6);
        pagView = findViewById(R.id.pag_view);
        pagView2 = findViewById(R.id.pag_view2);
        pagView3 = findViewById(R.id.pag_view3);
    }

    public void onTest1(View v) {
        requestId1++;
        loadPag(v, pagView, "replacement test11", requestId1, 1);
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest2(View v) {
        requestId2++;
        loadPag(v, pagView2, "replacement test22", requestId2, 2);
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
        requestId3++;
        loadPag(v, pagView3, "replacement test33", requestId3, 3);
    }

    private void loadPag(View triggerView, PAGView targetView, String text, int requestId, int targetIndex) {
        CustomTarget<File> target = new CustomTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                PAGFile.LoadAsync(file.getAbsolutePath(), pagFile -> {
                    runOnUiThread(() -> {
                        if (isFinishing() || isDestroyed()) {
                            return;
                        }
                        if (!isLatestRequest(requestId, targetIndex)) {
                            return;
                        }
                        if (pagFile == null) {
                            targetView.stop();
                            targetView.setComposition(null);
                            return;
                        }
                        testEditText(text, pagFile, targetView);
                        targetView.setComposition(pagFile);
                        targetView.setRepeatCount(0);
                        targetView.play();
                    });
                });
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                targetView.stop();
                targetView.setComposition(null);
            }
        };

        Object tag = triggerView.getTag();
        if (tag instanceof CustomTarget) {
            Glide.with(this).clear((CustomTarget<?>) tag);
        }

        Glide.with(this)
                .asFile()
                .load(mPagUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(target);
        triggerView.setTag(target);
    }

    private boolean isLatestRequest(int requestId, int targetIndex) {
        if (targetIndex == 1) {
            return requestId == requestId1;
        } else if (targetIndex == 2) {
            return requestId == requestId2;
        } else {
            return requestId == requestId3;
        }
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
