use crate::surface::AppSurface;

pub struct WebGPUCanvas {
    pub app_surface: AppSurface,
}

impl WebGPUCanvas {
    pub fn new(app_surface: AppSurface) -> Self {
        let mut instance = WebGPUCanvas {
            app_surface
        };
    }

    pub fn resize(&mut self) {
        self.app_surface.resize_surface();
    }
}