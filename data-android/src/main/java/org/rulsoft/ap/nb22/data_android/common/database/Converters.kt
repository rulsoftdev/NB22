package org.rulsoft.ap.nb22.data_android.common.database
import androidx.room.TypeConverter
import java.util.Date

class Converters {
    // Converter para Date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        if (date == null) {
            return null
        }
        return date?.time
    }
}