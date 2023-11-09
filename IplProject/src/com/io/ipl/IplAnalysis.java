package com.io.ipl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




public class IplAnalysis {

	    private static final int MATCH_ID = 0;
	    private static final int SEASON = 1;
	    private static final int WINNER = 10;
	    private static final int BATTING_TEAM = 2;
	    private static final int BOWLER = 8;
	    private static final int WIDE_RUNS = 10;
	    private static final int BYE_RUNS = 11;
	    private static final int LEG_BYE_RUNS = 12;
	    private static final int NO_BALL_RUNS = 13;
	    private static final int EXTRA_RUNS = 16;
	    private static final int TOTAL_RUNS = 17;
	    private static final int Bats_Man=6;
	    private static final int Date=3;
	    private static final int Team1=4;
	    private static final int Team2=5;
	    
	    public static List<Delivery> getDeliveriesData() {
	        List<Delivery> deliveries = new ArrayList<>();
	        try {
	            Scanner scanner = new Scanner(new File("deliveries.csv"));
	            scanner.nextLine(); // Skip the header line
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] deliveryData = line.split(",");
	                Delivery delivery = new Delivery();
	               
	                delivery.setMatchId(deliveryData[MATCH_ID]);
	                delivery.setBattingTeam(deliveryData[BATTING_TEAM]);
	                delivery.setBowler(deliveryData[BOWLER]);
	                delivery.setWideRuns(deliveryData[WIDE_RUNS]);
	                delivery.setByeRuns(deliveryData[BYE_RUNS]);
	                delivery.setLegByeRuns(deliveryData[LEG_BYE_RUNS]);
	                delivery.setNoBallRuns(deliveryData[NO_BALL_RUNS]);
	                delivery.setExtraRuns(deliveryData[EXTRA_RUNS]);
	                delivery.setTotalRuns(deliveryData[TOTAL_RUNS]);
	                delivery.setBatsman(deliveryData[Bats_Man]);
	                deliveries.add(delivery);
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return deliveries;
	    }

	    public static List<Match> getMatchesData() {
	        List<Match> matches = new ArrayList<>();
	        try {
	            Scanner scanner = new Scanner(new File("matches.csv"));
	            scanner.nextLine(); // Skip the header line
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] matchData = line.split(",");
	                Match match = new Match();
	                match.setSeason(matchData[SEASON]);
	                match.setWinner(matchData[WINNER]);
	                match.setDate(matchData[Date]);
	                match.setTeam1(matchData[Team1]);
	                match.setTeam2(matchData[Team2]);
	                matches.add(match);
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return matches;
	    }


	    
	    
	    public static void findRCBMatchDatesAgainstOtherTeams() {
	        List<Match> matchesData = getMatchesData();
	        System.out.println("RCB Match Dates Against Other Teams:");
	        for (Match match : matchesData) {
	            String team1 = match.getTeam1();
	            String team2 = match.getTeam2();
	            String date = match.getDate();

	            if (((team1.equals("Royal Challengers Bangalore") && !team2.equals("Royal Challengers Bangalore"))
	                    || (!team1.equals("Royal Challengers Bangalore") && team2.equals("Royal Challengers Bangalore")))
	                    && date != null) {
	                System.out.println("Date: " + date + ", Team 1: " + team1 + ", Team 2: " + team2);
	            }
	        }
	    }
	    
	    
	    public static void findRunsScoredByViratForYear2015() {
	        List<Delivery> deliveriesData = getDeliveriesData();
	        Map<String, Integer> batsmanRunsMap = new HashMap<>();

	        for (Delivery delivery : deliveriesData) {
	            int matchId = Integer.parseInt(delivery.getMatchId());
	            String batsman = delivery.getBatsman();
	            int totalRuns = Integer.parseInt(delivery.getTotalRuns());

            if (matchId >= 518 && matchId <= 576) { // Matches in the year 2015
	            if (batsmanRunsMap.containsKey(batsman)) {
	                int currentRuns = batsmanRunsMap.get(batsman);
	             
	                batsmanRunsMap.put(batsman, currentRuns + totalRuns);
	            } else {
	                batsmanRunsMap.put(batsman, totalRuns);
	            }
	        }
	        }

	        // Get Virat Kohli's total runs in 2015
	        String viratKohli = "V Kohli";
	        int viratKohliRuns;
	        if (batsmanRunsMap.containsKey(viratKohli)) {
	            viratKohliRuns = batsmanRunsMap.get(viratKohli);
	        } else {
	            viratKohliRuns = 0;
	        }


	        System.out.println("Virat Kohli's total runs in 2015: " + viratKohliRuns);
	    }

	    
	    
	    public static void findTotalMatchesPlayedPerSeason(){
	        List<Match> matchesData = getMatchesData();
	        Map<String, Integer> map = new HashMap<>();

	        for (Match match : matchesData) {
	            String season = match.getSeason();
	            if (map.containsKey(season)) {
	                int count = map.get(season);
	                map.put(season, count + 1);
	            } else {
	                map.put(season, 1);
	            }
	        }


	        System.out.println("_______Matches Played in each year_________\n");
	        for (Map.Entry<String, Integer> year :
	                map.entrySet()) {
	            System.out.println(year);
	        }

	    }
	    
	    
	    public static void findExtraRunsConcededPerTeamForYear2016(){
	        List<Delivery> deliveriesData = getDeliveriesData();
	        Map<String, Integer> map = new HashMap<>();
	        for (Delivery delivery: deliveriesData){
	            int matchId = Integer.parseInt(delivery.getMatchId());
	            String battingTeam = delivery.getBattingTeam();
	            int extraRuns = Integer.parseInt(delivery.getExtraRuns());

	         if (matchId >= 577 && matchId <= 636) {
	             if (map.containsKey(battingTeam)) {
	                 map.put(battingTeam, map.get(battingTeam) + extraRuns);
	             } else {
	                 map.put(battingTeam, extraRuns);
	             }
	         } else {
	             if (!map.containsKey(battingTeam)) {
	                 map.put(battingTeam, 0);
	             }
	         }

	        }
	        System.out.println("________Extra runs conceded in year 2016________\n");
	        for (Map.Entry<String,Integer> runs:map.entrySet()){
	            System.out.println(runs);
	        }
	    }
	    
	    
	    public static void findMatchesWonPerTeam() {
	        List<Match> matchesData = getMatchesData();
	        Map<String, Integer> teamWinsMap = new HashMap<>();

	        for (Match match : matchesData) {
	            String winner = match.getWinner();

	            if (winner != null) {
	                teamWinsMap.put(winner, teamWinsMap.getOrDefault(winner, 0) + 1);
	            }
	        }

	        System.out.println("________Number of matches won by each team________\n");
	        for (Map.Entry<String, Integer> entry : teamWinsMap.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
	    }

	    public static void findTotalMatchesWonPerTeamPerSeason(){
	        List<Match> matchesData = getMatchesData();
	        Map<String, HashMap<String, Integer>> map = new HashMap<>();
	        for (Match match:matchesData){
	            String season=match.getSeason();
	            String winner = match.getWinner();
	            if (winner!=null) {
	                if (!map.containsKey(season)) {
	                    map.put(season, new HashMap<String, Integer>());
	                }
	                HashMap<String, Integer> teamMap = map.get(season);
	                teamMap.put(winner, teamMap.getOrDefault(winner, 0) + 1);
	                map.put(season, teamMap);
	            }
	        }
	        for (Map.Entry<String,HashMap<String,Integer>> entry:map.entrySet()){
	            System.out.println("___________________"+entry.getKey()+"_____________________\n");
	            for (Map.Entry<String,Integer> team:entry.getValue().entrySet()) {
	                System.out.println(team);
	            }
	            System.out.println();
	        }
	    }
	    
	    
	   
	    
	    public static void findTopEconomicalBowlersForYear2015(){
	        List<Delivery> deliveriesData = getDeliveriesData();
	        Map<String, double[]> map = new HashMap<>();
	        for (Delivery delivery: deliveriesData){
	            String bowler = delivery.getBowler();
	            int matchId= Integer.parseInt(delivery.getMatchId());
	            int totalRuns= Integer.parseInt(delivery.getTotalRuns());
	            int wideRuns= Integer.parseInt(delivery.getWideRuns());
	            int noBallRuns= Integer.parseInt(delivery.getNoBallRuns());
	            int byeRuns= Integer.parseInt(delivery.getByeRuns());
	            int legByeRuns= Integer.parseInt(delivery.getLegByeRuns());
	            int netRuns = totalRuns - (legByeRuns + byeRuns);
	            if (matchId>=518&&matchId<=576) {
	                if (!map.containsKey(bowler))
	                    map.put(bowler, new double[]{0.0, 0.0});
	                double[] runsAndBalls = map.get(bowler);
	                runsAndBalls[0] += netRuns;
	                if (wideRuns==0&&noBallRuns==0)
	                    runsAndBalls[1]++;
	                map.put(bowler,runsAndBalls);
	            }
	        }
	        HashMap<String, Double> topBowlerWithEconomy = new HashMap<>();
	        for (Map.Entry<String,double[]> entry:map.entrySet()) {
	            topBowlerWithEconomy.put(entry.getKey(),6*entry.getValue()[0]/entry.getValue()[1]);
	        }
	        ArrayList<Map.Entry<String,Double>> topBowlerList = new ArrayList<>(topBowlerWithEconomy.entrySet());
	        Collections.sort(topBowlerList, (e1,e2)->e1.getValue().compareTo(e2.getValue()));
	        System.out.println("________Most Economical Bowler________\n");
	        for (Map.Entry<String,Double> entry: topBowlerList) {
	            System.out.println(entry.getKey()+": "+String.format("%.2f",entry.getValue()));
	        }
	    }	 
	    
	   		public static void main(String[] args) {
		    findRCBMatchDatesAgainstOtherTeams() ;
			findRunsScoredByViratForYear2015();
			findTotalMatchesPlayedPerSeason();
			findExtraRunsConcededPerTeamForYear2016();
			findTotalMatchesWonPerTeamPerSeason();
			findMatchesWonPerTeam();
			findTopEconomicalBowlersForYear2015();
		
			
		}

}
