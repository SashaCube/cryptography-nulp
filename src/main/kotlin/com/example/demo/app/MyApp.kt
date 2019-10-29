package com.example.demo.app

import com.example.demo.view.GrothvaldView
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(GrothvaldView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = 400.0
        stage.height = 600.0
    }
}