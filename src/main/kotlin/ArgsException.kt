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

    fun errorMessage() = errorCode.message(errorArgumentId, errorParameter)
}
