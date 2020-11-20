package com.learning.geoquiz.model

import android.os.Parcel
import android.os.Parcelable

data class Question(
        val content: String?,
        val answer: Boolean,
        var guess: Boolean?
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readByte() != 0.toByte(),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(content)
                parcel.writeByte(if (answer) 1 else 0)
                parcel.writeValue(guess)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Question> {
                override fun createFromParcel(parcel: Parcel): Question {
                        return Question(parcel)
                }

                override fun newArray(size: Int): Array<Question?> {
                        return arrayOfNulls(size)
                }
        }
}