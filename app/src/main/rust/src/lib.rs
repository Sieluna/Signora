mod canvas;
mod math;
mod view;
mod renderer;

use android_logger::Config;
use jni::{JNIEnv, objects::JClass, sys::{jint, jlong, jobject}};
use jni_bind::jni_bind;
use log::{info, LevelFilter};
use crate::{
    canvas::WebGPUCanvas,
    view::SurfaceView
};

#[no_mangle]
#[jni_bind("com.shader.signora.ui.renderer")]
pub fn createWebGPUCanvas(env: *mut JNIEnv, _: JClass, surface: jobject, idx: jint) -> jlong {
    android_logger::init_once(Config::default().with_max_level(LevelFilter::Info));
    let canvas = WebGPUCanvas::new(SurfaceView::new(env as *mut _, surface), idx as i32);
    info!("WebGPUCanvas initialized...");
    Box::into_raw(Box::new(canvas)) as jlong
}

#[no_mangle]
#[jni_bind("com.shader.signora.ui.renderer")]
pub fn updateWebGPUCanvas(_env: *mut JNIEnv, _: JClass, obj: jlong) {
    let obj = unsafe { &mut *(obj as *mut WebGPUCanvas) };
    obj.on_update();
}

#[no_mangle]
#[jni_bind("com.shader.signora.ui.renderer")]
pub fn resizeWebGPUCanvas(_env: *mut JNIEnv, _: JClass, obj: jlong) {
    let obj = unsafe { &mut *(obj as *mut WebGPUCanvas) };
    obj.resize();
}

#[no_mangle]
#[jni_bind("com.shader.signora.ui.renderer")]
pub fn switchRenderer(_env: *mut JNIEnv, _: JClass, obj: jlong, idx: jint) {
    let obj = unsafe { &mut *(obj as *mut WebGPUCanvas) };
    obj.switch_renderer(idx as i32);
}

#[no_mangle]
#[jni_bind("com.shader.signora.ui.renderer")]
pub fn dropWebGPUCanvas(_env: *mut JNIEnv, _: JClass, obj: jlong) {
    let _obj: Box<WebGPUCanvas> = unsafe { Box::from_raw(obj as *mut _) };
}