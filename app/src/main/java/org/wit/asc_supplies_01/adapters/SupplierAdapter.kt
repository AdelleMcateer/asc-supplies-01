package org.wit.asc_supplies_01.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.asc_supplies_01.databinding.CardSupplierBinding
import org.wit.asc_supplies_01.models.SupplierModel
import com.squareup.picasso.Picasso

interface SupplierListener {
    fun onSupplierClick(supplier: SupplierModel)
}

class SupplierAdapter constructor(private var suppliers: List<SupplierModel>,
                                  private val listener: SupplierListener) :
    RecyclerView.Adapter<SupplierAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardSupplierBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val supplier = suppliers[holder.adapterPosition]
        holder.bind(supplier, listener)
    }

    override fun getItemCount(): Int = suppliers.size

    class MainHolder(private val binding : CardSupplierBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(supplier: SupplierModel, listener: SupplierListener) {
            binding.SupplierTitle.text = supplier.title
            binding.description.text = supplier.description
            binding.supplierStreetAddress.text = supplier.street
            binding.supplierCityAddress.text = supplier.city
            binding.supplierStateAddress.text = supplier.state
            binding.supplierZipAddress.text = supplier.zip
            binding.supplierTelephone.text = supplier.telephone
            binding.supplierEmail.text = supplier.email
            binding.supplierWebsite.text = supplier.website
            Picasso.get().load(supplier.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onSupplierClick(supplier) }
        }
    }
}