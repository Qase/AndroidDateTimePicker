package wtf.qase.datetimepicker.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import wtf.qase.datetimepicker.R
import wtf.qase.datetimepicker.fragment.DatePickerFragment
import wtf.qase.datetimepicker.fragment.TimePickerFragment
import java.util.Date

open class DateTimePickerAdapter(fm: FragmentManager, initDate: Date? = null) : PickerAdapter(fm, initDate) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TimePickerFragment.newInstance(initDate)
            1 -> DatePickerFragment.newInstance(initDate)
            else -> DatePickerFragment.newInstance(initDate)
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val instance =  super.instantiateItem(container, position)
        when (position) {
            0 -> timePickerFragment = instance as TimePickerFragment
            1 -> datePickerFragment = instance as DatePickerFragment
        }
        return instance
    }

    override fun getTitle(position: Int): Int {
        return when (position) {
            1 -> R.string.datetimepicker_date
            else -> R.string.datetimepicker_time
        }
    }
}
