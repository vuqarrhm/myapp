package com.example.lafyuu.screens

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu.repostory.ProductRepostory
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val productRepostory: ProductRepostory

):ViewModel() {
    val userResgister=MutableLiveData<Boolean>()
    val errorMessage=MutableLiveData<String>()

    fun register(email:String,password:String){
        viewModelScope.launch {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email,password).await()
                val result=productRepostory.register(email, password)
                userResgister.value=result.user?.uid!=null
            }catch (e:Exception){

            }
        }


    }
}