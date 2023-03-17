use crate::{
    renderer::Renderer,
    view::SurfaceView
};

pub struct FragmentRenderer {

}

impl FragmentRenderer {
    pub fn new(surface_view: &SurfaceView) -> Self {
        let config = &surface_view.config;
        let device = &surface_view.device;

        Self {

        }
    }
}

impl Renderer for FragmentRenderer {
    fn on_update(&mut self, surface_view: &SurfaceView) {

    }
}