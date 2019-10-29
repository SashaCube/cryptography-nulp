package com.example.demo.view

import com.example.demo.encrypt.Encryptor
import com.example.demo.encrypt.ReplaceEncryptor
import com.example.demo.encrypt.ReversEncryptor
import com.example.demo.encrypt.СezarEncryptor
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.Text
import tornadofx.*

class MainView : View("Програма Шифрування") {

    override val root = Form()

    private val encryptorsList = listOf(REPLACE, REVERS, CEZAR).observable()

    private var currentEncryptor: Encryptor = ReversEncryptor()

    private var inputTextField: TextField by singleAssign()
    private var outputTextField: TextField by singleAssign()
    private var aboutLabel: Text by singleAssign()

    init {
        initView()
        setEncryptingAlgorithm(REVERS)
    }

    private fun initView() {
        hbox {
            labHeaderView()
            chooseEncryptorVeiw()
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
            text("Лабораторна робота №1\n") {
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
            REVERS -> {
                currentEncryptor = ReversEncryptor()
            }
            CEZAR -> {
                currentEncryptor = СezarEncryptor()
            }
            REPLACE -> {
                currentEncryptor = ReplaceEncryptor()
            }
        }
        aboutLabel.text = currentEncryptor.getAbout()
    }

    companion object {
        const val REVERS = "ПЕРЕСТАНОВКИ"
        const val REPLACE = "ЗАМІНИ"
        const val CEZAR = "ЦЕЗАР"
    }
}