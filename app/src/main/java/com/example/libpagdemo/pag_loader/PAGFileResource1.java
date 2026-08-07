package com.example.libpagdemo.pag_loader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;

import org.libpag.PAGFile;

public class PAGFileResource1 implements Resource<PAGFile> {

    private PAGFile pagFile;
    private final int size;

    public PAGFileResource1(@NonNull PAGFile pagFile, int size) {
        this.pagFile = pagFile;
        this.size = size;
    }

    @NonNull
    @Override
    public Class<PAGFile> getResourceClass() {
        return PAGFile.class;
    }

    @NonNull
    @Override
    public PAGFile get() {
        if (pagFile == null)
            throw new IllegalStateException("Resource already recycled");
        return pagFile;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void recycle() {
        pagFile = null;
    }

}
