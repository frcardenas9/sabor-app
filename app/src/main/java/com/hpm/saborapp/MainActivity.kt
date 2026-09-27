package com.hpm.saborapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity :
    AppCompatActivity(),
    MenuFragment.OnMenuOptionSelectedListener {

    private lateinit var menuPanel: View
    private lateinit var btnToggleMenu: View

    private var menuVisible = true


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)

        /*
         * Permite que la aplicación dibuje
         * detrás de las barras del sistema.
         */
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        /*
         * En Android 14 o anteriores hacemos
         * transparente la barra para que se vea
         * el fondo negro que dibujamos nosotros.
         *
         * En Android 15+ esta propiedad deja
         * de controlar realmente el color.
         */
        if (
            Build.VERSION.SDK_INT <
            Build.VERSION_CODES.VANILLA_ICE_CREAM
        ) {

            @Suppress("DEPRECATION")
            window.statusBarColor =
                Color.TRANSPARENT
        }

        setContentView(
            R.layout.activity_main
        )

        configurarBarrasDelSistema()

        configurarMenuLateral()


        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.menuContainer,
                    MenuFragment()
                )
                .replace(
                    R.id.contentContainer,
                    ContentFragment.newInstance(
                        "perfil"
                    )
                )
                .commit()
        }
    }


    /**
     * Configura la zona donde aparecen:
     *
     * hora
     * señal
     * Wi-Fi
     * batería
     *
     * para que tenga fondo negro
     * e iconos blancos.
     */
    private fun configurarBarrasDelSistema() {

        val appContent =
            findViewById<View>(
                R.id.appContent
            )


        /*
         * Obtenemos el tamaño real
         * de las barras del sistema.
         */
        ViewCompat.setOnApplyWindowInsetsListener(
            appContent
        ) { view, windowInsets ->

            val statusBarInsets =
                windowInsets.getInsets(
                    WindowInsetsCompat.Type.statusBars()
                )

            val navigationBarInsets =
                windowInsets.getInsets(
                    WindowInsetsCompat.Type.navigationBars()
                )


            /*
             * Movemos SaborApp hacia abajo
             * la altura exacta de la barra
             * de estado.
             *
             * El espacio que queda arriba
             * deja visible el fondo negro
             * del FrameLayout raíz.
             */
            val layoutParams =
                view.layoutParams as
                        FrameLayout.LayoutParams

            layoutParams.topMargin =
                statusBarInsets.top

            view.layoutParams =
                layoutParams


            /*
             * Evita que el contenido termine
             * debajo de la barra inferior.
             */
            view.setPadding(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                navigationBarInsets.bottom
            )


            windowInsets
        }


        /*
         * false = iconos blancos en
         * la barra superior.
         */
        WindowCompat
            .getInsetsController(
                window,
                window.decorView
            )
            .isAppearanceLightStatusBars =
            false


        /*
         * Dejamos también claros los iconos
         * de navegación inferior.
         */
        WindowCompat
            .getInsetsController(
                window,
                window.decorView
            )
            .isAppearanceLightNavigationBars =
            false
    }


    /**
     * Configura ☰ para mostrar
     * u ocultar el menú lateral.
     */
    private fun configurarMenuLateral() {

        menuPanel =
            findViewById(
                R.id.menuPanel
            )

        btnToggleMenu =
            findViewById(
                R.id.btnToggleMenu
            )


        btnToggleMenu.setOnClickListener {

            menuVisible =
                !menuVisible

            menuPanel.visibility =
                if (menuVisible) {

                    View.VISIBLE

                } else {

                    View.GONE
                }
        }
    }


    /**
     * Recibe la selección hecha
     * en MenuFragment.
     */
    override fun onMenuOptionSelected(
        option: String
    ) {

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.contentContainer,
                ContentFragment.newInstance(
                    option
                )
            )
            .commit()

        // Ocultar el menú después de seleccionar una sección
        menuPanel.visibility = View.GONE

        // Actualizamos el estado para que el botón ☰
        // sepa que actualmente el menú está oculto
        menuVisible = false
    }
}