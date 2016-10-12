package com.datas.retrofitdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.get)
    TextView get;
    @Bind(R.id.post)
    TextView post;
    @Bind(R.id.download)
    TextView download;
    @Bind(R.id.camera)
    TextView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*Retrofit.Builder builder = new Retrofit.Builder();
        final Retrofit retrofit = builder.baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();*/


        get.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RetrofitUtil.getService().getDataByGet("course_list")
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String s = response.body().string();
                                    Json.paresData(s);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
            }
        });


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("tag", "执行post");
                RetrofitUtil.getService()
                        .getDataByPost(12666, 3)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String s = response.body().string();
                                    Log.v("tag", "s:" + s);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.v("tag", "onFailure");
                            }
                        });
            }
        });


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("tag","开始拍照");
                saveFullImage();
            }
        });

    }

    private void saveFullImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //文件夹aaaa
        String path = Environment.getExternalStorageDirectory().toString()+"/aaaa";
        File path1 = new File(path);
        if(!path1.exists()){
            path1.mkdirs();
        }
        File file = new File(path1,System.currentTimeMillis()+".jpg");
       Uri mOutPutFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Uri imageUri = null;
            if(data != null){
                if(data.hasExtra("data")){
                    Bitmap thunbnail = data.getParcelableExtra("data");
                    //处理缩略图
                }
            }else{
                //处理mOutPutFileUri中的完整图像
            }

        }
    }
}
