package com.example.libpagdemo.test3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PAGBytesStreamDecoder3 implements ResourceDecoder<InputStream, byte[]> {

    @Override
    public boolean handles(@NonNull InputStream source, @NonNull Options options) {
        return true;
    }

    @Nullable
    @Override
    public Resource<byte[]> decode(@NonNull InputStream source, int width, int height, @NonNull Options options) throws IOException {
        return new PAGBytesResource3(inputStreamToBytes(source));
    }

    private byte[] inputStreamToBytes(@NonNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] temp = new byte[8192];
        int len;
        while ((len = inputStream.read(temp)) != -1) {
            buffer.write(temp, 0, len);
        }
        return buffer.toByteArray();
    }
}
