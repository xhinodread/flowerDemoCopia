package com.example.flowerappcopy.data

import com.example.flowerappcopy.R

object FlowersData{
    val descr = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
    val list = listOf(
        Flowers(
            id = 0,
            name = "Angle",
            price = "15700",
            image = R.drawable.ic_red_rose_bouquet,
            descr = descr
        ),
        Flowers(
            id = 1,
            name = "JannienX",
            price = "16500",
            image = R.drawable.ic_pink_rose_bouquet,
            sub_name = "Flores Lyly y Jannien y Lily ",
            descr = descr
        ),
        Flowers(
            id = 2,
            name = "AngleX",
            price = "15700",
            image = R.drawable.ic_red_rose_bouquet ,
            sub_name = "Flores Jannien y Lily ",
            descr = descr
        ),
        Flowers(
            id = 3,
            name = "Jannien",
            price = "16500",
            image = R.drawable.ic_pink_rose_bouquet,
            descr = descr
        ),
        Flowers(
            id = 4,
            name = "Margarita",
            price = "19800",
            image = R.drawable.ic_pink_rose_bouquet,
            descr = descr
        ),
        Flowers(
            id = 5,
            name = "Jacintos",
            price = "13400",
            image = R.drawable.ic_pink_rose_bouquet,
            descr = descr
        ),
        Flowers(
            id = 6,
            name = "Rosas",
            price = "19800",
            image = R.drawable.ic_pink_rose_bouquet,
            descr = descr
        )
    )
}