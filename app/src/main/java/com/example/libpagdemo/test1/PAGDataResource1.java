package com.example.libpagdemo.test1;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;

import org.libpag.PAGFile;

public class PAGDataResource1 implements Resource<PAGData1> {

    private final Resource<PAGFile> originalResource;
    private final PAGData1 pagData;

    public PAGDataResource1(Resource<PAGFile> originalResource) {
        this.originalResource = originalResource;
        this.pagData = new PAGData1();
        this.pagData.pagFile = originalResource.get();
    }

    @NonNull
    @Override
    public Class<PAGData1> getResourceClass() {
        return PAGData1.class;
    }

    @NonNull
    @Override
    public PAGData1 get() {
        return pagData;
    }

    @Override
    public int getSize() {
        return originalResource.getSize();
    }

    @Override
    public void recycle() {
        originalResource.recycle();
    }
}
