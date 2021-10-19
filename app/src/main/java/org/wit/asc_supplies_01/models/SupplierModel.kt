package org.wit.asc_supplies_01.models

data class SupplierModel(var title: String = "",
                         var description: String = "",
                         var street: String = "",
                         var city: String = "",
                         var state: String = "",
                         var zip: String = "",//TODO: change to int
                         var telephone: String = "", //TODO: change to int
                         var email: String = "", //TODO: change to email format only
                         var website: String = "",
                         )
