package wtf.qase.datetimepicker.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.ViewGroup
import wtf.qase.datetimepicker.R
import wtf.qase.datetimepicker.fragment.TimePickerFragment
import java.util.Date

class TimeOnlyPickerAdapter(fm: androidx.fragment.app.FragmentManager, initDate: Date? = null) : PickerAdapter(fm, initDate) {

    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return TimePickerFragment.newInstance(initDate)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val instance =  super.instantiateItem(container, position)
        timePickerFragment = instance as TimePickerFragment
        return instance
    }

    override fun getTitle(position: Int): Int {
        return R.string.datetimepicker_time
    }
}
