package com.anstel.getargs

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, GregorianCalendar}

/**
 * Exception lancée en cas de problème avec un paramètre en ligne
 *
 * @param msg message d'erreur
 */
class GetArgsException(msg: String) extends Exception(msg) {
  println(msg)
}

/**
 * Classe définissant l'objet GetArgs
 *
 * @param args paramètres en ligne de commande
 * @author Thierry Baribaud.
 * @version 1.05
 */
class GetArgs(args: Array[String]) {

  private val today = Calendar.getInstance.getTime
  private val ddmmyyyyFormat = new SimpleDateFormat("dd/MM/yyyy")

  var dbType: String = "dev"
  var nbDay: Int = 7
//  var begDate: String = ddmmyyyyFormat.format(today - nbDay * - 1000 * 60 * 60 * 24)
//  var endDate: String = ddmmyyyyFormat.format(today)
  var begDate: Date = GetArgs.dateAtMidnight(new Date(today.getTime - nbDay * 1000 * 60 * 60 * 24))
  var endDate: Date = GetArgs.dateAtMidnight(today)
  var callCenter: String = "Excelia"
  var client: String = _
  var clientUuid: String = _
  var path: String = "."
  var suffix: String = _
  var debugMode: Boolean = false
  var testMode: Boolean = false

  readParams()


  private def readParams(): Unit = {
    val n: Int = args.length
    var i: Int = 0
    var ip1: Int = 0
    var currentParam: String = ""
    var nextParam: String = ""

//    println("nargs:" + n)
    //  for (arg <- args) println(arg)
    i = 0
    while (i < n) {
      currentParam = args(i)
      ip1 = i + 1
      nextParam = if (ip1 < n) args(ip1) else ""
      println("currentParam:"+currentParam+", nextParam:"+nextParam)
      if (currentParam.equals("-db")) {
        if (ip1 < n) {
          dbType = nextParam
          i = ip1
        }
        else {
          throw new GetArgsException("ERREUR : Serveur MongoDb non défini")
        }
      } else if (currentParam.equals("-b")) {
        if (ip1 < n) {
          try {
            begDate = ddmmyyyyFormat.parse(nextParam)
            i = ip1
          } catch {
            case exception: Exception => throw new GetArgsException("ERREUR : Mauvaise date -b " + nextParam)
          }
        }
        else {
          throw new GetArgsException("ERREUR : Date de début non définie")
        }
      } else if (currentParam.equals("-e")) {
        if (ip1 < n) {
          try {
            endDate = ddmmyyyyFormat.parse(nextParam)
            i = ip1
          } catch {
            case exception: Exception => throw new GetArgsException("ERREUR : Mauvaise date -e " + nextParam)
          }
        }
        else {
          throw new GetArgsException("ERREUR : Date de fin non définie")
        }
      } else if (currentParam.equals("-n")) {
        if (ip1 < n) {
          try {
            nbDay = nextParam.toInt
            i = ip1
          } catch {
            case exception: Exception => throw new GetArgsException("ERREUR : Il faut un entier pour -n " + nextParam)
          }
        }
        else {
          throw new GetArgsException("ERREUR : Nombre de jours non défini")
        }
      } else if (currentParam.equals("-cc")) {
        if (ip1 < n) {
          callCenter = nextParam
          i = ip1
        }
        else {
          throw new GetArgsException("ERREUR : Centre d'appel non défini")
        }
      } else if (currentParam.equals("-client")) {
        if (ip1 < n) {
          client = nextParam
          i = ip1
        }
        else {
          throw new GetArgsException("ERREUR : Client non défini")
        }
      } else if (currentParam.equals("-clientUuid")) {
        if (ip1 < n) {
          clientUuid = nextParam
          i = ip1
        }
        else {
          throw new GetArgsException("ERREUR : Uuid client non défini")
        }
      } else if (currentParam.equals("-path")) {
        if (ip1 < n) {
          path = nextParam
          i = ip1
        }
        else {
          throw new GetArgsException("ERREUR : chemin non défini")
        }
      } else if (currentParam.equals("-suffix")) {
        if (ip1 < n) {
          suffix = nextParam
          i = ip1
        }
        else {
          throw new GetArgsException("ERREUR : Uuid client non défini")
        }
      } else if (currentParam.equals("-d")) {
        debugMode = true
      } else if (currentParam.equals("-t")) {
        testMode = true
      } else {
        usage()
        throw new GetArgsException("ERREUR : Mauvais paramètre " + currentParam)
      }
      i += 1
    }

    if (begDate.after(endDate))
      throw new GetArgsException("ERREUR : La date de début " + ddmmyyyyFormat.format(begDate) +
                  " doit être antérieure à la date de fin " + ddmmyyyyFormat.format(endDate))
    //  readParams()
  }

  /**
   * Retourne l'objet sous forme textuelle
   *
   * @return l'objet sous forme textuelle
   */
  override def toString: String =
    "GetArgs:{" +
      "dbType:" + dbType +
      ", begDate:" + ddmmyyyyFormat.format(begDate) +
      ", endDate:" + ddmmyyyyFormat.format(endDate) +
      ", nbDay:" + nbDay +
      ", callCenter:" + callCenter +
      ", client:" + client +
      ", clientUuid:" + clientUuid +
      ", debugMode:" + debugMode +
      ", testMode:" + testMode +
      "}"

  /**
   * Affiche comment utiliser le programme
   */
  private def usage(): Unit = {
    println("scala GetArgs [-db db] [[-b début] [-e fin]|-n nbJour]\n\t\t[-cc callCenter] [-client client|-clientUuid uuid]\n\t\t[-p path] [-s suffixe] [-d] [-t]")
  }
}

/**
 * Pour ajouter des paramètres en ligne de commande via SBT
 * Depuis Intellij :
 * Cliquer sur Run, Edit configurations..., Application, GetArgs, Program arguments
 * Cliquer sur OK
 */
object GetArgs {
  def main(args: Array[String]) {
    println("Instanciation d'un objet GetArgs ...")
    try {
      val getArgs: GetArgs = new GetArgs(args)
      println(getArgs)
      println("Instanciation effectuée.")
    }
    catch {
      case ex: GetArgsException => println("ERREUR : Problème lors de l'instanciation de GetArgs")
    }
  }

  def dateAtMidnight(date: Date):Date = {
    var dateAtMidnigth:Calendar = new GregorianCalendar()

    dateAtMidnigth.setTime(date)
    dateAtMidnigth.set(Calendar.HOUR_OF_DAY, 0)
    dateAtMidnigth.set(Calendar.MINUTE, 0)
    dateAtMidnigth.set(Calendar.SECOND, 0)
    dateAtMidnigth.set(Calendar.MILLISECOND, 0)

    dateAtMidnigth.getTime
  }

}