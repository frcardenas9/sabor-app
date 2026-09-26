package com.hpm.saborapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.imgRecipe)
        val name: TextView = view.findViewById(R.id.tvRecipeName)
        val info: TextView = view.findViewById(R.id.tvRecipeInfo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)

        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) {

        val recipe = recipes[position]

        holder.image.setImageResource(recipe.imageResId)

        holder.name.text = recipe.name

        holder.info.text =
            "${recipe.category} · ${recipe.preparationTime} mins · ${recipe.servings} porciones"

        holder.itemView.setOnClickListener {
            onRecipeClick(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
