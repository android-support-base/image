package com.amlzq.android.image;

import android.content.Context;

/**
 * Created by Anthony on 2016/3/3.
 * Class Note:
 * abstract class/interface defined to load image
 * (Strategy Pattern used here)
 */
public interface IImageLoaderStrategy {

    void load(Context ctx, ImageLoader loader);

    void loadRound(Context ctx, ImageLoader loader);

    void loadCircle(Context ctx, ImageLoader loader);

}
