package oppgave4;


import  todo.TODO;
import oppgave1.GPSPoint;
import oppgave2.GPSData;
import static oppgave2.GPSDataConverter.toSeconds;
import static oppgave3.GPSUtils.*;

import oppgave2.GPSDataFileReader;
import oppgave3.GPSUtils;

public class GPSComputer {

    private GPSPoint[] gpspoints;

    public GPSComputer(String filename) {

        GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
        gpspoints = gpsdata.getGPSPoints();

    }

    public GPSComputer(GPSPoint[] gpspoints) {
        this.gpspoints = gpspoints;
    }

    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;
    }

    public double totalDistance() {

        double distance = 0;
        int i = 0;
        while(i < gpspoints.length-1){
            distance += distance(gpspoints[i],gpspoints[i+1]);
            i++;
        };
        return distance;
    }

    // beregn totale høydemeter (i meter)
    // denne leiter ikke etter den totale høyden, den leiter etter det høyeste punkte som er på ruten.
    public double totalElevation() {

        double elevation = 0;
        double[] elevationpoints = new double[gpspoints.length];

        for(int i = 0; i < gpspoints.length; i++){
            elevationpoints[i] = gpspoints[i].getElevation();
        }
        elevation = findMax(elevationpoints);

        return elevation;
    }

    // beregn total tiden for hele turen (i sekunder)
    public int totalTime() {
        int time = 0;

        int start = gpspoints[0].getTime();
        int slutt = gpspoints[gpspoints.length-1].getTime();
        time = slutt - start;

        return time;

    }

    // beregn gjennomsnitshastighets mellom hver av gps punktene

    public double[] speeds() {

        double[] speeds = new double[gpspoints.length-1];

        for(int i = 0; i < gpspoints.length-1; i++){

                speeds[i] = speed(gpspoints[i], gpspoints[i+1]);
        }

        return speeds;

    }

    public double maxSpeed() {

        double maxspeed = 0;

        maxspeed = findMax(speeds());
        return maxspeed;

    }

    public double averageSpeed() {

        double average = 0;
        // skal ha endelig svar i km/t så må bruke 3.6 for å omgjøre til km/t fra m/s. dette burde stå i oppgave tekst.
        average = (totalDistance() / totalTime()) * 3.6;
        return average;

    }

    // conversion factor m/s to miles per hour
    public static double MS = 2.236936;

    // beregn kcal gitt weight og tid der kjøres med en gitt hastighet
    public double kcal(double weight, int secs, double speed) {

        double kcal;

        // MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
        double met = 0;
        double speedmph = speed * MS;
        double time = secs/3600;

        // TODO - START
        if(speedmph <= 10){
            met = 4;
        }else if(speedmph <= 12){
            met = 6;
        }else if(speedmph <= 14){
            met = 8;
        }else if(speedmph <= 16){
            met = 10;
        }else if(speedmph <= 20){
            met = 12;
        }else{
            met = 16;
        }
        kcal = met * time * weight;
        return kcal;
    }

    public double totalKcal(double weight) {


        double totalkcal = 0;
        for(int i = 0; i < gpspoints.length-1; i++){
            double speed = speed(gpspoints[i], gpspoints[i+1]);
            int time = gpspoints[i+1].getTime() - gpspoints[i].getTime();
            totalkcal += kcal(weight,time,speed);
        }
        return totalkcal;
    }

    private static double WEIGHT = 80.0;

    public void displayStatistics() {

        System.out.println("==============================================");

        // TODO - START

        System.out.printf("Total Time\t\t:\t%s \n",formatTime(totalTime()));
        System.out.printf("Total distance\t:\t%s \n",totalDistance());
        System.out.printf("Total elevation\t:\t%s\n",totalElevation());
        System.out.printf("Max speed\t\t:\t%s\n",maxSpeed());
        System.out.printf("Average speed\t:\t%s\n",averageSpeed());
        System.out.printf("Energy\t\t\t:\t%s\n",totalKcal(WEIGHT));
        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT
        System.out.println("==============================================");
        /*==============================================
        Total Time     :   00:36:35
        Total distance :      13.74 km
        Total elevation:     210.60 m
        Max speed      :      47.98 km/t
        Average speed  :      22.54 km/t
        Energy         :     744.40 kcal
         ==============================================*/
    }

}

