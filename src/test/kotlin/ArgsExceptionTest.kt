import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArgsExceptionTest {
    @Test
    fun testUnexpectedMessage() {
        val e = ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, 'x')
        assertEquals("Argument -x unexpected.", e.errorMessage())
    }

    @Test
    fun testMissingStringMessage() {
        val e = ArgsException(ErrorCode.MISSING_STRING, 'x')
        assertEquals("Could not find string parameter for -x.", e.errorMessage())
    }

    @Test
    fun testInvalidIntegerMessage() {
        val e = ArgsException(ErrorCode.INVALID_INTEGER, 'x', "Forty two")
        assertEquals("Argument -x expects an integer but was Forty two.", e.errorMessage())
    }

    @Test
    fun testMissingIntegerMessage() {
        val e = ArgsException(ErrorCode.MISSING_INTEGER, 'x')
        assertEquals("Could not find integer parameter for -x.", e.errorMessage())
    }

    @Test
    fun testInvalidDoubleMessage() {
        val e = ArgsException(ErrorCode.INVALID_DOUBLE, 'x', "Forty two")
        assertEquals("Argument -x expects an double but was Forty two.", e.errorMessage())
    }

    @Test
    fun testMissingDoubleMessage() {
        val e = ArgsException(ErrorCode.MISSING_DOUBLE, 'x')
        assertEquals("Could not find double parameter for -x.", e.errorMessage())
    }

    @Test
    fun testMissingStringArray() {
        val e = ArgsException(ErrorCode.MISSING_STRING_ARRAY, 'x')
        assertEquals("Could not find string array parameter for -x.", e.errorMessage())
    }
}