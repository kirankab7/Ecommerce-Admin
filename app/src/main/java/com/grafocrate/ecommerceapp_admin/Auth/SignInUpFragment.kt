package com.grafocrate.ecommerceapp_admin.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grafocrate.ecommerceapp_admin.R
import com.grafocrate.ecommerceapp_admin.databinding.FragmentSignInUpBinding

class SignInUpFragment : Fragment() {
    private var _binding: FragmentSignInUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInUpFragment_to_signUpFragment)
        }
        binding.signinButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInUpFragment_to_signInFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}