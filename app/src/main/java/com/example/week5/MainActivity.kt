@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.week5

import android.graphics.Paint
import android.graphics.Paint.Align
import android.os.Bundle
import android.view.textclassifier.SelectionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.Data.DataSource.jenis
import com.example.week5.Data.Dataform
import com.example.week5.ui.theme.Week5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tempillayout()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun Tempillayout(
modifier: Modifier = Modifier
){
    Card ( modifier = modifier,
        elevation = CardDefaults.cardElevation( defaultElevation = 5.dp)
    ) {
        Column ( verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(28.dp)) {
            TempleForm()

        }

    }

}


@Composable
fun header(){
    Card ( modifier = Modifier,
        ) {

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TempleForm(cobaViewModel: CobaViewModel = viewModel()){
    
    var textNama by remember { mutableStateOf("") }
    var texttlp by remember { mutableStateOf("")  }
    var address by remember { mutableStateOf("")  }
    var email by remember { mutableStateOf("")  }

    val context = LocalContext.current
    val dataform: Dataform
    val uiState by cobaViewModel.uiState.collectAsState()
    dataform = uiState

    top()

    OutlinedTextField(
        value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Full name")},
        onValueChange = {
            textNama = it
        })

    OutlinedTextField(
        value = texttlp,
        singleLine = true,
       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Phone number")},
        onValueChange = {
            texttlp = it
        })

    OutlinedTextField(
        value = email,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Email")},
        onValueChange = {
            email = it
        })
    Card {
        Column {
            SelectJK(options = jenis.map { id -> context.resources.getString(id) },
                onSelectionChanged = {cobaViewModel.setJenisK(it)})
        }
    }



    OutlinedTextField(
        value = address,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Address")},
        onValueChange = {
            address = it
        })

    Button(modifier = Modifier.fillMaxWidth(),onClick = {
        cobaViewModel.insertData(textNama,texttlp,email, dataform.sex,address) }
    ) {
        Text(text = stringResource(id = R.string.submit),
            fontSize = 16.sp)
    }
    Spacer(modifier = Modifier.height(100.dp))
    TextHasil(namanya = cobaViewModel.namalsr, telponnya = cobaViewModel.notlp, jenisnya =cobaViewModel.jenisKl , addressnya = cobaViewModel.addresss, emailnya = cobaViewModel.emaill)
}


@Composable
fun top(){
    Card (){
        Row{
            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back arrow")
            Spacer(modifier = Modifier.padding(40.dp, 30.dp,20.dp,15.dp))
            Text(text = "Register", fontWeight = FontWeight.Bold)
        }
        Text(text = "Create Your Account", fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
    }

}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){

    Text(text = "Jenis", fontWeight = FontWeight.Bold)
    var selectedValue by rememberSaveable { mutableStateOf("") }
            Row (modifier = Modifier.padding(5.dp)) {
                options.forEach { item -> run {
                    Row  (
                        modifier = Modifier.selectable(
                            selected = selectedValue == item,
                            onClick = {
                                selectedValue = item
                                onSelectionChanged(item)
                            }),
                        verticalAlignment =  Alignment.CenterVertically
                    ){
                        RadioButton(selected = selectedValue == item ,
                            onClick = {
                                selectedValue = item
                                onSelectionChanged(item)
                            })
                        Text(item)


                    }
                }
            }


        }


}




@Composable
fun TextHasil(namanya: String, telponnya: String,emailnya: String, jenisnya: String,addressnya : String ){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
    ),
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = "Full name : " + namanya,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
            )
        Text(text = "Telepon : " + telponnya,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
            )
        Text(text = "Jenis : " + jenisnya,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
        )
        Text(text = "Email  : " + emailnya,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
        )

        Text(text = "Address  : " + addressnya,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week5Theme {
        Tempillayout()
    }
}