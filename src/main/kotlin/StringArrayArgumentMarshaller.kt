import java.util.NoSuchElementException

class StringArrayArgumentMarshaller : ArgumentMarshaller {
    override fun set(currentArgument: Iterator<String>) {
        val param: String?
        try {
            param = currentArgument.next()
            val regex = "\"([^\"]+)\"".toRegex()
            val matches = regex.findAll(param)
            value = matches.map { it.groupValues[1] }.toMutableList()
        } catch (e: NoSuchElementException) {
            throw ArgsException(ErrorCode.MISSING_STRING_ARRAY)
        }
    }

    override var value: MutableList<String> = mutableListOf()
}