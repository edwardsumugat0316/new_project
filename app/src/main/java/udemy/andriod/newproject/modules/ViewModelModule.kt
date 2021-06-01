package udemy.andriod.newproject.modules

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import udemy.andriod.newproject.main.MainActivityViewModel
import udemy.andriod.newproject.userInfo.UserInfoViewModel

val viewModelModule = module {
    viewModel{MainActivityViewModel(repository = get())}
}

val viewModelModuleUserInfo = module {
    viewModel { (login: String) -> UserInfoViewModel(login  , repository = get()) }
}