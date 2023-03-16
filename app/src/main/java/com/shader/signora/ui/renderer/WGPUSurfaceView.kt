package com.shader.signora.ui.renderer

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    external fun createWebGPUCanvas(surface: Surface, idx: Int): Long
    external fun enterFrame(rustObj: Long)
    external fun dropWebGPUCanvas(rustObj: Long)

    init {
        System.loadLibrary("signora")
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        TODO("Not yet implemented")
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        TODO("Not yet implemented")
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

    }
}