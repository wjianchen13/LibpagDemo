package com.example.libpagdemo.pag_loader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

import java.nio.ByteBuffer;

public class PAGBytesBufferDecoder3 implements ResourceDecoder<ByteBuffer, byte[]> {

    private static final byte[] PAG_MAGIC = {0x50, 0x41, 0x47};

    @Override
    public boolean handles(@NonNull ByteBuffer source, @NonNull Options options) {
        if (source.remaining() < PAG_MAGIC.length) {
            return false;
        }
        int position = source.position();
        for (byte magicByte : PAG_MAGIC) {
            if (source.get() != magicByte) {
                source.position(position);
                return false;
            }
        }
        source.position(position);
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
