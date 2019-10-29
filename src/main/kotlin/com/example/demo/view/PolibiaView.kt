package com.example.demo.view

import com.example.demo.encrypt.*
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.Text
import tornadofx.*

class PolibiaView : View("Програма Шифрування") {

    override val root = Form()

    private val encryptorsList = listOf(POLIBIA).observable()

    private var currentEncryptor: Encryptor = PolibiaEncryptor()

    private var inputTextField: TextField by singleAssign()
    private var outputTextField: TextField by singleAssign()
    private var aboutLabel: Text by singleAssign()

    private var polibiaList = mutableListOf<TextField>()

    init {
        initView()
        setEncryptingAlgorithm(POLIBIA)

        for(i in 1..25){
            polibiaList.add(TextField())
        }
    }

    private val alf = listOf("q", "e", "r", "t", "y", "a", "s", "d", "f",
            "g", "z", "x", "c", "v", "b", "u", "i", "o", "p", "h", "m", "n", "l", "k", "j")
    private fun initView() {
        hbox {
            labHeaderView()
            chooseEncryptorVeiw()

           vbox {
               hbox {
                   textfield("q")
                   textfield("e")
                   textfield("r")
                   textfield("t")
                   textfield("y")
               }
               hbox {
                   textfield("a")
                   textfield("s")
                   textfield("d")
                   textfield("f")
                   textfield("g")
               }
               hbox {
                   textfield("z")
                   textfield("x")
                   textfield("c")
                   textfield("v")
                   textfield("b")
               }
               hbox {
                   textfield("u")
                   textfield("i")
                   textfield("o")
                   textfield("p")
                   textfield("h")
               }
               hbox {
                   textfield("m")
                   textfield("n")
                   textfield("l")
                   textfield("k")
                   textfield("j")
               }
           }
            encryptinView()
        }
    }

    private fun encryptinView(): Button {
        fieldset("Шифрування") {
            field("Вхідні Дані") {
                inputTextField = textfield()
            }

            field("Вихідні Дані") {
                outputTextField = textfield()
                outputTextField.isEditable = false
            }
        }
        return button("Зашифрувати") {
            setOnAction {
                (currentEncryptor as PolibiaEncryptor).setAlfavit(alf)
                outputTextField.text = currentEncryptor.encrypt(inputTextField.text)
            }

            shortcut("Enter")
        }
    }

    private fun chooseEncryptorVeiw() {
        fieldset("Вибір Шифрування") {
            spinner(encryptorsList).valueProperty().onChange { encryptorName ->
                encryptorName?.let { setEncryptingAlgorithm(it) }
            }
            textflow {
                paddingAll = 10
                aboutLabel = text {
                    fill = Color.PURPLE
                    font = Font.font("Veranda", FontPosture.ITALIC, 14.0)
                }
            }.prefHeight = 270.0
        }
    }

    private fun labHeaderView() {
        textflow {
            text("Лабораторна робота №2\n") {
                fill = Color.PURPLE
                font = Font.font("Veranda", FontPosture.ITALIC, 14.0)
            }
            text("Виконав Гаврилюк Олександр\n") {
                fill = Color.PURPLE
                font = Font.font("Veranda", FontPosture.ITALIC, 14.0)
            }
            text("студент КН-303 (АСУ)\n") {
                fill = Color.PURPLE
                font = Font.font("Veranda", FontPosture.ITALIC, 14.0)
            }
            text("2019") {
                fill = Color.PURPLE
                font = Font.font("Veranda", FontPosture.ITALIC, 14.0)
            }
        }.prefHeight = 150.0
    }

    private fun setEncryptingAlgorithm(encryptor: String) {
        when (encryptor) {
            POLIBIA -> {
                currentEncryptor = PolibiaEncryptor()
            }
        }
        aboutLabel.text = currentEncryptor.getAbout()
    }

    companion object {
        const val POLIBIA = "ПОЛІБІЯ"
    }
}