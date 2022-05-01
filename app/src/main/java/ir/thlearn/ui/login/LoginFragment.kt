package ir.thlearn.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ir.thlearn.Application
import ir.thlearn.R
import ir.thlearn.ThcodeApi
import ir.thlearn.databinding.FragmentLoginBinding
import org.json.JSONObject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private var usernameLayout: TextInputLayout? = null
    private var passwordLayout: TextInputLayout? = null
    private var username: TextInputEditText? = null
    private var password: TextInputEditText? = null
    private var login: TextView? = null
    private var submit: MaterialButton? = null
    private var loading: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // views
        usernameLayout = binding.usernameLayout
        passwordLayout = binding.passwordLayout
        username = binding.username
        password = binding.password
        login = binding.login
        submit = binding.singIn
        loading = binding.loading

        username?.afterTextChanged {
            loginUser(
                username?.text.toString(),
                password?.text.toString()
            )
        }

        password?.apply {
            afterTextChanged {
                loginUser(
                    username?.text.toString(),
                    password?.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginUser(
                            username?.text.toString(),
                            password?.text.toString()
                        )
                }
                false
            }

            submit!!.setOnClickListener {
                loading!!.visibility = View.VISIBLE
                if (loginUser(username?.text.toString(), password?.text.toString())) {
                    val data = JSONObject()
                    data.put("uname", username?.text.toString())
                    data.put("pass", password?.text.toString())

                    Application.volley.add(ThcodeApi("/login", data, object : ThcodeApi.Listeners {
                        override fun onResponse(message: String, data: JSONObject) {
                            Snackbar.make(submit!!, message, Snackbar.LENGTH_SHORT).show()
                            Application.getPreferences().edit().putBoolean("log", true).apply()
                        }

                        override fun onError(error: ThcodeApi.Error, message: String) {
                            var _message = message

                            if (error == ThcodeApi.Error.ERROR_INTERNET)
                                _message = "error"

                            Snackbar.make(submit!!, _message, Snackbar.LENGTH_SHORT).show()
                        }
                    }))
                }
            }
        }
    }

    private fun loginUser(email: String, password: String): Boolean {
        val usernameError = !true
        val passwordError = password.trim().length < 5

        if (!usernameError)
            usernameLayout!!.isErrorEnabled = false
        else

            usernameLayout?.error = getString(R.string.invalid_username)

        if (!passwordError)
            passwordLayout!!.isErrorEnabled = false
        else
            passwordLayout?.error = getString(R.string.invalid_password)

        submit!!.isEnabled = !(usernameError && passwordError)

        return !(usernameError && passwordError)
    }

    /**
     * Extension function to simplify setting an afterTextChanged action to EditText components.
     */
    private fun TextInputEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int,
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}