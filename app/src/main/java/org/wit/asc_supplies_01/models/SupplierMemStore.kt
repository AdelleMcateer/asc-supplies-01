package org.wit.asc_supplies_01.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class SupplierMemStore : SupplierStore {

    val suppliers = ArrayList<SupplierModel>()

    override fun findAll(): List<SupplierModel> {
        return suppliers
    }

    override fun create(supplier: SupplierModel) {
        supplier.id = getId()
        suppliers.add(supplier)
        logAll()
    }

    override fun update(supplier: SupplierModel) {
        var foundSupplier: SupplierModel? = suppliers.find { p -> p.id == supplier.id }
        if (foundSupplier != null) {
            foundSupplier.title = supplier.title
            foundSupplier.description = supplier.description
            foundSupplier.street = supplier.street
            foundSupplier.city = supplier.city
            foundSupplier.state = supplier.state
            foundSupplier.zip = supplier.zip
            foundSupplier.telephone = supplier.telephone
            foundSupplier.email = supplier.email
            foundSupplier.website = supplier.website
            foundSupplier.image = supplier.image
            logAll()
        }
    }

    private fun logAll() {
        suppliers.forEach { i("$it") }
    }
}