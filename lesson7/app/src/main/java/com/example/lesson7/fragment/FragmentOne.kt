package com.example.lesson7.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.lesson7.R

class FragmentOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }
}