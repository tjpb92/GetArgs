package com.anstel.libUtilsScala

import java.io.{FileInputStream, FileNotFoundException}
import java.util.Properties

import scala.io.Source

/**
 * Classe définissant l'objet ApplicationProperties
 * ApplicationProperties is a class that derives from Properties class and
 * that get its values from a file whose name is passed as parameter.
 * This file is supposed to be in project directory.
 * If an error is encountered, an IOException is thrown.
 *
 * @param filename of the file that contains application properties
 * @author Thierry Baribaud.
 * @version 1.06
 */
class ApplicationProperties(filename: String) extends Properties {

  println("Creating ApplicationProperties object ...")

  println("Reading " + filename + " ...")
  load(new FileInputStream(filename))
  println("ApplicationProperties read.")
}
