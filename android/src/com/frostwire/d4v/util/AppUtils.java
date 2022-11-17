package com.frostwire.d4v.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.io.File;

/**
 * dp、sp 转换为 px 的工具类
 *
 * @author fxsky 2012.11.12
 */
public class AppUtils {
    private static final String TAG = "AppUtils";

    public final static String PATH365 = Environment.getExternalStorageDirectory() + "/av365/";

    public static String sLatest_APK_URL = "";

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void removeViewFormParent(View v) {
        if (v == null) return;
        ViewParent parent = v.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(v);
        }
    }

    private final static double million = 1000000.00;
    private final static double kilo = 1000.00;

    public static String toDisplayCount(String count) {
        try {
            int iCount = Integer.valueOf(count);
            return toStringCount(iCount);
        } catch (Exception e) {
        }
        return count;
    }

    public static String toStringCount(int count) {
        String displayCount;
        if (count > million) {
            String value = String.format("%.2f", (count / million));
            if (value.endsWith(".00")) {
                value = value.substring(0, value.length() - 3);
            } else if (value.endsWith("0")) {
                value = value.substring(0, value.length() - 1);
            }
            displayCount = value + "M";
        } else if (count > kilo) {
            String value = String.format("%.2f", (count / kilo));
            if (value.endsWith(".00")) {
                value = value.substring(0, value.length() - 3);
            } else if (value.endsWith("0")) {
                value = value.substring(0, value.length() - 1);
            }
            displayCount = value + "K";
        } else {
            displayCount = String.valueOf(count);
        }
        return displayCount;
    }

    private static String sCountry;



    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取手机外部总空间大小
     *
     * @return 总大小，字节为单位
     */
    static public long getTotalExternalMemorySize() {
        if (isSDCardEnable()) {
            //获取SDCard根目录
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return -1;
        }
    }

    public static final long GB2 = 2 * 1024 * 1024;  // K size

    public static long getFreeSpaceK() {
        if (!isSDCardEnable()) return -1;
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        StatFs stat = new StatFs(sdcardPath);
        long blockSize, availableBlocks;
        availableBlocks = stat.getAvailableBlocksLong();
        blockSize = stat.getBlockSizeLong();

        final long sizeK = availableBlocks * blockSize / 1024L; // k
        return sizeK;
    }

    /**
     * 获取SD卡剩余空间
     *
     * @return SD卡剩余空间
     */
    public static String getFreeSpaceConvert() {
        long sizeK = getFreeSpaceK();
        return toConvertSize(sizeK);
    }

    public static String toConvertSize(long sizeK) {
        if (sizeK <= 0) return "0";
        String strSize;
        long sizeM = sizeK / 1024;
        long sizeG = sizeM / 1024;
        long sizeMShort = (sizeM % 1000);

        if (sizeG > 0 && sizeMShort > 100) {
            return sizeG + "." + (sizeMShort / 100) + "G";
        } else if (sizeG > 0) {
            return sizeG + "G";
        } else if (sizeMShort > 0) {
            return sizeMShort + "M";
        }

        return "0M";
    }

    public static String toSpeedConvertStr(long speed) {
        speed = speed / 1024;
        if (speed < 1024) {
            return speed + "k/s";
        }

        long kSpeed = (speed % 1024) / 100;
        speed = speed / 1024;

        return speed + "." + kSpeed + "M/S";
    }

}