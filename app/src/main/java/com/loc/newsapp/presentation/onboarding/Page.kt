package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Stay Informed",
        description =  "Get the latest news delivered to your fingertips. From breaking headlines to in-depth articles, our news app keeps you informed on what matters most.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Personalized News Feed",
        description = "Tailored to your interests, our app provides a personalized news feed. Explore topics you love and discover new stories that resonate with you.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Effortless Donations Anytime, Anywhere",
        description = "With our app, making a meaningful contribution is just a tap away. Your generosity knows no bounds with our convenient in-app donation feature.",
        image = R.drawable.onboarding3
    )
)