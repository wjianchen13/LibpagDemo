package com.example.libpagdemo.test11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.libpagdemo.R;
import com.example.libpagdemo.pag_loader.PAGFileViewTarget1;

import org.libpag.PAGComposition;
import org.libpag.PAGFile;
import org.libpag.PAGImage;
import org.libpag.PAGImageLayer;
import org.libpag.PAGLayer;
import org.libpag.PAGText;
import org.libpag.PAGView;

/**
 * 加载assets目录下的文件
 */
public class TestActivity11 extends AppCompatActivity {

    private static final String TAG = "PAG_INFO";
    private static final Option<String> PAG_MEMORY_KEY =
            Option.memory("com.example.libpagdemo.pag_memory_key");
    private static final Uri PAG_ASSET_URI = Uri.parse("file:///android_asset/test_image.pag");

    private PAGView pagView;
    private PAGView pagView2;
    private PAGView pagView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
        pagView = findViewById(R.id.pag_view);
        pagView2 = findViewById(R.id.pag_view2);
        pagView3 = findViewById(R.id.pag_view3);
    }

    public void onTest1(View v) {
        loadPag(pagView, "pag_view_1", true);
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest2(View v) {
        loadPag(pagView2, "pag_view_2", false);
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest3(View v) {
        loadPag(pagView3, "pag_view_3", false);
    }

    private void loadPag(PAGView targetView, String viewKey, boolean printInfo) {
        Glide.with(this)
                .as(PAGFile.class)
                .load(PAG_ASSET_URI)
                .apply(new RequestOptions()
                        .set(PAG_MEMORY_KEY, viewKey + ":ic_head_test.png")
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(new PAGFileViewTarget1(targetView, pagFile -> {
                    if (printInfo) {
                        printPagEditableInfo(pagFile);
                    }
                    replaceSourcePhotoImage(pagFile);
                    replaceTargetNameTextImage(pagFile, "hello");
                }));
    }

    private void printPagEditableInfo(PAGFile pagFile) {
        if (pagFile == null) {
            Log.d(TAG, "PAGFile is null");
            return;
        }

        Log.d(TAG, "file path=" + pagFile.path()
                + ", width=" + pagFile.width()
                + ", height=" + pagFile.height()
                + ", numTexts=" + pagFile.numTexts()
                + ", numImages=" + pagFile.numImages()
                + ", numChildren=" + pagFile.numChildren());

        for (int i = 0; i < pagFile.numTexts(); i++) {
            PAGText text = pagFile.getTextData(i);
            Log.d(TAG, "editable text index=" + i
                    + ", text=" + text.text
                    + ", font=" + text.fontFamily
                    + ", size=" + text.fontSize);
        }

        for (int i = 0; i < pagFile.numImages(); i++) {
            Log.d(TAG, "editable image index=" + i);
        }

        printLayers(pagFile, "");
    }

    private void printLayers(PAGComposition composition, String indent) {
        for (int i = 0; i < composition.numChildren(); i++) {
            PAGLayer layer = composition.getLayerAt(i);
            Log.d(TAG, indent
                    + "layer[" + i + "]"
                    + ", name=" + layer.layerName()
                    + ", type=" + layerTypeToString(layer.layerType())
                    + ", editableIndex=" + layer.editableIndex()
                    + ", bounds=" + layer.getBounds()
                    + ", visible=" + layer.visible());

            if (layer instanceof PAGComposition) {
                printLayers((PAGComposition) layer, indent + "  ");
            }
        }
    }

    private String layerTypeToString(int layerType) {
        if (layerType == PAGLayer.LayerTypeText) {
            return "Text";
        } else if (layerType == PAGLayer.LayerTypeImage) {
            return "Image";
        } else if (layerType == PAGLayer.LayerTypePreCompose) {
            return "PreCompose";
        } else if (layerType == PAGLayer.LayerTypeShape) {
            return "Shape";
        } else if (layerType == PAGLayer.LayerTypeSolid) {
            return "Solid";
        } else if (layerType == PAGLayer.LayerTypeNull) {
            return "Null";
        } else {
            return "Unknown(" + layerType + ")";
        }
    }

    private void replaceSourcePhotoImage(PAGFile pagFile) {
        if (pagFile == null) {
            return;
        }
        PAGImage image = createHeadPAGImage();
        if (image == null) {
            Log.d(TAG, "createHeadPAGImage failed");
            return;
        }
        int count = replaceImageLayerByName(pagFile, "sourcePhoto", image);
        Log.d(TAG, "replace sourcePhoto image layer count=" + count);
    }

    private int replaceImageLayerByName(PAGComposition composition, String layerName, PAGImage image) {
        int count = 0;
        for (int i = 0; i < composition.numChildren(); i++) {
            PAGLayer layer = composition.getLayerAt(i);
            if (layer.layerType() == PAGLayer.LayerTypeImage
                    && layerName.equals(layer.layerName())
                    && layer instanceof PAGImageLayer) {
                ((PAGImageLayer) layer).setImage(image);
                count++;
            }

            if (layer instanceof PAGComposition) {
                count += replaceImageLayerByName((PAGComposition) layer, layerName, image);
            }
        }
        return count;
    }

    private PAGImage createHeadPAGImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_head_test);
        if (bitmap == null) {
            return null;
        }
        return PAGImage.FromBitmap(bitmap);
    }

    private void replaceTargetNameTextImage(PAGFile pagFile, String text) {
        if (pagFile == null) {
            return;
        }
        int count = replaceTextImageLayerByName(pagFile, "targetName", text);
        Log.d(TAG, "replace targetName text image layer count=" + count);
    }

    private int replaceTextImageLayerByName(PAGComposition composition, String targetName, String text) {
        int count = 0;
        for (int i = 0; i < composition.numChildren(); i++) {
            PAGLayer layer = composition.getLayerAt(i);
            String layerName = layer.layerName();
            if (layer.layerType() == PAGLayer.LayerTypeImage
                    && layer instanceof PAGImageLayer
                    && isTargetBusinessName(layerName, targetName)) {
                PAGImage image = createTextPAGImage(layerName, text);
                if (image != null) {
                    ((PAGImageLayer) layer).setImage(image);
                    count++;
                }
            }

            if (layer instanceof PAGComposition) {
                count += replaceTextImageLayerByName((PAGComposition) layer, targetName, text);
            }
        }
        return count;
    }

    private boolean isTargetBusinessName(String layerName, String targetName) {
        if (layerName == null) {
            return false;
        }
        String[] parts = layerName.split("#");
        return parts.length > 0 && targetName.equals(parts[0]);
    }

    private PAGImage createTextPAGImage(String layerName, String text) {
        TextRule rule = parseTextRule(layerName);
        Bitmap bitmap = Bitmap.createBitmap(rule.width, rule.height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(rule.color);
        paint.setTextSize(rule.textSize);
        paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float x = rule.width / 2f;
        float y = rule.height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f;
        canvas.drawText(text, x, y, paint);

        return PAGImage.FromBitmap(bitmap);
    }

    private TextRule parseTextRule(String layerName) {
        TextRule rule = new TextRule();
        String[] parts = layerName == null ? new String[0] : layerName.split("#");
        if (parts.length > 1) {
            rule.color = parseColor(parts[1], rule.color);
        }
        if (parts.length == 5) {
            rule.textSize = parseInt(parts[2], rule.textSize);
            rule.width = parseInt(parts[3], rule.width);
            rule.height = parseInt(parts[4], rule.height);
        } else if (parts.length >= 6) {
            rule.width = parseInt(parts[2], rule.width);
            rule.height = parseInt(parts[3], rule.height);
            rule.textSize = parseInt(parts[4], rule.textSize);
        }
        return rule;
    }

    private int parseColor(String colorString, int fallback) {
        try {
            if (colorString == null || colorString.length() == 0) {
                return fallback;
            }
            return Color.parseColor(colorString.startsWith("#") ? colorString : "#" + colorString);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

    private int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private static class TextRule {
        int color = Color.WHITE;
        int textSize = 26;
        int width = 174;
        int height = 40;
    }

    /**
     * 重复点击时能正确取消旧请求。
     * @param v
     */
    public void onTest4(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pagView != null) {
            pagView.stop();
            pagView.freeCache();
        }
        if (pagView2 != null) {
            pagView2.stop();
            pagView2.freeCache();
        }
        if (pagView3 != null) {
            pagView3.stop();
            pagView3.freeCache();
        }
    }
}
