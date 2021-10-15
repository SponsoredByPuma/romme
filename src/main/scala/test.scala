package Romme

import scala.io.StdIn._

object test {
  def main(args: Array[String]) = {
    println("Welcome to RommÃ© \n")
    val greeting = "Hello " + signUp(args)
    println(greeting)
  }

  def signUp(playerNames: Array[String]): String = {
    if (playerNames.length > 0)
      playerNames.head
    else
      readLine("Please enter your name: ")
  }
}
