package point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){
    Point p00 = new Point(0, 0);
    Point p34 = new Point(3, 4);
    Point p_3_4 = new Point(-3, -4);
    Point p11 = new Point(1, 1);
    Assert.assertEquals(p00.distance(p00), 0);
    Assert.assertEquals(p34.distance(p34), 0);
    Assert.assertEquals(p00.distance(p34), 5);
    Assert.assertEquals(p00.distance(p_3_4), 5);
    Assert.assertEquals(p00.distance(p11), Math.sqrt(2));
  }
}
