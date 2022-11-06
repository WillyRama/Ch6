package com.willyramad.logindatastore.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.asLiveData
import com.willyramad.logindatastore.R
import com.willyramad.logindatastore.databinding.ActivityProfilBinding
import com.willyramad.logindatastore.datastore.DatastoreLogin
import kotlinx.android.synthetic.main.activity_profil.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ProfilActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfilBinding
    lateinit var dataLogin : DatastoreLogin
    private val REQUEST_CODE_PERMISSION = 100
    private var imageUri: Uri? = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataLogin = DatastoreLogin(this)

        dataLogin.Nam.asLiveData().observe(this,{
            binding.namaProfil.setText(it.toString())
        })
        dataLogin.Em.asLiveData().observe(this,{
            binding.emailProfil.setText(it.toString())
        })
        dataLogin.userName.asLiveData().observe(this,{
            binding.uProfil.setText(it.toString())
        })
        var image = BitmapFactory.decodeFile(this.applicationContext.filesDir.path + File.separator +"Foto"+ File.separator +"Profile.png")
        binding.ivImage.setImageBitmap(image)


        binding.btnUpdate.setOnClickListener {
            val upNama = binding.namaProfil.text.toString()
            val upEmail = binding.emailProfil.text.toString()
            val upUser = binding.uProfil.text.toString()
            val upPass = binding.uProfil.text.toString()
            val upKpass = binding.uProfil.text.toString()
            GlobalScope.launch {
                dataLogin.saveData(upNama,upEmail,upUser,upPass,upKpass)
                startActivity(Intent(this@ProfilActivity,HomeActivity::class.java))
            }
            val img = BitmapFactory.decodeStream(
                this.applicationContext.contentResolver.openInputStream(Uri.parse(imageUri.toString())))
            writeBitmapToFile(this,img)
        }
        binding.btnKeluar.setOnClickListener {
            GlobalScope.launch {
                DatastoreLogin(this@ProfilActivity).clearData()
                startActivity(Intent(this@ProfilActivity,MainActivity::class.java))
            }
        }
        binding.ivImage.setOnClickListener {
            checkingPermissions()
        }
    }
    fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
        val name = "Profile.png"
        val outputDir = File(applicationContext.filesDir, "Foto")
        if (!outputDir.exists()) {
            outputDir.mkdirs() // should succeed
        }
        val outputFile = File(outputDir, name)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
        } finally {
            out?.let {
                try {
                    it.close()
                } catch (ignore: IOException) {
                }

            }
        }
        return Uri.fromFile(outputFile)
    }
    private fun checkingPermissions() {
        if (isGranted(
                this,
                Manifest.permission.CAMERA,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION,
            )
        ) {
            chooseImageDialog()
        }
    }
    private fun isGranted(
        activity: Activity,
        permission: String,
        permissions: Array<String>,
        request: Int,
    ): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(activity, permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, request)
            }
            false
        } else {
            true
        }
    }
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton(
                "App Settings"
            ) { _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }
    private fun chooseImageDialog() {
        AlertDialog.Builder(this)
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> openGallery() }
            .setNegativeButton("Camera") { _, _ -> openCamera() }
            .show()
    }
    private fun openGallery() {
        intent.type = "image/*"
        galleryResult.launch("image/*")
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraResult.launch(cameraIntent)
    }
    private fun handleCameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        binding.ivImage.setImageBitmap(bitmap)
    }
    private val cameraResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                handleCameraImage(result.data)
            }
        }
    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            imageUri = result
            binding.ivImage.setImageURI(result)
        }
}