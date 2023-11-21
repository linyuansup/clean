class ArgsException(var errorCode: ErrorCode, errorArgumentId: Char? = null, errorParameter: String? = null) :
    Exception() {
    var errorArgumentId = '0'
    var errorParameter: String = "TILT"

    init {
        errorArgumentId?.let {
            this.errorArgumentId = it
        }
        errorParameter?.let {
            this.errorParameter = it
        }
    }

    fun errorMessage() = when (errorCode) {
        ErrorCode.OK -> "TILT: Should not get here."
        ErrorCode.UNEXPECTED_ARGUMENT -> String.format("Argument -%c unexpected.", errorArgumentId)
        ErrorCode.MISSING_STRING -> String.format(
            "Could not find string parameter for -%c.",
            errorArgumentId
        )

        ErrorCode.INVALID_INTEGER -> String.format(
            "Argument -%c expects an integer but was %s.",
            errorArgumentId, errorParameter
        )

        ErrorCode.MISSING_INTEGER -> String.format(
            "Could not find integer parameter for -%c.",
            errorArgumentId
        )

        ErrorCode.INVALID_DOUBLE -> String.format(
            "Argument -%c expects an double but was %s.",
            errorArgumentId, errorParameter
        )

        ErrorCode.MISSING_DOUBLE -> String.format(
            "Could not find double parameter for -%c.",
            errorArgumentId
        )

        ErrorCode.INVALID_ARGUMENT_FORMAT -> String.format(
            "Invalid argument format for -%c.",
            errorArgumentId
        )

        ErrorCode.INVALID_ARGUMENT_NAME -> String.format(
            "Invalid argument name for -%c.",
            errorArgumentId
        )
    }
}
