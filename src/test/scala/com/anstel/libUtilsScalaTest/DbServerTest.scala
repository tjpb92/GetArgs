package com.anstel.libUtilsScalaTest

import com.anstel.libUtilsScala.ApplicationProperties
import com.anstel.libUtilsScala.DbServer
import org.junit.Assert.{assertEquals, assertNotNull}
import org.junit.Test

/**
 * Jeux de tests pour la classe DbServer
 *
 * @author Thierry Baribaud
 * @version 1.07
 */
class DbServerTest {

  var applicationProperties: ApplicationProperties = new ApplicationProperties("GetArgs_Example.prop")
  val dbServer: DbServer = new DbServer("test", "mongodb", applicationProperties)

  @Test
  def dbServerShouldBeNotNull(): Unit = {
    assertNotNull(dbServer)
  }

  @Test
  def databaseShouldBeDb(): Unit = {
    assertEquals("db", dbServer.database)
  }

  @Test
  def testToString(): Unit = {
    val result = dbServer.toString
    println(s"toString:$result")
    assertNotNull(result)
  }
}
