package com.anstel.getargs

/**
 * Exception lancée en cas de problème avec un paramètre en ligne
 *
 * @param msg message d'erreur
 */
class GetArgsException(msg: String) extends Exception(msg) {}

/**
 * Classe définissant l'objet GetArgs
 *
 * @param args paramètres en ligne de commande
 * @author Thierry Baribaud.
 * @version 1.03
 */
class GetArgs(args: Array[String]) {

  var dbType: String = "dev"
  var debugMode: Boolean = false
  var testMode: Boolean = false

  readParams()

  private def readParams (): Unit = {
    val n: Int = args.length
    var i: Int = 0
    var ip1: Int = 0
    var currentParam: String = ""
    var nextParam: String = ""

    println ("nargs:" + n)
    //  for (arg <- args) println(arg)
    i = 0
    while (i < n) {
      currentParam = args (i)
      ip1 = i + 1
      nextParam = if (ip1 < n) args (ip1) else ""
      if (currentParam.equals ("-db") ) {
        dbType = nextParam
        i = ip1
      } else if (currentParam.equals ("-d") ) {
        debugMode = true
      } else if (currentParam.equals ("-t") ) {
        testMode = true
      } else {
        usage ()
        throw new GetArgsException ("Bad argument " + currentParam)
      }
      i += 1
    }

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
      println("dbtype:" + getArgs.dbType)
      println("debugMode:" + getArgs.debugMode)
      println("Instanciation effectuée.")
    }
    catch {
      case ex: GetArgsException => println("Problème lors de l'instanciation de GetArgs")
    }
  }
}