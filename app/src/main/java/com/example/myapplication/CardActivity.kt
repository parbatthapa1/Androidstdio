package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainCardApp()
            }
        }
    }
}

val AppGreen = Color(0xFF00C896)

@Composable
fun MainCardApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = AppGreen,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = Color.White
                    )
                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Category View", color = Color.White, fontWeight = FontWeight.Bold) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Cards List View", color = Color.White, fontWeight = FontWeight.Bold) }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (selectedTab == 0) {
                CategoryGridView()
            } else {
                CardsListView()
            }
        }
    }
}


@Composable
fun CategoryGridView() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGreen)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Card",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Simple and easy to use app",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            Image(
                painter = painterResource(R.drawable.images),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        val categories = listOf(
            CategoryItemData("📖", "Text", "11 items content"),
            CategoryItemData("⛪", "Address", "8 items content"),
            CategoryItemData("👤", "Character", "15 items content"),
            CategoryItemData("💳", "Bank card", "5 items content"),
            CategoryItemData("🔑", "Password", "21 items content"),
            CategoryItemData("📦", "Logistics", "13 items content")
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (i in categories.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CategoryCard(
                        modifier = Modifier.weight(1f),
                        item = categories[i]
                    )
                    if (i + 1 < categories.size) {
                        CategoryCard(
                            modifier = Modifier.weight(1f),
                            item = categories[i + 1]
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "⚙️", fontSize = 28.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Fingerprint code and so on",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

data class CategoryItemData(
    val icon: String,
    val title: String,
    val subtitle: String
)

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    item: CategoryItemData
) {
    Card(
        modifier = modifier.height(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = item.icon, fontSize = 36.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.subtitle,
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}


@Composable
fun CardsListView() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Top Green Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppGreen)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Card",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Image(
                        painter = painterResource(R.drawable.images),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                GradientCard(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFF9F43), Color(0xFFFECA57))
                    ),
                    title = "Dribbble",
                    subtitle1 = "Paidax",
                    subtitle2 = "********",
                    trailingIcon = "🔒"
                )


                GradientCard(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF0097E6), Color(0xFF00A8FF))
                    ),
                    title = "HJM",
                    subtitle1 = "173****8838",
                    trailingIcon = "📄"
                )


                GradientCard(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1DD1A1), Color(0xFF10AC84))
                    ),
                    title = "Tom",
                    subtitle1 = "Room 601, Building 8, Zhongnan Century City, No.8, Taoyuan Road...",
                    subtitle2 = "130****3920"
                )


                GradientCard(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF5F27CD), Color(0xFF341F97))
                    ),
                    title = "1882 **** **** 8695",
                    subtitle1 = "ICBC",
                    bottomLeft = "Debit Card",
                    bottomRight = "12/19"
                )


                GradientCard(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFEE5253), Color(0xFFB33939))
                    ),
                    title = "Young",
                    subtitle1 = "This is the story of me and them, very..."
                )


                GradientCard(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF3867D6), Color(0xFF4B6584))
                    ),
                    title = "Jinjun street, golden chrysanthemum",
                    subtitle1 = "Road, Haizhuqu District..."
                )

                Spacer(modifier = Modifier.height(60.dp))
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = AppGreen,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Text(text = "+", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun GradientCard(
    brush: Brush,
    title: String,
    subtitle1: String? = null,
    subtitle2: String? = null,
    bottomLeft: String? = null,
    bottomRight: String? = null,
    trailingIcon: String? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(brush)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    if (subtitle1 != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = subtitle1,
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.85f),
                            maxLines = 2
                        )
                    }
                    if (subtitle2 != null) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = subtitle2,
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }
                }
                if (trailingIcon != null) {
                    Text(text = trailingIcon, fontSize = 24.sp)
                }
            }

            if (bottomLeft != null || bottomRight != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = bottomLeft ?: "",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = bottomRight ?: "",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardAppPreview() {
    MyApplicationTheme {
        MainCardApp()
    }
}