package com.example.lafyuu.screens

import android.content.SharedPreferences
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
import com.example.lafyuu.databinding.FragmentLoginBinding
import com.example.lafyuu.util.goneItem
import com.example.lafyuu.util.visibleItem
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    lateinit var binding: FragmentLoginBinding
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        viewModel.getUser()

        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }



        binding.imageViewPassword.setOnClickListener {
            if (binding.editTextTextPassword.inputType==InputType.TYPE_CLASS_TEXT){
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


        var chek=sharedPreferences.getBoolean("ceklendimi",false)
        if (chek){
            Toast.makeText(context,"Ugurlu giris",Toast.LENGTH_LONG).show()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }


        binding.button.setOnClickListener {

            val email=binding.editTextTextEmail.text.toString().trim()
            val password=binding.editTextTextPassword.text.toString().trim()

            if (email.isNotEmpty()&&password.isNotEmpty()){
                viewModel.login(email, password)
                val editor=sharedPreferences.edit()
                val ischeked=binding.checkBox.isChecked
                editor.putBoolean("ceklendimi",ischeked)
                editor.apply()


            }else{
                Toast.makeText(context,"Bos ola bilmez",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun observeData(){
        viewModel.userLogin.observe(viewLifecycleOwner){
            if (it==true){
                Toast.makeText(context,"Ugurlu giris",Toast.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())


            }else{
                Toast.makeText(context,"Ugursuz giris",Toast.LENGTH_LONG).show()
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){error->
            binding.editTextTextPassword.setBackgroundResource(R.drawable.edittext_xeta)
            binding.editTextTextEmail.setBackgroundResource(R.drawable.edittext_xeta)
            binding.editTextTextEmail.error = error
            binding.editTextTextPassword.error = error
        }

        viewModel.isAuther.observe(viewLifecycleOwner){
            if (it==true){

            }

        }

        viewModel.progresed.observe(viewLifecycleOwner){
            if (it==true){
                binding.progressBar.visibleItem()
            }else{
                binding.progressBar.goneItem()
            }
        }
    }

}
