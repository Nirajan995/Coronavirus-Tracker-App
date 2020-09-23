package org.nirajan.coronavirustracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.nirajan.coronavirustracker.models.LocationStats;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronaVirusDataService {
	
	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> totalStats=new ArrayList<>();
	
	public List<LocationStats> getTotalStats() {
		return totalStats;
	}
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> stats=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		StringReader reader=new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records) {
			LocationStats locationStats=new LocationStats();
		    String state = record.get("Province/State");
		    String country=record.get("Country/Region");
		    int latestCases=Integer.parseInt(record.get(record.size()-1));
		    int previousDayCase=Integer.parseInt(record.get(record.size()-2));
		    locationStats.setStates(state);
		    locationStats.setCountry(country);
		    locationStats.setLatestTotalCases(latestCases);
		    locationStats.setDiffFromPreviousDay(latestCases-previousDayCase);
		    stats.add(locationStats);
		    
		}
		this.totalStats=stats;
	}
}
