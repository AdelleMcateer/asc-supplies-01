package org.wit.asc_supplies_01.main

import android.app.Application
import org.wit.asc_supplies_01.models.SupplierMemStore
//import org.wit.asc_supplies_01.models.SupplierStore
//import org.wit.asc_supplies_01.models.SupplierModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val suppliers = ArrayList<SupplierModel>()
    val suppliers = SupplierMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Supplier started")
       /* suppliers.add(SupplierModel("One", "About One...", " ","",
            "", "", "", "","" ))
        suppliers.add(SupplierModel("Two", "About two...", " ","",
            "", "", "", "","" ))
        suppliers.add(SupplierModel("Three", "About three...", " ","",
            "", "", "", "","" ))*/
    }
}