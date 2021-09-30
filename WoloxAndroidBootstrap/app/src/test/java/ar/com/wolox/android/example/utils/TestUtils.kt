package ar.com.wolox.android.example.utils

import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.model.dtos.NewDetailsResponse

val user = User(3, "test@example.com", "uid", "T'Soni", "Liara", null)
val new = New(1, "Mordin", "seashells", "2150-02-10", "", "2150-02-10", "2150-02-10", mutableListOf(1, 3, 99))
val newDetailsResponse = NewDetailsResponse("Garrus", "I'm in the middle of some calibrations", "2020-12-02", "", listOf())