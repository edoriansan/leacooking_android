package com.android.leacooking.ui.subcategories

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.models.room.SubCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SubCategoriesViewModel @Inject constructor() : ViewModel() {
    // Mock data
    private val subCategoryItems = listOf(
        // Catégorie "Salé"
        SubCategory(
            id = 1,
            subCategoryLabel = "Viande",
            subCategoryImg = "https://www.alimentsduquebec.com/files/20210331/600x600_1617197027.jpg",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 2,
            subCategoryLabel = "Poisson",
            subCategoryImg = "https://www.demotivateur.fr/uploads/4483/poisson%20bbq%20-%20copie.jpg",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 3,
            subCategoryLabel = "Végétarien",
            subCategoryImg = "https://assets.magimix.com/files/rec_4735/brocolis-vapeur_photo.jpg",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 4,
            subCategoryLabel = "Pizza",
            subCategoryImg = "https://cdn.shopify.com/s/files/1/0274/9503/9079/files/20220211142754-margherita-9920_5a73220e-4a1a-4d33-b38f-26e98e3cd986.jpg?v=1723650067",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 5,
            subCategoryLabel = "Sandwich",
            subCategoryImg = "https://cdn.pratico-pratiques.com/app/uploads/sites/2/2018/08/29093254/club-sandwich-au-poulet-et-prosciutto.jpeg",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 6,
            subCategoryLabel = "Asiatique",
            subCategoryImg = "https://freethepickle.fr/wp-content/uploads/2021/03/chow-mein-vegetarien-3-of-3.jpg",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 7,
            subCategoryLabel = "Bases",
            subCategoryImg = "https://res.cloudinary.com/swissmilk/image/fetch/w_1600,c_fill,g_auto,f_auto,q_auto:eco,ar_16:9/https://api.swissmilk.ch/wp-content/uploads/2020/12/Hefeteig_TEIG_2013_05.jpg",
            categoryLabel = "Salé",
            categoryId = 1
        ),
        SubCategory(
            id = 8,
            subCategoryLabel = "Sauces",
            subCategoryImg = "https://files.meilleurduchef.com/mdc/photo/recette/sauce-au-madere/sauce-au-madere-640.jpg",
            categoryLabel = "Salé",
            categoryId = 1
        ),

        // Catégorie "Sucré"
        SubCategory(
            id = 9,
            subCategoryLabel = "Gâteaux",
            subCategoryImg = "https://media.focus-cuisine.com/i/850/8505958/entremets-citron-praline-L-KUO4qB.jpeg",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 10,
            subCategoryLabel = "Tartes",
            subCategoryImg = "https://res.cloudinary.com/swissmilk/image/fetch/w_1600,c_fill,g_auto,f_auto,q_auto:eco,ar_16:9/https://api.swissmilk.ch/wp-content/uploads/2019/06/erdbeerkuchen-tarte-aux-fraises.jpg",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 11,
            subCategoryLabel = "Crèmes",
            subCategoryImg = "https://img.cuisineaz.com/660x660/2018/01/29/i135314-creme-patissiere-legere.jpeg",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 12,
            subCategoryLabel = "Biscuits",
            subCategoryImg = "https://www.fashioncooking.fr/wp-content/uploads/2018/06/parfaits-biscuits-personnalises.jpg",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 13,
            subCategoryLabel = "Glaces",
            subCategoryImg = "https://static.wixstatic.com/media/d2c4b4_7b5f516d67d94ed1a834d0b7c5af0b8a~mv2.jpg/v1/fill/w_568,h_746,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/d2c4b4_7b5f516d67d94ed1a834d0b7c5af0b8a~mv2.jpg",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 14,
            subCategoryLabel = "Bonbons",
            subCategoryImg = "https://static.cuisineaz.com/610x610/i149750-bonbons-maison.jpg",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 15,
            subCategoryLabel = "Orientales",
            subCategoryImg = "https://static.750g.com/images/1200-630/7d6ad37c484c839af5b9bfc35a5c7243/makrout-classique.png",
            categoryLabel = "Sucré",
            categoryId = 2
        ),
        SubCategory(
            id = 16,
            subCategoryLabel = "Viennoiseries",
            subCategoryImg = "https://www.paul.fr/media/catalog/product/2/0/2020-amb-vien-plateau-12mini-viennoiserie.jpg?quality=80&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700",
            categoryLabel = "Sucré",
            categoryId = 2
        )
    )

    fun loadSubCategories(categoryId: Long) {
        _subCategories.value = subCategoryItems.filter { it.categoryId == categoryId }
    }

    private val _subCategories = MutableStateFlow<List<SubCategory>>(subCategoryItems)
    val subCategories: StateFlow<List<SubCategory>> get() = _subCategories
}
