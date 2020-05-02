package com.anstel.getargs

import java.util.{Calendar, Date, GregorianCalendar}

import org.junit.Assert._
import org.junit._

class GetArgsTest {

  var args: Array[String] = _
  var getArgs: GetArgs = _
  var today:Date = _

  @Before def makeArgs(): Unit = {
    args = Array.empty
    getArgs = new GetArgs(args)
    today=GetArgs.dateAtMidnight(new Date())
  }

  @Test def dbTypeShouldBeDev(): Unit = {
    assertEquals("dev", getArgs.dbType)
  }

  @Test def nbDayShouldBe7(): Unit = {
    assertEquals(7, getArgs.nbDay)
  }

  @Test def begDateShouldBeNotNull(): Unit = {
    assertNotNull(getArgs.begDate)
  }

  @Test def begDateShouldBeTodayMinusNbDay(): Unit = {
    var calendar:Calendar = new GregorianCalendar()
    var begDate:Date = new Date()

    calendar.setTime(today)
    calendar.add(Calendar.DAY_OF_YEAR, - getArgs.nbDay)
    begDate.setTime(calendar.getTimeInMillis)

    assertEquals(begDate, getArgs.begDate)
  }

  @Test def endDateShouldBeNotNull(): Unit = {
    assertNotNull(getArgs.endDate)
  }

  @Test def endDateShouldBeToday(): Unit = {
    assertEquals(today, getArgs.endDate)
  }

  @Test def callCenterShouldBeExcelia(): Unit = {
    assertEquals("Excelia", getArgs.callCenter)
  }

  @Test def clientShouldBeNull(): Unit = {
    assertNull(getArgs.client)
  }

  @Test def clientUuidShouldBeNull(): Unit = {
    assertNull(getArgs.clientUuid)
  }

  @Test def pathShouldBeDot(): Unit = {
    assertEquals(".", getArgs.path)
  }

  @Test def suffixShouldBeUndefined(): Unit = {
    assertNull(getArgs.suffix)
  }

  @Test def debugModeShouldBeFalse(): Unit = {
    assertFalse(getArgs.debugMode)
  }

  @Test def testModeShouldBeFalse(): Unit = {
    assertFalse(getArgs.testMode)
  }
}
