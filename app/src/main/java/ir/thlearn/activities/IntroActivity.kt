package ir.thlearn.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.thlearn.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    private var navController: NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var exitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.introContainer.id) as NavHostFragment

        navController = navHostFragment.navController

        val layout = binding.collapsingToolbarLayout
        val toolbar = binding.toolbar
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        layout.setupWithNavController(toolbar, navController, appBarConfiguration)
    }

    override fun binding(): View {
        binding = ActivityIntroBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onBackPressed() {
        Log.d(TAG, "BackPressed")
        if (navController?.navigateUp() != true) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("exit")
                    .setMessage("exit from app")
                    .setPositiveButton("exit") { _, _ ->
                        finish()
                    }
                    .setNegativeButton("no", null)
                    .create()
                    .show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "IntroActivity"
    }
}