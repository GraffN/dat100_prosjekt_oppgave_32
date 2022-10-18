package oppgave2;

import todo.TODO;
import oppgave1.GPSPoint;

public class GPSDataConverter {

    private static int TIME_STARTINDEX = 11; 

    public static int toSeconds(String timestr) {
    		
    	int secs; 
    	int hr, min, sec; 
      
        String[] oppdeling = timestr.substring(TIME_STARTINDEX, 19).split(":");
        hr = Integer.parseInt(oppdeling[0]);
        min = Integer.parseInt(oppdeling[1]);
        sec = Integer.parseInt(oppdeling[2]);
        secs = hr * 60 * 60 + min * 60 + sec;

    	return secs;

    }

    public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

        int timePoint = toSeconds(timeStr);
        double latpoint = Double.parseDouble(latitudeStr);
        double longpoint = Double.parseDouble(longitudeStr);
        double elevpoint = Double.parseDouble(elevationStr);

        return new GPSPoint(timePoint, latpoint, longpoint, elevpoint);

        

    }

}
