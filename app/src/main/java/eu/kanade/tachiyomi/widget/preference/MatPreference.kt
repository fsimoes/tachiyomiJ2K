package eu.kanade.tachiyomi.widget.preference

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import eu.kanade.tachiyomi.data.preference.PreferencesHelper
import eu.kanade.tachiyomi.data.preference.getOrDefault
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

open class MatPreference @JvmOverloads constructor(val activity: Activity?, context: Context,
    attrs:
    AttributeSet? =
        null) :
    Preference(context, attrs) {

    protected val prefs: PreferencesHelper = Injekt.get()
    private var isShowing = false

    override fun onClick() {
        if (!isShowing)
            dialog().apply {
                onDismiss { this@MatPreference.isShowing = false }
            }.show()
        isShowing = true
    }

    open fun dialog(): MaterialDialog {
        return MaterialDialog(activity ?: context).apply {
            if (title != null)
                title(text = title.toString())
            negativeButton(android.R.string.cancel)
        }
    }
}