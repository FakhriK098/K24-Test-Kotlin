package id.fakhri_khairi.core.presentation.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentLoginBinding.setupEvent() {}

    override suspend fun FragmentLoginBinding.setupState() {}

    override fun FragmentLoginBinding.initBinding() {}
}