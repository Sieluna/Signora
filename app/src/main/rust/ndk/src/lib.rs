// Why fork? Because target_os not working. we remove all target_os related.

#![allow(non_upper_case_globals)]
#![allow(non_camel_case_types)]
#![allow(non_snake_case)]
#![allow(improper_ctypes)]
#![allow(unused_variables)]
#![allow(clippy::all)]
// Temporarily allow UB nullptr dereference in bindgen layout tests until fixed upstream:
// https://github.com/rust-lang/rust-bindgen/pull/2055
// https://github.com/rust-lang/rust-bindgen/pull/2064
#![allow(deref_nullptr)]

use jni_sys::*;

#[cfg(all(any(target_arch = "arm", target_arch = "armv7")))]
include!("ffi_arm.rs");

#[cfg(target_arch = "aarch64")]
include!("ffi_aarch64.rs");

#[cfg(target_arch = "x86")]
include!("ffi_i686.rs");

#[cfg(target_arch = "x86_64")]
include!("ffi_x86_64.rs");
