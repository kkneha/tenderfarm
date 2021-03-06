package com.example.tenderfarm1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.tensorflow.lite.Interpreter;

import java.io.ByteArrayOutputStream;
import java.io.File;
public class Detect_disease extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 300;



    File modelFile= new File("C:/Users/Admin/AndroidStudioProjects/tenderfarm/app/src/main/assets/plant_disease_model.tflite");
    Interpreter obj =new Interpreter(modelFile);
    String input;
    String output;


    Interpreter interpreter = new Interpreter(modelFile);
    {


        interpreter.run(input, output);


        interpreter.close();
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_disease);

    }

    public void gotocaptureImage(View view) {


        int TAKE_PHOTO_CODE = 0;
        int count = 0;


        int MY_INTENT_CLICK = 302;
        int PICK_IMAGE_REQUEST = 1;
        int first = 1;


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        } else {
            new Thread(new Runnable() {
                public void run() {

                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                    startActivityForResult(intent, 1);
                }
            }).start();

        }


    }

    Uri uri;

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 || resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
            } else {
                Log.e("debug for camera", "onActivityResult: " + "in result of 1");
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Log.e("uri data", "onActivityResult: " + uri);
                try {
                    Uri tempUri = getImageUri(getApplicationContext(), photo);
                    Log.e("uri from bitmap", "onActivityResult: " + tempUri);
                    String pth0 = getRealPathFromURI(tempUri);
                    Log.d("path", "onActivityResult: " + pth0);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    private MappedByteBuffer loadModelFile(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(getModelPath());
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
    // Initialization code
// Create an ImageProcessor with all ops required. For more ops, please
// refer to the ImageProcessor Architecture section in this README.
    ImageProcessor imageProcessor =
            new ImageProcessor.Builder()
                    .add(new ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
                    .build();

    // Create a TensorImage object, this creates the tensor the TensorFlow Lite
// interpreter needs
    TensorImage tImage = new TensorImage(DataType.UINT8);

// Analysis code for every frame
// Preprocess the image
tImage.load(bitmap);
    tImage = imageProcessor.process(tImage);
*/
}
