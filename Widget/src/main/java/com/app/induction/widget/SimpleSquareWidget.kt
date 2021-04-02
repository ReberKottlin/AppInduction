package com.app.induction.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * @author reber
 * on 2021/4/2 11:41
 *
 * 简单的正方形
 */
class SimpleSquareWidget(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attr, defStyleAttr) {

    private var mDefaultSize: Int = 10

    init {
        attr?.let {
            val typeArray = context.obtainStyledAttributes(attr, R.styleable.SimpleSquareWidget)
            mDefaultSize =
                typeArray.getDimensionPixelSize(R.styleable.SimpleSquareWidget_defaultSize, 10)
            typeArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getMeasureSize(widthMeasureSpec)
        val height = getMeasureSize(heightMeasureSpec)
        val realSize = width.coerceAtMost(height)
        setMeasuredDimension(realSize, realSize)
    }

    private fun getMeasureSize(measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> size
            MeasureSpec.UNSPECIFIED -> 100
            else -> 100
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}