package com.nuttyknot.feelingswheel.ui.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.nuttyknot.feelingswheel.R

@Composable
fun AppFooter(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.footer_made_by),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        )
        Text(
            text = stringResource(R.string.footer_report_bug),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier.clickable(role = Role.Button) {
                    val intent =
                        Intent(Intent.ACTION_VIEW).apply {
                            data =
                                android.net.Uri.parse(
                                    "https://github.com/tiratatp/feelings_wheel/issues",
                                )
                        }
                    context.startActivity(intent)
                },
        )
        Text(
            text = stringResource(R.string.footer_share_app),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier.clickable(role = Role.Button) {
                    val intent =
                        Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                context.getString(R.string.footer_share_message),
                            )
                        }
                    context.startActivity(
                        Intent.createChooser(intent, null),
                    )
                },
        )
    }
}
