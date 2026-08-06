package com.example.libpagdemo.test2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
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
    private PAGView pagView2;
    private PAGView pagView3;
    private PAGFile mPagFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        pagView = findViewById(R.id.pag_view);
        pagView2 = findViewById(R.id.pag_view2);
        pagView3 = findViewById(R.id.pag_view3);
    }

    public void onTest1(View v) {
        CustomTarget<PAGFile> target = new CustomTarget<PAGFile>() {
            @Override
            public void onResourceReady(@NonNull PAGFile pagFile, @Nullable Transition<? super PAGFile> transition) {
                testEditText("replacement test11", pagFile, pagView);
                mPagFile = pagFile;
                pagView.setComposition(pagFile);
                pagView.setRepeatCount(0);
                pagView.play();
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                if (pagView != null) {
                    pagView.stop();
                    pagView.setComposition(null);
                }
            }
        };

        Object tag = v.getTag();
        if (tag instanceof CustomTarget) {
            Glide.with(this).clear((CustomTarget<?>) tag);
        }

        Glide.with(this)
                .as(PAGFile.class)
                .load(mPagUrl)
                .into(target);
        v.setTag(target);
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest2(View v) {
//        CustomTarget<PAGFile> target = new CustomTarget<PAGFile>() {
//            @Override
//            public void onResourceReady(@NonNull PAGFile pagFile, @Nullable Transition<? super PAGFile> transition) {
//                testEditText("replacement test22", pagFile, pagView2);
//                pagView2.setComposition(pagFile);
//                pagView2.setRepeatCount(0);
//                pagView2.play();
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//                if (pagView2 != null) {
//                    pagView2.stop();
//                    pagView2.setComposition(null);
//                }
//            }
//        };
//
//        Object tag = v.getTag();
//        if (tag instanceof CustomTarget) {
//            Glide.with(this).clear((CustomTarget<?>) tag);
//        }
//
//        Glide.with(this)
//                .as(PAGFile.class)
//                .load(mPagUrl)
//                .into(target);
//        v.setTag(target);
//        mPagFile = pagFile;
        pagView2.setComposition(mPagFile);
        pagView2.setRepeatCount(0);
        pagView2.play();
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
        CustomTarget<PAGFile> target = new CustomTarget<PAGFile>() {
            @Override
            public void onResourceReady(@NonNull PAGFile pagFile, @Nullable Transition<? super PAGFile> transition) {
                testEditText("replacement test33", pagFile, pagView3);
                pagView3.setComposition(pagFile);
                pagView3.setRepeatCount(0);
                pagView3.play();
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                if (pagView3 != null) {
                    pagView3.stop();
                    pagView3.setComposition(null);
                }
            }
        };

        Object tag = v.getTag();
        if (tag instanceof CustomTarget) {
            Glide.with(this).clear((CustomTarget<?>) tag);
        }

        Glide.with(this)
                .as(PAGFile.class)
                .load(mPagUrl)
                .into(target);
        v.setTag(target);
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
