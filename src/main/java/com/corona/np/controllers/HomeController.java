package com.corona.np.controllers;

import com.corona.np.models.LocationStats;
import com.corona.np.services.CoronaDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaDataServices coronaDataServices;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaDataServices.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int prevDaysCasesFound = allStats.stream().mapToInt(stat->stat.getDifferenceFromPreviousDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCase",totalCases);
        model.addAttribute("newCasesFound",prevDaysCasesFound);
        return "home";
    }
}
