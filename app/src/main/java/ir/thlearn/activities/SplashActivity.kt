package ir.thlearn.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import ir.thlearn.databinding.ActivitySplashBinding
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                finish()
            }
        }, 1500)
    }

    override fun binding(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }
}