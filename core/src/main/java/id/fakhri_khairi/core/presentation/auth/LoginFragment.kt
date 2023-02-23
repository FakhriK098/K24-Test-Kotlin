package id.fakhri_khairi.core.presentation.auth

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentLoginBinding
import id.fakhri_khairi.data.database.DataBaseManager
import id.fakhri_khairi.data.misc.Constants
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentLoginBinding.setupEvent() {}

    override suspend fun FragmentLoginBinding.setupState() {}

    override fun FragmentLoginBinding.initBinding() {
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (validate(username, password)) {
                if (username == Constants.USERNAME_ADMIN && password == Constants.PASSWORD_ADMIN) {
                    findNavController().navigate(R.id.action_loginFragment_to_memberListFragment)

                    sharedPreferences.edit {
                        putString(Constants.MEMBER_ID, "0AD")
                        putString(Constants.MEMBER_TYPE, "admin")
                    }
                    return@setOnClickListener
                }

                val memberResult = DataBaseManager(requireContext()).fetchByUsernameAndPassword(username, password)

                if (memberResult.id != 0){
                    sharedPreferences.edit {
                        putString(Constants.MEMBER_ID, "${memberResult.id}MB")
                        putString(Constants.MEMBER_TYPE, "member")
                    }
                    val bundle = bundleOf(Constants.MEMBER_ID to memberResult.id)
                    findNavController().navigate(R.id.action_loginFragment_to_memberDetailFragment, bundle)

                    return@setOnClickListener
                }
            }

            Toast.makeText(requireContext(), getString(R.string.title_wording_wrong_username_password), Toast.LENGTH_SHORT)
        }
    }

    private fun validate(username: String, password: String): Boolean {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            return false
        }

        return true
    }
}