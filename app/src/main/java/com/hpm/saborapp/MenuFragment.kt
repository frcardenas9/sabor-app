package com.hpm.saborapp

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class MenuFragment : Fragment(R.layout.fragment_menu) {

    interface OnMenuOptionSelectedListener {
        fun onMenuOptionSelected(option: String)
    }

    private var listener: OnMenuOptionSelectedListener? = null

    private lateinit var btnInicio: Button
    private lateinit var btnPerfil: Button
    private lateinit var btnFotos: Button
    private lateinit var btnVideo: Button
    private lateinit var btnWeb: Button
    private lateinit var btnBotones: Button

    private var opcionSeleccionada = "perfil"

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener =
            context as? OnMenuOptionSelectedListener
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        inicializarBotones(view)

        opcionSeleccionada =
            savedInstanceState?.getString(
                "opcionSeleccionada"
            ) ?: "perfil"

        actualizarSeleccion(
            opcionSeleccionada
        )

        configurarEventos()
    }

    /**
     * Relaciona cada variable Kotlin
     * con su identificador del XML.
     */
    private fun inicializarBotones(
        view: View
    ) {

        btnInicio =
            view.findViewById(
                R.id.btnInicio
            )

        btnPerfil =
            view.findViewById(
                R.id.btnPerfil
            )

        btnFotos =
            view.findViewById(
                R.id.btnFotos
            )

        btnVideo =
            view.findViewById(
                R.id.btnVideo
            )

        btnWeb =
            view.findViewById(
                R.id.btnWeb
            )

        btnBotones =
            view.findViewById(
                R.id.btnBotones
            )
    }

    /**
     * Define los eventos de cada
     * opción del menú.
     */
    private fun configurarEventos() {

        btnInicio.setOnClickListener {
            seleccionarOpcion("inicio")
        }

        btnPerfil.setOnClickListener {
            seleccionarOpcion("perfil")
        }

        btnFotos.setOnClickListener {
            seleccionarOpcion("fotos")
        }

        btnVideo.setOnClickListener {
            seleccionarOpcion("video")
        }

        btnWeb.setOnClickListener {
            seleccionarOpcion("web")
        }

        btnBotones.setOnClickListener {
            seleccionarOpcion("botones")
        }
    }

    /**
     * Cambia visualmente la opción
     * seleccionada y notifica a MainActivity.
     */
    private fun seleccionarOpcion(
        opcion: String
    ) {

        opcionSeleccionada = opcion

        actualizarSeleccion(opcion)

        listener?.onMenuOptionSelected(
            opcion
        )
    }

    /**
     * Resalta la opción activa.
     *
     * Opción seleccionada:
     * fondo vino + texto blanco.
     *
     * Otras opciones:
     * fondo transparente + texto oscuro.
     */
    private fun actualizarSeleccion(
        opcion: String
    ) {

        val botones = mapOf(
            "inicio" to btnInicio,
            "perfil" to btnPerfil,
            "fotos" to btnFotos,
            "video" to btnVideo,
            "web" to btnWeb,
            "botones" to btnBotones
        )

        botones.forEach {
                (nombreOpcion, boton) ->

            if (
                nombreOpcion ==
                opcion
            ) {

                aplicarEstiloSeleccionado(
                    boton
                )

            } else {

                aplicarEstiloNormal(
                    boton
                )
            }
        }
    }

    /**
     * Estilo de la opción activa.
     */
    private fun aplicarEstiloSeleccionado(
        boton: Button
    ) {

        val colorVino =
            ContextCompat.getColor(
                requireContext(),
                R.color.wine
            )

        val colorBlanco =
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )

        boton.backgroundTintList =
            ColorStateList.valueOf(
                colorVino
            )

        boton.setTextColor(
            colorBlanco
        )
    }

    /**
     * Estilo de las opciones
     * que no están seleccionadas.
     */
    private fun aplicarEstiloNormal(
        boton: Button
    ) {

        val transparente =
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )

        val colorTexto =
            ContextCompat.getColor(
                requireContext(),
                R.color.text_primary
            )

        boton.backgroundTintList =
            ColorStateList.valueOf(
                transparente
            )

        boton.setTextColor(
            colorTexto
        )
    }

    /**
     * Guarda la opción seleccionada
     * cuando Android recrea el Fragment.
     */
    override fun onSaveInstanceState(
        outState: Bundle
    ) {

        super.onSaveInstanceState(
            outState
        )

        outState.putString(
            "opcionSeleccionada",
            opcionSeleccionada
        )
    }
}