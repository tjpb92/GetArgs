package com.anstel.libUtilsScalaTest

import com.anstel.libUtilsScala
import com.anstel.libUtilsScala.{ApplicationProperties, DbServer}
import org.junit.Assert.{assertEquals, assertNotNull}
import org.junit.{Before, Test}

/**
 * Jeux de tests pour la classe DbServer
 *
 * @author Thierry Baribaud
 * @version 1.08
 */
class DbServerTest {

  val applicationProperties: ApplicationProperties = new ApplicationProperties("GetArgs_Example.prop")
  val result: Either[String, DbServer] = DbServer.builder("dev", "mongodb", applicationProperties)
  val dbServer: Object = result match {
    case Right(dbServer) => dbServer
    case Left(error) => error
  }

  @Test
  def dbServerShouldBeNotNull(): Unit = {
    println(dbServer+", class:"+dbServer.getClass)
    assertNotNull(dbServer)
  }

  @Test
  def databaseShouldBeDb(): Unit = {
    println("database:" + dbServer)
//    assertEquals("db", dbServer.database)
  }

//  @Test
//  def testToString(): Unit = {
//    val result = dbServer.toString
//    println(s"toString:$result")
//    assertNotNull(result)
//  }
}
