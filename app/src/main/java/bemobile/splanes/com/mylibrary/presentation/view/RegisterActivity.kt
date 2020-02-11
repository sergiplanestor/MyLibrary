package bemobile.splanes.com.mylibrary.presentation.view

import android.view.View
import androidx.lifecycle.Observer
import bemobile.splanes.com.core.domain.User
import bemobile.splanes.com.mylibrary.R
import bemobile.splanes.com.mylibrary.presentation.common.BaseActivity
import bemobile.splanes.com.mylibrary.presentation.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterViewModel>(), View.OnClickListener {

// =================================================================================================
// Views
// =================================================================================================

    override fun initializeViews() {
        super.initializeViews()
        registerButton.setOnClickListener(this)
    }

    private fun clearFieldError() {
        usernameField.error = null
        pwdField.error = null
        pwd2Field.error = null
    }

    private fun showFieldError(field: String, error: Int) {
        val errorStr = getString(error)
        when(field) {
            "username" -> usernameField.error = errorStr
            "pwd" -> pwdField.error = errorStr
            "pwd2" -> pwd2Field.error = errorStr
            "pwds" ->  {
                pwdField.error = errorStr
                pwd2Field.error = errorStr
            }
        }
    }

// =================================================================================================
// View.OnClickListener impl
// =================================================================================================

    override fun onClick(v: View?) {
        clearFieldError()
        val state = getViewModel().getFieldsState(
            usernameEditText.text?.toString(),
            pwdEditText.text?.toString(),
            pwd2EditText.text?.toString()
        )

        if (state.status) {

            getViewModel().registerUser(
                User(usernameEditText.text.toString(), pwdEditText.text.toString())
            ).observe(this, Observer { success ->

                if (success) {
                    // Navigate to main
                } else {
                    // show error
                }

            })

        } else {
            showFieldError(state.errorField!!, state.errorMessage!!)
        }
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
