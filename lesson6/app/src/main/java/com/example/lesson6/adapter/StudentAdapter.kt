package com.example.lesson6.adapter

import android.view.*
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.lesson6.R
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.model.Student

class StudentAdapter(
    private val students: List<Student>,
    private val onClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtMajor: TextView = view.findViewById(R.id.txtMajor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.txtName.text = student.name
        holder.txtMajor.text = student.major
        holder.imgAvatar.setImageResource(student.avatar)

        holder.itemView.setOnClickListener {
            onClick(student)
        }
    }

    override fun getItemCount(): Int = students.size
}