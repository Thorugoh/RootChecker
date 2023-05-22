package com.rnrootchecker;

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class RootCheckerPackage : TurboReactPackage() {
     override fun getModule(name: String?, reactContext: ReactApplicationContext): NativeModule? =
               if (name == RootCheckerModule.NAME) {
                     RootCheckerModule(reactContext)
                   } else {
                     null
                  }

     override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
           mapOf(
                     RootCheckerModule.NAME to ReactModuleInfo(
                               RootCheckerModule.NAME,
                               RootCheckerModule.NAME,
                               false, // canOverrideExistingModule
                               false, // needsEagerInit
                               true, // hasConstants
                               false, // isCxxModule
                               true // isTurboModule
                                     )
                          )
         }
}