package dev.rulsoft.nb22.common.data.networking.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateSerializer : KSerializer<Date> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        val formatted = dateFormat.format(value)
        encoder.encodeString(formatted)
    }

    override fun deserialize(decoder: Decoder): Date {
        val date = decoder.decodeString()
        return dateFormat.parse(date)!!
    }
}