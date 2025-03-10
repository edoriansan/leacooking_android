package com.android.leacooking.ui.subcategories

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.model.room.RecipeSubcategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SubCategoriesViewModel @Inject constructor() : ViewModel() {
    // Mock data
    private val recipeSubcategoryItems = listOf(
        // Catégorie "Salé"
        RecipeSubcategory(
            id = 1,
            recipeSubcategoryLabel = "Viande",
            recipeSubcategoryImg = "https://www.alimentsduquebec.com/files/20210331/600x600_1617197027.jpg",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 2,
            recipeSubcategoryLabel = "Poisson",
            recipeSubcategoryImg = "https://www.demotivateur.fr/uploads/4483/poisson%20bbq%20-%20copie.jpg",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 3,
            recipeSubcategoryLabel = "Végétarien",
            recipeSubcategoryImg = "https://assets.magimix.com/files/rec_4735/brocolis-vapeur_photo.jpg",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 4,
            recipeSubcategoryLabel = "Pizza",
            recipeSubcategoryImg = "https://cdn.shopify.com/s/files/1/0274/9503/9079/files/20220211142754-margherita-9920_5a73220e-4a1a-4d33-b38f-26e98e3cd986.jpg?v=1723650067",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 5,
            recipeSubcategoryLabel = "Sandwich",
            recipeSubcategoryImg = "https://cdn.pratico-pratiques.com/app/uploads/sites/2/2018/08/29093254/club-sandwich-au-poulet-et-prosciutto.jpeg",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 6,
            recipeSubcategoryLabel = "Asiatique",
            recipeSubcategoryImg = "https://freethepickle.fr/wp-content/uploads/2021/03/chow-mein-vegetarien-3-of-3.jpg",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 7,
            recipeSubcategoryLabel = "Bases",
            recipeSubcategoryImg = "https://res.cloudinary.com/swissmilk/image/fetch/w_1600,c_fill,g_auto,f_auto,q_auto:eco,ar_16:9/https://api.swissmilk.ch/wp-content/uploads/2020/12/Hefeteig_TEIG_2013_05.jpg",
            recipeCategoryId = 1
        ),
        RecipeSubcategory(
            id = 8,
            recipeSubcategoryLabel = "Sauces",
            recipeSubcategoryImg = "https://files.meilleurduchef.com/mdc/photo/recette/sauce-au-madere/sauce-au-madere-640.jpg",
            recipeCategoryId = 1
        ),

        // Catégorie "Sucré"
        RecipeSubcategory(
            id = 9,
            recipeSubcategoryLabel = "Gâteaux",
            recipeSubcategoryImg = "https://media.focus-cuisine.com/i/850/8505958/entremets-citron-praline-L-KUO4qB.jpeg",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 10,
            recipeSubcategoryLabel = "Tartes",
            recipeSubcategoryImg = "https://res.cloudinary.com/swissmilk/image/fetch/w_1600,c_fill,g_auto,f_auto,q_auto:eco,ar_16:9/https://api.swissmilk.ch/wp-content/uploads/2019/06/erdbeerkuchen-tarte-aux-fraises.jpg",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 11,
            recipeSubcategoryLabel = "Crèmes",
            recipeSubcategoryImg = "https://img.cuisineaz.com/660x660/2018/01/29/i135314-creme-patissiere-legere.jpeg",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 12,
            recipeSubcategoryLabel = "Biscuits",
            recipeSubcategoryImg = "https://www.fashioncooking.fr/wp-content/uploads/2018/06/parfaits-biscuits-personnalises.jpg",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 13,
            recipeSubcategoryLabel = "Glaces",
            recipeSubcategoryImg = "https://static.wixstatic.com/media/d2c4b4_7b5f516d67d94ed1a834d0b7c5af0b8a~mv2.jpg/v1/fill/w_568,h_746,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/d2c4b4_7b5f516d67d94ed1a834d0b7c5af0b8a~mv2.jpg",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 14,
            recipeSubcategoryLabel = "Bonbons",
            recipeSubcategoryImg = "https://static.cuisineaz.com/610x610/i149750-bonbons-maison.jpg",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 15,
            recipeSubcategoryLabel = "Orientales",
            recipeSubcategoryImg = "https://static.750g.com/images/1200-630/7d6ad37c484c839af5b9bfc35a5c7243/makrout-classique.png",
            recipeCategoryId = 2
        ),
        RecipeSubcategory(
            id = 16,
            recipeSubcategoryLabel = "Viennoiseries",
            recipeSubcategoryImg = "https://www.paul.fr/media/catalog/product/2/0/2020-amb-vien-plateau-12mini-viennoiserie.jpg?quality=80&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700",
            recipeCategoryId = 2
        )
    )

    fun loadSubCategories(recipeCategoryId: Long) {
        _subCategories.value = recipeSubcategoryItems.filter { it.recipeCategoryId == recipeCategoryId }
    }

    private val _subCategories = MutableStateFlow<List<RecipeSubcategory>>(recipeSubcategoryItems)
    val subCategories: StateFlow<List<RecipeSubcategory>> get() = _subCategories
}
