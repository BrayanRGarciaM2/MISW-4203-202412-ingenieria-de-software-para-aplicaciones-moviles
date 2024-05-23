package com.tsdc.vinilos.data.model

enum class Recurrence {
    Classical,
    Salsa,
    Rock,
    Folk
}

enum class RecordLabel(val value: String) {
    SonyMusic ("Sony Music"),
    EMI("EMI"),
    DiscosFuentes("Discos Fuentes"),
    Elektra("Elektra"),
    FaniaRecords("Fania Records")
}