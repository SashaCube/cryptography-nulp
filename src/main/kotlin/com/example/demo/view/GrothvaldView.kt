package com.example.demo.view

import com.example.demo.encrypt.Encryptor
import com.example.demo.encrypt.GrothvaldEncryptor
import com.example.demo.encrypt.PolibiaEncryptor
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.Text
import tornadofx.*
import kotlin.streams.toList

class GrothvaldView : View("Програма Шифрування") {

    override val root = Form()

    private val encryptorsList = listOf(GROTHVALD).observable()

    private var currentEncryptor: Encryptor = GrothvaldEncryptor()

    private var inputTextField: TextField by singleAssign()
    private var outputTextField: TextField by singleAssign()
    private var aboutLabel: Text by singleAssign()

    private var polibiaList = mutableListOf<TextField>()

    init {
        initView()
        setEncryptingAlgorithm(GROTHVALD)

        for(i in 1..25){
            polibiaList.add(TextField())
        }
    }

    private val alf = listOf(4, 5, 6, 9, 1, 7)
    private fun initView() {
        hbox {
            labHeaderView()
            chooseEncryptorVeiw()

            vbox {
                hbox {
                    textfield("4")
                    textfield("5")
                    textfield("6")
                    textfield("9")
                    textfield("1")
                    textfield("7")
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
                (currentEncryptor as GrothvaldEncryptor).setAlfavit(alf)
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
            GROTHVALD -> {
                currentEncryptor = GrothvaldEncryptor()
            }
        }
        aboutLabel.text = currentEncryptor.getAbout()
    }

    companion object {
        const val GROTHVALD = "Гронсфельда"
    }
}