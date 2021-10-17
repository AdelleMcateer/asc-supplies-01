package org.wit.asc_supplies_01.models

data class SupplierModel(var title: String = "",
                         var street: String = "",
                         var city: String = "",
                         var state: String = "",
                         var zip: String = "",
                         var telephone: String = "", //Update
                         var email: String = "", //Change from String
                         var website: String = "",
                         )
