use std::sync::Arc;
use jni::{JNIEnv, sys::jobject};
use log::info;
use pollster::block_on;
use wgpu::*;
use super::{
    window::NativeWindow,
    device::SurfaceDeviceQueue,
    touch::Touch
};

pub struct SurfaceView {
    pub native_window: NativeWindow,
    pub scale_factor: f32,
    pub sdq: SurfaceDeviceQueue,
    pub instance: Instance,
    pub callback_to_app: Option<extern "C" fn(arg: i32)>,
}

impl SurfaceView {
    pub fn new(env: *mut JNIEnv, surface: jobject) -> Self {
        let native_window = NativeWindow::new(env, surface);

        let backend = Backends::VULKAN | Backends::GL;
        let instance = Instance::new(InstanceDescriptor {
            backends: backend, ..Default::default()
        });
        let surface = unsafe { instance.create_surface(&native_window).unwrap() };
        let (adapter, device, queue) = block_on(request_device(&instance, backend, &surface));

        let caps = surface.get_capabilities(&adapter);

        info!("Web GPU adapter limits: {:?}", adapter.limits());

        let config = SurfaceConfiguration {
            usage: TextureUsages::RENDER_ATTACHMENT,
            format: TextureFormat::Rgba8UnormSrgb,
            width: native_window.get_width(),
            height: native_window.get_height(),
            present_mode: caps.present_modes[0], // Default PresentMode::Fifo
            alpha_mode: caps.alpha_modes[0], // Default CompositeAlphaMode::auto
            view_formats: vec![],
        };
        surface.configure(&device, &config);

        Self {
            native_window,
            scale_factor: 1.0,
            sdq: SurfaceDeviceQueue {
                surface,
                config,
                adapter,
                device: Arc::new(device),
                queue: Arc::new(queue),
            },
            instance,
            callback_to_app: None,
        }
    }

    pub fn get_view_size(&self) -> (u32, u32) {
        (self.native_window.get_width(), self.native_window.get_height())
    }
}

/**
 * Create Adapters by enumerate_adapters(request_adapter for wasm), e.g. A windows device
 * have 2 graphic card we will have at least 4 adapter, 2 Vulkan and 2 DirectX.
 *
 * Options configuration by environment variable:
 *  - WGPU_POWER_PREF: low or high or empty
 *  - WGPU_BACKEND:
 *     - vulkan = "vulkan" or "vk"
 *     - dx12 = "dx12" or "d3d12"
 *     - dx11 = "dx11" or "d3d11"
 *     - metal = "metal" or "mtl"
 *     - gles = "opengl" or "gles" or "gl"
 *     - webgpu = "webgpu"
 */
async fn request_device(instance: &Instance, backend: Backends, surface: &Surface) -> (Adapter, Device, Queue) {
    let adapter = util::initialize_adapter_from_env_or_default(instance, backend, Some(surface))
        .await
        .expect("No suitable GPU adapters found");
    let res = adapter
        .request_device(
            &DeviceDescriptor {
                label: None,
                features: adapter.features(),
                limits: adapter.limits(),
            },
            None,
        )
        .await;
    match res {
        Err(err) => { panic!("request_device failed: {:?}", err); }
        Ok((device, queue)) => (adapter, device, queue),
    }
}
