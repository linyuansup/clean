import java.util.NoSuchElementException

class StringArgumentMarshaller: ArgumentMarshaller {
    private var stringValue = ""

    override fun set(currentArgument: Iterator<String>) {
        try {
            stringValue = currentArgument.next()
        } catch (e:NoSuchElementException) {
            throw ArgsException(ErrorCode.MISSING_STRING)
        }
    }

    companion object {
        fun getValue(am: ArgumentMarshaller) = if (am is StringArgumentMarshaller) {
            am.stringValue
        } else {
            ""
        }
    }
}