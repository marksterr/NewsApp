package com.markdawes.newsapp.ui.newsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.markdawes.newsapp.R
import com.markdawes.newsapp.ui.NewsViewModel
import com.markdawes.newsapp.util.loadPicture
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val newsList by viewModel.articles.observeAsState(initial = emptyList())
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "NEWS APP",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.DarkGray
                        )

                        Button(onClick = {
                            viewModel.refreshArticles()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_refresh),
                                contentDescription = "Refresh"
                            )
                        }
                    }

                    Divider(color = Color.Gray, thickness = 2.dp)

                    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                        items(newsList) { article ->
                            Card(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .clickable {
                                        val args: Bundle = bundleOf(
                                            "title" to article.title,
                                            "image" to article.image,
                                            "content" to article.content,
                                            "url" to article.url
                                        )
                                        findNavController().navigate(
                                            R.id.action_newsFragment_to_articleFragment,
                                            args
                                        )
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    val bitmap = loadPicture(article.image, "Article Image")
                                    bitmap.value?.let { bmp ->
                                        Image(
                                            bitmap = bmp.asImageBitmap() ?: ImageBitmap(0, 0),
                                            contentDescription = "Article Image",
                                            modifier = Modifier
                                                .size(95.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                                            contentScale = ContentScale.FillBounds // Fill the bounds of the Image composable
                                        )
                                    }
                                    Spacer(Modifier.width(16.dp))
                                    Text(
                                        article.title,
                                        style = MaterialTheme.typography.bodyLarge,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 3
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
