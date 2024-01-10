package com.example.llyoddemoproject.di.login

import com.example.llyoddemoproject.data.repos.LoginRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(LoginComponent::class)
interface LoginEntryPoint {
    fun getLoginRepo(): LoginRepository
}