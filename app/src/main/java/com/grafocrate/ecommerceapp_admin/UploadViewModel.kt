package com.grafocrate.ecommerceapp_admin
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
class UploadViewModel : ViewModel(){
    private val _uploadStatus = MutableLiveData<String>()
    val uploadStatus: LiveData<String> get() = _uploadStatus

    private val database = FirebaseDatabase.getInstance().getReference("products")
    private val storage = FirebaseStorage.getInstance().getReference("product_images")

    fun uploadProduct(product: Product, imageUri: Uri?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val imageUrl = imageUri?.let { uploadImage(it) } ?: ""
                val productId = database.push().key ?: throw Exception("Failed to generate product ID")
                val productWithImage = product.copy(imageUrl = imageUrl)

                database.child(productId).setValue(productWithImage).await()
                _uploadStatus.postValue("Upload successful")
            } catch (e: Exception) {
                _uploadStatus.postValue("Upload failed: ${e.message}")
            }
        }
    }

    private suspend fun uploadImage(imageUri: Uri): String {
        val imageRef = storage.child("${System.currentTimeMillis()}.jpg")
        imageRef.putFile(imageUri).await()
        return imageRef.downloadUrl.await().toString()
    }
}
