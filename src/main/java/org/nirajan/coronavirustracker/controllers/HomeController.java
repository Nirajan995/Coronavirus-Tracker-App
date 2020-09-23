package org.nirajan.coronavirustracker.controllers;

import java.util.List;

import org.nirajan.coronavirustracker.models.LocationStats;
import org.nirajan.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@Autowired
	private CoronaVirusDataService service;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats=service.getTotalStats();
		int totalCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		model.addAttribute("localStats", allStats);
		model.addAttribute("totalCasesReported",totalCases);
		return "home";
	}
}
