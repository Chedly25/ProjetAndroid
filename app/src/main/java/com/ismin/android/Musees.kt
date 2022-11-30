package com.ismin.android

import java.lang.RuntimeException

class Musees {

    private val musees: HashMap<String, Musee> = HashMap();

    fun addMusee(musee : Musee) {
        musees[musee.id] = musee;
    }

    fun getMusee(name: String): Musee {
        return musees[name] ?: throw RuntimeException("No museum with name: $name");
    }

    fun getAllMuseums(): ArrayList<Musee> {
        return ArrayList(musees.values.sortedBy { it.region })
    }

    fun getMuseumsIn(region: String): List<Musee> {
        return musees.filterValues { it.region.equals(region, ignoreCase = true) }
            .values
            .sortedBy { it.nom }
            .toList();
    }

    fun getTotalNumberOfMuseums(): Int {
        return musees.size;
    }

    fun clean() {
        musees.clear();
    }


}
