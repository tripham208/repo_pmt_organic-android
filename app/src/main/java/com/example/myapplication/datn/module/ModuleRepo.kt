package com.example.myapplication.datn.module

import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface ModuleRepo {
    @Binds
    fun provideRepo(repository: UserRepository): IUserRepository
}