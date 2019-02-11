package example

import chisel3._
import freechips.rocketchip.subsystem._
import freechips.rocketchip.config.Parameters
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip.util.DontTouch
import testchipip._
import icenet._
import memblade.client.{HasPeripheryRemoteMemClient, HasPeripheryRemoteMemClientModuleImp}
import memblade.cache.{HasPeripheryDRAMCache, HasPeripheryDRAMCacheModuleImp}

class ExampleTop(implicit p: Parameters) extends RocketSubsystem
    with CanHaveMasterAXI4MemPort
    with HasPeripheryBootROM
//    with HasSystemErrorSlave
    with HasSyncExtInterrupts
    with HasNoDebug
    with HasPeripherySerial {
  override lazy val module = new ExampleTopModuleImp(this)
}

class ExampleTopModuleImp[+L <: ExampleTop](l: L) extends RocketSubsystemModuleImp(l)
    with HasRTCModuleImp
    with CanHaveMasterAXI4MemPortModuleImp
    with HasPeripheryBootROMModuleImp
    with HasExtInterruptsModuleImp
    with HasNoDebugModuleImp
    with HasPeripherySerialModuleImp
    with DontTouch

class ExampleTopWithPWM(implicit p: Parameters) extends ExampleTop
    with HasPeripheryPWM {
  override lazy val module = new ExampleTopWithPWMModule(this)
}

class ExampleTopWithPWMModule(l: ExampleTopWithPWM)
  extends ExampleTopModuleImp(l) with HasPeripheryPWMModuleImp

class ExampleTopWithBlockDevice(implicit p: Parameters) extends ExampleTop
    with HasPeripheryBlockDevice {
  override lazy val module = new ExampleTopWithBlockDeviceModule(this)
}

class ExampleTopWithBlockDeviceModule(l: ExampleTopWithBlockDevice)
  extends ExampleTopModuleImp(l)
  with HasPeripheryBlockDeviceModuleImp

class ExampleTopWithIceNIC(implicit p: Parameters) extends ExampleTop
    with HasPeripheryIceNIC {
  override lazy val module = new ExampleTopWithIceNICModule(this)
}

class ExampleTopWithIceNICModule(outer: ExampleTopWithIceNIC)
  extends ExampleTopModuleImp(outer)
  with HasPeripheryIceNICModuleImp

class ExampleTopWithRemoteMemClient(implicit p: Parameters) extends ExampleTop
    with HasPeripheryRemoteMemClient {
  override lazy val module = new ExampleTopWithRemoteMemClientModule(this)
}

class ExampleTopWithRemoteMemClientModule(outer: ExampleTopWithRemoteMemClient)
  extends ExampleTopModuleImp(outer)
  with HasPeripheryRemoteMemClientModuleImp

class ExampleTopWithMemBench(implicit p: Parameters) extends ExampleTop
    with HasPeripheryMemBench {
  override lazy val module = new ExampleTopWithMemBenchModule(this)
}

class ExampleTopWithMemBenchModule(outer: ExampleTopWithMemBench)
  extends ExampleTopModuleImp(outer)

class ExampleTopWithDRAMCache(implicit p: Parameters) extends ExampleTop
    with HasPeripheryDRAMCache
    with HasPeripheryMemBench {
  override lazy val module = new ExampleTopWithDRAMCacheModule(this)
}

class ExampleTopWithDRAMCacheModule(outer: ExampleTopWithDRAMCache)
    extends ExampleTopModuleImp(outer)
    with HasPeripheryDRAMCacheModuleImp
