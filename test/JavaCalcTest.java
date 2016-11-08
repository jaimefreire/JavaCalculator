/**
 * Created by jaimefreire on 07.11.16.
 */
import org.junit.Assert;
import org.junit.Test;
public class JavaCalcTest {

    @Test
     public void testSum() {
        Assert.assertEquals(54,JavaCalc.getSum("1+2+3+4+5+6+7+8+9+9"));
    }

//    @Test
//    public void testMulti() {
//        Assert.assertEquals(19,JavaCalc.getMulti("9+2*5"));
//    }

    //  @Test
  //  public void testMultiInverseOrder() {
    //  Assert.assertEquals(19,JavaCalc.getMulti("5*2+9"));
    // }
}
