package com.example.camerabase64;

import static android.graphics.BitmapFactory.decodeFile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

//import com.github.drjacky.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ImageButton btncamera,btncamera1,btncamera2,btncamera3;
    String image1;
    private final int REQUEST_IMAGE_CAPTURE1 = 1;
    private static final int PICK_IMAGE = 100;
    String currentPhotoPath;
    private File file1;
    private File file2;
    private File file3;
    private File file4;

    private String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncamera = findViewById(R.id.imagebtn);
        btncamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE1);
                    } else {

                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (photoFile != null) {
                            Uri photoUri1 = FileProvider.getUriForFile(MainActivity.this,
                                    BuildConfig.APPLICATION_ID + ".provider",
                                    photoFile);
                            file1 = photoFile;
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri1);
                            takePictureResultCallback.launch(takePictureIntent);
                        }
                    }
                }

        });
        btncamera1 = findViewById(R.id.imagebtn1);
        btncamera1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE1);
                } else {

                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (photoFile != null) {
                        Uri photoUri1 = FileProvider.getUriForFile(MainActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile);
                        file2 = photoFile;
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri1);
                        takePictureResultCallback1.launch(takePictureIntent);
                    }
                }
            }

        });
        btncamera2 = findViewById(R.id.imagebtn2);
        btncamera2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE1);
                } else {

                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (photoFile != null) {
                        Uri photoUri1 = FileProvider.getUriForFile(MainActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile);
                        file3 = photoFile;
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri1);
                        takePictureResultCallback2.launch(takePictureIntent);
                    }
                }
            }

        });
        btncamera3 = findViewById(R.id.imagebtn3);
        btncamera3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE1);
                } else {

                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (photoFile != null) {
                        Uri photoUri1 = FileProvider.getUriForFile(MainActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile);
                        file4 = photoFile;
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri1);
                        takePictureResultCallback3.launch(takePictureIntent);
                    }
                }
            }

        });
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getFilesDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        this.imgPath = image.getAbsolutePath();
        Log.e("imagepath",""+imgPath.toString());
        return image;

    }
    private final ActivityResultLauncher<Intent> takePictureResultCallback = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            Log.e("file size1",""+file1.length());
            File fi= CompressFile.getCompressedImageFile(file1,this);
            Log.e("file size1 after",""+fi.length());

            image1=Base64code.getStringFile(fi);
            Log.e("Image 1", image1);
            btncamera.setImageBitmap(Base64code.getdecoded64ImageStringFromBitmap(image1));
        }
    });

    private final ActivityResultLauncher<Intent> takePictureResultCallback1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            Log.e("file size1",""+file2.length());
            File fi= CompressFile.getCompressedImageFile(file2,this);
            Log.e("file size1 after",""+fi.length());
            image1=Base64code.getStringFile(fi);
            Log.e("Image 1", image1);
            btncamera1.setImageBitmap(Base64code.getdecoded64ImageStringFromBitmap(image1));
        }
    });


    private final ActivityResultLauncher<Intent> takePictureResultCallback2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            Log.e("file size1",""+file3.length());
            File fi= CompressFile.getCompressedImageFile(file3,this);
            Log.e("file size1 after",""+fi.length());
            image1=Base64code.getStringFile(fi);
            Log.e("Image 1", image1);
            btncamera2.setImageBitmap(Base64code.getdecoded64ImageStringFromBitmap(image1));
        }
    });

    private final ActivityResultLauncher<Intent> takePictureResultCallback3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            Log.e("file size1",""+file4.length());
            File fi= CompressFile.getCompressedImageFile(file4,this);
            Log.e("file size1 after",""+fi.length());
            image1=Base64code.getStringFile(fi);
            Log.e("Image 1", image1);
            btncamera3.setImageBitmap(Base64code.getdecoded64ImageStringFromBitmap(image1));
        }
    });
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE1)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE1);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

}