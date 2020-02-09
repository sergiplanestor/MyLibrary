package bemobile.splanes.com.mylibrary.presentation.view

import android.view.View
import bemobile.splanes.com.mylibrary.R
import bemobile.splanes.com.mylibrary.presentation.common.BaseActivity
import bemobile.splanes.com.mylibrary.presentation.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterViewModel>(), View.OnClickListener {

// =================================================================================================
// Init views
// =================================================================================================

    override fun initializeViews() {
        super.initializeViews()
        registerButton.setOnClickListener(this)
    }

// =================================================================================================
// View.OnClickListener impl
// =================================================================================================

    override fun onClick(v: View?) {

    }

// =================================================================================================
// Base abstract methods impl
// =================================================================================================

    override fun getLayoutRes(): Int {
        return R.layout.activity_register
    }

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }
}