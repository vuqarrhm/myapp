package com.example.lafyuu.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu.repostory.ProductRepostory
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val productRepostory: ProductRepostory
): ViewModel() {

    val userLogin=MutableLiveData<Boolean>()
    val errorMessage=MutableLiveData<String>()
    val isAuther=MutableLiveData<Boolean>()
    val progresed=MutableLiveData<Boolean>()


    fun login(email:String,password:String){
        progresed.value=true
        viewModelScope.launch {

            try {
                val result=productRepostory.login(email, password)
                userLogin.value=result.user?.uid!=null
            }
            catch (e: FirebaseAuthInvalidCredentialsException) {
                errorMessage.value = "E-poçt və ya şifrə səhvdir"
            } catch (e: FirebaseAuthInvalidUserException) {
                errorMessage.value = "Belə bir istifadəçi mövcud deyil"
            } catch (e: Exception) {
                errorMessage.value = "Giriş zamanı xəta baş verdi: ${e.localizedMessage}"
            }

            progresed.value=false


        }

    }

    fun getUser(){
        val user=firebaseAuth.currentUser?.uid
        if (user!=null){
            isAuther.value=true
        }else{
            isAuther.value=false
        }
    }
}