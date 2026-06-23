package com.localdirect.desktop

import java.awt.Robot
import java.awt.event.InputEvent

class Handlers {
    private val robot = Robot()

    fun sendLeftMouseClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }
}