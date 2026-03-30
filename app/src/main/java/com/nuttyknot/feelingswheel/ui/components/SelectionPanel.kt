package com.nuttyknot.feelingswheel.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nuttyknot.feelingswheel.R
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.ui.util.darken

@Composable
fun SelectionPanel(
    selectedEmotion: SelectedEmotion?,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean = false,
) {
    AnimatedVisibility(
        visible = selectedEmotion != null,
        enter =
            if (isLandscape) {
                slideInHorizontally(initialOffsetX = { it }) + fadeIn()
            } else {
                slideInVertically(initialOffsetY = { it }) + fadeIn()
            },
        exit =
            if (isLandscape) {
                slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
            } else {
                slideOutVertically(targetOffsetY = { it }) + fadeOut()
            },
        modifier = modifier,
    ) {
        selectedEmotion?.let { selected ->
            val context = LocalContext.current

            Surface(
                shape =
                    if (isLandscape) {
                        RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                    } else {
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    },
                tonalElevation = 4.dp,
                modifier =
                    if (isLandscape) {
                        Modifier.fillMaxHeight().fillMaxWidth(0.4f)
                    } else {
                        Modifier.fillMaxWidth()
                    },
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier)
                            .padding(16.dp)
                            .semantics { liveRegion = LiveRegionMode.Polite },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.Top,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.im_feeling),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.clear_selection),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Breadcrumb
                    val breadcrumb =
                        buildList {
                            add(selected.coreName)
                            selected.middleName?.let { add(it) }
                            selected.outerName?.let { add(it) }
                        }

                    Text(
                        text = breadcrumb.joinToString(" > "),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Show the selected emotion prominently
                    Text(
                        text = selected.segment.label,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = selected.segment.color.darken(0.55f),
                    )

                    // Description
                    if (selected.segment.description.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = selected.segment.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Copy & Share buttons
                    val breadcrumbText = breadcrumb.joinToString(" > ")
                    val copyLabel = stringResource(R.string.copy_emotion)
                    val shareLabel = stringResource(R.string.share_emotion)
                    val copiedMessage = stringResource(R.string.emotion_copied)

                    Row(
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = {
                                val clipboard =
                                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("emotion", selected.segment.label)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, copiedMessage, Toast.LENGTH_SHORT).show()
                            },
                        ) {
                            Text(copyLabel)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        TextButton(
                            onClick = {
                                val shareText = "${selected.segment.label}\n$breadcrumbText"
                                val intent =
                                    Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, shareText)
                                    }
                                context.startActivity(
                                    Intent.createChooser(intent, null),
                                )
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(shareLabel)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
