package com.example.sportify.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportify.data.Service
import com.example.sportify.databinding.FragmentAccountEditBinding
import com.example.sportify.entity.User

class AccountInformationFragment : Fragment() {

    private var _binding: FragmentAccountEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentAccountEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener {
            if (invalidate()) {
                Service.getUsersDataRef().child(Service.getCurrentUser()?.uid ?: "").setValue(
                    binding.run {
                        User(
                            fullName = fullNameTv.text.toString(),
                            userName = userNameTv.text.toString(),
                            email = emailEditText.text.toString(),
                            phoneNumber = phoneNumber.text.toString(),
                            bio = bioEditText.text.toString()
                        )
                    }

                )
            }
        }
    }

    private fun invalidate(): Boolean {
        binding.run {
            if (fullNameTv.text.isNullOrEmpty()
                || userNameTv.text.isNullOrEmpty()
                || phoneNumber.text.isNullOrEmpty()
                || emailEditText.text.isNullOrEmpty()
                || fullNameTv.text.isNullOrEmpty()
                || bioEditText.text.isNullOrEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "fill all fields",
                    Toast.LENGTH_SHORT,
                ).show()
                return false
            }
        }
        return true
    }

}