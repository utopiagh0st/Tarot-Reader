package com.example.tarot_reader.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tarot_reader.model.TarotCard
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

private val defaultDeck = listOf(
    TarotCard("El Loco", "ar00", "0", 0, null, "major", "Nuevos comienzos, inocencia, fe ciega.", "Imprudencia, falta de dirección.", "El inicio del viaje del héroe.", "🃏"),
    TarotCard("El Mago", "ar01", "I", 1, null, "major", "Manifestación, habilidad, recursos.", "Manipulación, talento oculto.", "Posees todas las herramientas necesarias.", "🪄"),
    TarotCard("La Sacerdotisa", "ar02", "II", 2, null, "major", "Intuición, misterio, voz interior.", "Secretos guardados, desconexión.", "Escucha tu sabiduría interior.", "🌙"),
    TarotCard("La Emperatriz", "ar03", "III", 3, null, "major", "Abundancia, creatividad, fertilidad.", "Dependencia, bloqueo creativo.", "Florecimiento y crecimiento constante.", "👑"),
    TarotCard("El Emperador", "ar04", "IV", 4, null, "major", "Estructura, autoridad, liderazgo.", "Rigidez, exceso de control.", "Construye bases sólidas.", "🏛️"),
    TarotCard("El Sumo Sacerdote", "ar05", "V", 5, null, "major", "Tradición, guía espiritual, aprendizaje.", "Rebelión, dogma rígido.", "Busca el conocimiento verdadero.", "📜"),
    TarotCard("Los Enamorados", "ar06", "VI", 6, null, "major", "Amor, armonía, elecciones importantes.", "Conflicto de valores, desalineación.", "Decisiones del corazón.", "💖"),
    TarotCard("El Carro", "ar07", "VII", 7, null, "major", "Determinación, victoria, enfoque.", "Falta de control, agresión.", "Avanza con disciplina.", "⚔️"),
    TarotCard("La Fuerza", "ar08", "VIII", 8, null, "major", "Coraje, paciencia, compasión.", "Duda interior, debilidad.", "La verdadera fuerza viene de la calma.", "🦁"),
    TarotCard("El Ermitaño", "ar09", "IX", 9, null, "major", "Introspección, soledad sanadora, guía.", "Aislamiento, soledad extrema.", "Busca la luz en tu interior.", "🕯️"),
    TarotCard("La Rueda de la Fortuna", "ar10", "X", 10, null, "major", "Cambios, ciclos, buena suerte.", "Resistencia al cambio, mala racha.", "Todo fluye en ciclos.", "🎡"),
    TarotCard("La Justicia", "ar11", "XI", 11, null, "major", "Equidad, verdad, causa y efecto.", "Injusticia, falta de honestidad.", "La verdad siempre sale a la luz.", "⚖️"),
    TarotCard("El Colgado", "ar12", "XII", 12, null, "major", "Pausa, nueva perspectiva, rendición.", "Apatía, postergación.", "A veces hay que soltar para avanzar.", "🙃"),
    TarotCard("La Muerte", "ar13", "XIII", 13, null, "major", "Transformación, finales, nuevo ciclo.", "Miedo al cambio, aferramiento.", "Cierra puertas para abrir ventanas.", "🦋"),
    TarotCard("La Templanza", "ar14", "XIV", 14, null, "major", "Equilibrio, moderación, paciencia.", "Desequilibrio, exceso.", "Encuentra el punto medio.", "🧪"),
    TarotCard("El Diablo", "ar15", "XV", 15, null, "major", "Apegos, sombra, ilusión material.", "Liberación, romper cadenas.", "Reconoce lo que te ata.", "🔥"),
    TarotCard("La Torre", "ar16", "XVI", 16, null, "major", "Despertar, revelación, cambio drástico.", "Miedo a la verdad, desastre evitable.", "Derriba las falsas estructuras.", "⚡"),
    TarotCard("La Estrella", "ar17", "XVII", 17, null, "major", "Esperanza, fe, renovación, paz.", "Desesperanza, falta de fe.", "Sigue tu estrella guía.", "⭐"),
    TarotCard("La Luna", "ar18", "XVIII", 18, null, "major", "Intuición, sueños, lo inconsciente.", "Confusión, engaño.", "Confía en tu sexto sentido.", "🌕"),
    TarotCard("El Sol", "ar19", "XIX", 19, null, "major", "Éxito, vitalidad, alegría, claridad.", "Tristeza temporal, exceso de orgullo.", "Brilla con luz propia.", "☀️"),
    TarotCard("El Juicio", "ar20", "XX", 20, null, "major", "Renacimiento, llamado, perdón.", "Duda, juicio destructivo.", "Escucha tu llamado interior.", "🎺"),
    TarotCard("El Mundo", "ar21", "XXI", 21, null, "major", "Realización, plenitud, viaje completo.", "Incompletitud, retraso.", "El ciclo se completa con éxito.", "🌍")
)

private val spreadPositions = listOf(
    "1. Pasado" to "Eventos e influencias pasadas que te han traído hasta aquí.",
    "2. Presente" to "Energía actual, oportunidades y retos del momento.",
    "3. Futuro" to "Tendencia que se proyecta en tu camino próximo.",
    "4. Consejo" to "La orientación o enfoque recomendado por el oráculo.",
    "5. Resultado" to "El desenlace probable si sigues la dirección actual."
)

@Composable
fun TarotHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TarotViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedIndex by remember { mutableIntStateOf(0) }

    val cards = when (val state = uiState) {
        is TarotUiState.Exito -> state.cartas
        else -> remember { defaultDeck.shuffled().take(5) }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MidnightBackground,
                        MysticSurface,
                        MidnightBackground
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "TIRADA DE 5 CARTAS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CelestialGold,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (uiState is TarotUiState.Cargando) {
                CircularProgressIndicator(
                    color = CelestialGold,
                    modifier = Modifier.padding(24.dp)
                )
            } else {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(cards) { index, card ->
                        val posLabel = spreadPositions.getOrNull(index)?.first ?: "Carta ${index + 1}"
                        TarotCardItem(
                            card = card,
                            positionLabel = posLabel,
                            isSelected = index == selectedIndex,
                            onClick = { selectedIndex = index }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            val currentCard = cards.getOrNull(selectedIndex)
            val currentPosition = spreadPositions.getOrNull(selectedIndex)

            if ((currentCard != null) && (currentPosition != null)) {
                CardDetailSection(
                    card = currentCard,
                    positionTitle = currentPosition.first,
                    positionDesc = currentPosition.second
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    selectedIndex = 0
                    viewModel.cargarCincoCartasAleatorias()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = CelestialGold,
                    contentColor = MidnightBackground
                ),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(52.dp)
            ) {
                Text(
                    text = "🔄  Obtener 5 Cartas Aleatorias",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "✦", fontSize = 16.sp, color = CelestialGold)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "ORÁCULO MÍSTICO",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CelestialGold,
                letterSpacing = 3.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "✦", fontSize = 16.sp, color = CelestialGold)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Lectura de Tarot",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MysticTextPrimary,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Selecciona cada carta para conocer su interpretación",
            fontSize = 13.sp,
            color = MysticTextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TarotCardItem(
    card: TarotCard,
    positionLabel: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) CelestialGold else MysticCardBorder,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MysticCardSurface else MysticSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) CelestialGold else MysticCardSurface)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = positionLabel.split(". ").lastOrNull() ?: positionLabel,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) MidnightBackground else MysticTextSecondary
                )
            }

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MysticPurple.copy(alpha = 0.3f))
                    .border(1.dp, CelestialGold.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = card.simboloIcono,
                    fontSize = 24.sp
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = card.valor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CelestialGold
                )
                Text(
                    text = card.nombre,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MysticTextPrimary,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun CardDetailSection(
    card: TarotCard,
    positionTitle: String,
    positionDesc: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .border(1.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = MysticSurface),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = CelestialGold.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = positionTitle.uppercase(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = card.tipo.uppercase(),
                    fontSize = 11.sp,
                    color = MysticRoseGold,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${card.valor} - ${card.nombre}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = CelestialGoldGlow,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = positionDesc,
                fontSize = 12.sp,
                color = MysticTextSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MysticCardSurface)
                    .border(1.dp, MysticCardBorder, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        text = "✨  Significado al Derecho",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = card.significadoDerecho,
                        fontSize = 14.sp,
                        color = MysticTextPrimary,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "🌙  Significado Invertido",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MysticRoseGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = card.significadoInvertido,
                        fontSize = 13.sp,
                        color = MysticTextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
