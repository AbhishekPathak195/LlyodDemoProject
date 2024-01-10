package com.example.llyoddemoproject.di.login

import com.example.llyoddemoproject.di.login.LoginScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@LoginScope
@DefineComponent(parent = SingletonComponent::class)
interface LoginComponent {
}