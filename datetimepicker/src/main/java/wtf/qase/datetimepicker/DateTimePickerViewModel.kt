package wtf.qase.datetimepicker

import androidx.lifecycle.ViewModel
import java.util.Date

class DateTimePickerViewModel: ViewModel() {
    var onSuccess: ((Date) -> Unit)? = null
}
