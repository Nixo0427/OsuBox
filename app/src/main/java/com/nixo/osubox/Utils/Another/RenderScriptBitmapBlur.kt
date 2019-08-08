package com.nixo.osubox.Utils.Another

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.IntRange

class RenderScriptBitmapBlur(context: Context) {

    private val renderScript: RenderScript = RenderScript.create(context)

    fun getBlurBitmap(@IntRange(from = 1, to = 25) radius: Int, original: Bitmap): Bitmap {
        // 使用Renderscript和in/out位图创建分配(in/out)
        val input = Allocation.createFromBitmap(renderScript, original)
        val output = Allocation.createTyped(renderScript, input.type)
        // 使用Renderscript创建一个固有的模糊脚本
        val scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        // 设置模糊半径:0 < radius <= 25
        scriptIntrinsicBlur.setRadius(radius.toFloat())
        // 执行渲染脚本
        scriptIntrinsicBlur.setInput(input)
        scriptIntrinsicBlur.forEach(output)
        // 将out分配创建的最终位图复制到original
        output.copyTo(original)
        return original
    }
}
