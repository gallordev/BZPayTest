package com.gallordev.myapplication.di

import com.gallordev.myapplication.repository.AuthRepository
import com.gallordev.myapplication.repository.StudentRepository
import com.gallordev.myapplication.repository.impl.AuthRepositoryImpl
import com.gallordev.myapplication.repository.impl.StudentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideStudentRepository(impl: StudentRepositoryImpl): StudentRepository

}