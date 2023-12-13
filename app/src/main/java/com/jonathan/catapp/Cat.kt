package com.jonathan.catapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
