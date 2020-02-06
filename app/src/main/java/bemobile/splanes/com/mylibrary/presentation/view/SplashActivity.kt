package bemobile.splanes.com.mylibrary.presentation.view

import bemobile.splanes.com.mylibrary.R
import bemobile.splanes.com.mylibrary.presentation.common.BaseActivity
import bemobile.splanes.com.mylibrary.presentation.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<SplashViewModel>() {

// =================================================================================================
// Lifecycle
// =================================================================================================

    override fun loadData() {
        super.loadData()
        checkRegisterState()
    }

    private fun checkRegisterState() {
        getViewModel().getRegisterState()
    }

// =================================================================================================
// Base abstract methods impl
// =================================================================================================

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }
}