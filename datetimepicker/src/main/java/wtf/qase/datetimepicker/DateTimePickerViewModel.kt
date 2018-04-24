package wtf.qase.datetimepicker

import android.arch.lifecycle.ViewModel
import java.util.Date

class DateTimePickerViewModel: ViewModel() {
    var onSuccess: ((Date) -> Unit)? = null
}
