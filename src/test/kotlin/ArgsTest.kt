import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.fail

class ArgsTest {
    @Test
    fun testCreateWithNoSchemaOrArguments() {
        val args = Args("", arrayOf())
        assertEquals(0, args.cardinality())
    }

    @Test
    fun testWithNoSchemaButWithOneArgument() {
        try {
            Args("", arrayOf("-x"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.UNEXPECTED_ARGUMENT, e.errorCode)
            assertEquals('x', e.errorArgumentId)
        }
    }

    @Test
    fun testWithNoSchemaButWithMultipleArguments() {
        try {
            Args("", arrayOf("-x", "-y"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.UNEXPECTED_ARGUMENT, e.errorCode)
            assertEquals('x', e.errorArgumentId)
        }
    }

    @Test
    fun testNonLetterSchema() {
        try {
            Args("*", arrayOf())
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.INVALID_ARGUMENT_NAME, e.errorCode)
            assertEquals('*', e.errorArgumentId)
        }
    }

    @Test
    fun testInvalidArgumentFormat() {
        try {
            Args("f~", arrayOf())
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.INVALID_ARGUMENT_FORMAT, e.errorCode)
            assertEquals('f', e.errorArgumentId)
        }
    }

    @Test
    fun testSimpleBooleanPresent() {
        val args = Args("x", arrayOf("-x"))
        assertEquals(1, args.cardinality())
        assertEquals(true, args.get<Boolean>('x'))
    }

    @Test
    fun testSimpleStringPresent() {
        val args = Args("x*", arrayOf("-x", "param"))
        assertEquals(1, args.cardinality())
        assert(args.has('x'))
    }

    @Test
    fun testSpacesInFormat() {
        val args = Args("x, y", arrayOf("-xy"))
        assertEquals(2, args.cardinality())
        assert(args.has('x'))
        assert(args.has('y'))
    }

    @Test
    fun testSimpleIntPresent() {
        val args = Args("x#", arrayOf("-x", "42"))
        assertEquals(1, args.cardinality())
        assert(args.has('x'))
        assertEquals(42, args.get<Int>('x'))
    }

    @Test
    fun testInvalidInteger() {
        try {
            Args("x#", arrayOf("-x", "Forty two"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.INVALID_INTEGER, e.errorCode)
            assertEquals('x', e.errorArgumentId)
            assertEquals("Forty two", e.errorParameter)
        }
    }

    @Test
    fun testMissingInteger() {
        try {
            Args("x#", arrayOf("-x"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.MISSING_INTEGER, e.errorCode)
            assertEquals('x', e.errorArgumentId)
        }
    }

    @Test
    fun testSimpleDoublePresent() {
        val args = Args("x##", arrayOf("-x", "42.4"))
        assertEquals(1, args.cardinality())
        assert(args.has('x'))
        assertEquals(42.4, args.get<Double>('x'))
    }

    @Test
    fun testInvalidDouble() {
        try {
            Args("x##", arrayOf("-x", "Forty two"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.INVALID_DOUBLE, e.errorCode)
            assertEquals('x', e.errorArgumentId)
            assertEquals("Forty two", e.errorParameter)
        }
    }

    @Test
    fun testMissingDouble() {
        try {
            Args("x##", arrayOf("-x"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.MISSING_DOUBLE, e.errorCode)
            assertEquals('x', e.errorArgumentId)
        }
    }

    @Test
    fun testSimpleStringArrayPresent() {
        val args = Args("x*[]", arrayOf("-x", "[\"litian\", \"yang\"]"))
        assertEquals(1, args.cardinality())
        assert(args.has('x'))
        assertEquals(listOf("litian", "yang"), args.get<List<String>>('x'))
    }

    @Test
    fun testMissingStringArray() {
        try {
            Args("x*[]", arrayOf("-x"))
            fail()
        } catch (e: ArgsException) {
            assertEquals(ErrorCode.MISSING_STRING_ARRAY, e.errorCode)
            assertEquals('x', e.errorArgumentId)
        }
    }
}