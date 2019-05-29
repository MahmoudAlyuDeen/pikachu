package com.pepefute.pikachu.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmPokemon extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private int height;
    private String type;
    private int weight;
    private String sprite;

    public RealmPokemon() {
    }

    public RealmPokemon(int id, String name, int height, String type, int weight, String sprite) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.type = type;
        this.weight = weight;
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
