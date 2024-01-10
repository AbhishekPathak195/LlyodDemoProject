package com.example.llyoddemoproject.di.login

import com.example.llyoddemoproject.data.repos.LoginRepository
import dagger.BindsInstance
import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface LoginComponentBuilder {
    fun bindLoginRepo(@BindsInstance loginRepository: LoginRepository): LoginComponentBuilder
    fun build(): LoginComponent?
}