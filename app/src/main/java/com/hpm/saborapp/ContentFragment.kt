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
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast

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

    private val recipes = listOf(

        Recipe(
            id = 1,
            name = "Ajiaco santafereño",
            category = "Sopa",
            preparationTime = 90,
            servings = 6,
            imageResId = R.drawable.ajiaco,
            videoResId = R.raw.postre_natas,
            description = "El ajiaco bogotano o santafereño es una sopa típica y tradicional de la región de Bogotá, Cundinamarca, Colombia, a base de pollo y diferentes clases de papa. A diferencia de lo que sugiere su nombre, el ajiaco no es picante.",
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
            videoResId = R.raw.postre_natas,
            description = "La arepa de choclo es una preparación tradicional colombiana elaborada principalmente con maíz tierno. Es común acompañarla con queso y disfrutarla especialmente durante el desayuno o como merienda.",
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
            videoResId = R.raw.postre_natas,
            description = "El sancocho de gallina es una preparación tradicional colombiana que combina gallina, tubérculos, plátano y otros ingredientes en un caldo abundante y lleno de sabor.",
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
            description = "El postre de natas es una preparación tradicional colombiana elaborada a partir de la nata de la leche y azúcar, con una textura suave y un sabor dulce característico.",
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
        super.onViewCreated(view, savedInstanceState)

        showOption(
            view,
            arguments?.getString(ARG_OPTION)
        )
    }

    private fun showOption(
        view: View,
        option: String?
    ) {

        when (option) {

            "fotos" -> {
                showRecipeList(
                    view,
                    "Galería de recetas",
                    "Toca una receta para ver su descripción",
                    false
                )
            }

            "inicio" -> {
                showDefaultContent(view)
            }

            "perfil" -> {
                showSimpleContent(
                    view,
                    "Perfil",
                    "Información del perfil."
                )
            }

            "video" -> {
                showRecipeList(
                    view,
                    "Videos de recetas",
                    "Toca una receta para ver su video",
                    true
                )
            }

            "web" -> {
                showWebContent(view)
            }

            "botones" -> {
                showSimpleContent(
                    view,
                    "Botones",
                    "Ejemplos de botones."
                )
            }

            else -> {
                showDefaultContent(view)
            }
        }
    }

    private fun showDefaultContent(view: View) {

        val title = view.findViewById<TextView>(
            R.id.tvContentTitle
        )

        val description = view.findViewById<TextView>(
            R.id.tvContentDescription
        )

        title.text = "Bienvenido a SaborApp"

        description.text =
            "Selecciona una opción del menú."
    }

    private fun showSimpleContent(
        view: View,
        titleText: String,
        descriptionText: String
    ) {

        val title = view.findViewById<TextView>(
            R.id.tvContentTitle
        )

        val description = view.findViewById<TextView>(
            R.id.tvContentDescription
        )

        title.text = titleText

        description.text = descriptionText
    }

    private fun showRecipeList(
        view: View,
        titleText: String,
        subtitleText: String,
        openVideo: Boolean
    ) {

        val root = view.findViewById<ViewGroup>(
            R.id.contentRoot
        )

        root.removeAllViews()

        val recipeListView = LayoutInflater.from(requireContext())
            .inflate(
                R.layout.fragment_recipe_list,
                root,
                false
            )

        root.addView(recipeListView)

        val galleryTitle = recipeListView.findViewById<TextView>(
            R.id.tvGalleryTitle
        )

        val gallerySubtitle = recipeListView.findViewById<TextView>(
            R.id.tvGallerySubtitle
        )

        galleryTitle.text = titleText
        gallerySubtitle.text = subtitleText

        val recyclerView = recipeListView.findViewById<RecyclerView>(
            R.id.recyclerRecipes
        )

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        recyclerView.adapter = RecipeAdapter(
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

    private fun showRecipeDetail(
        root: ViewGroup,
        recipe: Recipe
    ) {

        root.removeAllViews()

        val detailView = LayoutInflater.from(requireContext())
            .inflate(
                R.layout.fragment_recipe_detail,
                root,
                false
            )

        root.addView(detailView)

        val title = detailView.findViewById<TextView>(
            R.id.tvDetailTitle
        )

        val image = detailView.findViewById<ImageView>(
            R.id.imgDetailRecipe
        )

        val description = detailView.findViewById<TextView>(
            R.id.tvDetailDescription
        )

        val ingredients = detailView.findViewById<TextView>(
            R.id.tvIngredients
        )

        val steps = detailView.findViewById<TextView>(
            R.id.tvSteps
        )

        val backButton = detailView.findViewById<Button>(
            R.id.btnBackToRecipes
        )

        title.text = recipe.name

        image.setImageResource(
            recipe.imageResId
        )

        description.text = recipe.description

        ingredients.text = recipe.ingredients.joinToString(
            separator = "\n"
        ) { ingredient ->
            "• $ingredient"
        }

        steps.text = recipe.steps.mapIndexed { index, step ->
            "${index + 1}. $step"
        }.joinToString("\n")

        backButton.setOnClickListener {

            showRecipeList(
                root,
                "Galería de recetas",
                "Toca una receta para ver su descripción",
                false
            )
        }
    }

    private fun showRecipeVideo(
        root: ViewGroup,
        recipe: Recipe
    ) {

        root.removeAllViews()

        val videoView = LayoutInflater.from(requireContext())
            .inflate(
                R.layout.fragment_recipe_video,
                root,
                false
            )

        root.addView(videoView)

        val title = videoView.findViewById<TextView>(
            R.id.tvVideoRecipeTitle
        )

        val category = videoView.findViewById<TextView>(
            R.id.chipCategory
        )

        val time = videoView.findViewById<TextView>(
            R.id.chipTime
        )

        val servings = videoView.findViewById<TextView>(
            R.id.chipServings
        )

        val description = videoView.findViewById<TextView>(
            R.id.tvVideoDescription
        )

        val backButton = videoView.findViewById<Button>(
            R.id.btnBackToVideoRecipes
        )

        val playerView = videoView.findViewById<androidx.media3.ui.PlayerView>(
            R.id.playerView
        )

        title.text = "${recipe.name} paso a paso"

        category.text = recipe.category

        time.text = "${recipe.preparationTime} mins"

        servings.text = "${recipe.servings} porciones"

        description.text =
            "En este video podrás conocer el proceso de preparación de ${recipe.name}, desde los primeros pasos hasta el resultado final."

        val player = androidx.media3.exoplayer.ExoPlayer.Builder(
            requireContext()
        ).build()

        playerView.player = player

        val videoUri = android.net.Uri.parse(
            "android.resource://${requireContext().packageName}/${recipe.videoResId}"
        )

        val mediaItem =
            androidx.media3.common.MediaItem.fromUri(videoUri)

        player.setMediaItem(mediaItem)

        player.prepare()

        player.playWhenReady = false

        backButton.setOnClickListener {

            player.release()

            showRecipeList(
                root,
                "Videos de recetas",
                "Toca una receta para ver su video",
                true
            )
        }

        videoView.addOnAttachStateChangeListener(
            object : View.OnAttachStateChangeListener {

                override fun onViewAttachedToWindow(
                    v: View
                ) { }

                override fun onViewDetachedFromWindow(
                    v: View
                ) {
                    player.release()
                }
            }
        )
    }

    private fun showWebContent(view: View) {
        val root = view.findViewById<ViewGroup>(
            R.id.contentRoot
        )

        root.removeAllViews()

        val webViewLayout = LayoutInflater.from(requireContext())
            .inflate(
                R.layout.fragment_web,
                root,
                false
            )

        root.addView(webViewLayout)

        val addressInput = webViewLayout.findViewById<EditText>(
            R.id.etWebAddress
        )

        val loadButton = webViewLayout.findViewById<Button>(
            R.id.btnLoadWeb
        )

        val webView = webViewLayout.findViewById<WebView>(
            R.id.webView
        )

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true

        webView.webChromeClient = WebChromeClient()

        loadButton.setOnClickListener {

            val address = addressInput.text
                .toString()
                .trim()

            if (address.isEmpty()) {

                addressInput.error = "Ingresa una dirección"

                return@setOnClickListener
            }

            val url = normalizeUrl(address)

            webView.loadUrl(url)

            val inputMethodManager =
                requireContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(
                addressInput.windowToken,
                0
            )
        }
    }

    private fun normalizeUrl(address: String): String {
        return if (
            address.startsWith("http://") ||
            address.startsWith("https://")
        ) {
            address
        } else {
            "https://$address"
        }
    }
}
