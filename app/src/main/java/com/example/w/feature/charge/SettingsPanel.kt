package com.example.w.feature.charge

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background

private enum class SettingsSection {
    Language, Brightness, Currency, History
}

@Composable
fun SettingsPanel(
    language: String,
    onLanguageSelect: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSection by remember { mutableStateOf<SettingsSection?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        // Верхняя панель с динамическим заголовком
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "←",
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .clickable {
                        if (selectedSection == null) onClose() else selectedSection = null
                    }
            )

            // Меняем заголовок при выборе раздела
            val headerTitle = selectedSection?.let {
                when (it) {
                    SettingsSection.Language -> "Language"
                    SettingsSection.Brightness -> "Brightness"
                    SettingsSection.Currency -> "Currency"
                    SettingsSection.History -> "History"
                }
            } ?: "Settings"

            Text(
                text = headerTitle,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (selectedSection == null) {
            // Область сетки: центральная линия и горизонтальные разделители строк
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .drawBehind {
                        val x = size.width / 2f
                        val stroke = 1.dp.toPx()
                        drawLine(
                            color = Color.White,
                            start = Offset(x, 0f),
                            end = Offset(x, size.height),
                            strokeWidth = stroke
                        )
                    }
            ) {
                BoxWithConstraints(Modifier.fillMaxSize()) {
                    val cellW = maxWidth / 2
                    val cellH = maxHeight / 5
                    val rowDivider: Modifier.() -> Modifier = {
                        this.drawBehind {
                            val stroke = 1.dp.toPx()
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = stroke
                            )
                        }
                    }

                    Column(Modifier.fillMaxSize()) {
                        // 1-я строка — с горизонтальной линией снизу
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(cellH)
                                .let { it.rowDivider() }
                        ) {
                            GridCell("History", Modifier.width(cellW).height(cellH)) {
                                selectedSection = SettingsSection.History
                            }
                            GridCell("Language", Modifier.width(cellW).height(cellH)) {
                                selectedSection = SettingsSection.Language
                            }
                        }
                        // 2-я строка — с горизонтальной линией снизу
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(cellH)
                                .let { it.rowDivider() }
                        ) {
                            GridCell("Brightness", Modifier.width(cellW).height(cellH)) {
                                selectedSection = SettingsSection.Brightness
                            }
                            GridCell("Currency", Modifier.width(cellW).height(cellH)) {
                                selectedSection = SettingsSection.Currency
                            }
                        }
                        // 3-я строка — с горизонтальной линией снизу
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(cellH)
                                .let { it.rowDivider() }
                        ) {
                            GridCell("", Modifier.width(cellW).height(cellH))
                            GridCell("", Modifier.width(cellW).height(cellH))
                        }
                        // 4-я строка — с горизонтальной линией снизу
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(cellH)
                                .let { it.rowDivider() }
                        ) {
                            GridCell("", Modifier.width(cellW).height(cellH))
                            GridCell("", Modifier.width(cellW).height(cellH))
                        }
                        // 5-я строка — БЕЗ линии снизу (чтобы не было нижней рамки)
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(cellH)
                        ) {
                            GridCell("", Modifier.width(cellW).height(cellH))
                            GridCell(
                                text = "Log out",
                                modifier = Modifier.width(cellW).height(cellH),
                                backgroundColor = Color.Red
                            ) {
                                onClose()
                            }
                        }
                    }
                }
            }
        } else {
            // Экран выбранного раздела
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                selectedSection?.let { section ->
                    val title = when (section) {
                        SettingsSection.Language -> "Language"
                        SettingsSection.Brightness -> "Brightness"
                        SettingsSection.Currency -> "Currency"
                        SettingsSection.History -> "History"
                    }
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun GridCell(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            // Без рамок слева/справа: убрал border, использую фон клетки
            .background(backgroundColor)
            .let { base ->
                if (onClick != null) base.clickable { onClick() } else base
            },
        contentAlignment = Alignment.Center
    ) {
        if (text.isNotEmpty()) {
            val textColor = if (backgroundColor == Color.Red) Color.White else Color.White
            Text(text = text, color = textColor, fontSize = 18.sp)
        }
    }
}