package bemobile.splanes.com.mylibrary.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

// =================================================================================================
// Attributes
// =================================================================================================

    private lateinit var viewModel : VM
    private var isFirstOnResume = true

// =================================================================================================
// Lifecycle
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initializeViewModel()
        initializeViews()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstOnResume) {
            loadData()
            isFirstOnResume = false
        } else {
            reloadData()
        }
    }

// =================================================================================================
// Views
// =================================================================================================

    open fun initializeViews() {
        // Nothing to do here.
    }

    open fun loadData() {
        // Nothing to do here.
    }

    open fun reloadData() {
        // Nothing to do here.
    }

// =================================================================================================
// ViewModel
// =================================================================================================

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(getViewModelClass())
    }

    fun getViewModel() : VM {
        return this.viewModel
    }

// =================================================================================================
// Abstract methods
// =================================================================================================

    abstract fun getLayoutRes() : Int

    abstract fun getViewModelClass() : Class<VM>
}