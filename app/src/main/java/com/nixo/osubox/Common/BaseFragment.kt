package com.nixo.osubox.Common

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure


/**
 * @author Nixo
 * 夢はいつも届かない
 * 梦想总是遥不可及
 * @date 2018-12-18
 */

abstract class BaseFragment<out P: BasePresenter<BaseFragment<P>>>: IView<P>,Fragment() {
    override val presenter: P

    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    abstract fun onLayout() : Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View? = null
        if(view == null){
            view = inflater.inflate(onLayout(),container,false)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initActivity()
    }

    private fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                yield(thisClass.supertypes) // 添加BseFragment子类的父类
//                然后替换掉这个类为
//                他的上一层父类，如果遍历到空，也就是Any的父类，那么就break出去，
//                这时候我们拿到了每一个父类的父类序列，每一个参数就是每一个父类和每一个父类的父类序列
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            //然后遍历第一个类的父类序列(也就是继承BaseFragment的父类的类型序列)
            it.flatMap { it.arguments }.asSequence()
        }.first {
            //            我们拿到上面遍历的第一个类型，去判断是否是Ipresenter的子类，是的话调用下层，不是返回false
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            //            最后我们返回这个Presenter的实现类
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }

    abstract fun initActivity ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }


    fun Action(activity : Class<*>) {
        var intent = Intent(context, activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity as Activity).toBundle())
        } else {
            startActivity(intent)
        }
    }

    fun paramerAction(activity: Class<*>,bundle: Bundle){
        var intent = Intent()
        intent.setClass(context,activity)
        intent.putExtras(bundle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity as Activity).toBundle())
        }else{
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        presenter.onDestory()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }


}