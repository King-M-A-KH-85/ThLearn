package ir.thlearn.activities

import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding())

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false)
        window.statusBarColor = Color.TRANSPARENT
//        window.navigationBarColor = Color.TRANSPARENT
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    abstract fun binding(): View

    fun getRandom(_min: Int, _max: Int): Int {
        val random = Random()
        return random.nextInt(_max - _min + 1) + _min
    }

    fun getDip(_input: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            _input.toFloat(),
            resources.displayMetrics)
    }

    fun showMessage(_s: String) {
        Toast.makeText(this, _s, Toast.LENGTH_SHORT).show()
    }

    fun isConnectedNetwork(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun hideKeyboard() {
        val _inputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        _inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun showKeyboard() {
        val _inputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        _inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}