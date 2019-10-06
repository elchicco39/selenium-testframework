package testklassen;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Playgroundtest01 {

	@Test(groups = { "einfach", "wahr" })
  public void einTestDerWahrIst01() {
	  Assert.assertEquals(false, false);
  }

	@Test(groups = { "einfach", "wahr" })
  public void einTestDerWahrIst02() {
	  Assert.assertEquals(true, true);
  }

	@Test(groups = { "einfach", "falsch" })
  public void einTestDerFalschIst01() {
	  Assert.assertEquals(false, true);
  }
}
