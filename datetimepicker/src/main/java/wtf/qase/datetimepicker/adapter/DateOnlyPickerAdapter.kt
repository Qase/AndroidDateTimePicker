package wtf.qase.datetimepicker.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import wtf.qase.datetimepicker.R
import wtf.qase.datetimepicker.fragment.DatePickerFragment
import java.util.Date

open class DateOnlyPickerAdapter(fm: FragmentManager, initDate: Date? = null) : PickerAdapter(fm, initDate) {

    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): Fragment {
        return DatePickerFragment.newInstance(initDate)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val instance =  super.instantiateItem(container, position)
        datePickerFragment = instance as DatePickerFragment
        return instance
    }

    override fun getTitle(position: Int): Int {
        return R.string.datetimepicker_date
    }
}
