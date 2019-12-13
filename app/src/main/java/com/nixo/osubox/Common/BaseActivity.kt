package com.nixo.osubox.Common

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.transition.Slide
import com.nixo.osubox.R
import com.nixo.osubox.Utils.LaucherTime
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

    /**
     * 确定P层具体类型
     * 通过队列+操作符对Kotlin反射进行操作
     * 获取具体的泛型类型
     */
    private fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseActivity::class
            while (true) {
                yield(thisClass.supertypes)
                //获取JVM虚拟机上第一个泛型类型(这里获取的是父类的所有及包括父类的TypeList集合
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            //对子List进行操作，并将其转化为队列
            it.flatMap { it.arguments }.asSequence()
        }.first {
            //如果是当前类型的子类，那么就继续，否则就已经不存在了

            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            //将队列顶部泛型实例化
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }


    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        Sofia.with(presenter.view).statusBarLightFont().statusBarBackground(R.color.color_zero)
        super.onCreate(savedInstanceState)
        setContentView(onLayout())
        initActivity()
        //崩溃重启
        CarchHandler.getInstance().initCrashHandler(this.application)
        //初始化Toasty
        Toasty.Config.getInstance()
            .tintIcon(true)
            .tintIcon(false) // optional (apply textColor also to the icon)
            .setTextSize(18) // optional
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply()
        //P层生性周期同步
        presenter.onCreate(savedInstanceState)
        //任务栈管理器
        AppManager.appManager!!.addActivity(this)
        //进场退场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = Explode().setDuration(800)
            window.exitTransition = Explode().setDuration(200)
        }else{
            startActivity(intent)
        }
    }

    abstract fun onLayout () : Int
    abstract fun initActivity ()

    //ActivityThread创建回调
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


    fun Action(activity : Class<*>){
        var intent = Intent(baseContext,activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }else{
            startActivity(intent)
        }
    }

    fun paramerAction(activity: Class<*>,bundle: Bundle){
        var intent = Intent()
        intent.setClass(this@BaseActivity,activity)
        intent.putExtras(bundle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }else{
            startActivity(intent)
        }
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

