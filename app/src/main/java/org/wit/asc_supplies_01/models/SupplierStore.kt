package org.wit.asc_supplies_01.models

interface SupplierStore {
    fun findAll(): List<SupplierModel>
    fun create(supplier: SupplierModel)
    fun update(supplier: SupplierModel)
    fun delete(supplier: SupplierModel)
}