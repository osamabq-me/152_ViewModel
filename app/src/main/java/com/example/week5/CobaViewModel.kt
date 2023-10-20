package com.example.week5

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week5.Data.Dataform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namalsr: String by mutableStateOf("")
        private set
    var notlp: String by mutableStateOf("")
        private set

    var jenisKl: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(Dataform())
    val uiState: StateFlow<Dataform> = _uiState.asStateFlow()

    fun insertData(nm: String, tlp: String, jk: String){
        namalsr = nm;
        notlp = tlp;
        jenisKl = jk;
    }

    fun setJenisK(pilihJK: String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }
}