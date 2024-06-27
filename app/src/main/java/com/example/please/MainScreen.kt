package com.example.please

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen()
{
    val context = LocalContext.current
    val database = Firebase.database

    var name by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var phone by remember{ mutableStateOf("") }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(
            value = name,
            onValueChange = {name = it},
            label = { Text(text = "Name") })

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "E-mail") })

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = phone,
            onValueChange = {phone = it},
            label = { Text(text = "Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick =
        {
            val contactsRef = database.reference.child("Contacts")
            val contactRef = contactsRef.child(name)
            val contact = Contact(email,phone)
            contactRef.setValue(contact)
            Toast.makeText(context,"save contact",Toast.LENGTH_SHORT).show()
            name = ""
            email = ""
            phone = ""
        },
            modifier = Modifier.padding(16.dp))
        {
            Text(text = "contact saved")
        }
    }
}

data class Contact (val email: String,val phone: String)


//  val myRef = database.getReference()

//onClick = { myRef.setValue(text) },
//            modifier = Modifier.padding(16.dp))
