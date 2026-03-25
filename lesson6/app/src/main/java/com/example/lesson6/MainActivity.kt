package com.example.lesson6

import android.content.Intent
import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.adapter.StudentAdapter
import com.example.lesson6.model.Student
import com.example.lesson6.ui.theme.LESSON6Theme

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val studentList = listOf(
        Student(1, "Bang", 20, "IT", R.drawable.avatar1),
        Student(2, "An", 21, "Design", R.drawable.avatar2),
        Student(3, "Minh", 22, "Business", R.drawable.avatar3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = StudentAdapter(studentList) { student ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", student.id)
            intent.putExtra("name", student.name)
            intent.putExtra("age", student.age)
            intent.putExtra("major", student.major)
            intent.putExtra("avatar", student.avatar)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
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
    LESSON6Theme {
        Greeting("Android")
    }
}