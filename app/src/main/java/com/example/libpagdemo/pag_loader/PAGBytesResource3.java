package com.example.libpagdemo.pag_loader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;

public class PAGBytesResource3 implements Resource<byte[]> {

    private byte[] bytes;

    public PAGBytesResource3(@NonNull byte[] bytes) {
        this.bytes = bytes;
    }

    @NonNull
    @Override
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    @NonNull
    @Override
    public byte[] get() {
        if (bytes == null) {
            throw new IllegalStateException("Resource already recycled");
        }
        return bytes;
    }

    @Override
    public int getSize() {
        return bytes == null ? 0 : bytes.length;
    }

    @Override
    public void recycle() {
        bytes = null;
    }
}
