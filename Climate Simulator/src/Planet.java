import java.text.DecimalFormat;

public class Planet {

	private static final int EARTH_INSOLATION = 1390; // w/ m^2
	
	private static final String [] names = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Neptune", "Uranus", "Pluto"};
	// from http://www.starhop.com/library/pdf/studyguide/high/brsp-19solarint.pdf
	private static final double [] distances =  {0.387, 0.723, 1.0, 1.524, 5.203, 9.539, 19.189, 30.06, 39.439};
	private static final double [] albedos = {0.10, 0.75, 0.30, 0.25, 0.52, 0.76, 0.51, 0.35, 0.4};
	private static final DecimalFormat decimalformat = new DecimalFormat ("0.0");
	private static final double SB_CONSTANT = .0000000567; // 5.67 x 10^-8
	
	private String myName;
	private double myDistance; // in AU
	private double myAlbedo; // fraction of sunlight reflected
	
	
	public Planet(String name, double distance, double albedo) {
		
		myName = name;
		myDistance = distance;
		myAlbedo = albedo;
		
	}
	
	public double getInsolation ()
	{
		double relativeSolarIntensity = 1 / (myDistance * myDistance); // fraction relative to Earth
		return EARTH_INSOLATION * relativeSolarIntensity;	
	}
	
	public double getAverageSurfaceInsolation()
	{
		double temp = (1 - myAlbedo) * this.getInsolation() / 4;
		return temp;
		
	}
	
	public double getPredictedTemp()
	{
		double tempInK = Math.pow(getAverageSurfaceInsolation() / SB_CONSTANT, 0.25);
		return tempInK;
		
	}
	
	public String toString ()
	{
		String s = myName + "\nDistance: " + myDistance + " (AU)";
		s += "\nAlbedo: " + myAlbedo;
		s += "\nInsolation: " + decimalformat.format(getInsolation()) + " w/m^2";
		s += "\nAverage Insolation at Surface: " + decimalformat.format((getAverageSurfaceInsolation()));
		double tempInK = getPredictedTemp();
		double tempInC = tempInK - 273;
		s+= "\nPredicted Temperature: " + decimalformat.format(tempInK) + " K or " 
				 + decimalformat.format(tempInC) + " degrees C";
		return s;
	}
	
	public static void main (String [] args)
	{
		Planet [] thePlanets = new Planet [9];
		for (int i = 0; i < thePlanets.length; i++)
		{
			thePlanets[i] = new Planet (names[i], distances[i], albedos[i]);	
		}
		
		for(Planet p: thePlanets)
		{
			System.out.println();
			System.out.println(p);
		}
		
		System.out.println();
		Planet examPlanet = new Planet ("McPlanet", 0.5, 0.5);
		System.out.println(examPlanet);
	}
	


}
