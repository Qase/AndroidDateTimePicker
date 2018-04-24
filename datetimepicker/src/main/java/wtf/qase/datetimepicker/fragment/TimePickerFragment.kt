package wtf.qase.datetimepicker.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import kotlinx.android.synthetic.main.fragment_timepicker.*
import wtf.qase.datetimepicker.R
import java.util.Calendar
import java.util.Date

class TimePickerFragment : Fragment() {

    companion object {

        private const val ARG_INIT_DATETIME = "ARG_INIT_DATETIME"

        fun newInstance(initDatetime: Date? = null): TimePickerFragment {
            val fragment = TimePickerFragment()
            if (initDatetime !=null ) {
                val bundle = Bundle()
                bundle.putSerializable(ARG_INIT_DATETIME, initDatetime)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_timepicker, container, false)
        (view as TimePicker).setIs24HourView(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date  = arguments?.get(ARG_INIT_DATETIME) as Date? ?: Date()
        val cal = Calendar.getInstance()
        cal.time = date
        time.currentHour = cal.get(Calendar.HOUR_OF_DAY)
        time.currentMinute = cal.get(Calendar.MINUTE)
    }

    val hours: Int
        get() = time.currentHour

    val minutes: Int
        get() = time.currentMinute

}
