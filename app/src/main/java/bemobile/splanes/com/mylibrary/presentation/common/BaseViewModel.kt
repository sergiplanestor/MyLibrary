package bemobile.splanes.com.mylibrary.presentation.common

import androidx.lifecycle.ViewModel

/**
 * @author sergiplanes on 2020-02-11
 */

abstract class BaseViewModel : ViewModel() {

    fun errorConsumer() : (error: Throwable) -> Unit {
        return {

            // Show pop up or something like that?
            it.printStackTrace()
        }
    }
}
