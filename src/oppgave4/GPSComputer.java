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


        while(i < gpspoints.length){


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

        // TODO - START

        for(int i = 0; i < gpspoints.length; i++){
            elevationpoints[i] = gpspoints[i].getElevation();
        }
        elevation = findMax(elevationpoints);
        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT
        return elevation;
    }

    // beregn total tiden for hele turen (i sekunder)
    public int totalTime() {
        int time = 0;
        /*
        for (int i = 0; i < gpspoints.length; i++){
            time += gpspoints[i].getTime();
        }
        */
        int start = gpspoints[0].getTime();
        int slutt = gpspoints[gpspoints.length-1].getTime();
        time = slutt - start;

        return time;
        //throw new UnsupportedOperationException(TODO.method());

    }

    // beregn gjennomsnitshastighets mellom hver av gps punktene

    public double[] speeds() {

        // TODO - START		// OPPGAVE - START
        double[] speeds = new double[gpspoints.length-1];

        for(int i = 0; i < gpspoints.length-1; i++){
            if(i +1 > gpspoints.length){
                i++;
                break;
            }else{
                speeds[i] = speed(gpspoints[i], gpspoints[i+1]);
            }

        }

        return speeds;
        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT

    }

    public double maxSpeed() {
        //ambefaler å kalle denne topSpeed istede for maxSpeed

        double maxspeed = 0;

        // TODO - START
        maxspeed = findMax(speeds());
        return maxspeed;
        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT

    }

    public double averageSpeed() {

        double average = 0;
        double total = 0;

        // TODO - START
        //average = (findMax(speeds()) - findMin(speeds())) / speeds().length; Denne linjen burde kunne finne omtrent gjennomsnitt.
        for(int i = 0; i < speeds().length; i++){
            total += speeds()[i];
        }
        average = total / speeds().length;
        return average;
        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT

    }

    /*
     * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
     * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
     * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
     * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
     * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
     * bicycling, >20 mph, racing, not drafting 16.0
     */

    // conversion factor m/s to miles per hour
    public static double MS = 2.236936;

    // beregn kcal gitt weight og tid der kjøres med en gitt hastighet
    public double kcal(double weight, int secs, double speed) {

        double kcal;

        // MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
        double met = 0;
        double speedmph = speed * MS;

        // TODO - START
        if(speedmph < 10){
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
        kcal = met * (secs/3600) * weight;
        return kcal;

        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT

    }

    public double totalKcal(double weight) {

        double totalkcal = 0;

        /*
        // TODO - START
        for(int i = 0; i < gpspoints.length-1; i++){
            totalkcal += kcal(weight,2,speed(gpspoints[i], gpspoints[i+1]));
        }
        return totalkcal;

         */
        throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT

    }

    private static double WEIGHT = 80.0;

    public void displayStatistics() {

        System.out.println("==============================================");

        // TODO - START

        System.out.printf("Total Time\t:\t%s",totalTime());
        System.out.printf("Total distance\t:\t%s",totalDistance());
        System.out.printf("Total elevation\t:\t%s",totalElevation());
        System.out.printf("Max speed\t:\t%s",maxSpeed());
        System.out.printf("Average speed\t:\t%s",averageSpeed());
        System.out.printf("Energy\t:\t%s",totalKcal(WEIGHT));
        //throw new UnsupportedOperationException(TODO.method());

        // TODO - SLUTT
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

