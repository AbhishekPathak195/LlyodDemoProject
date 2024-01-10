package com.example.llyoddemoproject.di.login

import com.example.llyoddemoproject.data.network.ApiHelper
import com.example.llyoddemoproject.data.prefs.PreferencesHelper
import com.example.llyoddemoproject.data.repos.LoginRepository
import com.example.llyoddemoproject.util.coroutines.AppDispatcherProvider
import com.example.llyoddemoproject.util.coroutines.DispatcherProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class LoginComponentManager @Inject constructor(
    private val appDispatcherProvider: DispatcherProvider,
    private val loginComponentProvider: Provider<LoginComponentBuilder>,
    private val apiHelper: ApiHelper,
    private val preferencesHelper: PreferencesHelper,
)
{

    var loginComponent: LoginComponent? = null

    fun getComponent():LoginComponent{
        if(loginComponent==null){
            val loginRepository = LoginRepository(appDispatcherProvider, apiHelper, preferencesHelper)
            loginComponent=  loginComponentProvider.get().bindLoginRepo(loginRepository).build()

        }

        return loginComponent!!
    }

    fun destroyLoginComponent() {
        loginComponent = null
    }
}


