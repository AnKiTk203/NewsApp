package com.codingblocks.myprojecttodoapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.myprojecttodoapp.databinding.ActivityTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


const val DB_NAME = "todo.db"

class TaskActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTaskBinding
    lateinit var myCalendar: Calendar
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent:PendingIntent
    var dateTimeMillis: Long = 0
    var timeMillis: Long = 0
    var posi: String = ""
    lateinit var dateSetListener: OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener
    var uid = -1L
    private val labels = arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking")

    val db by lazy {
        ADatabase.getDatabase(this)
    }
    var todoDao = db.toDoDao()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createChannel()
        setUpSpinner()


        binding.etDate.setOnClickListener(this)
        binding.etTime.setOnClickListener(this)
        binding.idSaveBtn.setOnClickListener {
            saveMyTodo()
        }

    }


    private fun setUpSpinner() {
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, labels
        )
        labels.sort()
        binding.spCategory.adapter = adapter
        getPosition()
    }

    private fun getPosition() {
        binding.spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

    private fun saveMyTodo() {
        with(binding) {


            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.US)
            val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

            val date = dateFormat.parse(etDate.editableText.toString())
            val time = timeFormat.parse(etTime.editableText.toString())

             dateTimeMillis = date?.time ?: 0L
            timeMillis = time?.time ?: 0L

            val toDoModel = ToDoModel(
                etTaskTitle.text.toString(),
                etAbout.text.toString(),
                posi,
                dateTimeMillis,
                timeMillis,
                isFinished = 0
            )
            CoroutineScope(Dispatchers.IO).launch {
                todoDao.insertTask(toDoModel)
            }
            setAlarm(dateTimeMillis,timeMillis)
            finish()
        }
    }

    private fun setAlarm(date:Long,time:Long) {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

         myCalendar = Calendar.getInstance()
        myCalendar.timeInMillis = date

        val timeCalendar = Calendar.getInstance()
        timeCalendar.timeInMillis = time

        myCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY))
        myCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE))

       alarmManager.setExact(
           AlarmManager.RTC_WAKEUP,
          myCalendar.timeInMillis,
           pendingIntent
       )
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
            myCalendar.get(Calendar.MINUTE), false
        )

        timePickerDialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateDate() {
        val myFormat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myFormat)
        binding.etDate.setText(sdf.format(myCalendar.time))
        binding.etTimeIL.visibility = View.VISIBLE
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTime(){
        val myFormat = "h:mm a"
        val sdf = SimpleDateFormat(myFormat)
        binding.etTime.setText(sdf.format(myCalendar.time))
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel"
            val description = "description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("notif", name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

    }

}