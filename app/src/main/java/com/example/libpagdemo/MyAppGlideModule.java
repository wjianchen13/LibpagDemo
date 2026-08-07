package com.example.libpagdemo;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.libpagdemo.pag_loader.PAGData1;
import com.example.libpagdemo.pag_loader.PAGFileBufferDecoder1;
import com.example.libpagdemo.pag_loader.PAGFileStreamDecoder1;
import com.example.libpagdemo.pag_loader.PAGFileToPAGDataTranscoder1;
import com.example.libpagdemo.pag_loader.PAGBytesBufferDecoder3;
import com.example.libpagdemo.pag_loader.PAGBytesStreamDecoder3;

import org.libpag.PAGFile;

import java.io.InputStream;
import java.nio.ByteBuffer;

@GlideModule
public class MyAppGlideModule extends AppGlideModule {
  @Override
  public void registerComponents(Context context, Glide glide, Registry registry) {
    // test2
//    registry.prepend(String.class, ByteBuffer.class, new Base64ModelLoaderFactory());
//
//    // test4: 注册 PAGFile 解码器（ResourceDecoder 方式）：ByteBuffer -> PAGFile
//    registry.prepend(ByteBuffer.class, PAGFile.class, new PAGFileResourceDecoder());

//    // test5: 注册 PAG ModelLoader（ModelLoader 方式）：GlideUrl -> InputStream
//    registry.prepend(GlideUrl.class, InputStream.class, new PAGModelLoaderFactory());
//    // test5: 注册 PAGFile 解码器：InputStream -> PAGFile
//    registry.prepend(InputStream.class, PAGFile.class, new PAGFileStreamDecoder());

      // test6: 注册 PAGFile 解码器（InputStream 方式）：InputStream -> PAGFile
//    registry.prepend(InputStream.class, PAGFile.class, new PAGFileStreamDecoder2());

    // test9
//    // 注册 PAGFile 解码器（ResourceDecoder 方式）：ByteBuffer -> PAGFile
//    registry.prepend(ByteBuffer.class, PAGFile.class, new PAGFileResourceDecoder9());
//
//    // 注册 PAGFile 解码器：InputStream -> PAGFile
//    registry.prepend(InputStream.class, PAGFile.class, new PAGFileStreamDecoder9());
//
//    // 注册转码器：PAGFile → PagData
//    registry.register(PAGFile.class, PagData.class, new PAGFileToPagDataTranscoder9());

    registry.prepend(ByteBuffer.class, PAGFile.class, new PAGFileBufferDecoder1());
    registry.prepend(InputStream.class, PAGFile.class, new PAGFileStreamDecoder1());
    registry.register(PAGFile.class, PAGData1.class, new PAGFileToPAGDataTranscoder1());
    registry.prepend(ByteBuffer.class, byte[].class, new PAGBytesBufferDecoder3());
    registry.prepend(InputStream.class, byte[].class, new PAGBytesStreamDecoder3());

  }


}
