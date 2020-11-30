package com.learning.geoquiz.model

import android.os.Parcel
import android.os.Parcelable

data class Question(
        var content: String?,
        var answer: Boolean?,
        var guess: Boolean?,
        var cheated: Boolean?
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(content)
                parcel.writeValue(answer)
                parcel.writeValue(guess)
                parcel.writeValue(cheated)
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