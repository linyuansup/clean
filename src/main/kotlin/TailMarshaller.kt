val tailMarshaller = mapOf(
    "" to BooleanArgumentMarshaller(),
    "*" to StringArgumentMarshaller(),
    "#" to IntegerArgumentMarshaller(),
    "##" to DoubleArgumentMarshaller(),
    "*[]" to StringArrayArgumentMarshaller(),
)
