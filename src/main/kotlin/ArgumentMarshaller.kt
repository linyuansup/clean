interface ArgumentMarshaller {
    fun set(currentArgument: Iterator<String>)
    val value: Any
}