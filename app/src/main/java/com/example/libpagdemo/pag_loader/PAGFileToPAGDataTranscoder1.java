package com.example.libpagdemo.pag_loader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

import org.libpag.PAGFile;

public class PAGFileToPAGDataTranscoder1 implements ResourceTranscoder<PAGFile, PAGData1> {

    @Nullable
    @Override
    public Resource<PAGData1> transcode(@NonNull Resource<PAGFile> toTranscode, @NonNull Options options) {
        return new PAGDataResource1(toTranscode);
    }
}
