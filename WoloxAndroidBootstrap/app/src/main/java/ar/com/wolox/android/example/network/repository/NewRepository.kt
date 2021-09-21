package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.model.New
import org.joda.time.DateTime

class NewRepository {

    fun getNews(): List<New> {
        return listOf<New>(
                New(1,
                        "Rodrigo Lesch Brown",
                        "I Hope to see Ruby help",
                        "2019-07-01",
                        "https://api.adorable.io/avatar/250/Rodrigo Lesch Brown.png",
                        DateTime("2020-10-01T16:22:10.490Z")
                )
        )
    }
}