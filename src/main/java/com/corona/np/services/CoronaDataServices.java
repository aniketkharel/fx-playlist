package com.corona.np.services;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.corona.np.models.LocationStats;

@Service
public class CoronaDataServices {

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    @PostConstruct
    @Scheduled(cron = "10 * * * * *")
    public void getDataFromUrl() throws IOException, InterruptedException {

        //create new list
        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());

        StringReader csvReader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);



        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size()-1));
            int prevDay = Integer.parseInt(record.get(record.size()-2));
            locationStats.setLatestTotalCases(latestCases);
            locationStats.setDifferenceFromPreviousDay(latestCases-prevDay);
            System.out.println(locationStats.toString());
            newStats.add(locationStats);
        }

        this.allStats = newStats;

    }

}
