package com.and2long.pageindicator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = arguments?.getString("content")
        tv_content.text = content
    }

    companion object {
        fun create(content: String): PageFragment {
            val bundle = Bundle(1).apply {
                putString("content", content)
            }
            val fragment = PageFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}