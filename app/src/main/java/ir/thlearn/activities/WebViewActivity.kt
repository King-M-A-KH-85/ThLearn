package ir.thlearn.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import ir.thlearn.databinding.WebviewActivityBinding

class WebViewActivity : BaseActivity() {
    lateinit var binding: WebviewActivityBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding.web.loadUrl("")
    }

    override fun binding(): View {
        binding = WebviewActivityBinding.inflate(layoutInflater)
        return binding.root
    }
}