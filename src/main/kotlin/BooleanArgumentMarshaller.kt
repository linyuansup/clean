class BooleanArgumentMarshaller : ArgumentMarshaller {
    private var booleanValue = false

    override fun set(currentArgument: Iterator<String>) {
        booleanValue = true
    }

    companion object {
        fun getValue(am: ArgumentMarshaller) = if (am is BooleanArgumentMarshaller) {
            am.booleanValue
        } else {
            false
        }
    }
}