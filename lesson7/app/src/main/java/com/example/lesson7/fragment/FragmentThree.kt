package com.example.lesson7.fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lesson7.R

class FragmentThree : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString("param1")
            param2 = it.getString("param2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv1 = view.findViewById<TextView>(R.id.tv1)
        val tv2 = view.findViewById<TextView>(R.id.tv2)

        tv1.text = param1
        tv2.text = param2
    }

    companion object {
        fun newInstance(p1: String, p2: String): FragmentThree {
            val fragment = FragmentThree()
            val bundle = Bundle()
            bundle.putString("param1", p1)
            bundle.putString("param2", p2)
            fragment.arguments = bundle
            return fragment
        }
    }
}