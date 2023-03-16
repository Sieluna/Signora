mod canvas;
mod surface;

use android_logger::Config;
use jni::JNIEnv;
use jni::objects::JClass;
use jni::sys::{jint, jlong, jobject};
use jni_fn::jni_fn;
use crate::canvas::WebGPUCanvas;
use crate::surface::AppSurface;

#[no_mangle]
#[jni_fn("com.shader.signora.ui.renderer")]
pub fn createWebGPUCanvas(env: *mut JNIEnv, _: JClass, surface: jobject, idx: jint) -> jlong {
    android_logger::init_once(Config::default());
    let canvas = WebGPUCanvas::new(AppSurface::new(env as *mut _, surface));
    Box::into_raw(Box::new(canvas)) as jlong
}

#[no_mangle]
#[jni_fn("com.shader.signora.ui.renderer")]
pub fn dropWebGPUCanvas(_env: *mut JNIEnv, _: JClass, obj: jlong) {
    let _obj: Box<WebGPUCanvas> = unsafe { Box::from_raw(obj as *mut _) };
}