import java.lang.NumberFormatException
import java.util.NoSuchElementException

class DoubleArgumentMarshaller : ArgumentMarshaller {
    override var value = 0.0
    override fun set(currentArgument: Iterator<String>) {
        var param: String? = null
        try {
            param = currentArgument.next()
            value = param.toDouble()
        } catch (e: NoSuchElementException) {
            throw ArgsException(ErrorCode.MISSING_DOUBLE)
        } catch (e: NumberFormatException) {
            throw ArgsException(ErrorCode.INVALID_DOUBLE, errorParameter = param)
        }
    }
}