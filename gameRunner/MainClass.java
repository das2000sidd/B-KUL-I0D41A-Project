package gameRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import amenity.AmenityEnum;
import budget.Budget;
//import buildings.Building;
import buildings.BuildingsEnum;
import landType.LandSurface;
import landType.LandSurfaceEnum;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {


		System.out.println("Please input any number to start the game. Please 0 to end the game");
		Scanner s = new Scanner(System.in);
		/*
		 * User inputs any number to start the game
		 */
		int userInput = s.nextInt(); 
		try{
			while(userInput !=0){
				/*
				 * CityBuilder objects need a LandSurface object to be supplied as an arguement to build a city object
				 */
				LandSurface ls1 = new LandSurface(LandSurfaceEnum.Forest); 
				/*
				 * LandSurface object ls1 being passed to CityBuilder object to start building the city
				 */
				CityBuilder sample = new CityBuilder(ls1);
				/*
				 * CityBuilder printDetailsAboutGanme() method called to notify user of the rules of the game including costs and prompting to enter dimensions of city
				 */
				CityBuilder.printDetailsAboutGame();
				System.out.println("What will be the height of your city grid?");
				/*
				 * Taking in the height of the 2D city grid as user input from the console
				 */
				Scanner gridHeight1 = new Scanner(System.in);
				int gridHeight = gridHeight1.nextInt();
				System.out.println("What will be the width of your city grid?");
				/*
				 * Taking in the width of the 2D city grid as user input from the console
				 */
				Scanner gridWidth1 = new Scanner(System.in);
				int gridWidth = gridWidth1.nextInt();




				/*
				 * The initial city array needs a string 2D array object which is declared as testing.
				 */
				String[][] testing = new String[gridHeight][gridWidth];
				/*
				 * Calling the buildTheCityAmenityGrid of the BuildingAmenityGrid object that calls the 
				 * makeBuildingAmenity of the BuildingAmenityGrid object which again calls the make2Darray
				 * method of the LandSurfaceClass to build the initial city landscape.
				 * This gets displayed by calling the displayLandGridOnConsole method of LandSurface class
				 * Following which the user will again be prompted to 
				 */
				String[][] cityAmenity = sample.buildTheCityAmenityGrid(testing, gridHeight, gridWidth);
				/*
				 * Calling the displayTheCityAtAnyTime method of the CityBuilder class to display the city grid
				 * on the console
				 */
				sample.displayTheCityAtAnyTime(cityAmenity);
				System.out.println("To inspect city details, enter 1");
				Scanner loopController = new Scanner(System.in);
				int num = loopController.nextInt();
				/*
				 * Displaying the user the various options he has to inspect the city
				 * or build new buildings and amenities on the 2D grid
				 */
				while(num!=0){
					System.out.println("Following are the options that you have now. Please input the numbers you see.");
					System.out.println("1. Get count of number of building made"); // working
					System.out.println("2. Get count of number of each of the city amenities"); // working
					System.out.println("3. Get building details at a particular position"); // working
					System.out.println("4. Get population size"); 
					System.out.println("5. Get total incoming money via taxes"); 
					System.out.println("6. Get current budget");
					System.out.println("7. Demolish buildings in city");
					System.out.println("8. Build a building at a specific position");
					System.out.println("9. Build an amenity at a specific position");
					System.out.println("0. Entering 0 will end the game");

					/*
					 * All the methods in the switch case statement correspond as per the user option
					 */



					int count=1;
					try {
						try {
							/*
							 * Dummy variable count to keep the loop running
							 */
							while(count<10){
								count=count*1;
								/*
								 * Prompting the user to make a choice as per the number displayed above
								 */
								System.out.println("Please make a choice");
								int userChoice;
								Scanner anyNum = new Scanner(System.in);
								userChoice = anyNum.nextInt();
								switch(userChoice){
								/*
								 * Implementing switch case logic based on user input
								 */
								case 1:
									int apartmentCount = sample.getBuildingCount(BuildingsEnum.Apartment);
									int houseCount = sample.getBuildingCount(BuildingsEnum.House);
									System.out.println("The city has a total of " + apartmentCount+" " + BuildingsEnum.Apartment.toString()+ " and " +houseCount +" "+ BuildingsEnum.House.toString());
									break;
								case 2:
									int factoryCount = sample.getAmenityCount(AmenityEnum.Factory);
									int fireStionCount = sample.getAmenityCount(AmenityEnum.FireStation);
									int hospitalCount = sample.getAmenityCount(AmenityEnum.Hospital);
									int parkCount = sample.getAmenityCount(AmenityEnum.Park);
									System.out.println("Follwoing are the counts of city amenities");
									System.out.println(AmenityEnum.Factory.getAmenitySymbol().toString() + " : " +factoryCount );
									System.out.println(AmenityEnum.FireStation.getAmenitySymbol().toString() + " : " +fireStionCount );
									System.out.println(AmenityEnum.Hospital.getAmenitySymbol().toString() + " : " +hospitalCount );
									System.out.println(AmenityEnum.Park.getAmenitySymbol().toString() + " : " +parkCount );
									break;
								case 3:
									System.out.println("Which is the building or amenity that you want details of. Please give grid positions");
									System.out.println("The number that you input next will be along the row of 2D array");
									Scanner s2 = new Scanner(System.in);
									int rowPosition = s2.nextInt();
									System.out.println("The number that you input next will be along the column of 2D array");
									Scanner s3 = new Scanner(System.in);
									int columnPosition = s3.nextInt();
									Scanner s4 = new Scanner(System.in);

									String buildingDetails = sample.getBuildingDetails(rowPosition, columnPosition);
									System.out.println(buildingDetails);
									break;
								case 4:
									int populationTotal[][] = sample.PopulationPerBuildingArray(BuildingsEnum.Apartment, gridHeight, gridWidth);
									int totalPopulation = sample.gettotalPopulation(populationTotal);
									System.out.println("The totoal population is " + totalPopulation);
								case 5:
									double totalTaxEarned = sample.getTotalTaxPaid(BuildingsEnum.Apartment,AmenityEnum.Factory);
									System.out.println("The total tax earned is " +totalTaxEarned);
									break;
								case 6:
									int currentBudget = sample.UpdateBudget();
									System.out.println("The current budget is " + currentBudget);
									break;
								case 7:
									System.out.println("You have choose to destroy a building");
									System.out.println("The number that you input next will be along the row of 2D array");
									Scanner s5 = new Scanner(System.in);
									int rowBuilding = s5.nextInt();
									System.out.println("The number that you input next will be along the column of 2D array");
									Scanner s6 = new Scanner(System.in);
									int columnBuilding = s6.nextInt();
									System.out.println("Since you have choosen to destroy the building, you have to set a land surface back at that position");
									System.out.println("0. Forest, 1. Grass, 2. Water, 3.Volcano ");
									System.out.println("Please make a choice between 0/1/2/3");
									Scanner s7 = new Scanner(System.in);
									int userChoiceOfLand = s7.nextInt();
									switch(userChoiceOfLand){
									case 0:
										String[][] postBuildingDestroyNewLandTypeSelect = sample.demolishBuildingsinCity( rowBuilding,columnBuilding,LandSurfaceEnum.Forest);
										sample.displayTheCityAtAnyTime(postBuildingDestroyNewLandTypeSelect);


										break;
									case 1:
										String[][] postBuildingDestroyNewLandTypeSelect1 = sample.demolishBuildingsinCity( rowBuilding,columnBuilding,LandSurfaceEnum.Grass);
										sample.displayTheCityAtAnyTime(postBuildingDestroyNewLandTypeSelect1);

										break;
									case 2:
										String[][] postBuildingDestroyNewLandTypeSelect2 = sample.demolishBuildingsinCity(rowBuilding,columnBuilding,LandSurfaceEnum.Water);
										sample.displayTheCityAtAnyTime(postBuildingDestroyNewLandTypeSelect2);
										int populationPostBuildingDemoliton2 = sample.modifiedPopulationPostBuildingDemolition(postBuildingDestroyNewLandTypeSelect2,BuildingsEnum.Apartment);

										System.out.println("The population of your city now is " + populationPostBuildingDemoliton2);
										break;
									case 3:
										String[][] postBuildingDestroyNewLandTypeSelect3 = sample.demolishBuildingsinCity(rowBuilding,columnBuilding,LandSurfaceEnum.Volcano );
										sample.displayTheCityAtAnyTime(postBuildingDestroyNewLandTypeSelect3);
										int populationPostBuildingDemoliton3 = sample.modifiedPopulationPostBuildingDemolition(postBuildingDestroyNewLandTypeSelect3,BuildingsEnum.Apartment);

										System.out.println("The population of your city now is " + populationPostBuildingDemoliton3);
										break;

									}
									break;
								case 8:
									System.out.println("You have choose to build a new building");
									System.out.println("The number that you input next will be along the row of 2D array");
									Scanner s8 = new Scanner(System.in);
									int rowBuilding1 = s8.nextInt();
									System.out.println("The number that you input next will be along the column of 2D array");
									Scanner s9 = new Scanner(System.in);
									int columnBuilding1 = s9.nextInt();
									Scanner s10 = new Scanner(System.in);
									System.out.println("1. House 2. Apartment");
									int userChoiceBuilding = s10.nextInt();
									switch(userChoiceBuilding){
									case 1:
										String[][] buildingABuildingAtASpecificPos = sample.buildAnyBuildingAtPositionOfChoice(BuildingsEnum.House, rowBuilding1, columnBuilding1);
										sample.displayTheCityAtAnyTime(buildingABuildingAtASpecificPos);
										break;
									case 2:
										String[][] buildingABuildingAtASpecificPos1 = sample.buildAnyBuildingAtPositionOfChoice(BuildingsEnum.Apartment, rowBuilding1, columnBuilding1);
										sample.displayTheCityAtAnyTime(buildingABuildingAtASpecificPos1);
										break;
									}



									break;
								case 9:
									System.out.println("You have choose to build a new amenity");	
									System.out.println("The number that you input next will be along the row of 2D array");
									Scanner s11 = new Scanner(System.in);
									int rowAmenity1 = s11.nextInt();
									System.out.println("The number that you input next will be along the column of 2D array");
									Scanner s12 = new Scanner(System.in);
									int columnAmneity1 = s12.nextInt();
									Scanner s13 = new Scanner(System.in);
									System.out.println("1. Factory,2. FireStation 3. Hospital 4. Park");
									int userChoiceAmenity = s12.nextInt();
									switch(userChoiceAmenity){
									case 1:
										String[][] buildingAAmenityAtASpecificPos = sample.buildAnyAmenityAtPositionOfChoice(AmenityEnum.Factory, rowAmenity1, columnAmneity1);
										sample.displayTheCityAtAnyTime(buildingAAmenityAtASpecificPos);
										break;
									case 2:
										String[][] buildingAAmenityAtASpecificPos1 = sample.buildAnyAmenityAtPositionOfChoice(AmenityEnum.FireStation, rowAmenity1, columnAmneity1);
										sample.displayTheCityAtAnyTime(buildingAAmenityAtASpecificPos1);
										break;
									case 3:
										String[][] buildingAAmenityAtASpecificPos2 = sample.buildAnyAmenityAtPositionOfChoice(AmenityEnum.Hospital, rowAmenity1, columnAmneity1);
										sample.displayTheCityAtAnyTime(buildingAAmenityAtASpecificPos2);
										break;
									case 4:
										String[][] buildingAAmenityAtASpecificPos3 = sample.buildAnyAmenityAtPositionOfChoice(AmenityEnum.Park, rowAmenity1, columnAmneity1);
										sample.displayTheCityAtAnyTime(buildingAAmenityAtASpecificPos3);
										break;
										
									}
                                   break;
								case 0:
									System.out.println("Game over. Thank you for playing this city builder game");
									System.exit(0);
									System.out.println();
								}

							} 


						}



						catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch(InputMismatchException e){
						throw new InputMismatchException("An integer was expected");
					}



				} 
				System.exit(0);

			}



		} catch(InputMismatchException e){
			throw new InputMismatchException("An integer was expected for initial user input");
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(NegativeArraySizeException e2){
			throw new NegativeArraySizeException("The inputs for grid width and height must be greater than zero");
		}





	}
}





