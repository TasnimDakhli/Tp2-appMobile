package com.gl4.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp2mobile.Student
import com.gl4.tp2mobile.StudentsAdapter

class MainActivity : AppCompatActivity() {
    val searchEditText : EditText by lazy { findViewById(R.id.searchEditText) }
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    var matieres = listOf<String>("Cours","TP")
    val students = arrayListOf(
        Student(1, "Alice", "Smith", "F", true),
        Student(2, "Bob", "Johnson", "M", true),
        Student(3, "Carol", "Brown", "F", false),
        Student(4, "David", "Lee", "M", true),
        Student(5, "Eve", "Wilson", "F", false)
    )
    val adapter = StudentsAdapter(students)
    val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedMatiere = matieres[position]

                if (selectedMatiere == "Cours") {
                    // Afficher tous les étudiants
                    adapter.filter.filter("")
                } else if (selectedMatiere == "TP") {
                    // Afficher uniquement les étudiants liés aux TP
                    adapter.filter.filter("true") // Vous devez ajuster cela en fonction de votre logique de filtrage
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                // Gérer le cas où rien n'est sélectionné
            }
        }
    setupEditTextFilter()








        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
    fun setupEditTextFilter() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
                println("before text changed")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the student list based on the EditText input
                val filterQuery = s?.toString() ?: ""
                adapter.filter.filter(filterQuery)
                println("text changed")
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used
                println("after text changed")
            }
        })
    }


}