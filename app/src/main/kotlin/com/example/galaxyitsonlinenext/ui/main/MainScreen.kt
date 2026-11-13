package com.example.galaxyitsonlinenext.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.galaxyitsonlinenext.R
import com.example.galaxyitsonlinenext.ui.theme.GalaxyItSOnlineNextTheme
import com.example.galaxyitsonlinenext.ui.theme.GlassAccent
import com.example.galaxyitsonlinenext.ui.theme.GlassCardBackground
import com.example.galaxyitsonlinenext.ui.theme.GlassCardBorder
import com.example.galaxyitsonlinenext.ui.theme.GlassInputBackground
import com.example.galaxyitsonlinenext.ui.theme.GlassInputBorder
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
  onItemClick: (NavKey) -> Unit = {},
  modifier: Modifier = Modifier,
) {
  GlassHomeScreen(
    modifier = modifier,
    onCheckLink = { /* Nav or link inspect */ }
  )
}

@Composable
fun GlassHomeScreen(
  modifier: Modifier = Modifier,
  onCheckLink: (String) -> Unit = {}
) {
  var urlInput by rememberSaveable { mutableStateOf("") }
  var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
  val clipboard = LocalClipboard.current
  val coroutineScope = rememberCoroutineScope()
  val invalidUrlText = stringResource(R.string.home_status_invalid_url)

  val darkGradient = Brush.verticalGradient(
    colors = listOf(
      Color(0xFF0D0B18),
      Color(0xFF16122C),
      Color(0xFF090712)
    )
  )

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(darkGradient)
      .statusBarsPadding()
      .padding(horizontal = 20.dp, vertical = 24.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(26.dp))
        .background(GlassCardBackground)
        .border(BorderStroke(1.dp, GlassCardBorder), RoundedCornerShape(26.dp))
        .padding(horizontal = 24.dp, vertical = 28.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.Medium,
          letterSpacing = 1.sp,
          color = Color.White
        )
      )

      Spacer(modifier = Modifier.height(28.dp))

      OutlinedTextField(
        value = urlInput,
        onValueChange = {
          urlInput = it
          if (errorMessage != null) {
            errorMessage = null
          }
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
          Text(
            text = stringResource(R.string.home_input_placeholder),
            color = Color.White.copy(alpha = 0.50f)
          )
        },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Uri,
          imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
          onDone = {
            val trimmed = urlInput.trim()
            if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
              onCheckLink(trimmed)
            } else {
              errorMessage = invalidUrlText
            }
          }
        ),
        colors = OutlinedTextFieldDefaults.colors(
          focusedContainerColor = GlassInputBackground,
          unfocusedContainerColor = GlassInputBackground,
          focusedTextColor = Color.White,
          unfocusedTextColor = Color.White,
          focusedBorderColor = GlassAccent,
          unfocusedBorderColor = GlassInputBorder,
          cursorColor = GlassAccent
        )
      )

      if (errorMessage != null) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = errorMessage.orEmpty(),
          color = Color(0xFFFFB4AB),
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier.align(Alignment.Start)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        OutlinedButton(
          onClick = {
            coroutineScope.launch {
              val clipEntry = clipboard.getClipEntry()
              val clipData = clipEntry?.clipData
              if (clipData != null && clipData.itemCount > 0) {
                val pasted = clipData.getItemAt(0).text?.toString()
                if (!pasted.isNullOrBlank()) {
                  urlInput = pasted.trim()
                  errorMessage = null
                }
              }
            }
          },
          modifier = Modifier
            .weight(1f)
            .height(48.dp),
          shape = RoundedCornerShape(50),
          border = BorderStroke(1.dp, GlassCardBorder),
          colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White.copy(alpha = 0.08f),
            contentColor = Color.White
          )
        ) {
          Text(
            text = stringResource(R.string.home_btn_paste),
            fontWeight = FontWeight.Medium
          )
        }

        Button(
          onClick = {
            val trimmed = urlInput.trim()
            if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
              onCheckLink(trimmed)
            } else {
              errorMessage = invalidUrlText
            }
          },
          modifier = Modifier
            .weight(1f)
            .height(48.dp),
          shape = RoundedCornerShape(50),
          colors = ButtonDefaults.buttonColors(
            containerColor = GlassAccent,
            contentColor = Color.White
          )
        ) {
          Text(
            text = stringResource(R.string.home_btn_check_link),
            fontWeight = FontWeight.Medium
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
  GalaxyItSOnlineNextTheme {
    GlassHomeScreen()
  }
}
