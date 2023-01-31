package com.greemoid.breakthehabit.presentation

import androidx.annotation.DrawableRes
import com.greemoid.breakthehabit.R

abstract class AddictionUi(
    private val days: String,
    private val date: String,
    private val image: String,
) {
    @DrawableRes
    protected abstract fun getIconResId(): Int

}

class AddictionUiModel(
    private val days: String,
    private val date: String,
    private val image: String,
) : AddictionUi(days, date, image) {
    override fun getIconResId(): Int {
        return R.drawable.bicepss
    }
}

class AddictionFailed(
    private val errorText: String,
) : AddictionUi(errorText, "", "") {
    override fun getIconResId(): Int = R.drawable.ic_time
}

