package com.nixo.osubox.Common

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yanzhenjie.sofia.Sofia
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onLayout())
        Sofia.with(this)
            .statusBarBackgroundAlpha(0)
            .navigationBarBackgroundAlpha(0)
        initActivity()
        presenter.onCreate(savedInstanceState)
        AppManager.appManager!!.addActivity(this)
    }

    abstract fun onLayout () : Int
    abstract fun initActivity ()

    fun Action(activity : Class<*>){
        var intent = Intent(baseContext,activity)
    }

    fun paramerAction(activity: Class<*>,bundle: Bundle){
        var intent = Intent()
        intent.setClass(this@BaseActivity,activity)
        intent.putExtras(bundle)

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
