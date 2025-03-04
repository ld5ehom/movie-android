package com.ld5ehom.movie.device.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.movie.component.Haptic
import com.ld5ehom.movie.device.impl.DeviceImpl
import com.ld5ehom.movie.device.impl.HapticImpl
import com.ld5ehom.movie.device.impl.ToasterImpl
import com.ld5ehom.movie.component.Device
import com.ld5ehom.movie.component.Toaster
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DeviceModule {

    @Binds
    @Singleton
    abstract fun bindDevice(device: DeviceImpl): Device

    @Binds
    @Singleton
    abstract fun bindToaster(toaster: ToasterImpl): Toaster

    @Binds
    @Singleton
    abstract fun bindHaptic(haptic: HapticImpl): Haptic
}
