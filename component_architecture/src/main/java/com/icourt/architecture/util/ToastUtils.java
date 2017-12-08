package com.icourt.architecture.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description
 * * <p>
 * 【不建议使用 不建议使用】
 * <p>
 * 某些系统可能屏蔽通知
 * 1:检查 SystemUtils.isEnableNotification(BaseApplication.getApplication());
 * 2:替代方案 SnackbarUtils.showSnack(topActivity, noticeStr);
 * <p>
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/4/27
 * version 1.0.0
 */
public class ToastUtils {

    private ToastUtils() {
    }

    /**
     * toast是否可用
     *
     * @param context
     * @return
     */
    public static boolean isToastAvailable(@NonNull Context context) {
        try {
            final String CHECK_OP_NO_THROW = "checkOpNoThrow";
            final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            Class appOpsClass = null;
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    appOpsClass = Class.forName(AppOpsManager.class.getName());
                    Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
                    Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
                    int value = (int) opPostNotificationValue.get(Integer.class);
                    return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } catch (Throwable e) {
        }
        return true;
    }

    @UiThread
    @Nullable
    public static Toast showToast(@NonNull Context context, @NonNull CharSequence notice) {
        if (TextUtils.isEmpty(notice) || context == null) {
            return null;
        }
        Toast toast = Toast.makeText(context, notice, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    @UiThread
    @Nullable
    public static Toast showToast(@NonNull Context context, @StringRes int notice) {
        if (context == null) {
            return null;
        }
        Toast toast = Toast.makeText(context, notice, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }
}
