package org.wit.asc_supplies_01.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.asc_supplies_01.databinding.ActivitySupplierBinding
import org.wit.asc_supplies_01.models.SupplierModel
import timber.log.Timber
import timber.log.Timber.i

class SupplierActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupplierBinding
    var supplier = SupplierModel()
    val suppliers = ArrayList<SupplierModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        i("Supplier Activity started..")

        binding.btnAdd.setOnClickListener() {
            supplier.title = binding.SupplierTitle.text.toString()
            //val supplierTitle = binding.supplierTitle.text.toString()
            supplier.street = binding.streetAddress.text.toString()
            supplier.city = binding.cityAddress.text.toString()
            supplier.state = binding.stateAddress.text.toString()
            supplier.zip = binding.zipAddress.text.toString()
            supplier.telephone = binding.telephone.text.toString()
            supplier.email = binding.email.text.toString()
            supplier.website = binding.website.text.toString()

            if (supplier.title.isNotEmpty()) {
                //suppliers.add(supplier)
                suppliers.add(supplier.copy())
                i("add Button Pressed: ${supplier}")
                for (i in suppliers.indices)
                { i("Suppliers[$i]:${this.suppliers[i]}") }
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}