package oppgave3;

import static java.lang.Math.*;

import todo.TODO;
import oppgave1.GPSPoint;

import java.text.DecimalFormat;

public class GPSUtils {

    public static double findMax(double[] da) {

        double max;

        max = da[0];

        for (double d : da) {
            if (d > max) {
                max = d;
            }
        }
        return max;
    }

    public static double findMin(double[] da) {

        double min;

        min = da[0];

        for (double d : da) {
            if (d < min) {
                min = d;
            }
        }

        return min;

    }

    public static double[] getLatitudes(GPSPoint[] gpspoints) {

        double[] latitudes = new double[gpspoints.length];
        for(int i = 0; i < gpspoints.length; i++){
            latitudes[i] = gpspoints[i].getLatitude();
        }

        return latitudes;
    }

    public static double[] getLongitudes(GPSPoint[] gpspoints) {

        double[] longitudes = new double[gpspoints.length];
        for(int i = 0; i < gpspoints.length; i++){
            longitudes[i] = gpspoints[i].getLongitude();
        }

        return longitudes;

    }

    private static int R = 6371000; 

    public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

        double d, a, c;
        double latitude1, longitude1, latitude2, longitude2;
        double distanceLat, distanceLong;

        // TODO - START
        latitude1 = Math.toRadians(gpspoint1.getLatitude());
        longitude1 = Math.toRadians(gpspoint1.getLongitude());
        latitude2 = Math.toRadians(gpspoint2.getLatitude());
        longitude2 = Math.toRadians(gpspoint2.getLongitude());

        distanceLat = latitude1 - latitude2;
        distanceLong = longitude1 - longitude2;

        a = Math.pow(sin(distanceLat/2),2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(distanceLong/2),2);
        c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        d = R * c;

        return d;

    }

    public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

        int secs = gpspoint2.getTime() - gpspoint1.getTime();
        double distance = distance(gpspoint1,gpspoint2);
        double speed = Math.abs(distance / secs) * 3.6;
        return speed;
    }

    public static String formatTime(int secs) {

        String timestr;
        String TIMESEP = ":";
        String seconds, minutes, hours;


        seconds = String.valueOf(secs%60);
        minutes = String.valueOf(secs%3600/60);
        hours = String.valueOf(secs/3600);

        if(Integer.parseInt(hours) < 10){
            hours = "0" + hours;
        }
        if(Integer.parseInt(minutes) < 10){
            minutes = "0" + minutes;
        }
        if(Integer.parseInt(seconds) < 10){
            seconds = "0" + seconds;
        }
        timestr = hours + TIMESEP + minutes + TIMESEP + seconds;
        return timestr;
      
        
    }
    private static int TEXTWIDTH = 10;

    public static String formatDouble(double d) {

        String str;


        str = String.valueOf(Math. round(d*100.0)/100.0);

        return str;
    }
}
