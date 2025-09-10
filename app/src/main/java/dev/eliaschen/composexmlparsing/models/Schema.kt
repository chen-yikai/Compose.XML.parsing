package dev.eliaschen.composexmlparsing.models

data class City(
    val name: String,
    val nameEnglish: String,
    val fileName: String,
    val isCurrent: Boolean = false
)