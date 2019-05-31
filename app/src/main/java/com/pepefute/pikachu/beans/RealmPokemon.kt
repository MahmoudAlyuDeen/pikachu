package com.pepefute.pikachu.beans

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmPokemon : RealmObject {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var height: Int = 0
    var type: String? = null
    var weight: Int = 0
    var sprite: String? = null

    constructor()

    constructor(id: Int, name: String, height: Int, type: String, weight: Int, sprite: String) {
        this.id = id
        this.name = name
        this.height = height
        this.type = type
        this.weight = weight
        this.sprite = sprite
    }
}
