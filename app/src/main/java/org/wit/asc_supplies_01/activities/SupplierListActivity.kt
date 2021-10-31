package org.wit.asc_supplies_01.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.asc_supplies_01.R
import org.wit.asc_supplies_01.adapters.SupplierAdapter
import org.wit.asc_supplies_01.adapters.SupplierListener
import org.wit.asc_supplies_01.databinding.ActivitySupplierListBinding
import org.wit.asc_supplies_01.main.MainApp
import org.wit.asc_supplies_01.models.SupplierModel

class SupplierListActivity : AppCompatActivity(), SupplierListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySupplierListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = SupplierAdapter(app.suppliers.findAll(),this)

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, SupplierActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupplierClick(supplier: SupplierModel) {
        val launcherIntent = Intent(this, SupplierActivity::class.java)
        launcherIntent.putExtra("supplier_edit", supplier)
        startActivityForResult(launcherIntent,0)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { binding.recyclerView.adapter?.notifyDataSetChanged() }
    }
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }*/

}


