package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

import org.w3c.dom.Text;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    public Button generater;
    public EditText email;
    public ImageView image;
    Bitmap bitmap;
    QRGEncoder encoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generater = findViewById(R.id.geneator);
        email = findViewById(R.id.email);
        image = findViewById(R.id.qr_view);


        generater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = email.getText().toString();
                if(s.length()>0) {
                    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    assert windowManager != null;
                    Display display = windowManager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int x = point.x;
                    int y = point.y;
                    int icon = x < y ? x : y;
                    icon = icon * 3 / 4;

                    encoder = new QRGEncoder(s,null, QRGContents.Type.TEXT,icon);

                    try{
                        bitmap = encoder.encodeAsBitmap();
                        image.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot be blank",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}