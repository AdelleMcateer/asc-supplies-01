package org.wit.asc_supplies_01.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.asc_supplies_01.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "suppliers.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<SupplierModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class SupplierJSONStore(private val context: Context) : SupplierStore {

    var suppliers = mutableListOf<SupplierModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<SupplierModel> {
        logAll()
        return suppliers
    }

    override fun create(supplier: SupplierModel) {
        supplier.id = generateRandomId()
        suppliers.add(supplier)
        serialize()
    }

    override fun delete(supplier: SupplierModel) {
        suppliers.remove(supplier)
        serialize()
    }

    override fun update(supplier: SupplierModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(suppliers, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        suppliers = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        suppliers.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}