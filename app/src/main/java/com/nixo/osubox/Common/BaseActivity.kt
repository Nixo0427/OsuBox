package com.nixo.osubox.Common

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nixo.osubox.R
import com.yanzhenjie.sofia.Sofia
import es.dmoral.toasty.Toasty
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseActivity<out P : BasePresenter<BaseActivity<P>>> : IView<P>, AppCompatActivity() {
    override val presenter : P

    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    private fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseActivity::class
            while (true) {
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap { it.arguments }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }


    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        Sofia.with(presenter.view).statusBarLightFont().statusBarBackground(R.color.color_zero)
        super.onCreate(savedInstanceState)
        setContentView(onLayout())
        initActivity()
        Toasty.Config.getInstance()
            .tintIcon(true)
            .tintIcon(false) // optional (apply textColor also to the icon)
            .setTextSize(18) // optional
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply()
        presenter.onCreate(savedInstanceState)
        AppManager.appManager!!.addActivity(this)
    }

    abstract fun onLayout () : Int
    abstract fun initActivity ()

    fun Action(activity : Class<*>){
        var intent = Intent(baseContext,activity)
        startActivity(intent)
    }

    fun paramerAction(activity: Class<*>,bundle: Bundle){
        var intent = Intent()
        intent.setClass(this@BaseActivity,activity)
        intent.putExtras(bundle)
        startActivity(intent)

    }


    override fun onStart() {
        super.onStart()
        presenter.onStart()
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
