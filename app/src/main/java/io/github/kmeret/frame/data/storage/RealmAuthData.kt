package io.github.kmeret.frame.data.storage

import io.realm.RealmObject

open class RealmAuthData (
    var token: String? = null
) : RealmObject()