package com.scy.fastmovie.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.scy.fastmovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadImageActivity extends AppCompatActivity {

    private ImageView headimage;
    private Button changeuserhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image);
        initdata();

    }

    private void initdata() {
        findviewbyid();
        setonclicklistener();
    }

    private void findviewbyid() {
        headimage = (ImageView) findViewById(R.id.headimage);
        changeuserhead = (Button) findViewById(R.id.changeuserhead);
    }

    private void setonclicklistener() {
        headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changeuserhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HeadImageActivity.this);
                String[] names = {"设置头像", "选择本地图片", "拍照", "取消"};
                List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < names.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", names[i]);
                    data.add(map);
                }

                SimpleAdapter adapter = new SimpleAdapter(HeadImageActivity.this,
                        data, R.layout.dialog_item,
                        new String[]{"name"},
                        new int[]{R.id.name}
                );
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                getphotofromsdcard();
//                                Toast.makeText(HeadImageActivity.this, "从本地选择照片", Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                getphotofromtakephoto();
//                                Toast.makeText(HeadImageActivity.this, "我要拍照", Toast.LENGTH_LONG).show();
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    public void getphotofromsdcard() {
        Intent fromsdcardintent = new Intent();
        fromsdcardintent.setType("image/*");
        fromsdcardintent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(fromsdcardintent, 100);

    }

    public void getphotofromtakephoto() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    if (hassdcard()) {
                        cropRawPhoto(data.getData());

                    }

                   /*
                      Toast.makeText(HeadImageActivity.this,"cuocuo、、、",Toast.LENGTH_SHORT).show();
                        */
                }
            }
        }
        if (requestCode==201){
            Bundle extras=data.getExtras();
            if (extras!=null){
                Bitmap bmp= extras.getParcelable("data");
                headimage.setImageBitmap(bmp);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public boolean hassdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        Toast.makeText(HeadImageActivity.this, "检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);

        startActivityForResult(intent,201);
    }
}
