package dev.pimous.l2s3sdn.td1;

import dev.pimous.javautils.AutoToString;

/** Represents a geographical point (Geographic 3D coordinates) relative to
 * another separed by a delta of time.
 * @author Xibitol
 */
public class Point extends AutoToString{

	/** In kilometers. Source: {@link
	 * https://en.wikipedia.org/w/index.php?title=Earth_radius&oldid=1243786232
	 * }.
	 */
	private static final double EARTH_AVERAGE_RADIUS = 6371;

	private double longitude;
	private double latitude;
	private double altitude;
	private int deltatime;

	/** Creates a geographic point.
	 * @param longitude Longitude of the point.
	 * @param latitude Latitude of the point.
	 */
	public Point(
		double longitude, double latitude, double altitude,
		int deltatime
	){
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.deltatime = deltatime;
	}

	// GETTERS
	/** Retrieves the longitude of the point.
	 * @return Geographic point's longitude in degrees.
	 */
	public double getLongitude(){ return longitude; }
	/** Retrieves the longitude of the point.
	 * @return Geographic point's longitude in radians.
	 */
	public double getLongitudeRad(){ return Math.toRadians(getLongitude()); }
	/** Retrieves the latitude of the point.
	 * @return Geographic point's latitude in degrees.
	 */
	public double getLatitude(){ return latitude; }
	/** Retrieves the latitude of the point.
	 * @return Geographic point's latitude in radians.
	 */
	public double getLatitudeRad(){ return Math.toRadians(getLatitude()); }
	/** Retrieves the altitude of the point.
	 * @return Geographic point's altitude in meters.
	 */
	public double getAltitude(){ return altitude; }
	/** Retrieves the delta of time separating this point to another.
	 * @return Point delta of time in seconds.
	 */
	public int getDeltatime(){ return deltatime; }

	/** Calculates the distance between this {@link Point} and another.
	 * @param other Another to calculate a distance from.
	 * @return The distance between this {@link Point} and {@code other} in
	 * kilometers.
	 */
	public double distance(Point other){
		return EARTH_AVERAGE_RADIUS*Math.acos(
			Math.sin(getLatitudeRad())*Math.sin(other.getLatitudeRad())
			+ Math.cos(getLatitudeRad())*Math.cos(other.getLatitudeRad())
				*Math.cos(getLongitudeRad() - other.getLongitudeRad())
		);
	}
}