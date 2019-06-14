package com.amlzq.android.image;

import android.widget.ImageView;

/**
 * Created by Anthony on 2016/3/3.
 * Class Note: encapsulation of ImageView,Build Pattern used
 */
public class ImageLoader {
    private int type;  // (Big,Medium,small)
    private String url; //url to parse
    private int placeHolder = -1; //placeholder when fail to load pics
    private int error = -1; //error when fail to load pics
    private ImageView imgView; //ImageView instantce
    private int strategy;//load strategy

    private ImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.error = builder.error;
        this.imgView = builder.imgView;
        this.strategy = builder.strategy;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public int getError() {
        return error;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public int getStrategy() {
        return strategy;
    }

    public static class Builder {
        private int type;
        private String url;
        private int placeHolder;
        private int error;
        private ImageView imgView;
        private int strategy;

        public Builder() {
            this.type = ImageLoaderHelper.PIC_MEDIUM;
            this.url = "";
            this.placeHolder = R.drawable.pic_default_place;
            this.error = R.drawable.pic_default_error;
            this.imgView = null;
            this.strategy = ImageLoaderHelper.LOAD_STRATEGY_NORMAL;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder error(int error) {
            this.error = error;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }

        public Builder strategy(int strategy) {
            this.strategy = strategy;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }
    }

}
