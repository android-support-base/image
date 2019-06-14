package com.amlzq.asb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amlzq.android.image.ImageLoader;
import com.amlzq.android.image.ImageLoaderHelper;
import com.amlzq.asb.glide.GlideImageLoaderStrategy;

public class MainActivity extends Activity {
    String TAG = "MainActivity";

    TextView mTVInfo;
    ImageView mImgPicture;
    ImageView mImgPicture2;
    String mUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544530677745&di=481cf504b33ac77bd69a75197b718499&imgtype=0&src=http%3A%2F%2Fpic28.photophoto.cn%2F20130706%2F1190120561270420_b.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTVInfo = findViewById(R.id.tv_info);
        mImgPicture = findViewById(R.id.img_picture);
        mImgPicture2 = findViewById(R.id.img_picture_2);

        ImageLoaderHelper.getInstance().setImageLoaderStrategy(new GlideImageLoaderStrategy());
        ImageLoaderHelper.getInstance().load(this,
                new ImageLoader.Builder()
                        .url(mUrl)
                        .placeHolder(R.drawable.pic_default_place)
                        .error(R.drawable.pic_default_error)
                        .imgView(mImgPicture)
                        .strategy(ImageLoaderHelper.getInstance().getDefaultStrategy(this))
                        .build());
        ImageLoaderHelper.getInstance().loadCircle(this,
                new ImageLoader.Builder()
                        .url(mUrl)
                        .placeHolder(R.drawable.pic_default_place)
                        .error(R.drawable.pic_default_error)
                        .imgView(mImgPicture2)
                        .strategy(ImageLoaderHelper.getInstance().getDefaultStrategy(this))
                        .build());
    }

}
