package com.example.flowerappcopy.data

import com.example.flowerappcopy.R

object FlowersData{
    val descr = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
    val list = listOf(
        Flowers(
            id = 0,
            name = "Angle",
            price = "$570.00",
            image = R.drawable.ic_red_rose_bouquet,
            descr = descr
        ),
        Flowers(
            id = 1,
            name = "JannienX",
            price = "$650.00",
            image = R.drawable.ic_pink_rose_bouquet,
            sub_name = "Flores Lyly y Jannien y Lily ",
            descr = descr
        ),
        Flowers(
            id = 2,
            name = "AngleX",
            price = "$570.00",
            image = R.drawable.ic_red_rose_bouquet ,
            sub_name = "Flores Jannien y Lily ",
            descr = descr
        ),
        Flowers(
            id = 3,
            name = "Jannien",
            price = "$650.00",
            image = R.drawable.ic_pink_rose_bouquet,
            descr = descr
        )
    )
}