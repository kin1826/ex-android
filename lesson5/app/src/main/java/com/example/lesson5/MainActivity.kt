package com.example.lesson5

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lesson5.ui.theme.LESSON5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val from = findViewById<EditText>(R.id.inputFrom)
        val to = findViewById<EditText>(R.id.inputTo)
        val subject = findViewById<EditText>(R.id.inputSubject)
        val content = findViewById<EditText>(R.id.inputContent)
        val sendBtn = findViewById<Button>(R.id.btnSend)

        sendBtn.setOnClickListener {
            val fromText = from.text.toString()
            val toText = to.text.toString()
            val subjectText = subject.text.toString()
            val contentText = content.text.toString()

            val message = """
                From: $fromText
                To: $toText
                Subject: $subjectText
                
                $contentText
            """.trimIndent()

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LESSON5Theme {
        Greeting("Android")
    }
}