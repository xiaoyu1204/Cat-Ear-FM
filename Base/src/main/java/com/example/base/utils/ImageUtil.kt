package com.example.base.utils

import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

//TODO Adapter条目中imageview图片的加载
class ImageUtil {

    //TODO 伴生对象生成单例的操作
    companion object ImageLoader {

        /**
         * TODO 定义adapter中组件的自定义属性
         * TODO requireAll = true 所有属性必须同时使用
         */
        @BindingAdapter(value = ["bind:imgUrl", "bind:corner"], requireAll = true)
        @JvmStatic
        fun loadCornerImg(img: ImageView, url: String, corner: Int) {
            var corNum = if (corner == 0) 1 else corner
            val options = RequestOptions()
            options.transform(CenterCrop(), RoundedCorners(corNum))
            val viewTarget = if (!TextUtils.isEmpty(url)) {
                Glide.with(img).load(url).apply(options).into(img)
            } else {
                Log.e("TAG", "加载图片的路径为空")
            }
        }

        //TODO 普通的加载图片
        @BindingAdapter(value = ["bind:imageUrl"])
        @JvmStatic
        fun loadImage(img: ImageView, url: String) {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(img).load(url).into(img)
            } else {
                Log.e("TAG", "加载图片的路径为空")
            }
        }
    }

}