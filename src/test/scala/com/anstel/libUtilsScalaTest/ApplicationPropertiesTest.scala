package com.anstel.libUtilsScalaTest

import java.io.FileNotFoundException

import com.anstel.libUtilsScala.ApplicationProperties
import org.junit.Assert._
import org.junit.Test
import scala.util.{Failure, Success, Try}

/**
 * Jeux de tests pour la classe GetArgs
 *
 * @author Thierry Baribaud
 * @version 1.07
 */
class ApplicationPropertiesTest {

  var applicationProperties: ApplicationProperties = new ApplicationProperties("GetArgs_Example.prop")
  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  //  @Before
  //  def makeApplicationProperties() {
  //    applicationProperties = new ApplicationProperties("GetArgs_Example.prop")
  //  }

  @Test
  def applicationPropertiesShouldBeNotNull(): Unit = {
    assertNotNull(applicationProperties)
  }

//  @Test
//  def databasePropertyShouldBeDb(): Unit = {
//    assertEquals("db", applicationProperties.getPropertyValue("dev.mongodb.database") match {
//      case Left(value) => value
//      case Right(value) => value
//    })
//  }
//
//  @Test
//  def unknownProperty(): Unit = {
//    assertEquals("ERREUR", applicationProperties.getPropertyValue("dev.mongodb.unknown") match {
//      case Left(value) => "ERREUR"
//      case Right(value) => value
//    })
//  }
//
//  @Test
//  def validPortNumber(): Unit = {
//    assertEquals("unEntier", applicationProperties.getPropertyValue("dev.mongodb.port") match {
//      case Left(value) => "ERREUR"
//      case Right(value) => toInt(value) match {
//        case Failure(exception) => "ERREUR"
//        case Success(value) => "unEntier"
//      }
//    })
//  }
//
//  @Test
//  def invalidPortNumber(): Unit = {
//    assertEquals("ERREUR", applicationProperties.getPropertyValue("test.mongodb.port") match {
//      case Left(value) => "autreErreur"
//      case Right(value) => toInt(value) match {
//        case Failure(exception) => "ERREUR"
//        case Success(value) => "unEntier"
//      }
//    })
//  }

  @Test
  def emptyUsername(): Unit = {
    assertEquals("ERREUR", applicationProperties.getPropertyValue("test.mongodb.username") match {
      case Left(value) => "ERREUR"
      case Right(value) => value
    })
  }

  @Test(expected = classOf[FileNotFoundException])
  def fileNotFoundException(): Unit = {
    val applicationProperties: ApplicationProperties = new ApplicationProperties("BadFile.prop")
  }
}
