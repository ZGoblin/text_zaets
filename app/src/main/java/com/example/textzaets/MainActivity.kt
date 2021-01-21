package com.example.textzaets

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textzaets.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nameList: ArrayList<SpannableString> = arrayListOf()
    private val adapter: NameAdapter by lazy {
        NameAdapter(nameList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
        setupTextWatchers()
        binding.secondName.error = "Min length - 3"
        binding.firstName.error = "Min length - 3"

        binding.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = adapter
    }

    private fun setupTextWatchers() {
        val twFirstUpperCase = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.also {
                    if (it.length < 3) {
                        if (binding.firstName.isFocused) {
                            binding.firstName.error = "Min length - 3"
                        }
                        else {
                            binding.secondName.error = "Min length - 3"
                        }
                    }
                    else {
                        if (binding.firstName.isFocused) {
                            binding.firstName.error = null
                        }
                        else {
                            binding.secondName.error = null
                        }
                    }
                }
                binding.btnAdd.isEnabled = binding.firstName.error == null && binding.secondName.error == null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        binding.firstName.addTextChangedListener(twFirstUpperCase)
        binding.secondName.addTextChangedListener(twFirstUpperCase)
    }

    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            newNameAdded()
        }
    }

    private fun newNameAdded() {
        val name = StringBuilder()
            .append(binding.firstName.text)
            .append(" ")
            .append(binding.secondName.text)
            .toString()

        val spanName = SpannableString(name)
        val spanFlag = 0

        val stringTarget = binding.secondName.text.toString()
        val startIndex = name.indexOf(stringTarget)
        val endIndex = startIndex + stringTarget.length

        spanName.setSpan(ForegroundColorSpan(Color.MAGENTA), startIndex, endIndex, spanFlag)
        spanName.setSpan(UnderlineSpan(), startIndex, endIndex, spanFlag)


        runOnUiThread {
            nameList.add(spanName)
            adapter.notifyItemInserted(nameList.size - 1)
        }
    }

}