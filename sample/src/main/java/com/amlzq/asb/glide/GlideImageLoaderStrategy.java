package com.amlzq.asb.glide;

import android.annotation.SuppressLint;
import android.content.Context;

import com.amlzq.android.image.IImageLoaderStrategy;
import com.amlzq.android.image.ImageLoader;
import com.amlzq.android.image.ImageLoaderHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Anthony on 2016/3/3.
 * Class Note:
 * using {@link Glide 4.8.0} to load image
 */
public class GlideImageLoaderStrategy implements IImageLoaderStrategy {

    @Override
    public void load(Context ctx, ImageLoader imageLoader) {
        int strategy = imageLoader.getStrategy();
        if (strategy == ImageLoaderHelper.LOAD_STRATEGY_NORMAL) {
            loadNormal(ctx, imageLoader);
        } else if (strategy == ImageLoaderHelper.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = ImageLoaderHelper.getNetWorkType(ctx);
            //if wifi, load pic
            if (netType == ImageLoaderHelper.NETWORKTYPE_WIFI) {
                loadNormal(ctx, imageLoader);
            } else {
                //if not wifi ,load cache
                loadCache(ctx, imageLoader);
            }
        } else {
            loadNormal(ctx, imageLoader);
        }
    }

    /**
     * load image with Glide
     * fitCenter:
     * centerCrop:
     */
    @SuppressLint("CheckResult")
    private void loadNormal(Context ctx, ImageLoader loader) {
        if (loader != null && loader.getImgView() != null) {
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.placeholder(loader.getPlaceHolder());
            options.error(loader.getError());
            Glide.with(ctx)
                    .load(loader.getUrl())
                    .apply(options)
                    .into(loader.getImgView());
        }
    }

    @Override
    public void loadRound(Context ctx, ImageLoader loader) {
        if (loader != null && loader.getImgView() != null) {
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadCircle(Context ctx, ImageLoader loader) {
        if (loader != null && loader.getImgView() != null) {
            RequestOptions options = new RequestOptions();
            options.circleCrop();
            options.placeholder(loader.getPlaceHolder());
            options.error(loader.getError());
            Glide.with(ctx)
                    .load(loader.getUrl())
                    .apply(options)
                    .into(loader.getImgView());
        }
    }

    /**
     * load cache image with Glide
     * 使用Glide加载缓存图像
     */
    private void loadCache(Context ctx, ImageLoader loader) {
        if (loader != null && loader.getImgView() != null) {

        }
    }

}
