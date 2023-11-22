class BooleanArgumentMarshaller : ArgumentMarshaller {
    override var value = false

    override fun set(currentArgument: Iterator<String>) {
        value = true
    }
}