package com.example.demo.encrypt

class GrothvaldEncryptor : Encryptor {

    lateinit var list: List<Int>
    override fun encrypt(value: String): String {
        return Grothvald.encrypt(value, list)
    }

    fun setAlfavit(list: List<Int>) {
        this.list = list
    }

    override fun getName(): String {
        return "Гронсфельда"
    }

    override fun getAbout(): String {
        return "Шифр Гронсфельда"
    }

    object Grothvald {

        fun encrypt(message: String, list: List<Int>): String {
            val arrayMessage = message.toCharArray()
            var encryptedarrayMessage = ""

            arrayMessage.mapIndexed { index, c ->
                encryptedarrayMessage += Caesar.encrypt(c.toString(), list.get(index))
            }

            return encryptedarrayMessage
        }

        fun decrypt(message: String): String {
            val arrayMessage = message.toCharArray()
            val decryptedarrayMessage = CharArray(message.length)
            for (i in arrayMessage.indices) {
                decryptedarrayMessage[i] = arrayMessage[i]
            }
            return String(decryptedarrayMessage)
        }

        object Caesar {
            fun encrypt(s: String, key: Int): String {
                val offset = key % 26
                if (offset == 0) return s
                var d: Char
                val chars = CharArray(s.length)
                for ((index, c) in s.withIndex()) {
                    if (c in 'A'..'Z') {
                        d = c + offset
                        if (d > 'Z') d -= 26
                    } else if (c in 'a'..'z') {
                        d = c + offset
                        if (d > 'z') d -= 26
                    } else
                        d = c
                    chars[index] = d
                }
                return chars.joinToString("")
            }

            fun decrypt(s: String, key: Int): String {
                return encrypt(s, 26 - key)
            }
        }
    }
}