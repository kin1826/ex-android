package com.example.lesson7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.MenuItem
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lesson7.ui.theme.Lesson7Theme
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.editTextName)
        val btnOpen = findViewById<Button>(R.id.buttonClickMe)
        val btnDial = findViewById<Button>(R.id.buttonDial)
        val url = findViewById<EditText>(R.id.editTextURL)
        val btnWeb = findViewById<Button>(R.id.buttonURL)

        // Explicit Intent
        btnOpen.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NAME", name.text.toString())
            startActivity(intent)
        }

        // Dial
        btnDial.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            startActivity(intent)
        }

        // Web
        btnWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, url.text.toString().toUri())
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.message -> Toast.makeText(this,"Message",Toast.LENGTH_SHORT).show()
            R.id.about -> Toast.makeText(this,"About",Toast.LENGTH_SHORT).show()
            R.id.exit -> finish()
        }
        return true
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lesson7Theme {
        Greeting("Android")
    }
}