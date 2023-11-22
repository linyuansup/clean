enum class ErrorCode(val message: (errorArgumentId: Char, errorParameter: String) -> String) {
    INVALID_ARGUMENT_FORMAT({ errorArgumentId, _ ->
        String.format(
            "Invalid argument format for -%c.",
            errorArgumentId
        )
    }),

    UNEXPECTED_ARGUMENT({ errorArgumentId, _ ->
        String.format("Argument -%c unexpected.", errorArgumentId)
    }),

    INVALID_ARGUMENT_NAME({ errorArgumentId, _ ->
        String.format(
            "Invalid argument name for -%c.",
            errorArgumentId
        )
    }),

    MISSING_STRING({ errorArgumentId, _ ->
        String.format(
            "Could not find string parameter for -%c.",
            errorArgumentId
        )
    }),

    MISSING_INTEGER({ errorArgumentId, _ ->
        String.format(
            "Could not find integer parameter for -%c.",
            errorArgumentId
        )
    }),

    INVALID_INTEGER({ errorArgumentId, errorParameter ->
        String.format(
            "Argument -%c expects an integer but was %s.",
            errorArgumentId, errorParameter
        )
    }),

    MISSING_DOUBLE({ errorArgumentId, _ ->
        String.format(
            "Could not find double parameter for -%c.",
            errorArgumentId
        )
    }),

    INVALID_DOUBLE({ errorArgumentId, errorParameter ->
        String.format(
            "Argument -%c expects an double but was %s.",
            errorArgumentId, errorParameter
        )
    }),

    MISSING_STRING_ARRAY({ errorArgumentId, _ ->
        String.format(
            "Could not find string array parameter for -%c.",
            errorArgumentId
        )
    }),
}