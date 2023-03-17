use crate::{
    renderer::{Empty, Renderer, FragmentRenderer},
    view::{SurfaceView, View}
};

pub struct WebGPUCanvas {
    pub surface_view: SurfaceView,
    pub renderer: Box<dyn Renderer>
}

impl WebGPUCanvas {
    pub fn new(surface_view: SurfaceView, renderer_id: i32) -> Self {
        let default_renderer = Box::new(Empty::new(&surface_view));
        let mut instance = WebGPUCanvas {
            surface_view,
            renderer: default_renderer
        };
        instance.switch_renderer(renderer_id);
        if let Some(callback) = instance.surface_view.callback_to_app {
            callback(0);
        }
        instance
    }

    pub fn on_update(&mut self) {
        self.renderer.on_update(&self.surface_view);
    }

    pub fn resize(&mut self) {
        self.surface_view.resize_surface();
    }

    pub fn switch_renderer(&mut self, index: i32) -> Box<dyn Renderer>{
        match index {
            0 => Box::new(FragmentRenderer::new(&mut self.surface_view)),
            _ => Box::new(FragmentRenderer::new(&mut self.surface_view))
        }
    }
}