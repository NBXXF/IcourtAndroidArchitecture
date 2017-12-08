package com.icourt.architecture.database;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Description  批量模型转化器件
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/4/11
 * version 1.0.0
 */
public class ListConvertor {

    private ListConvertor() {
    }

    /**
     * 自动过滤为空的实体
     *
     * @param from
     * @param <T>
     * @return
     */
    @Nullable
    @CheckResult
    public static <T> List<T> convertList(@NonNull List<IConvertModel<T>> from) {
        if (from != null) {
            List<T> datas = new ArrayList<>();
            for (int i = 0; i < from.size(); i++) {
                IConvertModel<T> tiConvertModel = from.get(i);
                if (tiConvertModel != null) {
                    datas.add(tiConvertModel.convert2Model());
                }
            }
            return datas;
        }
        return null;
    }
}
