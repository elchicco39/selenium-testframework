package besondereTestklassen;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaygroundtestX02 {

	@Test(groups = { "komplex", "falsch" })
  public void einXTestDerFalschIst01() {
	  Assert.assertEquals(true, false);
  }

}
