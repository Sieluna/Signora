mod window;
mod device;
mod surface;
mod touch;

pub use surface::SurfaceView;
use std::ops::Deref;
use super::view::{
    device::SurfaceDeviceQueue,
    touch::Touch
};

impl Deref for SurfaceView {
    type Target = SurfaceDeviceQueue;
    fn deref(&self) -> &Self::Target {
        &self.sdq
    }
}

pub trait View {
    fn resize_surface(&mut self);
    fn touch(&mut self, touch: Touch) {}
    fn get_current_frame_view(&self) -> (wgpu::SurfaceTexture, wgpu::TextureView);
    fn create_current_frame_view(
        &self,
        device: &wgpu::Device,
        surface: &wgpu::Surface,
        config: &wgpu::SurfaceConfiguration,
    ) -> (wgpu::SurfaceTexture, wgpu::TextureView) {
        let frame = match surface.get_current_texture() {
            Ok(frame) => frame,
            Err(_) => {
                surface.configure(&device, &config);
                surface.get_current_texture().expect("Failed to acquire next swap chain texture!")
            }
        };
        let view = frame.texture.create_view(&wgpu::TextureViewDescriptor {
            label: Some("frame texture view"), ..Default::default()
        });
        (frame, view)
    }
}

impl View for SurfaceView {
    fn resize_surface(&mut self) {
        let (width, height) = self.get_view_size();
        self.sdq.config.width = width;
        self.sdq.config.height = height;
        self.surface.configure(&self.device, &self.config);
    }

    fn get_current_frame_view(&self) -> (wgpu::SurfaceTexture, wgpu::TextureView) {
        self.create_current_frame_view(&self.device, &self.surface, &self.config)
    }
}
