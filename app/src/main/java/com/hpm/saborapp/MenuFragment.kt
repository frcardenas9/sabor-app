package com.hpm.saborapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class MenuFragment : Fragment(R.layout.fragment_menu) {
    interface OnMenuOptionSelectedListener {
        fun onMenuOptionSelected(option: String)
    }

    private var listener: OnMenuOptionSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? OnMenuOptionSelectedListener
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val btnPerfil = view.findViewById<Button>(R.id.btnPerfil)
        val btnFotos = view.findViewById<Button>(R.id.btnFotos)
        val btnVideo = view.findViewById<Button>(R.id.btnVideo)
        val btnWeb = view.findViewById<Button>(R.id.btnWeb)
        val btnBotones = view.findViewById<Button>(R.id.btnBotones)

        btnPerfil.setOnClickListener {
            listener?.onMenuOptionSelected("perfil")
        }

        btnFotos.setOnClickListener {
            listener?.onMenuOptionSelected("fotos")
        }

        btnVideo.setOnClickListener {
            listener?.onMenuOptionSelected("video")
        }

        btnWeb.setOnClickListener {
            listener?.onMenuOptionSelected("web")
        }

        btnBotones.setOnClickListener {
            listener?.onMenuOptionSelected("botones")
        }
    }

}