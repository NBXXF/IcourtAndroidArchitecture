package com.icourt.architecture.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description 绑定属性声明
 * @Company Beijing icourt
 * @date createTime：2017/12/5
 */
public class ICourtBindingConfig {
    private static final String ATTR_ADAPTER = "adapter";
    private static final String ATTR_ITEM_DECORATION = "itemDecoration";

    /**
     * recylerView设置适配器
     *
     * @param recyclerView
     * @param adapter
     */
    @BindingAdapter({
            ATTR_ADAPTER
    })
    public static void setAdapter(RecyclerView recyclerView,
                                  RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    /**
     * 设置viewPager的Adapter
     *
     * @param viewPager
     * @param adapter
     */
    @BindingAdapter({
            ATTR_ADAPTER
    })
    public static void setAdapter(ViewPager viewPager,
                                  PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    /**
     * 加载图片
     *
     * @param view
     * @param resId
     */
    @BindingAdapter({"android:src"})
    public static void setSrc(ImageView view, @DrawableRes int resId) {
        view.setImageResource(resId);
    }

    /**
     * 设置
     *
     * @param recyclerView
     * @param itemDecoration
     */
    @BindingAdapter({
            ATTR_ITEM_DECORATION
    })
    public static void setItemDecoration(RecyclerView recyclerView, @Nullable RecyclerView.ItemDecoration itemDecoration) {
        if(itemDecoration==null) {
            return;
        }
        recyclerView.addItemDecoration(itemDecoration);
    }


    @BindingAdapter({
            "clipToPaddingConfig"
    })
    public static void setClipToPaddingConfig(ViewGroup viewGroup, @Nullable ICourtClipPadding iCourtClipPadding) {
        if (iCourtClipPadding != null) {
            int[] realPaddings = new int[]{0, 0, 0, 0};
            if (iCourtClipPadding.paddings() != null) {
                realPaddings = Arrays.copyOf(iCourtClipPadding.paddings(), realPaddings.length);
            }
            viewGroup.setClipToPadding(iCourtClipPadding.clipToPadding());
            viewGroup.setPadding(realPaddings[0], realPaddings[1], realPaddings[2], realPaddings[3]);
        }
    }

}
