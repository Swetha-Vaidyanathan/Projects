/**
 * This class implements the class of meteorite object.
 */
public class Meteorite implements MeteoriteInterface, Comparable<Meteorite> {
    //private field to store the name of the meteorite.
    private String name;
    //private field to store the longitude of the meteorite.
    private double longitude;
    //private field to store the latitude of the meteorite.
    private double latitude;
    //private field to store the fall of the meteorite.
    private boolean fall;
    //private field to store the mass of the meteorite.
    private double mass;

    /**
     * This constructor assigns variables of meteorite object.
     * @param name
     * @param fall
     * @param longitude
     * @param latitude
     * @param mass
     */
   // public Meteortie(String name, boolean fall, double longitude, double latitude, double mass){
     //   this.name = name;
       // this.fall = fall;
        //this.longitude = longitude;
        //this.latitude = latitude;
        //this.mass = mass;
   // }

    /**
     * This constructor assigns the values of Meteorite object when there is no mass
     * @param name
     * @param fall
     * @param longitude
     * @param latitude
     */
    public Meteorite(String name, boolean fall, double longitude, double latitude, double mass) {
        this.name = name;
        this.fall = fall;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mass = mass;
    }

    public Meteorite(String name, boolean fall, double longitude, double latitude) {
        this.name = name;
        this.fall = fall;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mass = -1;
    }

    /**
     * This constructor assigns the values of the meteor object when there is no latitude or longitude
     * @param name
     * @param fall
     * @param mass
     */
    public Meteorite(String name, boolean fall, double mass) {
        this.name = name;
        this.fall = fall;
        this.longitude = 0.000000000000;
        this.latitude = 0.000000000000;
        this.mass = mass;
    }

   public Meteorite(String name, double reclat, double reclong, boolean fall, double mass) {
        this.name = name;
        this.latitude = reclat;
        this.longitude = reclong;
        this.fall = fall;
        this.mass = mass;
    }



    public String getName(){
        return this.name;
    }

    public boolean getObservedFall(){
        return this.fall;
    }

    public double getLongitude(){
        return this.longitude;
    }
    // return longitude;
    public double getLatitude(){
        return this.latitude;
    }
    // return latitude;
    public double getMass(){
        return this.mass;
    }

    public int compareTo (Meteorite other){
        if(this.getMass() > other.getMass()) {
            return 1;
        } else if (this.getMass() < other.getMass()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
