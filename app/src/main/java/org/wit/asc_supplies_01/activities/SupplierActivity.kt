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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Supplier Activity started..")

        binding.btnAdd.setOnClickListener() {
            supplier.title = binding.SupplierTitle.text.toString()
            //val supplierTitle = binding.supplierTitle.text.toString()
            if (supplier.title.isNotEmpty()) {
                i("add Button Pressed: $supplier.Title")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}