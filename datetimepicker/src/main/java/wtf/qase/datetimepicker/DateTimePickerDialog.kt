package wtf.qase.datetimepicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_datetimepicker.*
import kotlinx.android.synthetic.main.fragment_datetimepicker.view.*
import wtf.qase.datetimepicker.adapter.DateOnlyPickerAdapter
import wtf.qase.datetimepicker.adapter.DateTimePickerAdapter
import wtf.qase.datetimepicker.adapter.PickerAdapter
import wtf.qase.datetimepicker.adapter.TimeOnlyPickerAdapter
import java.util.Date

class DateTimePickerDialog : DialogFragment() {

    companion object {
        private const val ARG_PICKER_TYPE = "ARG_PICKERTYPE"
        const val ARG_DATETIME = "timestamp"

        fun show(fragment: Fragment, tag: String, requestCode: Int, initialDate: Date? = null, pickerType: Int = DATE_TIME) {
            createInstance(initialDate, pickerType)
                .apply { setTargetFragment(fragment, requestCode) }
                .show(fragment.requireFragmentManager(), tag)
        }

        fun show(fragmentManager: FragmentManager, tag: String, onSuccess : (Date) -> Unit, initialDate: Date? = null, pickerType: Int = DATE_TIME) {
            createInstance(initialDate, pickerType)
                .apply { setOnSuccess(onSuccess) }
                .show(fragmentManager, tag)
        }

        private fun createInstance(initialDate: Date?, pickerType: Int): DateTimePickerDialog {
            val bundle = Bundle()
            bundle.putInt(ARG_PICKER_TYPE, pickerType)
            return DateTimePickerDialog()
                .apply { this.initDate = initialDate }
                .apply { arguments = bundle }
        }

        const val DATE_TIME: Int = 0
        const val TIME_ONLY: Int = 1
        const val DATE_ONLY: Int = 2
        const val TIME_DATE: Int = 3
    }

    private var onSuccess: ((Date) -> Unit)? = null
    private var initDate: Date? = null
    private var pickerType: Int? = null

    private lateinit var viewModel: DateTimePickerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(DateTimePickerViewModel::class.java)
        if (onSuccess != null) {
            viewModel.onSuccess = onSuccess
        }

        val view = layoutInflater.inflate(R.layout.fragment_datetimepicker, null)
        pickerType = arguments?.getInt(ARG_PICKER_TYPE)
        val adapter = when (pickerType) {
            TIME_ONLY -> TimeOnlyPickerAdapter(childFragmentManager, initDate)
            DATE_ONLY -> DateOnlyPickerAdapter(childFragmentManager, initDate)
            TIME_DATE -> DateTimePickerAdapter(childFragmentManager, initDate, false)
            else -> DateTimePickerAdapter(childFragmentManager, initDate)
        }
        view.viewPager.adapter = adapter

        view.tabLayout.setupWithViewPager(view.viewPager)
        view.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> initNextButton()
                    1 -> initCloseButton()
                }
            }
        })

        for (i in 0 until adapter.count) {
            view.tabLayout.getTabAt(i)?.setText(adapter.getTitle(i))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNextButton()
    }

    private fun initNextButton() {
        if (pickerType == DATE_TIME || pickerType == TIME_DATE) {
            next.setText(R.string.datetimepicker_next)
            next.setOnClickListener {
                tabLayout.getTabAt(1)?.select()
            }
        } else {
            initCloseButton()
        }
    }

    private fun initCloseButton() {
        next.setText(R.string.datetimepicker_close)
        next.setOnClickListener {
            closeDialog()
        }
    }

    private fun closeDialog() {
        val adapter = viewPager.adapter as PickerAdapter
        if (viewModel.onSuccess==null) {
            targetFragment?.onActivityResult(
                targetRequestCode,
                Activity.RESULT_OK,
                Intent().putExtra(
                    ARG_DATETIME, adapter.getTimestamp()
                )
            )
        } else {
            viewModel.onSuccess?.invoke(adapter.getDate())
        }
        dismiss()
    }

    private fun setOnSuccess(onSuccess: (Date) -> Unit) {
        this.onSuccess = onSuccess
    }
}
