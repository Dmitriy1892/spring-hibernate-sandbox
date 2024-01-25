package com.github.dmitriy1892.utils

import org.hibernate.Session

fun <R> Session.executeInTransaction(
    block: Session.() -> R,
): R = use { session ->
    session.beginTransaction()
    val result = block()
    transaction.commit()
    return@use result
}