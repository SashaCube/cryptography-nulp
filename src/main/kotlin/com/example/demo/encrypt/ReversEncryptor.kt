package com.example.demo.encrypt

class ReversEncryptor : Encryptor {

    override fun encrypt(value: String): String {
        return Revers.encrypt(value)
    }

    override fun getName(): String {
        return "ПЕРЕСТАНОВКИ"
    }

    override fun getAbout(): String {
        return "Шифри перестановки. Відмінність цього типу шифру від шифрів заміни\n" +
                "полягає в тому, що при зашифруванні буква a i відкритого тексту переходить не\n" +
                "у фіксований знак алфавіту, а в іншу букву того ж відкритого тексту a j ,\n" +
                "внаслідок чого букви розташовуються на нових місцях, тобто переставляються.\n" +
                "Ключем для даного шифру також служить таблиця заміни, тільки не букв\n" +
                "алфавіту, а їх індексів (номерів місць) в тексті, який підлягає зашифруванню. У\n" +
                "загальному випадку, розмір таблиці заміни дорівнює довжині відкритого\n" +
                "тексту. Такі таблиці зручно формувати (і записувати) у вигляді підстановок."
    }

    object Revers {

        private val keyTable = arrayOf(arrayOf(0, 1, 2, 3, 4, 5), arrayOf(3, 5, 0, 4, 1, 2))

        fun encrypt(message: String): String {
            val arrayMessage = message.toCharArray()
            val encryptedarrayMessage = CharArray(message.length)
            for (i in 0 until message.length) {
                encryptedarrayMessage[i] = arrayMessage[keyTable[1][i]]
            }
            return String(encryptedarrayMessage)
        }

        fun decrypt(message: String): String {
            val arrayMessage = message.toCharArray()
            val uncryptedarrayMessage = CharArray(message.length)
            for (i in arrayMessage.indices) {
                uncryptedarrayMessage[keyTable[1][i]] = arrayMessage[i]
            }
            return String(uncryptedarrayMessage)
        }
    }
}