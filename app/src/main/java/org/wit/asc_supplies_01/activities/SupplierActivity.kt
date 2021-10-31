package org.wit.asc_supplies_01.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import org.wit.asc_supplies_01.R
import org.wit.asc_supplies_01.databinding.ActivitySupplierBinding
import org.wit.asc_supplies_01.main.MainApp
import org.wit.asc_supplies_01.models.SupplierModel
import org.wit.asc_supplies_01.helpers.showImagePicker
import org.wit.asc_supplies_01.models.Location
import timber.log.Timber
import timber.log.Timber.i

class SupplierActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupplierBinding
    var supplier = SupplierModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    //var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = ActivitySupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("Supplier Activity started...")

        if (intent.hasExtra("supplier_edit")) {
            edit = true
            supplier = intent.extras?.getParcelable("supplier_edit")!!
            binding.SupplierTitle.setText(supplier.title)
            binding.description.setText(supplier.description)
            binding.streetAddress.setText(supplier.street)
            binding.cityAddress.setText(supplier.city)
            binding.stateAddress.setText(supplier.state)
            binding.zipAddress.setText(supplier.zip)
            binding.telephone.setText(supplier.telephone)
            binding.email.setText(supplier.email)
            binding.website.setText(supplier.website)
            binding.btnAdd.setText(R.string.save_supplier)
            Picasso.get()
                .load(supplier.image)
                .into(binding.supplierImage)

            if (supplier.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_supplier_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            supplier.title = binding.SupplierTitle.text.toString()
            supplier.description = binding.description.text.toString()
            supplier.street = binding.streetAddress.text.toString()
            supplier.city = binding.cityAddress.text.toString()
            supplier.state = binding.stateAddress.text.toString()
            supplier.zip = binding.zipAddress.text.toString()
            supplier.telephone = binding.telephone.text.toString()
            supplier.email = binding.email.text.toString()
            supplier.website = binding.website.text.toString()

            if (supplier.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_supplier_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.suppliers.update(supplier.copy())
                } else {
                    app.suppliers.create(supplier.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.supplierLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (supplier.zoom != 0f) {
                location.lat =  supplier.lat
                location.lng = supplier.lng
                location.zoom = supplier.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }
        registerImagePickerCallback()
        registerMapCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_supplier, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.suppliers.delete(supplier)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            supplier.image = result.data!!.data!!
                            Picasso.get()
                                .load(supplier.image)
                                .into(binding.supplierImage)
                            binding.chooseImage.setText(R.string.change_supplier_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            supplier.lat = location.lat
                            supplier.lng = location.lng
                            supplier.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}