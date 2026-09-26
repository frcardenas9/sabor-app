package com.hpm.saborapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContentFragment : Fragment(R.layout.fragment_content) {
    companion object {

        private const val ARG_OPTION = "option"

        fun newInstance(option: String): ContentFragment {
            return ContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_OPTION, option)
                }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.tvContentTitle)
        val description = view.findViewById<TextView>(R.id.tvContentDescription)

        when (arguments?.getString(ARG_OPTION)) {

            "perfil" -> {
                title.text = "Perfil"
                description.text =
                    "Información del perfil."
            }

            "fotos" -> {
                title.text = "Fotos"
                description.text =
                    "Galería de fotos y recetas."
            }

            "video" -> {
                title.text = "Video"
                description.text =
                    "Videos disponibles."
            }

            "web" -> {
                title.text = "Web"
                description.text =
                    "Página web."
            }

            "botones" -> {
                title.text = "Botones"
                description.text =
                    "Ejemplos de botones."
            }

            else -> {
                title.text = "Bienvenido a SaborApp"
                description.text =
                    "Selecciona una opción del menú."
            }
        }
    }
}