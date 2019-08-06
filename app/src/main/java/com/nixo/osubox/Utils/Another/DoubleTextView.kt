package com.nixo.osubox.Utils.Another

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.nixo.osubox.R


/**
 * @author Nixo
 * @date 2019年5月17日11:44:37
 */

class DoubleTextView : LinearLayout {

    var textLeft = "1"
    var textRight = "2"
    private var textLeftColor: Int = 0
    private var textRightColor: Int = 0
    private var textLeftSize: Int = 14
    private var textRightSize: Int = 14
    var mPaint = Paint()


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        textLeftColor = context.resources.getColor(R.color.color_666666)
        textRightColor = context.resources.getColor(R.color.color_222222)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DoubleTextView)
        textLeft = typedArray.getString(R.styleable.DoubleTextView_text_left)
        textRight = typedArray.getString(R.styleable.DoubleTextView_text_right)
        textLeftColor = typedArray.getInt(R.styleable.DoubleTextView_text_left_color, textLeftColor)
        textRightColor = typedArray.getInt(R.styleable.DoubleTextView_text_right_color, textRightColor)
        //注掉就是xml设置的，没注掉就是左边666666右边222222
        textLeftColor = context.resources.getColor(R.color.color_666666)
        textRightColor = context.resources.getColor(R.color.color_222222)
        textLeftSize = typedArray.getInt(R.styleable.DoubleTextView_text_left_size, textLeftSize)
        textRightSize = typedArray.getInt(R.styleable.DoubleTextView_text_right_size, textRightSize)

        initPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawTextRight(canvas)
        drawTextLeft(canvas)
        invalidate()
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        drawTextRight(canvas)
        drawTextLeft(canvas)
        invalidate()
    }

    private fun drawTextLeft(canvas: Canvas?) {
        mPaint.color = textLeftColor
        mPaint.textSize = StringUtils.dip2px(context, textLeftSize.toFloat()).toFloat()
        var rect = Rect()
        mPaint.getTextBounds(textLeft, 0, 1, rect)

        canvas!!.drawText(textLeft, StringUtils.dip2px(context, 2f).toFloat()
                , (height / 2 + rect.height() / 2).toFloat(), mPaint)
    }


    private fun drawTextRight(canvas: Canvas?) {
        mPaint.color = textRightColor
        mPaint.strokeWidth = 40f
        mPaint.textSize = StringUtils.dip2px(context, textRightSize.toFloat()).toFloat()
        var rect = Rect()
        mPaint.getTextBounds(textLeft, 0, 1, rect)
        canvas!!.drawText(textRight, width - mPaint.measureText(textRight)
                , (height / 2 + rect.height() / 2).toFloat(), mPaint)
    }


    fun isChinese(c: Char): Boolean {

        val ub = Character.UnicodeBlock.of(c)

        return (ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub === Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val SpecWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val SpecWidthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val SpecHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        val SpecHeightMode = View.MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0

        if (SpecWidthMode == View.MeasureSpec.EXACTLY) {
            width = SpecWidth
        } else {
            val needWidth = MeasureWidth() + paddingLeft + paddingRight
            if (SpecWidthMode == View.MeasureSpec.AT_MOST) {
                width = Math.min(needWidth, width)
            } else {
                width = needWidth
            }
        }

        if (SpecHeightMode == View.MeasureSpec.EXACTLY) {
            height = (SpecHeight + width / 2).toInt()
        } else {
            val needHeight = MeasureHeight() + paddingBottom + paddingEnd
            if (SpecHeightMode == View.MeasureSpec.AT_MOST) {
                height = Math.min(needHeight, height)
            } else {
                height = needHeight
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    private fun MeasureHeight(): Int {
        //todo 自适应高度 height = height+字数/行限制字数*字高度
        return height
    }

    private fun MeasureWidth(): Int {
        return width
    }

    fun setRightTetColor(color:Int){
        textRightColor = context.resources.getColor(color)
    }

    fun setLeftText(string: String) {
        this.textLeft = string
    }

    fun setRightText(string: String) {
        this.textRight = string
    }

    fun initPaint() {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
    }


}