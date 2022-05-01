package ir.thlearn.activities

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding())

        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false)

        window.statusBarColor = Color.TRANSPARENT
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

    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    fun getRandom(_min: Int, _max: Int): Int {
        val random = Random()
        return random.nextInt(_max - _min + 1) + _min
    }

    fun showMessage(_s: String) {
        Toast.makeText(this, _s, Toast.LENGTH_SHORT).show()
    }
}
