package di

import data.KtorApiClient
import data.LoginRepository
import data.LoginRepositoryImpl
import data.LoginService
import data.LoginServiceImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.screens.forgotpassword.ForgotPasswordViewModel
import presentation.screens.login.LoginViewModel

val dataModules = module {
    single<LoginService> { LoginServiceImpl(KtorApiClient.httpClient) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}

val viewModelModules = module {
    viewModel { ForgotPasswordViewModel() }
    viewModel { LoginViewModel(get()) }
}

val appModules = listOf(dataModules, viewModelModules)