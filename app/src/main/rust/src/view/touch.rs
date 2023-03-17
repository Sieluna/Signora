use crate::math::Position;

#[repr(C)]
#[derive(Copy, Clone, Debug)]
pub enum TouchPhase {
    Started,
    Moved,
    Ended,
    Cancelled,
}

#[repr(C)]
#[derive(Copy, Clone, Debug)]
pub struct Touch {
    pub phase: TouchPhase,
    pub position: Position,
    pub pressure: f32,
    // The radius of the contact ellipse along the major axis, in logical pixels.
    pub major_radius: f32,
    // Time interval from the previous touch point
    pub interval: f32,
}

impl Touch {
    fn new(position: Position, phase: TouchPhase) -> Self {
        Touch {
            position,
            phase,
            pressure: 0.0,
            major_radius: 0.0,
            interval: 0.0,
        }
    }

    pub fn touch_start(position: Position) -> Self {
        Self::new(position, TouchPhase::Started)
    }

    pub fn touch_move(position: Position) -> Self {
        Self::new(position, TouchPhase::Moved)
    }

    pub fn touch_end(position: Position) -> Self {
        Self::new(position, TouchPhase::Ended)
    }
}
