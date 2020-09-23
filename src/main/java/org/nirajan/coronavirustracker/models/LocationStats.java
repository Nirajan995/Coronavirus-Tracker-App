package org.nirajan.coronavirustracker.models;

public class LocationStats {
	private String states;
	private String country;
	private int latestTotalCases;
	private int diffFromPreviousDay;
	
	public int getDiffFromPreviousDay() {
		return diffFromPreviousDay;
	}
	
	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		this.diffFromPreviousDay = diffFromPreviousDay;
	}
	
	public LocationStats() {
		super();
	}
	
	
	public LocationStats(String states, String country, int latestTotalCases) {
		super();
		this.states = states;
		this.country = country;
		this.latestTotalCases = latestTotalCases;
	}



	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}


    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + states + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
	

	
	
}
