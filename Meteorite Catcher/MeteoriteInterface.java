/**
 * An interface representing a meteorite object with various attributes.
 */
public interface MeteoriteInterface {

    /**
     * Get the name of the meteorite.
     *
     * @return The name of the meteorite.
     */
    public String getName();

    /**
     * Check if the meteorite's fall has been observed.
     *
     * @return `true` if the meteorite has fallen, `false` otherwise.
     */
    public boolean getObservedFall();

    /**
     * Get the longitude of the meteorite's impact location.
     *
     * @return The longitude of the meteorite's impact location.
     */
    public double getLongitude();

    /**
     * Get the latitude of the meteorite's impact location.
     *
     * @return The latitude of the meteorite's impact location.
     */
    public double getLatitude();

    /**
     * Get the mass of the meteorite.
     *
     * @return The mass of the meteorite. Returns -1 when the mass is unlisted.
     */
    public double getMass();

    /**
     * Compare this meteorite to another meteorite for ordering purposes.
     *
     * @param other The meteorite to compare to.
     * @return A negative value if this meteorite is less than the other,
     *         a positive value if it's greater, and 0 if they are equal.
     */
    public int compareTo(Meteorite other);
}
