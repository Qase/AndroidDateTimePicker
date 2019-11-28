package wtf.qase.datetimepicker.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import wtf.qase.datetimepicker.DateTimePickerDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { _ ->
            openDateTimePickerDialog()
        }
    }

    private fun openDateTimePickerDialog() {
        val callback: (date: Date) -> Unit = { newDate ->
            val sdf = SimpleDateFormat("dd.MM.yyyy - HH:mm", Locale.getDefault())
            textView?.text = sdf.format(newDate)
        }

        val currentDate = Date()

        DateTimePickerDialog.show(
            supportFragmentManager,
            "fragment_datepicker",
            callback,
            currentDate,
            DateTimePickerDialog.TIME_DATE
        )
    }
}
