package com.sentrin.iqtest.ui.aboutApp

import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.R
import com.sentrin.iqtest.databinding.FragmentAboutAppBinding
import kotlinx.android.synthetic.main.fragment_about_app.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class AboutAuthorFragment : Fragment() {

    lateinit var binding: FragmentAboutAppBinding
    lateinit var viewModel:AboutAppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAboutAppBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(AboutAppViewModel::class.java)

        binding.viewmodel = viewModel


        viewModel.onShareClick.observe(this, androidx.lifecycle.Observer {
            if (it) {

                viewModel.onShareClick.value = false
            }
        })



        return binding.root
    }


//    // Method to save an image to internal storage
//    private fun saveImageToInternalStorage(drawableId: Int): Uri {
//        // Get the image from drawable resource as drawable object
//        val drawable = ContextCompat.getDrawable(mainData.contextMainActivity!!, drawableId)
//
//        // Get the bitmap from drawable object
//        var bitmap:Bitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
//        drawable?.draw(Canvas(bitmap));
//
//
//
//        // Get the context wrapper instance
//        val wrapper = ContextWrapper(mainData.contextMainActivity!!)
//
//        // Initializing a new file
//        // The bellow line return a directory in internal storage
//        //var file = wrapper.getDir("images", Context.MODE_PRIVATE)
//
//        val extStorageDirectory: String = mainData.contextMainActivity?.getExternalFilesDir(null).toString()
//
//        // Create a file to save the image
//        var  file = File(extStorageDirectory ,"launcher2.png")
//
//        try {
//            // Get the file output stream
//            val stream: OutputStream = FileOutputStream(file)
//
//            // Compress bitmap
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//
//            // Flush the stream
//            stream.flush()
//
//            // Close stream
//            stream.close()
//        } catch (e: IOException){ // Catch the exception
//            e.printStackTrace()
//        }
//
//        // Return the saved image uri
//        return Uri.parse(file.absolutePath)
//    }





}