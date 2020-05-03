package com.anstel.getargs

import java.time.LocalDate
import com.anstel.libUtilsScala.GetArgsException
import org.junit.Assert._
import org.junit._

/**
 * Jeux de tests pour la classe GetArgs
 *
 * @author Thierry Baribaud
 * @version 1.07
 */
class GetArgsTest {

  var args: Array[String] = Array.empty[String]
  var getArgs: GetArgs = new GetArgs(args)
  var today: LocalDate = LocalDate.now()

  //  @Before def makeArgs(): Unit = {
  //    args = Array.empty
  //    getArgs = new GetArgs(args)
  //    today=LocalDate.now()
  //  }

  // ----------- miscellaneous ---------------------------------------
  @Test(expected = classOf[GetArgsException])
  def badParameterShouldFail(): Unit = {
    val args = Array("-bad")
    val getArgs = new GetArgs(args)
  }

  // ----------- dbType ---------------------------------------
  @Test def dbTypeShouldBeDev(): Unit = {
    assertEquals("dev", getArgs.dbType)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedDbTypeShouldFail(): Unit = {
    val args = Array("-db")
    val getArgs = new GetArgs(args)
  }

  // ----------- nbDay ---------------------------------------
  @Test def nbDayShouldBe7(): Unit = {
    assertEquals(7, getArgs.nbDay)
  }

  @Test
  def definedNbDayShouldSuccess(): Unit = {
    val n: Int = 10
    val args = Array("-n", n.toString)
    val getArgs = new GetArgs(args)
    assertEquals(n, getArgs.nbDay)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedNbDayShouldFail(): Unit = {
    val args = Array("-n")
    val getArgs = new GetArgs(args)
  }

  @Test(expected = classOf[GetArgsException])
  def nonNumericNbDayShouldFail(): Unit = {
    val args = Array("-n", "tooBad")
    val getArgs = new GetArgs(args)
  }

  // ----------- begDate ---------------------------------------
  @Test def begDateShouldBeNotNull(): Unit = {
    assertNotNull(getArgs.begDate)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedBegDateShouldFail(): Unit = {
    val args = Array("-b")
    val getArgs = new GetArgs(args)
  }

  @Test def begDateShouldBeTodayMinusNbDay(): Unit = {
    var begDate: LocalDate = LocalDate.now.minusDays(getArgs.nbDay)
    assertEquals(begDate, getArgs.begDate)
  }

  @Test
  def definedBegDateShouldSuccess(): Unit = {
    val args = Array("-b", today.format(GetArgs.ddmmyyyyFormat))
    val getArgs = new GetArgs(args)
    assertEquals(today, getArgs.begDate)
  }

  @Test(expected = classOf[GetArgsException])
  def badBegDateShouldFail(): Unit = {
    val args = Array("-b", "badDate")
    val getArgs = new GetArgs(args)
  }

  // ----------- endDate ---------------------------------------
  @Test def endDateShouldBeNotNull(): Unit = {
    assertNotNull(getArgs.endDate)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedEngDateShouldFail(): Unit = {
    val args = Array("-e")
    val getArgs = new GetArgs(args)
  }

  @Test def endDateShouldBeToday(): Unit = {
    assertEquals(today, getArgs.endDate)
  }

  @Test
  def definedEngDateShouldSuccess(): Unit = {
    val args = Array("-e", today.format(GetArgs.ddmmyyyyFormat))
    val getArgs = new GetArgs(args)
    assertEquals(today, getArgs.endDate)
  }

  @Test(expected = classOf[GetArgsException])
  def badEndDateShouldFail(): Unit = {
    val args = Array("-e", "badDate")
    val getArgs = new GetArgs(args)
  }

  // ----------- callCenter ---------------------------------------
  @Test def callCenterShouldBeExcelia(): Unit = {
    assertEquals("Excelia", getArgs.callCenter)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedCallCenterShouldFail(): Unit = {
    val args = Array("-cc")
    val getArgs = new GetArgs(args)
  }

  @Test
  def definedCallCenterShouldSuccess(): Unit = {
    val callCenter: String = "Anstel"
    val args = Array("-cc", callCenter)
    val getArgs = new GetArgs(args)
    assertEquals(callCenter, getArgs.callCenter)
  }

  // ----------- client ---------------------------------------
  @Test def clientShouldBeEmpty(): Unit = {
    assertTrue(getArgs.client.isEmpty)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedClientShouldFail(): Unit = {
    val args = Array("-client")
    val getArgs = new GetArgs(args)
  }

  @Test
  def definedClientShouldSuccess(): Unit = {
    val client: String = "Anstel"
    val args = Array("-client", client)
    val getArgs = new GetArgs(args)
    assertEquals(client, getArgs.client)
  }

  // ----------- clientUuid ---------------------------------------
  @Test def clientUuidShouldBeEmpty(): Unit = {
    assertTrue(getArgs.clientUuid.isEmpty)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedClientUuidShouldFail(): Unit = {
    val args = Array("-clientUuid")
    val getArgs = new GetArgs(args)
  }

  @Test
  def definedClientUuidShouldSuccess(): Unit = {
    val clientUuid: String = "03481b72-8e09-43b8-b448-3e487d92fa79"
    val args = Array("-clientUuid", clientUuid)
    val getArgs = new GetArgs(args)
    assertEquals(clientUuid, getArgs.clientUuid)
  }

  //TODO ATTENTION : prévoir de vérifier la bonne syntaxe de UUID

  // ----------- path ---------------------------------------
  @Test def pathShouldBeDot(): Unit = {
    assertEquals(".", getArgs.path)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedPathShouldFail(): Unit = {
    val args = Array("-path")
    val getArgs = new GetArgs(args)
  }

  @Test
  def definedPathShouldSuccess(): Unit = {
    val path: String = "/temp"
    val args = Array("-path", path)
    val getArgs = new GetArgs(args)
    assertEquals(path, getArgs.path)
  }

  // ----------- suffix ---------------------------------------
  @Test def suffixShouldBeEmpty(): Unit = {
    assertTrue(getArgs.suffix.isEmpty)
  }

  @Test(expected = classOf[GetArgsException])
  def undefinedSuffixShouldFail(): Unit = {
    val args = Array("-suffix")
    val getArgs = new GetArgs(args)
  }

  @Test
  def definedSuffixShouldSuccess(): Unit = {
    val suffix: String = "fw25"
    val args = Array("-suffix", suffix)
    val getArgs = new GetArgs(args)
    assertEquals(suffix, getArgs.suffix)
  }

  // ----------- debugMode ---------------------------------------
  @Test def debugModeShouldBeFalse(): Unit = {
    assertFalse(getArgs.debugMode)
  }

  @Test
  def definedDebugModeShouldSuccess(): Unit = {
    val args = Array("-d")
    val getArgs = new GetArgs(args)
    assertTrue(getArgs.debugMode)
  }

  // ----------- testMode ---------------------------------------
  @Test def testModeShouldBeFalse(): Unit = {
    assertFalse(getArgs.testMode)
  }

  @Test
  def definedTestModeShouldSuccess(): Unit = {
    val args = Array("-t")
    val getArgs = new GetArgs(args)
    assertTrue(getArgs.testMode)
  }
}
