package com.shader.signora.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback {
    /** Web GPU object address */
    private var webGPUObj: Long = Long.MAX_VALUE
    /** Current renderer type */
    private var idx: Int = 0

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private external fun createWebGPUCanvas(surface: Surface, idx: Int): Long
    private external fun updateWebGPUCanvas(rustObj: Long)
    private external fun resizeWebGPUCanvas(rustObj: Long)
    private external fun switchRenderer(rustObj: Long, idx: Int)
    private external fun dropWebGPUCanvas(rustObj: Long)

    init {
        System.loadLibrary("signora")
        // Set target class as SurfaceHolder target
        holder.addCallback(this)
        // The only way to set SurfaceView background color to transparent:
        // https://groups.google.com/g/android-developers/c/jYjvm7ItpXQ?pli=1
        this.setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSPARENT)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        holder.let {
            this.webGPUObj = createWebGPUCanvas(it.surface, this.idx)
            // Notify androidx engine ready to draw.
            setWillNotDraw(false)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (this.webGPUObj != Long.MAX_VALUE) {
            resizeWebGPUCanvas(this.webGPUObj)
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (this.webGPUObj != Long.MAX_VALUE) {
            dropWebGPUCanvas(this.webGPUObj)
            this.webGPUObj = Long.MAX_VALUE
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (this.webGPUObj == Long.MAX_VALUE) return
        updateWebGPUCanvas(this.webGPUObj)
        // Notify androidx engine, Next ui refresh lifecycle call draw() function
        invalidate()
    }

    fun switchRenderer(index: Int) {
        if (this.webGPUObj != Long.MAX_VALUE && this.idx != index) {
            switchRenderer(webGPUObj, index)
            this.idx = index
        }
    }
}