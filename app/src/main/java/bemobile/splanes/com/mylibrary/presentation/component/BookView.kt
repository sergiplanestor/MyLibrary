package bemobile.splanes.com.mylibrary.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.mylibrary.R

/**
 * @author sergiplanes on 2020-02-12
 */

class BookView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.component_book_view, this)
        initialize()
    }

    private fun initialize() {

    }

}
