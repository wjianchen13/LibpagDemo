package com.example.libpagdemo.test3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

import java.nio.ByteBuffer;

public class  PAGBytesBufferDecoder3 implements ResourceDecoder<ByteBuffer, byte[]> {

    @Override
    public boolean handles(@NonNull ByteBuffer source, @NonNull Options options) {
        return true;
    }

    @Nullable
    @Override
    public Resource<byte[]> decode(@NonNull ByteBuffer source, int width, int height, @NonNull Options options) {
        ByteBuffer buffer = source.asReadOnlyBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new PAGBytesResource3(bytes);
    }
}
