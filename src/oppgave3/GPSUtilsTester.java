package oppgave3;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import oppgave1.GPSPoint;
import org.junit.jupiter.api.Test;

public class GPSUtilsTester {

    @Test
    public void test_printDouble() {
        assertEquals("1.35",GPSUtils.formatDouble(1.346));
    }

    @Test
    public void testformatTime() {
        assertEquals("00:00:09",GPSUtils.formatTime(9));
        assertEquals("00:01:22",GPSUtils.formatTime(82));
        assertEquals("03:02:01",GPSUtils.formatTime(60 * 60 * 3 + 60 * 2 + 1));
    }

    @Test
    public void testFindMax() {
        double[] damiddle = { 1.0, 2.0, 7.0, 3.0};
        double[] dastart =  { 9.0, 1.0, 2.0, 6.0};
        double[] daend =    { 1.0, 2.0, 7.0, 10.0};


        assertEquals(7,GPSUtils.findMax(damiddle),0);
        assertEquals(9,GPSUtils.findMax(dastart),0);
        assertEquals(10,GPSUtils.findMax(daend),0);

    }

    @Test
    public void testFindMin() {
        double[] dastart = { 1.0, 2.0, 7.0, 3.0};
        double[] damiddle =  { 9.0, 1.0, 2.0, 6.0};
        double[] daend =    { 2.0, 2.0, 7.0, 1.0};

        assertEquals(1,GPSUtils.findMin(damiddle),0);
        assertEquals(1,GPSUtils.findMin(dastart),0);
        assertEquals(1,GPSUtils.findMin(daend),0);

    }

    @Test
    public void testDistanceLong1() {

        GPSPoint g1 = new GPSPoint(1,60.385390, 5.217217,0);
        GPSPoint g2 = new GPSPoint(1,60.379527, 5.3227322,0);

        assertEquals( 5835, GPSUtils.distance(g1,g2), 1.0);

    }

    @Test
    public void testDistanceLong2() {

        GPSPoint g1 = new GPSPoint(1,60.385390, 5.217217,0);
        GPSPoint g2 = new GPSPoint(1,60.379527, 5.3227322,0);

        assertEquals(5835, GPSUtils.distance(g2,g1), 1.0);

    }

    @Test
    public void testDistanceShort1() {

        GPSPoint g1 = new GPSPoint(1,60.385390, 5.217217,0);
        GPSPoint g2 = new GPSPoint(1,60.376988, 5.227082,0);

        assertEquals(1080, GPSUtils.distance(g1,g2), 1.0);
    }

    @Test
    public void testDistanceShort2() {

        GPSPoint g1 = new GPSPoint(1,60.376988, 5.227082,0);
        GPSPoint g2 = new GPSPoint(1,60.385390, 5.217217,0);

        assertEquals( 1080, GPSUtils.distance(g1,g2), 1.0);
    }

    @Test
    public void testDistanceZero() {

        GPSPoint g = new GPSPoint(1,60.376988, 5.227082,0);

        assertEquals(0, GPSUtils.distance(g,g), 0.01);
    }

    @Test
    public void testSpeed() {

        GPSPoint g1 = new GPSPoint(0,60.385390, 5.217217,0);
        GPSPoint g2 = new GPSPoint(10,60.376988, 5.227082,0);

        assertEquals((108.0 * 60 * 60)/1000 ,GPSUtils.speed(g1,g2),1.0);
    }

    @Test
    public void testgetLatitudes() {

        GPSPoint[] gs = new GPSPoint[2];
        double[] res = {60.385390,60.376988};

        gs[0] = new GPSPoint(0,60.385390, 5.27217,0);
        gs[1] = new GPSPoint(10,60.376988, 5.227082,0);

        // TODO: better Arrays comparison in Junit
        assertTrue(Arrays.equals(res,GPSUtils.getLatitudes(gs)));
    }

    @Test
    public void testgetLongitudes() {

        GPSPoint[] gs = new GPSPoint[2];
        double[] res = {5.217217,5.227082};

        gs[0] = new GPSPoint(0,60.385390, 5.217217,0);
        gs[1] = new GPSPoint(10,60.376988, 5.227082,0);

        // TODO: better Arrays comparison in Junit
        assertTrue(Arrays.equals(res,GPSUtils.getLongitudes(gs)));
    }
}
