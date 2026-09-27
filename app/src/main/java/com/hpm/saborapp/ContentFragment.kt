package com.hpm.saborapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContentFragment : Fragment(R.layout.fragment_content) {

    companion object {

        private const val ARG_OPTION = "option"

        fun newInstance(option: String): ContentFragment {

            return ContentFragment().apply {

                arguments = Bundle().apply {

                    putString(
                        ARG_OPTION,
                        option
                    )
                }
            }
        }
    }


    /*
     * Lista de recetas disponibles
     * en SaborApp.
     */
    private val recipes = listOf(

        Recipe(
            id = 1,
            name = "Ajiaco santafereño",
            category = "Sopa",
            preparationTime = 90,
            servings = 6,
            imageResId = R.drawable.ajiaco,
            videoResId = R.raw.ajiaco,
            description =
                "El ajiaco bogotano o santafereño es una sopa típica y tradicional de la región de Bogotá, Cundinamarca, Colombia, a base de pollo y diferentes clases de papa. A diferencia de lo que sugiere su nombre, el ajiaco no es picante.",
            ingredients = listOf(
                "1 pollo",
                "Papa criolla",
                "Papa pastusa",
                "Papa sabanera",
                "Guascas",
                "Mazorca",
                "Agua",
                "Sal al gusto"
            ),
            steps = listOf(
                "Cocinar el pollo en agua con sal.",
                "Agregar las papas y la mazorca.",
                "Cocinar hasta que las papas estén blandas.",
                "Agregar las guascas.",
                "Continuar la cocción durante unos minutos.",
                "Servir caliente."
            )
        ),


        Recipe(
            id = 2,
            name = "Arepa de choclo",
            category = "Desayuno",
            preparationTime = 25,
            servings = 4,
            imageResId = R.drawable.arepa_choclo,
            videoResId = R.raw.arepa_choclo,
            description =
                "La arepa de choclo es una preparación tradicional colombiana elaborada principalmente con maíz tierno. Es común acompañarla con queso y disfrutarla especialmente durante el desayuno o como merienda.",
            ingredients = listOf(
                "Mazorca tierna",
                "Harina de maíz",
                "Leche",
                "Azúcar",
                "Sal",
                "Queso"
            ),
            steps = listOf(
                "Desgranar las mazorcas.",
                "Moler o licuar el maíz con la leche.",
                "Mezclar con la harina, azúcar y sal.",
                "Formar las arepas.",
                "Cocinarlas en una plancha caliente.",
                "Servir con queso."
            )
        ),


        Recipe(
            id = 3,
            name = "Sancocho de gallina",
            category = "Plato fuerte",
            preparationTime = 120,
            servings = 8,
            imageResId = R.drawable.sancocho_gallina,
            videoResId = R.raw.sancocho_gallina,
            description =
                "El sancocho de gallina es una preparación tradicional colombiana que combina gallina, tubérculos, plátano y otros ingredientes en un caldo abundante y lleno de sabor.",
            ingredients = listOf(
                "1 gallina",
                "Plátano verde",
                "Yuca",
                "Papa",
                "Mazorca",
                "Cebolla",
                "Cilantro",
                "Sal al gusto"
            ),
            steps = listOf(
                "Cocinar la gallina hasta que esté tierna.",
                "Agregar el plátano y la mazorca.",
                "Añadir la yuca y la papa.",
                "Agregar los condimentos.",
                "Cocinar hasta que todos los ingredientes estén blandos.",
                "Servir caliente."
            )
        ),


        Recipe(
            id = 4,
            name = "Postre de natas",
            category = "Postre",
            preparationTime = 40,
            servings = 6,
            imageResId = R.drawable.postre_natas,
            videoResId = R.raw.postre_natas,
            description =
                "El postre de natas es una preparación tradicional colombiana elaborada a partir de la nata de la leche y azúcar, con una textura suave y un sabor dulce característico.",
            ingredients = listOf(
                "Leche",
                "Azúcar",
                "Canela",
                "Yemas de huevo",
                "Esencia de vainilla"
            ),
            steps = listOf(
                "Calentar la leche.",
                "Retirar cuidadosamente las natas que se formen.",
                "Continuar el proceso hasta obtener suficiente nata.",
                "Preparar la mezcla con las yemas y el azúcar.",
                "Incorporar las natas.",
                "Enfriar antes de servir."
            )
        )
    )


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        showOption(
            view,
            arguments?.getString(ARG_OPTION)
        )
    }


    /*
     * Determina qué contenido mostrar
     * según la opción seleccionada
     * en el menú lateral.
     */
    private fun showOption(
        view: View,
        option: String?
    ) {

        when (option) {

            /*
             * Galería de recetas.
             */
            "fotos" -> {

                showRecipeList(
                    view,
                    "Galería de recetas",
                    "Selecciona una receta para conocer sus ingredientes y preparación",
                    false
                )
            }


            /*
             * Pantalla inicial.
             */
            "inicio" -> {

                showDefaultContent(
                    view
                )
            }


            /*
             * Perfil del chef.
             *
             * Temporalmente se muestra contenido
             * sencillo. Posteriormente se reemplazará
             * por fragment_profile.xml.
             */
            "perfil" -> {

                showSimpleContent(
                    view,
                    "Perfil del chef",
                    "Conoce la trayectoria, experiencia y filosofía culinaria de la autora del recetario."
                )
            }


            /*
             * Video.
             *
             * Se abre directamente el video
             * de la primera receta para acercarnos
             * al comportamiento del mockup.
             */
            "video" -> {

                val root =
                    view.findViewById<ViewGroup>(
                        R.id.contentRoot
                    )

                showRecipeVideo(
                    root,
                    recipes.first()
                )
            }


            /*
             * Sitio web.
             *
             * Temporalmente se muestra texto.
             * Luego implementaremos WebView.
             */
            "web" -> {

                showSimpleContent(
                    view,
                    "Explorar un sitio web",
                    "Ingresa una dirección web válida para consultar contenido relacionado con gastronomía."
                )
            }


            /*
             * Calculadora de porciones.
             *
             * Se conserva internamente el nombre
             * 'botones' para no romper la navegación.
             */
            "botones" -> {

                showSimpleContent(
                    view,
                    "Calculadora de porciones",
                    "Ajusta el número de porciones y recalcula automáticamente las cantidades de los ingredientes."
                )
            }


            /*
             * Si no existe una opción válida,
             * se muestra la pantalla de bienvenida.
             */
            else -> {

                showDefaultContent(
                    view
                )
            }
        }
    }


    /*
     * Contenido inicial de SaborApp.
     */
    private fun showDefaultContent(
        view: View
    ) {

        val title =
            view.findViewById<TextView>(
                R.id.tvContentTitle
            )

        val description =
            view.findViewById<TextView>(
                R.id.tvContentDescription
            )

        title.text =
            "Bienvenido a SaborApp"

        description.text =
            "Selecciona una opción del menú."
    }


    /*
     * Muestra contenido textual sencillo.
     *
     * Lo utilizamos temporalmente para:
     *
     * - Perfil
     * - Web
     * - Calculadora de porciones
     *
     * hasta crear las interfaces definitivas.
     */
    private fun showSimpleContent(
        view: View,
        titleText: String,
        descriptionText: String
    ) {

        val title =
            view.findViewById<TextView>(
                R.id.tvContentTitle
            )

        val description =
            view.findViewById<TextView>(
                R.id.tvContentDescription
            )

        title.text =
            titleText

        description.text =
            descriptionText
    }


    /*
     * Muestra la galería/listado
     * de recetas.
     */
    private fun showRecipeList(
        view: View,
        titleText: String,
        subtitleText: String,
        openVideo: Boolean
    ) {

        val root =
            view.findViewById<ViewGroup>(
                R.id.contentRoot
            )

        root.removeAllViews()


        /*
         * Cargamos el XML correspondiente
         * a la galería de recetas.
         */
        val recipeListView =
            LayoutInflater
                .from(requireContext())
                .inflate(
                    R.layout.fragment_recipe_list,
                    root,
                    false
                )

        root.addView(
            recipeListView
        )


        /*
         * Título y subtítulo.
         */
        val galleryTitle =
            recipeListView
                .findViewById<TextView>(
                    R.id.tvGalleryTitle
                )

        val gallerySubtitle =
            recipeListView
                .findViewById<TextView>(
                    R.id.tvGallerySubtitle
                )

        galleryTitle.text =
            titleText

        gallerySubtitle.text =
            subtitleText


        /*
         * RecyclerView que contiene
         * las recetas.
         */
        val recyclerView =
            recipeListView
                .findViewById<RecyclerView>(
                    R.id.recyclerRecipes
                )

        recyclerView.layoutManager =
            LinearLayoutManager(
                requireContext()
            )


        /*
         * Adaptador de recetas.
         */
        recyclerView.adapter =
            RecipeAdapter(
                recipes
            ) { recipe ->

                if (openVideo) {

                    showRecipeVideo(
                        root,
                        recipe
                    )

                } else {

                    showRecipeDetail(
                        root,
                        recipe
                    )
                }
            }
    }


    /*
     * Muestra el detalle completo
     * de una receta seleccionada.
     */
    private fun showRecipeDetail(
        root: ViewGroup,
        recipe: Recipe
    ) {

        root.removeAllViews()


        /*
         * Cargamos la interfaz
         * de detalle.
         */
        val detailView =
            LayoutInflater
                .from(requireContext())
                .inflate(
                    R.layout.fragment_recipe_detail,
                    root,
                    false
                )

        root.addView(
            detailView
        )


        /*
         * Vinculación de vistas.
         */
        val title =
            detailView
                .findViewById<TextView>(
                    R.id.tvDetailTitle
                )

        val image =
            detailView
                .findViewById<ImageView>(
                    R.id.imgDetailRecipe
                )

        val description =
            detailView
                .findViewById<TextView>(
                    R.id.tvDetailDescription
                )

        val ingredients =
            detailView
                .findViewById<TextView>(
                    R.id.tvIngredients
                )

        val steps =
            detailView
                .findViewById<TextView>(
                    R.id.tvSteps
                )

        val backButton =
            detailView
                .findViewById<Button>(
                    R.id.btnBackToRecipes
                )


        /*
         * Información de la receta.
         */
        title.text =
            recipe.name

        image.setImageResource(
            recipe.imageResId
        )

        description.text =
            recipe.description


        /*
         * Ingredientes.
         */
        ingredients.text =
            recipe.ingredients
                .joinToString(
                    separator = "\n"
                ) { ingredient ->

                    "• $ingredient"
                }


        /*
         * Pasos de preparación.
         */
        steps.text =
            recipe.steps
                .mapIndexed {
                        index,
                        step ->

                    "${index + 1}. $step"

                }
                .joinToString(
                    "\n"
                )


        /*
         * Regresar a la galería.
         */
        backButton.setOnClickListener {

            showRecipeList(
                root,
                "Galería de recetas",
                "Selecciona una receta para conocer sus ingredientes y preparación",
                false
            )
        }
    }


    /*
     * Muestra el video correspondiente
     * a una receta.
     */
    private fun showRecipeVideo(
        root: ViewGroup,
        recipe: Recipe
    ) {

        root.removeAllViews()


        /*
         * Cargamos la interfaz
         * del reproductor.
         */
        val videoView =
            LayoutInflater
                .from(requireContext())
                .inflate(
                    R.layout.fragment_recipe_video,
                    root,
                    false
                )

        root.addView(
            videoView
        )


        /*
         * Vinculación de vistas.
         */
        val title =
            videoView
                .findViewById<TextView>(
                    R.id.tvVideoRecipeTitle
                )

        val category =
            videoView
                .findViewById<TextView>(
                    R.id.chipCategory
                )

        val time =
            videoView
                .findViewById<TextView>(
                    R.id.chipTime
                )

        val servings =
            videoView
                .findViewById<TextView>(
                    R.id.chipServings
                )

        val description =
            videoView
                .findViewById<TextView>(
                    R.id.tvVideoDescription
                )

        val backButton =
            videoView
                .findViewById<Button>(
                    R.id.btnBackToVideoRecipes
                )

        val playerView =
            videoView
                .findViewById<androidx.media3.ui.PlayerView>(
                    R.id.playerView
                )


        /*
         * Información de la receta.
         */
        title.text =
            "${recipe.name} paso a paso"

        category.text =
            recipe.category

        time.text =
            "${recipe.preparationTime} min"

        servings.text =
            "${recipe.servings} porciones"

        description.text =
            "En este video podrás conocer el proceso de preparación de ${recipe.name}, desde los primeros pasos hasta el resultado final."


        /*
         * Creamos el reproductor.
         */
        val player =
            androidx.media3.exoplayer.ExoPlayer
                .Builder(
                    requireContext()
                )
                .build()

        playerView.player =
            player


        /*
         * Localización del video
         * almacenado en res/raw.
         */
        val videoUri =
            android.net.Uri.parse(
                "android.resource://${requireContext().packageName}/${recipe.videoResId}"
            )


        /*
         * Creamos el elemento multimedia.
         */
        val mediaItem =
            androidx.media3.common.MediaItem
                .fromUri(
                    videoUri
                )

        player.setMediaItem(
            mediaItem
        )

        player.prepare()

        /*
         * El video no inicia automáticamente.
         */
        player.playWhenReady =
            false


        /*
         * Temporalmente conservamos el botón
         * para regresar al listado de videos.
         *
         * Más adelante podemos eliminarlo
         * para reproducir fielmente el mockup.
         */
        backButton.setOnClickListener {

            player.release()

            showRecipeList(
                root,
                "Videos de recetas",
                "Toca una receta para ver su video",
                true
            )
        }


        /*
         * Liberamos ExoPlayer cuando
         * la vista deja de estar visible.
         */
        videoView.addOnAttachStateChangeListener(

            object :
                View.OnAttachStateChangeListener {

                override fun onViewAttachedToWindow(
                    v: View
                ) {

                    // No es necesario realizar ninguna acción.
                }


                override fun onViewDetachedFromWindow(
                    v: View
                ) {

                    player.release()
                }
            }
        )
    }
}