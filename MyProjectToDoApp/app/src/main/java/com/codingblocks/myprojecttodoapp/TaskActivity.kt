package com.codingblocks.myprojecttodoapp

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.myprojecttodoapp.databinding.ActivityTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

const val DB_NAME ="todo.db"
class TaskActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTaskBinding

    lateinit var myCalendar: Calendar
    var posi: String = ""
    lateinit var dateSetListener: OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var finalDate = 0L
    var finalTime = 0L

    private val labels = arrayListOf("Personal","Business","Insurance","Shopping","Banking")
    val db by lazy {
          AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.etDate.setOnClickListener(this)
        binding.etTime.setOnClickListener(this)
        binding.idSaveBtn.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {
               saveMyTodo()
           }
        }
        setUpSpinner()
    }
    private fun setUpSpinner(){
        val adapter = ArrayAdapter(
            this,android.R.layout.simple_spinner_dropdown_item,labels)
        labels.sort()
        binding.spCategory.adapter = adapter
        getPosition()
    }

    private fun getPosition() {
        binding.spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                posi = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.etDate -> {
                setListener()
            }

            R.id.etTime -> {
                setTimeListener()
            }
        }
    }
    private suspend fun saveMyTodo() {
        val todoDao = db.todoDao()
        val toDoModel = ToDoModel(
            binding.etTaskTitle.editableText.toString(),
            binding.etAbout.editableText.toString(),
            posi,
            binding.etDate.editableText.toString(),
            binding.etTime.editableText.toString(),
            isFinished = -1,
        )
        todoDao.insertTask(toDoModel)
        finish()
    }

    private fun setListener() {
        myCalendar = Calendar.getInstance()
        dateSetListener =
            OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }
        val datePickerDialog = DatePickerDialog(
            this, dateSetListener, myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()
        timeSetListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, min: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, min)
                updateTime()
            }
        val timePickerDialog = TimePickerDialog(
            this, timeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),false)

        timePickerDialog.show()
    }
    private fun updateDate() {
        val myFormat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myFormat)
        binding.etDate.setText(sdf.format(myCalendar.time))
        binding.etTimeIL.visibility = View.VISIBLE
    }

    private fun updateTime() {
        val myFormat = "h:mm a"
        val sdf = SimpleDateFormat(myFormat)
        binding.etTime.setText(sdf.format(myCalendar.time))
    }

    }