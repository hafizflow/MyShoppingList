package com.example.myshoppinglist.ui.theme

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: String,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingListApp(modifier: Modifier) {
    var shoppingList by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(top = 50.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            items(shoppingList) {
                ShoppingListItem(it, {}, {})
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        ExtendedFloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.End)
                .padding(40.dp),
            text = { Text(text = "Add Item") },
            icon = { Icon(Icons.Default.Add, "") },
        )

    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, confirmButton = {
                Row {
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ShoppingItem(
                                id = shoppingList.size + 1, name = itemName,
                                quantity = itemQuantity,
                            )
                            shoppingList = shoppingList + newItem
                            showDialog = false
                            itemName = ""
                            itemQuantity = ""
                        }
                    }) {
                        Text(text = "Add")
                    }
                }
            },
            title = { Text(text = "Add Shopping Item") },
            text = {
                Column {
                    OutlinedTextField(
                        onValueChange = { itemName = it },
                        value = itemName,
                        label = { Text(text = "Item Name") },
                        singleLine = true,
                    )
                    OutlinedTextField(
                        onValueChange = { itemQuantity = it },
                        value = itemQuantity,
                        label = { Text(text = "Item Quantity") },
                        singleLine = true,
                    )
                }
            }
        )
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    editItem: () -> Unit,
    deleteItem: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(2.dp, color = Color(0xFF018786)),
                shape = RoundedCornerShape(20)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.name)
        Text(text = item.quantity)
        IconButton(onClick = {}, ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = {}, ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewFunction() {
    MyShoppingListTheme {
//        CFloatingActionButton()
    }
}