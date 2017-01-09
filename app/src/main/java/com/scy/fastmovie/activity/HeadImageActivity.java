package com.scy.fastmovie.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;


import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.MineDataCallBack;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadImageActivity extends AppCompatActivity {

    private ImageView headimage;
    private Button changeuserhead;
    private Bundle extras;
    private Bitmap bmp;
    private Button sure_btn;
    private File file;
    private Bitmap bitmap;


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
        sure_btn = (Button) findViewById(R.id.sure_btn);


    }

    private void setonclicklistener() {
        sure_btn.setOnClickListener(new View.OnClickListener() {
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
                            case 0:
                                break;
                            case 1:
                                getphotofromsdcard();
                                break;
                            case 2:
                                getphotofromcamera();
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

    public void getphotofromcamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = getStorageFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, 520);

    }

    //创建放置照片的文件
    private File getStorageFile() {
        File imgpath = null;
        if (hassdcard()) {
            File root = Environment.getExternalStorageDirectory();
            File directory = new File(root, "fastmovie");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            imgpath = new File(directory, System.currentTimeMillis() + ".jpg");
        } else {
            Toast.makeText(HeadImageActivity.this, "检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
        }
        return imgpath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            //从手机获取图片
            case 100:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (hassdcard()) {
                            cropRawPhoto(data.getData());

                        }
                    }
                }
                break;
            case 201:
                extras = data.getExtras();
                if (extras != null) {
                    bmp = extras.getParcelable("data");
                    headimage.setImageBitmap(bmp);
                    mineBack.setDataCallBack(bmp);

                }
                break;
            //拍照获取图片
            case 520:
                if (resultCode == RESULT_OK) {
                    if (hassdcard()) {
                        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        headimage.setImageBitmap(bitmap);
                        mineBack.setDataCallBack(bitmap);
                    }
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //检查SD卡是否挂载
    public boolean hassdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        Toast.makeText(HeadImageActivity.this, "检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
        return false;
    }

    //裁剪图片
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 201);
    }

    static MineDataCallBack mineBack = null;

    public static void setOnCallBackListener(MineDataCallBack mineDataCallBack) {
        mineBack = mineDataCallBack;
    }
}
