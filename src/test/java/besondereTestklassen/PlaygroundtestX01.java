package besondereTestklassen;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaygroundtestX01 {

	@Test(groups = { "komplex", "wahr" })
  public void einXTestDerWahrIst01() {
	  Assert.assertEquals(false, false);
  }

}
