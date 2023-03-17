use std::sync::Arc;
use wgpu::*;

pub struct SurfaceDeviceQueue {
    pub surface: Surface,
    pub config: SurfaceConfiguration,
    pub adapter: Adapter,
    pub device: Arc<Device>,
    pub queue: Arc<Queue>,
}

impl SurfaceDeviceQueue {
    pub fn update_config_format(&mut self, format: TextureFormat) {
        self.config.format = format;
        self.surface.configure(&self.device, &self.config);
    }
}
