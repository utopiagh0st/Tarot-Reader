package com.example.tarot_reader.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarot_reader.data.model.TarotCard
import com.example.tarot_reader.ui.state.TarotUiState
import com.example.tarot_reader.ui.theme.CelestialGold
import com.example.tarot_reader.ui.theme.CelestialGoldGlow
import com.example.tarot_reader.ui.theme.MidnightBackground
import com.example.tarot_reader.ui.theme.MysticCardBorder
import com.example.tarot_reader.ui.theme.MysticCardSurface
import com.example.tarot_reader.ui.theme.MysticPurple
import com.example.tarot_reader.ui.theme.MysticRoseGold
import com.example.tarot_reader.ui.theme.MysticSurface
import com.example.tarot_reader.ui.theme.MysticTextPrimary
import com.example.tarot_reader.ui.theme.MysticTextSecondary
import com.example.tarot_reader.ui.viewmodel.TarotViewModel

@Composable
fun TarotHomeScreen(
    viewModel: TarotViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.observeAsState()

    val options = remember {
        listOf("Carta Aleatoria", "Todas las Cartas", "Cartas de Corte", "Carta por ID")
    }

    var selectedOptionIndex by remember { mutableIntStateOf(0) }
    var selectedCardIndex by remember { mutableIntStateOf(0) }

    val mockFallbackCards = remember {
        listOf(
            TarotCard(
                name = "The Fool",
                name_short = "ar00",
                value = "0",
                value_int = 0,
                suit = null,
                type = "major",
                meaning_up = "Inocencia, nuevos comienzos, fe ciega.",
                meaning_rev = "Imprudencia, ingenuidad.",
                desc = "El Loco representa el inicio del viaje heroico."
            ),
            TarotCard(
                name = "The Magician",
                name_short = "ar01",
                value = "1",
                value_int = 1,
                suit = null,
                type = "major",
                meaning_up = "Manifestación, recursos, habilidad.",
                meaning_rev = "Manipulación, talento desaprovechado.",
                desc = "El Mago cuenta con todas las herramientas para sus metas."
            )
        )
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MidnightBackground,
                        Color(0xFF140F26),
                        MidnightBackground
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 32.dp)
        ) {
            HeaderSection()

            Text(
                text = "Endpoints de Tarot API",
                style = MaterialTheme.typography.titleMedium,
                color = CelestialGold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(options.size) { index ->
                    val option = options[index]
                    val isSelected = index == selectedOptionIndex
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                selectedOptionIndex = index
                                selectedCardIndex = 0
                            }
                            .border(1.dp, if (isSelected) CelestialGold else MysticCardBorder, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MysticPurple.copy(alpha = 0.4f) else MysticSurface
                        )
                    ) {
                        Text(
                            text = option,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            color = if (isSelected) CelestialGoldGlow else MysticTextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            when (val state = uiState) {
                is TarotUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = CelestialGold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Consultando Tarot API...",
                                color = CelestialGoldGlow,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                is TarotUiState.Success -> {
                    SpreadResultView(
                        cards = state.cards,
                        selectedCardIndex = selectedCardIndex,
                        onCardSelected = { index -> selectedCardIndex = index },
                        onNewReading = {
                            triggerEndpoint(selectedOptionIndex, viewModel)
                        }
                    )
                }
                is TarotUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Aviso: ${state.message}",
                            color = MysticRoseGold,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        SpreadResultView(
                            cards = mockFallbackCards,
                            selectedCardIndex = selectedCardIndex,
                            onCardSelected = { index -> selectedCardIndex = index },
                            onNewReading = {
                                triggerEndpoint(selectedOptionIndex, viewModel)
                            }
                        )
                    }
                }
                null -> {
                    DeckSelectionView(
                        endpointName = options[selectedOptionIndex],
                        onExecute = {
                            triggerEndpoint(selectedOptionIndex, viewModel)
                        }
                    )
                }
            }
        }
    }
}

fun triggerEndpoint(index: Int, viewModel: TarotViewModel) {
    when (index) {
        0 -> viewModel.getRandomCard()
        1 -> viewModel.getAllCards()
        2 -> viewModel.getCourtCards()
        3 -> viewModel.getCardById("ar00")
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 16.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = CelestialGold,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TAROT MÍSTICO",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                color = CelestialGold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = CelestialGold,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Diagrama de Clases Tarot API",
            fontSize = 13.sp,
            color = MysticTextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DeckSelectionView(
    endpointName: String,
    onExecute: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardDeckVisual()

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Acción: $endpointName",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = CelestialGold,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onExecute,
            colors = ButtonDefaults.buttonColors(
                containerColor = CelestialGold,
                contentColor = MidnightBackground
            ),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(52.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "EJECUTAR PETICIÓN",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun CardDeckVisual() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        val offsets = listOf(-25.dp, 0.dp, 25.dp)
        val rotations = listOf(-12f, 0f, 12f)

        for (i in 0 until 3) {
            val rotation = if (i < rotations.size) rotations[i] else 0f
            val offsetX = if (i < offsets.size) offsets[i] else 0.dp

            CardBack(
                modifier = Modifier
                    .width(110.dp)
                    .height(170.dp)
                    .graphicsLayer {
                        rotationZ = rotation
                        translationX = offsetX.toPx()
                    }
            )
        }
    }
}

@Composable
fun CardBack(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MysticCardSurface)
            .border(2.dp, CelestialGold, RoundedCornerShape(12.dp))
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MysticPurple.copy(alpha = 0.6f),
                            MysticCardSurface
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = CelestialGold,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "✦ ✦ ✦",
                    fontSize = 10.sp,
                    color = CelestialGold.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun SpreadResultView(
    cards: List<TarotCard>,
    selectedCardIndex: Int,
    onCardSelected: (Int) -> Unit,
    onNewReading: () -> Unit
) {
    val selectedCard = cards.getOrNull(selectedCardIndex) ?: cards.firstOrNull()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resultados (${cards.size} cartas)",
            color = CelestialGold,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(cards) { index, card ->
                val isSelected = index == selectedCardIndex

                TarotCardItem(
                    card = card,
                    isSelected = isSelected,
                    onClick = { onCardSelected(index) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (selectedCard != null) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(400)) + slideInVertically()
            ) {
                CardDetailSection(card = selectedCard)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onNewReading,
                border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = CelestialGold)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Consultar de Nuevo", fontSize = 13.sp)
            }

            Row {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = "Guardar",
                        tint = CelestialGold
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartir",
                        tint = CelestialGold
                    )
                }
            }
        }
    }
}

@Composable
fun TarotCardItem(
    card: TarotCard,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 0.95f,
        label = "scale"
    )

    Card(
        modifier = Modifier
            .width(130.dp)
            .height(200.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) CelestialGold else MysticCardBorder,
                shape = RoundedCornerShape(14.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MysticCardSurface else MysticSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = card.value.ifEmpty { "Val" },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CelestialGold,
                fontFamily = FontFamily.Serif
            )

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MysticPurple.copy(alpha = 0.3f))
                    .border(1.dp, CelestialGold.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (card.type == "major") Icons.Default.AutoAwesome else Icons.Default.Star,
                    contentDescription = card.name,
                    tint = CelestialGold,
                    modifier = Modifier.size(28.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = card.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MysticTextPrimary,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    maxLines = 2
                )
                Text(
                    text = card.type.uppercase(),
                    fontSize = 9.sp,
                    color = MysticRoseGold
                )
            }
        }
    }
}

@Composable
fun CardDetailSection(card: TarotCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .border(1.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = MysticSurface),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = card.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold,
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = "Valor: ${card.value} | Tipo: ${card.type}",
                        fontSize = 12.sp,
                        color = MysticRoseGold
                    )
                }

                if (card.suit != null) {
                    Box(
                        modifier = Modifier
                            .background(MysticPurple.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = card.suit.uppercase(),
                            fontSize = 11.sp,
                            color = CelestialGoldGlow,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                SuggestionChip(
                    onClick = { },
                    label = { Text("Valor Int: ${card.value_int}", fontSize = 11.sp, color = MysticTextPrimary) },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = MysticCardSurface),
                    border = SuggestionChipDefaults.suggestionChipBorder(enabled = true, borderColor = MysticCardBorder)
                )
                SuggestionChip(
                    onClick = { },
                    label = { Text(card.name_short, fontSize = 11.sp, color = MysticTextPrimary) },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = MysticCardSurface),
                    border = SuggestionChipDefaults.suggestionChipBorder(enabled = true, borderColor = MysticCardBorder)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Significado (Meaning Up)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CelestialGold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.meaning_up,
                fontSize = 13.sp,
                color = MysticTextPrimary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Significado (Meaning Rev)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CelestialGold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.meaning_rev,
                fontSize = 13.sp,
                color = MysticTextSecondary,
                lineHeight = 18.sp
            )

            if (card.desc.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Descripción",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CelestialGold,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = card.desc,
                    fontSize = 13.sp,
                    color = MysticTextSecondary,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
