import java.util.NoSuchElementException

class StringArgumentMarshaller: ArgumentMarshaller {
    override var value = ""

    override fun set(currentArgument: Iterator<String>) {
        try {
            value = currentArgument.next()
        } catch (e:NoSuchElementException) {
            throw ArgsException(ErrorCode.MISSING_STRING)
        }
    }
}