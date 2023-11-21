import java.lang.NumberFormatException
import java.util.NoSuchElementException

class IntegerArgumentMarshaller : ArgumentMarshaller {
    private var intValue = 0
    override fun set(currentArgument: Iterator<String>) {
        var param: String? = null
        try {
            param = currentArgument.next()
            intValue = param.toInt()
        } catch (e: NoSuchElementException) {
            throw ArgsException(ErrorCode.MISSING_INTEGER)
        } catch (e: NumberFormatException) {
            throw ArgsException(ErrorCode.INVALID_INTEGER, errorParameter = param)
        }
    }

    companion object {
        fun getValue(am: ArgumentMarshaller) = if (am is IntegerArgumentMarshaller) {
            am.intValue
        } else {
            0
        }
    }
}