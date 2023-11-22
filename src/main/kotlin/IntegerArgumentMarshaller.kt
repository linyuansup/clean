import java.lang.NumberFormatException
import java.util.NoSuchElementException

class IntegerArgumentMarshaller : ArgumentMarshaller {
    override var value = 0
    override fun set(currentArgument: Iterator<String>) {
        var param: String? = null
        try {
            param = currentArgument.next()
            value = param.toInt()
        } catch (e: NoSuchElementException) {
            throw ArgsException(ErrorCode.MISSING_INTEGER)
        } catch (e: NumberFormatException) {
            throw ArgsException(ErrorCode.INVALID_INTEGER, errorParameter = param)
        }
    }
}