package com.example.libpagdemo.test1;

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
import com.example.libpagdemo.pag_loader.PAGData1;
import com.example.libpagdemo.pag_loader.PAGDataViewTarget1;
import com.example.libpagdemo.pag_loader.PAGFileViewTarget1;

import org.libpag.PAGFile;
import org.libpag.PAGView;

/**
 * 加载插件基础使用
 */
public class TestActivity1 extends AppCompatActivity {

    private String mPagUrl = "https://pag.io/file/like.pag";
    private PAGView pagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        pagView = findViewById(R.id.pag_view);
    }

    public void onTest1(View v) {
        Glide.with(this)
                .as(PAGFile.class)
                .load(mPagUrl)
                .into(new PAGFileViewTarget1(pagView));
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest2(View v) {
        CustomTarget target = new CustomTarget<PAGFile>() {
            @Override
            public void onResourceReady(@NonNull PAGFile pagFile, @Nullable Transition<? super PAGFile> transition) {
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
        if(tag != null && tag instanceof CustomTarget) {
            Glide.with(this).clear((CustomTarget) tag);
        }

        Glide.with(this)
                .as(PAGFile.class)    // transcodeClass = PagData.class
                .load(mPagUrl)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)  // 加上这个
                .into(target);
        v.setTag(target);

    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest3(View v) {
        Glide.with(this)
                .as(PAGData1.class)
                .load(mPagUrl)
                .into(new PAGDataViewTarget1(pagView));
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest4(View v) {
        CustomTarget target = new CustomTarget<PAGData1>() {
            @Override
            public void onResourceReady(@NonNull PAGData1 pagData, @Nullable Transition<? super PAGData1> transition) {
                pagView.setComposition(pagData.pagFile);
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
        if(tag != null && tag instanceof CustomTarget) {
            Glide.with(this).clear((CustomTarget) tag);
        }

        Glide.with(this)
                .as(PAGData1.class)    // transcodeClass = PagData.class
                .load(mPagUrl)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)  // 加上这个
                .into(target);
        v.setTag(target);
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
