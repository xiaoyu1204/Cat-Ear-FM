package com.example.myshop.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.base.module.item.IItemClick

/**
 * adapter基类
 * layouts 布局id与界面绑定name的id匹配使用
 */
//TODO 1.上下文 2.集合 3.布局（也可能是多布局 所以使用轻量级集合） 4.点击事件 （多个点击）
open abstract class BaseAdapter<D>(
    //加var相当于public 实例也可以调用这个变量
    val context: Context,
    var list: List<D>,
    val layouts: SparseArray<Int>,
    var itemClick: IItemClick<D>
) :
    RecyclerView.Adapter<BaseAdapter<D>.BaseVH>() {

    //TODO 用来初始化创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        var dataBinding:ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),viewType,parent,false)
        return BaseVH(dataBinding)
    }


    //TODO item的赋值  {绑定，动态赋值}
    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        //获取当前条目的id
        var layoutId = getItemViewType(position)
        //获取layout id 所对应的BR的id
        val type = layouts.get(layoutId)
        holder.dataBinding.setVariable(type,list.get(position))
        holder.dataBinding.root.tag = list.get(position)

//        if (clicks.size() > 0) {
//            var brname = clicks[0]
//        }
        bindData(holder.dataBinding,list.get(position),layoutId)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId(position)
    }

    //TODO 获取对应的布局（多布局）
    protected abstract fun layoutId(position: Int):Int

    //TODO 视图和集合
    protected abstract fun bindData(binding: ViewDataBinding,data:D,layId:Int)

    //TODO 内联函数  内部类  参数是dataBinding 而不是View,因为dataBinding可以绑定数据，也可以显示页面
    inner class BaseVH(val dataBinding:ViewDataBinding) :RecyclerView.ViewHolder(dataBinding.root)

    //等同上面
    //inner class BaseVH(view:View) :RecyclerView.ViewHolder(view)
}