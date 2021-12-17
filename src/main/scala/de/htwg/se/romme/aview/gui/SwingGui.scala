package de.htwg.se.romme.aview.gui

import de.htwg.se.romme.controller.controllerComponent.ControllerInterface
import de.htwg.se.romme.controller.controllerComponent.controllerBaseImpl._


import de.htwg.se.romme.model.modelComponent.gameBaseImpl.Card

import scala.swing._
import scala.swing.event._
import scala.swing.Swing.LineBorder
import scala.io.Source._
import javax.swing.border.Border
import java.awt.Color
import javax.swing.JButton
import scala.collection.mutable.ListBuffer
import javax.swing.text.Position
import javax.swing.JTextArea
import javax.swing.JScrollPane
import javax.swing.JOptionPane
import javax.swing.SwingConstants
import javax.swing.SwingContainer
import javax.imageio.ImageIO
import java.io.File

case class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)

  title = "HTWG-Romme"
  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.gameStart
        redraw
      })
      contents += new MenuItem(Action("Quit") {System.exit(0)})
    }
  }

  def infoAboutGame: BoxPanel = new BoxPanel(Orientation.Vertical):
    if(controller.game.deck.deckList.size == 0)
      contents += new Label("Start the game through the menu File/New")
    else
      if(controller.playersTurn)
        contents += new Label("It's Player One's Turn.")
      else
        contents += new Label("It's Player Two's Turn.")
      end if
    end if
    
  def theTable: BoxPanel = new BoxPanel(Orientation.Vertical): 
    background = Color.BLACK
    var tmpCardsInsideList: ListBuffer[Card] = ListBuffer()

    for (cnt <- 0 to controller.getCardsTable.size - 1)
      for(cnt2 <- 0 to controller.getCardsTable(cnt).size - 1)
        tmpCardsInsideList.addOne(controller.getCardsTable(cnt)(cnt2))

    var pics: ListBuffer[ListBuffer[Image]] = ListBuffer()
    var f = new File("C:\\Users\\Till\\Desktop\\SoftwareEngineering\\Romme\\romme\\src\\PNG\\" + getPictureName(controller.getGraveyardCard.getCardName._1, controller.getGraveyardCard.getCardName._2) +".png")
    var friedhof = ImageIO.read(f).getScaledInstance(52,80,java.awt.Image.SCALE_SMOOTH)
    for(list <- controller.getCardsTable)
      var list2: ListBuffer[Image] = ListBuffer()
      for(card <- list)
        var fil = new File("C:\\Users\\Till\\Desktop\\SoftwareEngineering\\Romme\\romme\\src\\PNG\\" + getPictureName(card.getCardName._1,card.getCardName._2) +".png")
        list2.addOne(ImageIO.read(fil).getScaledInstance(52,80,java.awt.Image.SCALE_SMOOTH))
      pics.append(list2)
    override def paintComponent(g: java.awt.Graphics2D) =  {
      super.paintComponent(g)
      var y = 10
      for(list <- pics)
        var x = 30
        for(card <- list)
          g.drawImage(card,x,y,null)
          x = x + 60
        y = y + 90
      g.drawImage(friedhof,700,10,null)
    }

  def getPictureName(s1: String,s2: String): String = {
    var returnString = ""
    s2 match {
      case "two" => returnString = "2"
      case "three" => returnString = "3"
      case "four" => returnString = "4"
      case "five" => returnString = "5"
      case "six" => returnString = "6"
      case "seven" => returnString = "7"
      case "eight" => returnString = "8"
      case "nine" => returnString = "9"
      case "ten" => returnString = "10"
      case "jack" => returnString = "J"
      case "queen" => returnString = "Q"
      case "king" => returnString = "K"
      case "ace" => returnString = "A"
      case "" => returnString = "J"
    }
    s1 match {
      case "Heart" => returnString += "H"
      case "Diamond" => returnString += "D"
      case "Club" => returnString += "C"
      case "Spades" => returnString += "S"
      case "Joker" => returnString += "J"
      case "" => returnString += ""
    }
    returnString
  }

  def playerAction: GridPanel = new GridPanel(8,1):
    val pickButton = new Button("pick")
    contents += pickButton
    val dropButton = new Button("Drop")
    contents += dropButton
    val graveYardButton = new Button("Graveyard")
    contents += graveYardButton
    val dropMButton = new Button("dropM")
    contents += dropMButton
    val jokerButton = new Button("Joker")
    contents += jokerButton
    val addButton = new Button("add")
    contents += addButton
    val sortButton = new Button("Sort")
    contents += sortButton
    val switchButton = new Button("switch") 
    contents += switchButton
    listenTo(pickButton)
    listenTo(dropButton)
    listenTo(graveYardButton)
    listenTo(dropMButton)
    listenTo(jokerButton)
    listenTo(addButton)
    listenTo(sortButton)
    listenTo(switchButton)
    reactions += {
      case ButtonClicked(`switchButton`) => controller.switch
      case ButtonClicked(`sortButton`) => controller.sortPlayersCards
      case ButtonClicked(`pickButton`) => controller.pickUpACard
      case ButtonClicked(`graveYardButton`) => controller.pickUpGraveYard
      case ButtonClicked(`dropButton`) => 
        var tmp = JOptionPane.showInputDialog(null,"","Which Card would you like to drop ?",JOptionPane.DEFAULT_OPTION)
        controller.dropASpecificCard(tmp.toInt)
      case ButtonClicked(`dropMButton`) =>
        var amount = 0
        if(controller.player1Turn)
          while(amount < 3 || amount >= controller.game.player.hands.playerOneHand.size) 
          amount = JOptionPane.showInputDialog(null,"","How many Cards would you like to drop ?", JOptionPane.DEFAULT_OPTION).toInt
        else
          while(amount < 3 || amount >= controller.game.player2.hands.playerOneHand.size) 
          amount = JOptionPane.showInputDialog(null,"","How many Cards would you like to drop ?", JOptionPane.DEFAULT_OPTION).toInt
        end if
        val tmpList: ListBuffer[Integer] = new ListBuffer()
        while(amount > 0)
          tmpList.addOne(JOptionPane.showInputDialog(null,"","Which Card would you like to drop ?", JOptionPane.DEFAULT_OPTION).toInt)
          amount = amount - 1
        var dec = JOptionPane.showInputDialog(null,"","Would you like to drop them by Suit(0) or by Order(1) ?", JOptionPane.DEFAULT_OPTION).toInt
        var tt: ListBuffer[Integer] = new ListBuffer()
        tt = controller.checkForJoker(tmpList)
        if(tt.isEmpty)
          controller.dropMultipleCards(tmpList,dec,false)
        else
          var stringList: ListBuffer[String] = ListBuffer()
          if(dec == 0) // nach Suit
            for (x <- 0 to tt.size - 1)
              stringList.addOne(JOptionPane.showInputDialog(null,"","Which Suit should your Joker have ?", JOptionPane.DEFAULT_OPTION))
            controller.replaceCardSuit(tt,stringList)
            controller.dropMultipleCards(tmpList,dec,true)
          else
            for (x <- 0 to tt.size - 1)
              stringList.addOne(JOptionPane.showInputDialog(null,"","Which Rank should your Joker have ?", JOptionPane.DEFAULT_OPTION))
            controller.replaceCardOrder(tt,stringList)
            controller.dropMultipleCards(tmpList,dec,true)
          end if
        end if
      case ButtonClicked(`jokerButton`) =>
        var cardInput = JOptionPane.showInputDialog(null,"","Which Card would you like to drop ?",JOptionPane.DEFAULT_OPTION).toInt
        var setInput = JOptionPane.showInputDialog(null,"","Which Set would you like to change ?",JOptionPane.DEFAULT_OPTION).toInt
        controller.takeJoker(setInput,cardInput)
      case ButtonClicked(`addButton`) =>
        var cardInput = JOptionPane.showInputDialog(null,"","Which Card would you like to add ?",JOptionPane.DEFAULT_OPTION).toInt
        var setInput = JOptionPane.showInputDialog(null,"","Which Set would you like to expand ?",JOptionPane.DEFAULT_OPTION).toInt
        controller.addCard(cardInput,setInput)
    }
  
  def showCards: GridPanel = new GridPanel(1,1):
    background = Color.DARK_GRAY
    var tmpCards = controller.getCards
    var pics: ListBuffer[Image] = ListBuffer()
    for(card <- tmpCards)
      var f = new File("C:\\Users\\Till\\Desktop\\SoftwareEngineering\\Romme\\romme\\src\\PNG\\"+ getPictureName(card.getCardName._1,card.getCardName._2) +".png")
      pics.addOne(ImageIO.read(f).getScaledInstance(52,80,java.awt.Image.SCALE_SMOOTH))
    this.preferredSize = new Dimension(900, 125)

    override def paintComponent(g: java.awt.Graphics2D) =  {
      super.paintComponent(g)
      g.setColor(Color.DARK_GRAY)
      g.fillRect(0,0,1300,140)
      var x = 30
      var i = 0
      g.setColor(Color.WHITE)
      for(pic <- pics)
        g.drawImage(pic,x,10,null)
        g.drawString(i.toString,x + 20, 110)
        x = x + 60
        i = i + 1
    }

  visible = true
  redrawF
  centerOnScreen()

  reactions += {
    case event: showPlayerCards => redraw
    case event: showPlayerTable => redraw
  }

  def redrawF: Unit = 
    contents = new BorderPanel {
      add(infoAboutGame,BorderPanel.Position.Center)
    }
    repaint()

  def redraw: Unit = {
    contents = new BorderPanel {
      add(infoAboutGame, BorderPanel.Position.North)
      add(theTable,BorderPanel.Position.Center)
      add(playerAction, BorderPanel.Position.East)
      add(showCards,BorderPanel.Position.South)
      this.preferredSize_=(new Dimension(900,400))
    }
    repaint()
    centerOnScreen()
  }
}
