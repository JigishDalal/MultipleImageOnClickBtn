package com.example.camerabase64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Base64code {

    public static String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {


        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;*/

        //show tiny image
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        getdecoded64ImageStringFromBitmap(imgString);
        return imgString ;


        //bitmap Size increase in this code
      /*  ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap b=bitmap;
        int maxHeight = 2000;
        int maxWidth = 2000;
        float scale = Math.min(((float)maxHeight / bitmap.getWidth()), ((float)maxWidth / bitmap.getHeight()));

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap bitemap=Bitmap.createBitmap(b,0,0,b.getWidth(),bitmap.getHeight(),matrix,false);
        bitemap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();

        // Get the Base64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        getdecoded64ImageStringFromBitmap(imgString);
        return imgString ;*/
    }

    public static Bitmap getdecoded64ImageStringFromBitmap(String str) {
        byte[] decodestring=Base64.decode(str,Base64.NO_WRAP);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodestring, 0, decodestring.length);
     //   btnimage3.setImageBitmap(decodedByte);

  //      Log.e("Size",""+decodedByte.getHeight()+decodedByte.getWidth());
        return decodedByte;
    }




    public static String getEncoded64ImageStringFromUri(String picturePath) {

        Bitmap bm = BitmapFactory.decodeFile(picturePath);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        String ba1 = Base64.encodeToString(ba, Base64.NO_WRAP);

        Log.e("base64", "-----" + ba1);
        return ba1;
    }
    public static String getStringFile(File f) {
        InputStream inputStream = null;
        String encodedFile= "", lastVal;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.NO_WRAP);

            while ((bytesRead = inputStream.read(buffer)) != -1)
                output64.write(buffer, 0, bytesRead);
            output64.close();
            encodedFile =  output.toString();

        }
        catch (FileNotFoundException e1 ) {

            e1.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();

        }
        lastVal = encodedFile;
        return lastVal;
    }

}
