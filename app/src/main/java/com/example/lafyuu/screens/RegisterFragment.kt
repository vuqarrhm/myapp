package com.example.lafyuu.screens

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lafyuu.R
import com.example.lafyuu.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        super.onViewCreated(view, savedInstanceState)

        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.imageViewPassword.setOnClickListener {
            // Şəkilə kliklədikdə `inputType` xüsusiyyətini dəyişir

            if (binding.editTextTextPassword.inputType== InputType.TYPE_CLASS_TEXT){
                binding.editTextTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }else{
                binding.editTextTextPassword.inputType = InputType.TYPE_CLASS_TEXT
            }

        }

        binding.editTextTextEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editTextTextEmail.setBackgroundResource(R.drawable.blue_background)
                binding.imageViewEmail.setColorFilter(Color.parseColor("#40BFFF"))
            } else {
                binding.editTextTextEmail.setBackgroundResource(R.drawable.blue_background)
                binding.imageViewEmail.setColorFilter(Color.parseColor("#9098B1"))

            }
        }

        binding.editTextTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editTextTextPassword.setBackgroundResource(R.drawable.blue_background)
                binding.imageViewPassword.setColorFilter(Color.parseColor("#40BFFF"))


            } else {
                binding.editTextTextPassword.setBackgroundResource(R.drawable.blue_background)
                binding.imageViewPassword.setColorFilter(Color.parseColor("#9098B1"))

            }
        }

        binding.button.setOnClickListener {

            val email=binding.editTextTextEmail.text.toString().trim()
            val password=binding.editTextTextPassword.text.toString().trim()

            if (email.isNotEmpty()&&password.isNotEmpty()){
                if (password.length>=6){
                    viewModel.register(email, password)
                }else{
                    Toast.makeText(context,"Sifre min 6 olmalidir", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"Bos ola bilmez", Toast.LENGTH_LONG).show()

            }
        }
    }

    fun observeData(){
        viewModel.userResgister.observe(viewLifecycleOwner){
            if (it==true){
                Toast.makeText(context,"Ugurlu qeydiyyat", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Ugursuz qeydiyyat", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){error->
            binding.editTextTextPassword.setBackgroundResource(R.drawable.edittext_xeta)
            binding.editTextTextEmail.setBackgroundResource(R.drawable.edittext_xeta)
            binding.editTextTextEmail.error=error
            binding.editTextTextPassword.error=error
        }
    }
}
