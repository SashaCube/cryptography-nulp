package com.example.demo.encrypt

class СezarEncryptor : Encryptor {

    override fun encrypt(value: String): String {
        return Caesar.encrypt(value, 3)
    }

    override fun getName(): String {
        return "ЦЕЗАР"
    }

    override fun getAbout(): String {
        return "Шифр Цезаря або шифр зсуву — симетричний \n" +
                "моноалфавітний алгоритм шифрування, в \n" +
                "якому кожна буква відкритого тексту\n" +
                "заміняється на ту, що віддалена від \n" +
                "неї в алфавіті на сталу кількість позицій.\n" +
                "Римський імператор Юлій Цезар використовував \n" +
                "для приватного листування шифр зсуву з ключем\n" +
                "3 — замість літери A підставляв D, замість \n" +
                "B — E і так далі"
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
                }
                else if (c in 'a'..'z') {
                    d = c + offset
                    if (d > 'z') d -= 26
                }
                else
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
