package com.example.demo.encrypt

class ReplaceEncryptor : Encryptor {

    override fun encrypt(value: String): String {
        return Replace.encrypt(value)
    }

    override fun getName(): String {
        return "ЗАМІНИ"
    }

    override fun getAbout(): String {
        return "Шифр заміни (шифр підстановки) – метод шифрування,\n" +
                "при якому кожен елемент початкового тексту \n" +
                "взаємно-однозначно замінюється одним, або \n" +
                "декількома знаками деякого алфавіту. Шифр \n" +
                "простої заміни замінює кожен знак вхідного \n" +
                "алфавіту на деякий знак з того ж алфавіту, \n" +
                "Результат заміни не залежить від розташування \n" +
                "знаку у відкритому тексті. Ключами для шифрів \n" +
                "заміни є таблиці заміни."
    }

    object Replace {

        private val keyTable = arrayOf(arrayOf(0, 1, 2, 3, 4, 5), arrayOf(3, 5, 0, 4, 1, 2))

        fun encrypt(message: String): String {
            if (message.length > keyTable[0].size) throw IndexOutOfBoundsException()
            val arrayMessage = message.toCharArray()
            val encryptedarrayMessage = CharArray(message.length)

            for (i in arrayMessage.indices) {
                encryptedarrayMessage[i] = (arrayMessage[i] - keyTable[1][i])
            }
            return String(encryptedarrayMessage)
        }

        fun decrypt(message: String): String {
            val arrayMessage = message.toCharArray()
            val decryptedarrayMessage = CharArray(message.length)
            for (i in arrayMessage.indices) {
                decryptedarrayMessage[i] = arrayMessage[i] + keyTable[1][i]
            }
            return String(decryptedarrayMessage)
        }
    }
}