use crate::view::SurfaceView;

mod geometry;

pub trait Renderer {
    fn resize(&mut self, surface_view: &SurfaceView) {}
    fn on_update(&mut self, surface_view: &SurfaceView);
}

pub struct Empty;

impl Empty {
    pub fn new(surface_view: &SurfaceView) -> Self { Self {} }
}

impl Renderer for Empty {
    fn on_update(&mut self, surface_view: &SurfaceView) {}
}

mod fragment;
pub use fragment::*;
