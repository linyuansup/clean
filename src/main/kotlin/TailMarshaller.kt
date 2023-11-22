val tailMarshaller = mapOf(
    "*" to StringArgumentMarshaller(),
    "#" to IntegerArgumentMarshaller(),
    "##" to DoubleArgumentMarshaller(),
    "*[]" to StringArrayArgumentMarshaller(),
)
