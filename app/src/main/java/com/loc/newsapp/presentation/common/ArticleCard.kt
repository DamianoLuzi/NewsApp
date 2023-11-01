package com.loc.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.onboarding.Dimensions.ArticleCardSize
import com.loc.newsapp.presentation.onboarding.Dimensions.ExtraSmallPadding
import com.loc.newsapp.presentation.onboarding.Dimensions.ExtraSmallPadding2
import com.loc.newsapp.presentation.onboarding.Dimensions.SmallIconSize
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier : Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
){
    val context = LocalContext.current
    Row(modifier = modifier.clickable {onClick()}) {
        AsyncImage(
            modifier = Modifier.size(ArticleCardSize).clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
            .padding(horizontal = ExtraSmallPadding )
            .height(ArticleCardSize)) {

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true,uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "London has topped the charts in worldsbestcities.com's annual rankings, beating Paris, New York, Tokyo, Dubai, Barcelona, Roma, Madrid, Singapore and Amsterdam to the top spot",
                content = "",
                description = "London has topped the charts in worldsbestcities.com's annual rankings, beating Paris, New York, Tokyo, Dubai, Barcelona, Roma, Madrid, Singapore and Amsterdam to the top spot",
                publishedAt = "2 hours",
                source = Source(id = "",name = "BBC"),
                title = "London has been named the world's best city for 2023 beating out Paris and New York",
                url = "https://www.mirror.co.uk/travel/uk-ireland/london-been-named-worlds-best-28870550",
                urlToImage = "https://i2-prod.mirror.co.uk/incoming/article28871026.ece/ALTERNATES/s1200d/0_London-at-sunset.jpg"
            )
        ) {

        }
    }
}