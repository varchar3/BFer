package com.lzc.bfer.widget

import android.app.Activity
import android.content.Context
import android.hardware.*
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import org.jetbrains.annotations.NotNull
import android.hardware.SensorManager
import java.lang.Math.*

/**
- @author:  LZC
- @time:  2021/11/8
- @desc:
 */

class SensorLayout :FrameLayout, SensorEventListener2 {
    constructor(@NotNull context: Context):super(context)
    constructor(@NotNull context: Context, attrs: AttributeSet? = null):super(context,attrs)
    constructor(@NotNull context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0):super(context,attrs,defStyleAttr)

    private lateinit var f:ImageView
    private lateinit var m:ImageView
    private lateinit var b:ImageView
    private val NS2S = 1.0f / 1000000000.0f
    private var timestamp : Float = 0f
    private var dt = 0f
    private var anX:Double = 0.0
    private var anY:Double = 0.0
    private var totalX:Double = 0.0
    private var totalY:Double = 0.0
    private var angleX:Double = 0.0
    private var angleY:Double = 0.0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sensorManager = context.getSystemService(Activity.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI)
        f = (this.getChildAt(0)?:ImageView(context)) as ImageView//前
        m = (this.getChildAt(1)?:ImageView(context)) as ImageView//中
        b = (this.getChildAt(2)?:ImageView(context)) as ImageView//后
    }

    /**
     * 每一份角速度 * 每一份Time = 圆弧距离
     * 计算以ns为单位,允许为负数
     * */
    override fun onSensorChanged(event: SensorEvent) {
            dt = (event.timestamp - timestamp) * NS2S
            angleX += event.values[1] * dt//各Y角度相加
            angleY += event.values[0] * dt//各X角度相加
            anX = toDegrees(angleX)
            anY = toDegrees(angleY)

            if (totalY == 0.0) {//第一次时候赋值
                totalY = anY; return
            }
            if (totalX == 0.0) {
                totalX = anX; return
            }

            var scrollX = 0f
            var scrollY = 0f
            var dx = totalX - anX
            var dy = totalY - anY

            scrollX = handleX(dx.toFloat()) * 1f * 2.1f
            scrollY = handleY(dy.toFloat()) * 1f * 0.8f

            if (scrollX != 0f) totalX = anX
            if (scrollY != 0f) totalY = anY

            f.scrollBy(-scrollX.toInt(), scrollY.toInt())
            b.scrollBy(scrollX.toInt(), -scrollY.toInt())

            timestamp = event.timestamp.toFloat()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onFlushCompleted(sensor: Sensor?) {
    }


    private fun handleY(tranY: Float) =
        if (tranY > 0) tranY.coerceAtMost(maxMoveY) else tranY.coerceAtLeast(-maxMoveY)

    private fun handleX(tranX: Float) =
        if (tranX > 0) tranX.coerceAtMost(maxMoveX) else tranX.coerceAtLeast(-maxMoveX)

    companion object {
        const val maxMoveX = 40f
        const val maxMoveY = 20f
    }

}