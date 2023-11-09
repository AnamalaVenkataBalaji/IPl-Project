package com.io.ipl;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Map.Entry;
public class IplAnaylysis1 
{
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
	    private static final int Id=0;
	    private static final int Venue=14;
	    private static final int TossWin=6;
	    private static final int TossDecition=7;
	    private static final int win_by_runs=11;
	    private static final int win_by_wickets=12;
	    private static final int Bowler=8;
	   private static final int PlayerDismised1= 18;
	   private static final int PlayerOfTheMatch=13;
	   private static final int dismissal_kind=19;
       private static final int Super=9;
	    public static List<Delivery> getDeliveriesData() {
	        List<Delivery> deliveries = new ArrayList<>();
	        try {
	            Scanner scanner = new Scanner(new File("deliveries.csv"));
	            scanner.nextLine(); // Skip the header line
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] deliveryData = line.split(",");
	                Delivery delivery = new Delivery();

	                if (deliveryData.length > PlayerDismised1) {
	                    String playerDismissed = deliveryData[PlayerDismised1];
	                    
	                    // Check if playerDismissed is not empty or "retired hurt"
	                    if (!playerDismissed.isEmpty() && !playerDismissed.equalsIgnoreCase("retired hurt")) {
	                        // Set PlayerDismised using the correct index
	                        delivery.setPlayerDismissed(playerDismissed);
	                    }
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
	               delivery.setPlayerDismissed(deliveryData[PlayerDismised1]);
	                delivery.setDismissalKind(deliveryData[dismissal_kind]);
	                delivery.setIsSuperOver(deliveryData[Super]);
	                deliveries.add(delivery);
	           
	                }}
	         
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
	                match.setId(matchData[Id]);
	                match.setSeason(matchData[SEASON]);
	                match.setWinner(matchData[WINNER]);
	                match.setDate(matchData[Date]);
	                match.setTeam1(matchData[Team1]);
	                match.setTeam2(matchData[Team2]);
	                match.setVenue(matchData[Venue]);
	                match.setTossWinner(matchData[TossWin]);
	                match.setTossDecision(matchData[TossDecition]);
	                match.setWinByRuns(matchData[win_by_runs]);
	                match.setWinByWickets(matchData[win_by_wickets]);
	                match.setPlayerOfMatch(matchData[PlayerOfTheMatch]);
	                matches.add(match);
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return matches;
	    }
	    public static void findTopBatsmenByMatchesPlayed() {
	        List<Delivery> deliveriesData = getDeliveriesData();
	        Map<String, Integer> batsmanMatchesMap = new HashMap<>();

	        for (Delivery delivery : deliveriesData) {
	            String batsman = delivery.getBatsman();

	            // Count matches for the batsman
	            batsmanMatchesMap.put(batsman, batsmanMatchesMap.getOrDefault(batsman, 0) + 1);
	        }

	        // Sort the map by matches played in descending order and limit to top 5 batsmen
	        List<Map.Entry<String, Integer>> topBatsmen = batsmanMatchesMap.entrySet().stream()
	            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
	            .limit(5)
	            .collect(Collectors.toList());

	        // Print the top 5 batsmen who have played the maximum number of matches
	        System.out.println("Top 5 Batsmen by Matches Played:");
	        for (Map.Entry<String, Integer> entry : topBatsmen) {
	            System.out.println(entry.getKey() + ": " + entry.getValue() + " matches");
	        }
	    }
	    
	    public static void findTopVenuesByMatchesHosted() {
	        List<Match> matchesData = getMatchesData();
	        Map<String, Integer> venueMatchesMap = new HashMap<>();

	        for (Match match : matchesData) {
	            String venue = match.getVenue();

	            // Count matches for the venue
	            venueMatchesMap.put(venue, venueMatchesMap.getOrDefault(venue, 0) + 1);
	        }

	        // Sort the venues by matches hosted in descending order
	        venueMatchesMap.entrySet().stream()
	            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
	            .limit(5)
	            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " matches"));
	    }
	    
	    
	  	    
	    //Most Wickets by a Bowler in a Season: 
	    //Identify the top 5 bowlers who took the maximum wickets in a specific year.
	    
	    
	    public static void findTop5BowlersWhoTookTheMaximumWicketsInASpecificYear(String season) {
	        List<Delivery> deliveriesData = getDeliveriesData();
	        Map<String, Integer> bowlerWicketsMap = new HashMap<>();

	        // Filter deliveries data by season using matches file
	        List<Match> matchesData = getMatchesData();
	        List<String> matchIdsForSeason = matchesData.stream()
	                .filter(match -> match.getSeason().equals(season))
	                .map(Match::getId)
	                .collect(Collectors.toList());

	        List<Delivery> filteredDeliveries = deliveriesData.stream()
	                .filter(delivery -> matchIdsForSeason.contains(delivery.getMatchId()))
	                .collect(Collectors.toList());

	        for (Delivery delivery : filteredDeliveries) {
	            String bowler = delivery.getBowler();
	            String playerDismissed = delivery.getPlayerDismissed();

	            // Check if the dismissal is valid (not empty and not retired hurt)
	            if (!playerDismissed.isEmpty() && !playerDismissed.equalsIgnoreCase("retired hurt")) {
	                // Count wickets for the bowler
	                bowlerWicketsMap.put(bowler, bowlerWicketsMap.getOrDefault(bowler, 0) + 1);
	            }
	        }

	        // Sort the map by wickets taken in descending order and limit to top 5 bowlers
	        List<Map.Entry<String, Integer>> topBowlers = bowlerWicketsMap.entrySet().stream()
	                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
	                .limit(5)
	                .collect(Collectors.toList());

	        // Print the top 5 bowlers who took the maximum wickets in the given season
	        System.out.println("Top 5 Bowlers by Wickets Taken in Season " + season + ":");
	        for (Map.Entry<String, Integer> entry : topBowlers) {
	            System.out.println(entry.getKey() + ": " + entry.getValue() + " wickets");
	        }
	    }
	    
	    public static void findPlayerWithMostPlayerOfTheMatchAwards() {
	        List<Match> matchesData = getMatchesData();
	        Map<String, Long> playerOfTheMatchAwards = matchesData.stream()
	                .collect(Collectors.groupingBy(Match::getPlayerOfMatch, Collectors.counting()));

	        // Find player with most "Player of the Match" awards
	        Map.Entry<String, Long> mostAwardsPlayer = playerOfTheMatchAwards.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .orElse(null);

	        // Print the player with the most "Player of the Match" awards
	        if (mostAwardsPlayer != null) {
	            System.out.println("Player with Most Player of the Match Awards: " + mostAwardsPlayer.getKey()
	                    + " - " + mostAwardsPlayer.getValue() + " awards");
	        } else {
	            System.out.println("No data available.");
	        }
	    }
	    
	    public static void findPlayerWithMostPlayerOfTheMatchAwards(String season) {
	        List<Match> matchesData = getMatchesData();

	        // Filter matches data by the user input season
	        List<Match> filteredMatches = matchesData.stream()
	                .filter(match -> match.getSeason().equals(season))
	                .collect(Collectors.toList());

	        // Calculate Player of the Match awards for the specified season
	        Map<String, Long> playerOfTheMatchAwards = filteredMatches.stream()
	                .collect(Collectors.groupingBy(Match::getPlayerOfMatch, Collectors.counting()));

	        // Find player with most "Player of the Match" awards
	        Map.Entry<String, Long> mostAwardsPlayer = playerOfTheMatchAwards.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .orElse(null);

	        // Print the player with the most "Player of the Match" awards for the specified season
	        if (mostAwardsPlayer != null) {
	            System.out.println("Player with Most Player of the Match Awards in season " + season + ": "
	                    + mostAwardsPlayer.getKey() + " - " + mostAwardsPlayer.getValue() + " awards");
	        } else {
	            System.out.println("No data available for the specified season.");
	        }
	    }

	  
	  

	    public static void findTeamWithMostSuperOvers() {
	        List<Delivery> deliveriesData = getDeliveriesData();

	        // Group deliveries data by match ID and batting team, then count the number of super overs for each team
	        Map<String, Long> superOversCountMap = deliveriesData.stream()
	                .filter(delivery -> delivery.getIsSuperOver().equals("1")) // Filter super overs only
	                .collect(Collectors.groupingBy(
	                        delivery -> delivery.getMatchId() + "-" + delivery.getBattingTeam(),
	                        Collectors.counting()
	                ));

	        // Find the team with the most super overs
	        Map.Entry<String, Long> teamWithMostSuperOvers = superOversCountMap.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .orElse(null);

	        // Print the team with the most super overs and the number of super overs they have been involved in
	        if (teamWithMostSuperOvers != null) {
	            String[] parts = teamWithMostSuperOvers.getKey().split("-");
	            String matchId = parts[0];
	            String team = parts[1];
	            long superOversCount = teamWithMostSuperOvers.getValue();
	            System.out.println("Team with Most Super Overs:");
	        //    System.out.println("Match ID: " + matchId);
	            System.out.println("Team: " + team);
	            System.out.println("Number of Super Overs: " + superOversCount);
	        } else {
	            System.out.println("No super overs data available.");
	        }
	    }
	    
	    public static void PlayerWithMostApperance()
	    {
	    	List<Delivery> deliveryData=getDeliveriesData();
	    	Map <String, Integer> playerAppearances = new HashMap<>();
			   Map<Integer, HashMap<String, Integer>> map = new HashMap<>();
		    	for (Delivery delivery: deliveryData)
		         {
		         String playerName = delivery.getBatsman();
		         String playerName2 = delivery.getBowler();
		         int matchId = Integer.parseInt(delivery.getMatchId());
		         if (playerName != null && !playerName.isEmpty()) {
		             playerAppearances.put(playerName, playerAppearances.getOrDefault(playerName, 0) + 1);
		            //System.out.println( playerAppearances.get(playerName));
		         }
		     }List<Map.Entry<String, Integer>> sortedPlayers = new ArrayList<>(playerAppearances.entrySet());
		     sortedPlayers.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		     System.out.println("PlayerMostApperance");
		     for (int i = 0; i <5; i++) {
				System.out.println(sortedPlayers.get(i));
			}
		     }
	    
	    public static void MostMOM(){
	    	Map<String, Integer> map =new HashMap();
	    	List<Match> matches = getMatchesData();
	    	for (Match match : matches)
	    	{
	    		String playerOfMatch = match.getPlayerOfMatch();
	    		map.put(playerOfMatch, map.getOrDefault(playerOfMatch, 0) + 1);
	    	}
	    	 List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());//converting
	    	    list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())); // sorting
	    
	    	    
	    	       System.out.println(list.get(0));
	    }
	  
	    public static void main(String[] args) {
    	    findTopBatsmenByMatchesPlayed();
	    	findTopVenuesByMatchesHosted();
  
	    	findTop5BowlersWhoTookTheMaximumWicketsInASpecificYear("2015");    	
    	findPlayerWithMostPlayerOfTheMatchAwards("2014");
  	findTeamWithMostSuperOvers();
	    	PlayerWithMostApperance();
	  
		}

}
