package com.yeputra.footballclub.repository.database

import android.content.Context
import com.yeputra.footballclub.model.Event
import com.yeputra.footballclub.model.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoriteRepository(private val context: Context) {

    fun add(event: Event) {
        context.database.use {
            insert(Favorite.TABLE_FAVORITE,
                Favorite.EVENT_ID to event.idEvent,
                Favorite.EVENT_DATE to event.dateEvent,
                Favorite.HOME_NAME to event.homeTeam,
                Favorite.HOME_SCORE to event.homeScore,
                Favorite.AWAY_NAME to event.awayTeam,
                Favorite.AWAY_SCORE to event.awayScore)
        }
    }

    fun delete(id: String) {
        context.database.use {
            delete(Favorite.TABLE_FAVORITE,
                Favorite.EVENT_ID + "={id}",
                "id" to id)
        }
    }

    fun findAll(): MutableList<Favorite> {
        val data = mutableListOf<Favorite>()
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            data.addAll(result.parseList(classParser()))
        }
        return data
    }
}