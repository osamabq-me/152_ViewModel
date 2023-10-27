package com.example.week5

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week5.Data.Dataform
import com.example.week5.Data.datastatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namalsr: String by mutableStateOf("")
        private set
    var notlp: String by mutableStateOf("")
        private set
    var emaill: String by mutableStateOf("")
        private set
    var addresss: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(Dataform())
    val uiState: StateFlow<Dataform> = _uiState.asStateFlow()

    private val _uState = MutableStateFlow(datastatus())
    val uState: StateFlow<Dataform> = _uiState.asStateFlow()
    fun insertData(nm: String, tlp: String,email: String, jk: String,ads: String){
        namalsr = nm;
        notlp = tlp;
        emaill =email;
        jenisKl = jk;
        addresss = ads;
    }

    fun setJenisK(pilihJK: String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }

    fun setstatus(pilihsta: String){
        _uState.update { currentState -> currentState.copy( statuse  = pilihsta) }
    }
}