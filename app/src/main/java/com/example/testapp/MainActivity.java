package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button mBtn_1;//Activity还未创建，不能找到页面控件,不能在这里findView
    private Button mBtn_2;
    private Button mBtn_3;
    private Button mBtn_4;
    private Button mBtn_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_1 = findViewById(R.id.btn_1);
        mBtn_2 = findViewById(R.id.btn_2);
        mBtn_3 = findViewById(R.id.btn_3);
        mBtn_4 = findViewById(R.id.btn_4);
        mBtn_5 = findViewById(R.id.btn_5);
        setListner();
    }

    private void setListner(){
        OnClick onClick = new OnClick();
        mBtn_1.setOnClickListener(onClick);
        mBtn_2.setOnClickListener(onClick);
        mBtn_3.setOnClickListener(onClick);
        mBtn_4.setOnClickListener(onClick);
        mBtn_5.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch(view.getId()){
                case R.id.btn_1://启动新页面
                    intent = new Intent(MainActivity.this,OneActivity.class);
                    break;
                case R.id.btn_2://访问网址
                    Uri uri = Uri.parse("http://baidu.com");
                    intent = new Intent(Intent.ACTION_VIEW,uri);
                case R.id.btn_3://拍照获取相片
                    //打开拍照程序
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                    //获取相片数据（不存储）
                    Bundle extras = intent.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                case R.id.btn_4://剪切图片(AV画质)
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra("crop", "true"); // 开启剪切
                    intent.putExtra("aspectX", 1); // 剪切的宽高比为1:2
                    intent.putExtra("aspectY", 2);
                    intent.putExtra("outputX", 20); // 保存图片的宽和高
                    intent.putExtra("outputY", 40);
                    intent.putExtra("output", Uri.fromFile(new File("/sdcard/DCIM/2.jpeg"))); // 保存路径：模拟器中的路径
                    intent.putExtra("outputFormat", "JPEG");// 返回格式
                    startActivityForResult(intent, 0);
                case R.id.btn_5://Activity回传(后一个传给前一个)
                    intent = new Intent(MainActivity.this,OneActivity.class);

            }
            startActivity(intent);
        }
    }
}