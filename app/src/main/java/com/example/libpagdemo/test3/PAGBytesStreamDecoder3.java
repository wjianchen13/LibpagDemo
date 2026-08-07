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

    private static final byte[] PAG_MAGIC = {0x50, 0x41, 0x47};

    @Override
    public boolean handles(@NonNull InputStream source, @NonNull Options options) throws IOException {
        if (!source.markSupported()) {
            return false;
        }
        source.mark(PAG_MAGIC.length);
        byte[] header = new byte[PAG_MAGIC.length];
        int read = source.read(header);
        source.reset();
        if (read < PAG_MAGIC.length) {
            return false;
        }
        for (int i = 0; i < PAG_MAGIC.length; i++) {
            if (header[i] != PAG_MAGIC[i]) {
                return false;
            }
        }
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
