package oppgave2;

import todo.TGPSData.*;
import oppgave1.GPSPoint;
import todo.TODO;

import static oppgave2.GPSDataConverter.convert;

public class GPSData {

    private GPSPoint[] gpspoints;
    protected int antall = 0;

    public GPSData(int n) {

    	
        gpspoints = new GPSPoint[n];

    }

    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;

    }

    protected boolean insertGPS(GPSPoint gpspoint) {

    	
        boolean inserted = false;

        try {

                gpspoints[antall] = gpspoint;
                antall ++;
                inserted = true;

        }catch (Exception e){
            inserted = false;

        }
        return inserted;

    }

    public boolean insert(String time, String latitude, String longitude, String elevation) {

        gpspoints[antall] = convert(time,latitude,longitude,elevation);
        antall++;
    	
        return true;


    }

    public void print() {

    	
        System.out.println("====== Konvertert GPS Data - START ======");

        for(int i = 0; i < gpspoints.length; i++){
            System.out.printf("%s",gpspoints[i]);
        }
      
        // System.out.println("====== Konvertert GPS Data - SLUTT ======");

    }
}

