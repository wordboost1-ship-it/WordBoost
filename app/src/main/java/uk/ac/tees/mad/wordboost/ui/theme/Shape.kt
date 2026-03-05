package uk.ac.tees.mad.wordboost.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val WordBoostShapes = Shapes(

    // Small elements (chips, small cards)
    small = RoundedCornerShape(12.dp),

    // Medium elements (main cards, dialogs)
    medium = RoundedCornerShape(20.dp),

    // Large elements (bottom sheets, big containers)
    large = RoundedCornerShape(28.dp)
)
