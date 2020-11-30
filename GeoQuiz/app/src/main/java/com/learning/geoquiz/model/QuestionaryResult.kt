package com.learning.geoquiz.model

import android.os.Parcel
import android.os.Parcelable

data class QuestionaryResult(
    var totalQuestions: Int,
    var rightQuestions: Int,
    var wrongQuestions: Int,
    var cheatedQuestions: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalQuestions)
        parcel.writeInt(rightQuestions)
        parcel.writeInt(wrongQuestions)
        parcel.writeInt(cheatedQuestions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionaryResult> {
        override fun createFromParcel(parcel: Parcel): QuestionaryResult {
            return QuestionaryResult(parcel)
        }

        override fun newArray(size: Int): Array<QuestionaryResult?> {
            return arrayOfNulls(size)
        }
    }
}