class Args(schema: String, args: Array<String>) {
    val marshallers: MutableMap<Char, ArgumentMarshaller> = mutableMapOf()

    private lateinit var currentArgument: ListIterator<String>

    init {
        parseSchema(schema)
        parseArgumentStrings(args.toList())
    }

    private fun parseSchema(schema: String) {
        schema.split(",").forEach {
            if (it.isNotEmpty()) {
                parseSchemaElement(it.trim())
            }
        }
    }

    private fun parseSchemaElement(element: String) {
        val elementId = element.elementAt(0)
        val elementTail = element.substring(1)
        if (validateSchemaElementId(elementId)) {
            throw ArgsException(ErrorCode.INVALID_ARGUMENT_NAME, elementId)
        }
        val marshaller = if (elementTail.isEmpty()) {
            BooleanArgumentMarshaller()
        } else {
            tailMarshaller.get(elementTail) ?: throw ArgsException(
                ErrorCode.INVALID_ARGUMENT_FORMAT,
                elementId,
                elementTail
            )
        }
        marshallers[elementId] = marshaller
    }

    private fun validateSchemaElementId(elementId: Char) = !Character.isLetter(elementId)

    private fun parseArgumentStrings(argsList: List<String>) {
        currentArgument = argsList.listIterator()
        while (currentArgument.hasNext()) {
            val argString = currentArgument.next()
            if (argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1))
            } else {
                currentArgument.previous()
            }
        }
    }

    private fun parseArgumentCharacters(argChars: String) {
        argChars.forEach {
            parseArgumentCharacter(it)
        }
    }

    private fun parseArgumentCharacter(argChar: Char) {
        marshallers[argChar]?.let { marshaller ->
            try {
                marshaller.set(currentArgument)
            } catch (e: ArgsException) {
                e.errorArgumentId = argChar
                throw e
            }
        } ?: throw ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, argChar)
    }

    fun has(arg: Char) = marshallers.contains(arg)

    inline fun <reified T> get(arg: Char): T {
        return marshallers[arg]?.let {
            it.value as T
        } ?: throw ArgsException(ErrorCode.UNEXPECTED_ARGUMENT)
    }

    fun cardinality() = marshallers.size
}