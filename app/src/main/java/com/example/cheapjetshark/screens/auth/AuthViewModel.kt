package com.example.cheapjetshark.screens.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    //    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPass(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Auth Success", "signInWithEmailAndPass: ${task.result}")
                        //Todo: "take to HomeScreen"
                        home()
                    } else {
                        Log.d("Auth Error", "signInWithEmailAndPass: ${task.result}")
                    }
                }
            } catch (ex: Exception) {
                Log.d("Auth Error", "signInWithEmailAndPass: ${ex.message}")
            }
        }

    fun createUserWithEmailAndPass(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        home()
                    } else{
                        Log.d("Auth Error", "createUserWithEmailAndPass: ${task.result}")
                    }
                    _loading.value = false
                }
        }
    }
}
