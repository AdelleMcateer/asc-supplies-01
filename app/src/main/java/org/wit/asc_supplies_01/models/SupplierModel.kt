package org.wit.asc_supplies_01.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SupplierModel(var id: Long = 0,
                         var title: String = "",
                         var description: String = "",
                         var street: String = "",
                         var city: String = "",
                         var state: String = "",
                         var zip: String = "",//TODO: change to int
                         var telephone: String = "", //TODO: change to int
                         var email: String = "", //TODO: change to email format only
                         var website: String = "",
                         var image: Uri = Uri.EMPTY,
                         var lat : Double = 0.0,
                         var lng: Double = 0.0,
                         var zoom: Float = 0f) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable