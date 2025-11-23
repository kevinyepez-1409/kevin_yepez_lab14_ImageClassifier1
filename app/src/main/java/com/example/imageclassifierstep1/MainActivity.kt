package com.example.imageclassifierstep1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

// IMPORTS DE ML KIT
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Conectar con res/layout/activity_main.xml
        setContentView(R.layout.activity_main)

        // 1) Referencias a las vistas del layout
        val img: ImageView = findViewById(R.id.imageToLabel)
        val txtOutput: TextView = findViewById(R.id.txtOutput)
        val btn: Button = findViewById(R.id.btnTest)

        // 2) Cargar la imagen desde assets
        val fileName = "flower1.jpg"              // Debe estar en app/src/main/assets
        val bitmap: Bitmap? = assetsToBitmap(fileName)

        bitmap?.apply {
            img.setImageBitmap(this)
        }

        // 3) Cuando se presiona el botón, usamos ML Kit para etiquetar la imagen
        btn.setOnClickListener {
            // Nos aseguramos de que el bitmap no sea null
            val safeBitmap = bitmap
            if (safeBitmap == null) {
                txtOutput.text = "No image loaded"
                return@setOnClickListener
            }

            // Crear el labeler con las opciones por defecto
            val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

            // Crear la InputImage desde el bitmap
            val image = InputImage.fromBitmap(safeBitmap, 0)
            var outputText = ""

            // Procesar la imagen de forma asíncrona
            labeler.process(image)
                .addOnSuccessListener { labels ->
                    // Task completed successfully
                    for (label in labels) {
                        val text = label.text
                        val confidence = label.confidence
                        outputText += "$text : $confidence\n"
                    }
                    txtOutput.text = outputText
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                    txtOutput.text = "Error: ${e.message}"
                }
        }
    }
}

// Función de extensión para leer un Bitmap desde la carpeta assets
fun Context.assetsToBitmap(fileName: String): Bitmap? {
    return try {
        assets.open(fileName).use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
