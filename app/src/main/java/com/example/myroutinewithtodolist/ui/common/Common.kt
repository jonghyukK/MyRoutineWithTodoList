package com.example.myroutinewithtodolist.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myroutinewithtodolist.R

/**
 *
 * @author jonghyuk.
 * @since 4/26/24.
 */


@Composable
fun CommonAppBar(
	showBackButton: Boolean = false,
	title: String = "",
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(56.dp)
			.background(color = MaterialTheme.colorScheme.primary),
		verticalAlignment = Alignment.CenterVertically
	) {
		val paddingValue = if (showBackButton) 46.dp else 0.dp

		if (showBackButton) {
			IconButton(
				modifier = Modifier.size(46.dp),
				onClick = { }
			) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					tint = colorResource(id = R.color.white),
					contentDescription = "ArrowBack"
				)
			}
		}

		if (title.isNotEmpty()) {
			Text(
				modifier = Modifier
					.weight(1f)
					.padding(end = paddingValue),
				textAlign = TextAlign.Center,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				text = title,
				color = colorResource(id = R.color.white),
				fontSize = 20.sp,
				fontWeight = FontWeight.Bold
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun CommonAppBarPreview() {
	CommonAppBar(true, "Hello")
}