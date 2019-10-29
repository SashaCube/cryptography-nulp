package com.example.demo.encrypt

class PolibiaEncryptor : Encryptor {

    lateinit var list: List<String>
    override fun encrypt(value: String): String {
        return Polibia.encrypt(value, list)
    }

    fun setAlfavit(list: List<String>){
        this.list = list
    }

    override fun getName(): String {
        return "ПОЛІБІЯ"
    }

    override fun getAbout(): String {
        return "Шифр Полібія"
    }

    object Polibia {

        private val keyTable = arrayOf(arrayOf(0, 1, 2, 3, 4, 5), arrayOf(3, 5, 0, 4, 1, 2))

        fun encrypt(message: String, list: List<String>): String {
            val arrayMessage = message.toCharArray()
            var encryptedarrayMessage = ""

            for (i: Char in arrayMessage) {
                var index = list.indexOf(i.toString())
                index += 5

                if(index >= list.size){
                    index -= list.size
                }

                encryptedarrayMessage += list[index]
            }
            return encryptedarrayMessage
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