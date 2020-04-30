package com.anstel.getargs

/**
 * Classe définissant l'objet GetArgs
 * @param args paramètres en ligne de commande
 */
class GetArgs(args :Array[String]) {
  println("nargs:"+ args.length)
}

/**
 * Pour ajouter des paramètres en ligne de commande via SBT
 * Depuis Intellij :
 * Cliquer sur Run, Edit configurations..., Application, GetArgs, Program arguments
 * Cliquer sur OK
 */
object GetArgs {
  def main(args :Array[String]) {
    println("Instanciation d'un objet GetArgs ...")
    val getArgs: GetArgs = new GetArgs(args)
    println("Instanciation effectuée.")
  }
}