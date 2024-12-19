package di

import ShareManager
import data.KtorApiClient
import data.LoginRepository
import data.LoginRepositoryImpl
import data.LoginService
import data.LoginServiceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import preferenceModule
import presentation.screens.feed.FeedViewModel
import presentation.screens.feed.data.FeedRepository
import presentation.screens.feed.data.FeedRepositoryImpl
import presentation.screens.forgotpassword.ForgotPasswordViewModel
import presentation.screens.login.LoginViewModel
import presentation.screens.splash.SplashViewModel

val dataModules = module {
    single<LoginService> { LoginServiceImpl(KtorApiClient.httpClient) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<FeedRepository> { FeedRepositoryImpl() }
}

val viewModelModules = module {
    viewModel { ForgotPasswordViewModel() }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { FeedViewModel(get(), get()) }
}

val appModules = listOf(dataModules, viewModelModules, preferenceModule)