package com.hpm.saborapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

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

    /*
     * Se conservan los videos propios de cada receta.
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
            videoResId = R.raw.arepa_choclo,
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
            videoResId = R.raw.sancocho_gallina,
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

    /*
     * Cantidades base por porción para la calculadora.
     * Se incorpora desde origin/main.
     */
    private val recipeIngredients = mapOf(

        1 to listOf(
            RecipeIngredient("Pollo", 166.67, IngredientUnit.GRAMS),
            RecipeIngredient("Papa criolla", 100.0, IngredientUnit.GRAMS),
            RecipeIngredient("Papa pastusa", 100.0, IngredientUnit.GRAMS),
            RecipeIngredient("Papa sabanera", 100.0, IngredientUnit.GRAMS),
            RecipeIngredient("Guascas", 5.0, IngredientUnit.GRAMS),
            RecipeIngredient("Mazorca", 0.5, IngredientUnit.UNITS),
            RecipeIngredient("Agua", 500.0, IngredientUnit.MILLILITERS),
            RecipeIngredient("Sal", null, IngredientUnit.TO_TASTE)
        ),

        2 to listOf(
            RecipeIngredient("Mazorca tierna", 1.0, IngredientUnit.UNITS),
            RecipeIngredient("Harina de maíz", 75.0, IngredientUnit.GRAMS),
            RecipeIngredient("Leche", 100.0, IngredientUnit.MILLILITERS),
            RecipeIngredient("Azúcar", 10.0, IngredientUnit.GRAMS),
            RecipeIngredient("Sal", null, IngredientUnit.TO_TASTE),
            RecipeIngredient("Queso", 50.0, IngredientUnit.GRAMS)
        ),

        3 to listOf(
            RecipeIngredient("Gallina", 250.0, IngredientUnit.GRAMS),
            RecipeIngredient("Plátano verde", 0.5, IngredientUnit.UNITS),
            RecipeIngredient("Yuca", 150.0, IngredientUnit.GRAMS),
            RecipeIngredient("Papa", 100.0, IngredientUnit.GRAMS),
            RecipeIngredient("Mazorca", 0.5, IngredientUnit.UNITS),
            RecipeIngredient("Cebolla", 0.25, IngredientUnit.UNITS),
            RecipeIngredient("Cilantro", 5.0, IngredientUnit.GRAMS),
            RecipeIngredient("Sal", null, IngredientUnit.TO_TASTE)
        ),

        4 to listOf(
            RecipeIngredient("Leche", 300.0, IngredientUnit.MILLILITERS),
            RecipeIngredient("Azúcar", 50.0, IngredientUnit.GRAMS),
            RecipeIngredient("Canela", 2.0, IngredientUnit.GRAMS),
            RecipeIngredient("Yemas de huevo", 1.0, IngredientUnit.UNITS),
            RecipeIngredient("Esencia de vainilla", 2.0, IngredientUnit.MILLILITERS)
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
                    "Selecciona una receta para conocer sus ingredientes y preparación",
                    false
                )
            }

            "inicio" -> {
                showDefaultContent(view)
            }

            "perfil" -> {
                showSimpleContent(
                    view,
                    "Perfil del chef",
                    "Conoce la trayectoria, experiencia y filosofía culinaria de la autora del recetario."
                )
            }

            /*
             * Se conserva el comportamiento adaptado al mockup:
             * al tocar Video se abre directamente la primera receta.
             */
            "video" -> {
                val root = view.findViewById<ViewGroup>(
                    R.id.contentRoot
                )

                showRecipeVideo(
                    root,
                    recipes.first()
                )
            }

            /*
             * Se incorpora la funcionalidad real de Web de origin/main.
             */
            "web" -> {
                showWebContent(view)
            }

            /*
             * Se incorpora la calculadora de porciones y compartir.
             */
            "botones" -> {
                showPortionCalculator(view)
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
        image.setImageResource(recipe.imageResId)
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
                "Selecciona una receta para conocer sus ingredientes y preparación",
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
        time.text = "${recipe.preparationTime} min"
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

        /*
         * Se mantiene el acceso al listado de videos
         * desde el botón "Volver a recetas".
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

        videoView.addOnAttachStateChangeListener(
            object : View.OnAttachStateChangeListener {

                override fun onViewAttachedToWindow(
                    v: View
                ) {
                    // Sin acción.
                }

                override fun onViewDetachedFromWindow(
                    v: View
                ) {
                    player.release()
                }
            }
        )
    }

    /*
     * Funcionalidad Web incorporada desde origin/main.
     */
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

                addressInput.error =
                    "Ingresa una dirección"

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

    private fun normalizeUrl(
        address: String
    ): String {

        return if (
            address.startsWith("http://") ||
            address.startsWith("https://")
        ) {
            address
        } else {
            "https://$address"
        }
    }

    /*
     * Calculadora de porciones incorporada desde origin/main.
     */
    private fun showPortionCalculator(
        view: View
    ) {

        val root = view.findViewById<ViewGroup>(
            R.id.contentRoot
        )

        root.removeAllViews()

        val calculatorView = LayoutInflater.from(requireContext())
            .inflate(
                R.layout.fragment_buttons,
                root,
                false
            )

        root.addView(calculatorView)

        val spinnerRecipe = calculatorView.findViewById<Spinner>(
            R.id.spinnerRecipe
        )

        val decreaseButton = calculatorView.findViewById<Button>(
            R.id.btnDecreasePortion
        )

        val increaseButton = calculatorView.findViewById<Button>(
            R.id.btnIncreasePortion
        )

        val portionCountText = calculatorView.findViewById<TextView>(
            R.id.tvPortionCount
        )

        val gramsSwitch = calculatorView.findViewById<SwitchMaterial>(
            R.id.switchGrams
        )

        val includeStepsCheckBox = calculatorView.findViewById<CheckBox>(
            R.id.checkIncludeSteps
        )

        val resultTitle = calculatorView.findViewById<TextView>(
            R.id.tvResultTitle
        )

        val resultIngredients = calculatorView.findViewById<TextView>(
            R.id.tvResultIngredients
        )

        val shareButton = calculatorView.findViewById<Button>(
            R.id.btnShareRecipe
        )

        val recipeNames = recipes.map {
            it.name
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            recipeNames
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerRecipe.adapter = adapter

        var portions = 1

        fun updateResult() {

            val selectedPosition =
                spinnerRecipe.selectedItemPosition

            if (selectedPosition < 0) {
                return
            }

            val selectedRecipe =
                recipes[selectedPosition]

            portionCountText.text =
                portions.toString()

            resultTitle.text =
                "Resultado para $portions " +
                        if (portions == 1) {
                            "porción"
                        } else {
                            "porciones"
                        }

            val ingredients =
                recipeIngredients[
                    selectedRecipe.id
                ] ?: emptyList()

            resultIngredients.text =
                ingredients.joinToString("\n") { ingredient ->

                    "• ${ingredient.name}: ${
                        PortionCalculator.formatAmount(
                            ingredient,
                            portions,
                            gramsSwitch.isChecked
                        )
                    }"
                }

            decreaseButton.isEnabled =
                portions > 1
        }

        decreaseButton.setOnClickListener {

            if (portions > 1) {
                portions--
                updateResult()
            }
        }

        increaseButton.setOnClickListener {

            portions++
            updateResult()
        }

        spinnerRecipe.onItemSelectedListener =
            object :
                android.widget.AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    selectedView: View?,
                    position: Int,
                    id: Long
                ) {
                    updateResult()
                }

                override fun onNothingSelected(
                    parent: android.widget.AdapterView<*>?
                ) {
                    // Sin acción.
                }
            }

        gramsSwitch.setOnCheckedChangeListener { _, _ ->
            updateResult()
        }

        shareButton.setOnClickListener {

            val selectedPosition =
                spinnerRecipe.selectedItemPosition

            if (selectedPosition < 0) {
                return@setOnClickListener
            }

            val selectedRecipe =
                recipes[selectedPosition]

            shareRecipe(
                selectedRecipe,
                portions,
                gramsSwitch.isChecked,
                includeStepsCheckBox.isChecked
            )
        }

        updateResult()
    }

    private fun shareRecipe(
        recipe: Recipe,
        portions: Int,
        showGrams: Boolean,
        includeSteps: Boolean
    ) {

        val ingredients =
            recipeIngredients[
                recipe.id
            ] ?: emptyList()

        val ingredientText =
            ingredients.joinToString("\n") { ingredient ->

                "• ${ingredient.name}: ${
                    PortionCalculator.formatAmount(
                        ingredient,
                        portions,
                        showGrams
                    )
                }"
            }

        val portionsText =
            if (portions == 1) {
                "1 porción"
            } else {
                "$portions porciones"
            }

        val message = buildString {

            appendLine(recipe.name)
            appendLine()
            appendLine(recipe.description)
            appendLine()
            appendLine("Cantidad: $portionsText")
            appendLine()
            appendLine("Ingredientes:")
            appendLine(ingredientText)

            if (includeSteps) {

                appendLine()
                appendLine("Preparación:")

                recipe.steps.forEachIndexed { index, step ->

                    appendLine(
                        "${index + 1}. $step"
                    )
                }
            }
        }

        val shareIntent = Intent(
            Intent.ACTION_SEND
        ).apply {

            type = "text/plain"

            putExtra(
                Intent.EXTRA_TEXT,
                message
            )
        }

        startActivity(
            Intent.createChooser(
                shareIntent,
                "Compartir receta"
            )
        )

        Toast.makeText(
            requireContext(),
            "¡Receta lista para compartir!",
            Toast.LENGTH_SHORT
        ).show()
    }
}
