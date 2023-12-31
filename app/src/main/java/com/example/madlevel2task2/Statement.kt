package com.example.madlevel2task2

data class Statement(
    var statementText: String,
    var statementAnswers: Boolean
) {
    companion object {
        val STATEMENT_TEXTS = arrayOf(
                "A 'val' and 'var' are the same.",
                "Mobile Application Development grants 12 ECTS.",
                "A Unit in Kotlin corresponds to a void in Java.",
                "In Kotlin 'when' replaces the 'switch' operator in Java."
        )

        val STATEMENT_ANSWERS = arrayOf(
                false,
                true,
                false,
                true
        )
    }
}