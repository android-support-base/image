package com.amlzq.android.image;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by Anthony on 2016/3/3.
 * Class Note: use this class to load image,single instance
 */
public class ImageLoaderHelper {

    /**
     * 图片尺寸:大图
     */
    public static final int PIC_LARGE = 0;
    public static final int PIC_MEDIUM = 1;
    public static final int PIC_SMALL = 2;

    /**
     * 加载策略：正常（不受限制）
     */
    public static final int LOAD_STRATEGY_NORMAL = 0;
    /**
     * 加载策略：仅在WiFi加载
     */
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;
    /**
     * 仅限WIFI环境加载图片
     */
    public boolean onlyWiFiEnvironment;

    private IImageLoaderStrategy mStrategy;

    private static ImageLoaderHelper mInstance;

    public ImageLoaderHelper() {
    }

    //single instance
    public static ImageLoaderHelper getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderHelper.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderHelper();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public void setImageLoaderStrategy(IImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }

    public void load(Context context, ImageLoader loader) {
        mStrategy.load(context, loader);
    }

    public void loadCircle(Context context, ImageLoader loader) {
        mStrategy.loadCircle(context, loader);
    }

    public void loadRound(Context context, ImageLoader loader) {
        mStrategy.loadRound(context, loader);
    }

    public void setLoadImgStrategy(IImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }

    public int getDefaultStrategy(Context context) {
        // if currently is wifi environment
        if (NETWORKTYPE_WIFI == getNetWorkType(context)) {
            return LOAD_STRATEGY_NORMAL;
        } else if (onlyWiFiEnvironment) {
            return LOAD_STRATEGY_ONLY_WIFI;
        }
        return LOAD_STRATEGY_NORMAL;
    }

    /**
     * 没有网络
     */
    public static final int NETWORKTYPE_INVALID = 0;
    /**
     * WAP网络
     */
    public static final int NETWORKTYPE_WAP = 1;
    /**
     * 2G网络
     */
    public static final int NETWORKTYPE_2G = 2;
    /**
     * 3G和3G以上网络，或统称为快速网络
     */
    public static final int NETWORKTYPE_3G = 3;
    /**
     * WIFI网络
     */
    public static final int NETWORKTYPE_WIFI = 4;

    /**
     * @param context 上下文
     * @return get the network type (wifi,wap,2g,3g)
     * @permission required by ConnectivityManager.getActiveNetworkInfo: android.permission.ACCESS_NETWORK_STATE
     */
    @SuppressWarnings("deprecation")
    public static int getNetWorkType(Context context) {
        int mNetWorkType = NETWORKTYPE_INVALID;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                mNetWorkType = TextUtils.isEmpty(proxyHost)
                        ? (isFastMobileNetwork(context) ? NETWORKTYPE_3G : NETWORKTYPE_2G)
                        : NETWORKTYPE_WAP;
            }
        } else {
            mNetWorkType = NETWORKTYPE_INVALID;
        }
        return mNetWorkType;
    }

    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

}
