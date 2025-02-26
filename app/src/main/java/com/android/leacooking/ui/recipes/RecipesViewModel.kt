package com.android.leacooking.ui.recipes

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.models.custom.RecipePreview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor() : ViewModel() {
    private val recipeItems = listOf(
        // Sous-catégorie Viande
        RecipePreview(
            id = 1,
            title = "Poulet rôti",
            imageUrl = "https://www.apero-bordeaux.fr/wp-content/uploads/2024/02/20240216_65cfa1ce1fa54.jpg",
            recipeSubcategoryId = 1
        ),
        RecipePreview(
            id = 2,
            title = "Boulettes de bœuf scandinaves",
            imageUrl = "https://www.demotivateur.fr/uploads/4483/poisson%20bbq%20-%20copie.jpg",
            recipeSubcategoryId = 1
        ),

        // Sous-catégorie Poisson
        RecipePreview(
            id = 3,
            title = "Saumon grillé à l'aneth",
            imageUrl = "https://www.seafoodsource.com/sites/default/files/styles/large/public/2020-03/Salmon-grilled_1.jpg",
            recipeSubcategoryId = 2
        ),
        RecipePreview(
            id = 4,
            title = "Filets de morue au beurre citronné",
            imageUrl = "https://www.seriouseats.com/thmb/qfD6OQ0it0a5HTlQIdw6FgyC57g=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/20160303-lemons-are-the-secret-to-amazing-fish-3x2-5fa52bff4c1a49089be1fe0c3932e8eb.jpg",
            recipeSubcategoryId = 2
        ),

        // Sous-catégorie Végétarien
        RecipePreview(
            id = 5,
            title = "Bowl de légumes rôtis",
            imageUrl = "https://www.kiwimedia.fr/wp-content/uploads/2020/06/vegetarian-bowl.jpg",
            recipeSubcategoryId = 3
        ),
        RecipePreview(
            id = 6,
            title = "Tofu grillé aux légumes",
            imageUrl = "https://www.cookinglight.com/thmb/J0FY0qCdfqZv1sDqvklfyXYptoc=/1000x1000/smart/filters:no_upscale()/grilled-tofu-276d30bb77954e2b8d0b431c7b1c9f57.jpg",
            recipeSubcategoryId = 3
        ),

        // Sous-catégorie Pizza
        RecipePreview(
            id = 7,
            title = "Pizza Margherita",
            imageUrl = "https://cdn.shopify.com/s/files/1/0274/9503/9079/files/20220211142754-margherita-9920_5a73220e-4a1a-4d33-b38f-26e98e3cd986.jpg?v=1723650067",
            recipeSubcategoryId = 4
        ),
        RecipePreview(
            id = 8,
            title = "Pizza Quattro Formaggi",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3d/Pizza_Quattro_Formaggi.jpg",
            recipeSubcategoryId = 4
        ),

        // Sous-catégorie Sandwich
        RecipePreview(
            id = 9,
            title = "Club sandwich au poulet",
            imageUrl = "https://cdn.pratico-pratiques.com/app/uploads/sites/2/2018/08/29093254/club-sandwich-au-poulet-et-prosciutto.jpeg",
            recipeSubcategoryId = 5
        ),
        RecipePreview(
            id = 10,
            title = "Sandwich au thon et avocat",
            imageUrl = "https://www.cuisineaz.com/recettes/sandwich-au-thon-et-avocat-7891.jpg",
            recipeSubcategoryId = 5
        ),

        // Sous-catégorie Asiatique
        RecipePreview(
            id = 11,
            title = "Chow Mein aux légumes",
            imageUrl = "https://freethepickle.fr/wp-content/uploads/2021/03/chow-mein-vegetarien-3-of-3.jpg",
            recipeSubcategoryId = 6
        ),
        RecipePreview(
            id = 12,
            title = "Riz sauté aux crevettes",
            imageUrl = "https://www.asianinspirations.com.au/wp-content/uploads/2019/07/Chinese-Fried-Rice.jpg",
            recipeSubcategoryId = 6
        ),

        // Sous-catégorie Bases
        RecipePreview(
            id = 13,
            title = "Pain de mie maison",
            imageUrl = "https://res.cloudinary.com/swissmilk/image/fetch/w_1600,c_fill,g_auto,f_auto,q_auto:eco,ar_16:9/https://api.swissmilk.ch/wp-content/uploads/2020/12/Hefeteig_TEIG_2013_05.jpg",
            recipeSubcategoryId = 7
        ),
        RecipePreview(
            id = 14,
            title = "Pâte à pizza maison",
            imageUrl = "https://www.passionculinaire.fr/wp-content/uploads/2019/09/pate-pizza-1500x1000.jpg",
            recipeSubcategoryId = 7
        ),

        // Sous-catégorie Sauces
        RecipePreview(
            id = 15,
            title = "Sauce tomate maison",
            imageUrl = "https://files.meilleurduchef.com/mdc/photo/recette/sauce-au-madere/sauce-au-madere-640.jpg",
            recipeSubcategoryId = 8
        ),
        RecipePreview(
            id = 16,
            title = "Sauce béchamel",
            imageUrl = "https://www.cuisineaz.com/660x660/2019/04/05/i148131-sauce-bechamel.jpeg",
            recipeSubcategoryId = 8
        ),

        // Catégorie "Sucré"

        // Sous-catégorie 9
        RecipePreview(
            id = 17,
            title = "Entremets au citron et praliné",
            imageUrl = "https://media.focus-cuisine.com/i/850/8505958/entremets-citron-praline-L-KUO4qB.jpeg",
            recipeSubcategoryId = 9
        ),
        RecipePreview(
            id = 18,
            title = "Gâteau au chocolat fondant",
            imageUrl = "https://www.cuisineactuelle.fr/sites/cuisineactuelle.fr/files/styles/scale_1500_1500/public/gateau-au-chocolat-fondant.jpg",
            recipeSubcategoryId = 9
        ),

        // Sous-catégorie Tartes
        RecipePreview(
            id = 19,
            title = "Tarte aux fraises",
            imageUrl = "https://res.cloudinary.com/swissmilk/image/fetch/w_1600,c_fill,g_auto,f_auto,q_auto:eco,ar_16:9/https://api.swissmilk.ch/wp-content/uploads/2019/06/erdbeerkuchen-tarte-aux-fraises.jpg",
            recipeSubcategoryId = 10
        ),
        RecipePreview(
            id = 20,
            title = "Tarte au chocolat",
            imageUrl = "https://www.ouest-france.fr/economie/les-prix-des-fruits-et-legumes-au-20-juillet-2023-la-tarte-au-chocolat-mise-en-sommeil-22b7e199-01fc-4b4d-a2e5-ff4aab10b7d3",
            recipeSubcategoryId = 10
        ),

        // Sous-catégorie Crèmes
        RecipePreview(
            id = 21,
            title = "Crème pâtissière légère",
            imageUrl = "https://img.cuisineaz.com/660x660/2018/01/29/i135314-creme-patissiere-legere.jpeg",
            recipeSubcategoryId = 11
        ),
        RecipePreview(
            id = 22,
            title = "Crème brûlée",
            imageUrl = "https://img.cuisineaz.com/660x660/2018/01/29/i135314-creme-patissiere-legere.jpeg",
            recipeSubcategoryId = 11
        ),

        // Sous-catégorie Biscuits
        RecipePreview(
            id = 23,
            title = "Biscuits sablés",
            imageUrl = "https://www.fashioncooking.fr/wp-content/uploads/2018/06/parfaits-biscuits-personnalises.jpg",
            recipeSubcategoryId = 12
        ),
        RecipePreview(
            id = 24,
            title = "Biscuits à la confiture",
            imageUrl = "https://www.cuisineactuelle.fr/sites/cuisineactuelle.fr/files/styles/scale_1500_1500/public/biscuits-a-la-confiture.jpg",
            recipeSubcategoryId = 12,
        )
    )
    
    fun loadRecipes(recipeSubcategoryId: Long) {
        _recipes.value = recipeItems.filter { it.recipeSubcategoryId == recipeSubcategoryId }
    }

    private val _recipes = MutableStateFlow<List<RecipePreview>>(recipeItems)
    val recipes: StateFlow<List<RecipePreview>> get() = _recipes
}
