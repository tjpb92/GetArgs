package com.anstel.libUtilsScala

/**
 * Exception lancée en cas de problème avec un paramètre en ligne
 *
 * @param msg message d'erreur
 * @author Thierry Baribaud.
 * @version 1.06
 */
class GetArgsException(msg: String) extends Exception(msg) {
  println(msg)
}

