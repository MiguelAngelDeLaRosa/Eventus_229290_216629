package mx.edu.itson.potros.eventus

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class InitTimePickerFragment(val listener: (String) -> Unit ): DialogFragment(),
    TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener("$hourOfDay:$minute")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c: Calendar = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context, R.style.timePickerTheme, this, hour, minute, true)
        return dialog
    }
}