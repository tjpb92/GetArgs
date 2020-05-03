package com.anstel.libExcelScalaTest

import java.io.FileNotFoundException

import com.anstel.libUtilsScala.GetArgsException
import com.anstel.libUtilsScala.ApplicationProperties
import org.junit.{Before, Test}
import org.junit.Assert._

/**
 * Jeux de tests pour la classe GetArgs
 *
 * @author Thierry Baribaud
 * @version 1.06
 */
class ApplicationPropertiesTest {

  var applicationProperties: ApplicationProperties = _

  @Before
  def makeApplicationProperties() {
    applicationProperties = new ApplicationProperties("GetArgs.prop")
  }

  @Test
  def applicationPropertiesShouldBeNotNull(): Unit = {
    assertNotNull(applicationProperties)
  }

  @Test
  def databasePropertyShouldBeDb(): Unit = {
    assertEquals("db", applicationProperties.get("mongodb.prod.database"))
  }

  @Test(expected = classOf[FileNotFoundException])
  def fileNotFoundException(): Unit = {
    val applicationProperties : ApplicationProperties = new ApplicationProperties("BadFile.prop")
  }
}
