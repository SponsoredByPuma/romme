package de.htwg.se.romme
package util

trait Observer:
    def update: Unit
    def updated: Boolean


class Observable {
    var subscribers: Vector[Observer] = Vector()
    def add(s: Observer): Unit = subscribers = subscribers :+ s
    def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)
    def notifyObservers: Unit = subscribers.foreach(o => o.update)
}
