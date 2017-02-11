package makeBuildingAmenity;
import java.util.Scanner;
import amenity.AmenityEnum;
import budget.Budget;
import buildings.BuildingsEnum;
import landType.LandSurface;
import landType.LandSurfaceEnum;
/*
This is the class where the grid containing buildings or amenities will be made
after initial land Grid has been made. It is the class that has methods called by the CityBuilder class to build the initial land grid
followed by buildings and amenities and finally obtain statistics on them.
 */ 
public class BuildingAmenityGrid extends Budget {
	/*Every grid of buildings has a landSurfaceGrid with different kinds of land. Hence landSurface is an instance variable referencing LandSurface object	
	LandSurface has the method to build the initial 2D grid
	*/
	private LandSurface landSurface;  
	/* Every grid has a building and hence buildingsEnum is referencing a BuildingsEnum object
	 * */
	private BuildingsEnum buildingsEnum;
	/*Every grid has a type of amenity and hence amenityEnum is referencing a AmenityEnum object
	 * */
	private AmenityEnum amenityEnum;
	/*The LandSurfaceEnum class has the 4 different land surface objects that are used to 
	 * build the initial land surface in the LandSurface class
	 * */
	private LandSurfaceEnum landSurfaceEnum;
	private String[][] landArray;
	private int[][] peopleArray;
	/*
	 * A 2D array that holds people for each building
	 */
	
	private int totalPopulation;
	/*two overloaded constructors of the BuildingAmenityGrid class
	that takes in either an object of type BuildingsEnum or AmenityEnum
	depending on what has to be built
	*/
	public BuildingAmenityGrid(BuildingsEnum buildingsEnum) {
		this.buildingsEnum=buildingsEnum;
	}
	public BuildingAmenityGrid(AmenityEnum amenityEnum) {
		this.amenityEnum = amenityEnum;
	}

	@Override
	public int UpdateBudget() {
		// TODO Auto-generated method stub
		return Budget.StartingBudget;
	}
	/*getter method to return a BuildingsEnum object*/
	public BuildingsEnum getBuildingsEnum() {
		return buildingsEnum;
	}
	/*getter method to return a AmenityEnum object*/
	public AmenityEnum getAmenityEnum() {
		return amenityEnum;
	}

	/*This method builds the buildings amenities grid returns of a 2D representation of it
	It will take in the array from the landGrid made along with its height and width and 
	make amenities or buildings based on user choice
	User is allowed to build buildings and amenities as long as there is enough budget
	*/
	public  String[][] makeBuildingAmenity(String[][] landArray,int gridheight,int gridwidth){

		while(Budget.StartingBudget>0){
			/* making a new LandSurface object 
			that takes in a landSurfaceEnum object
			 *  
			 */
			LandSurface newLS = new LandSurface(landSurfaceEnum.Forest); 
			/* calling the make2Darray method LandSurface object that takes in a
			 *  specific grid dimensions and builds the grid by taking user input
			 */
			this.landArray =  newLS.make2Darray(landSurfaceEnum.Forest, gridheight, gridwidth);
			/*After the 2D array with various land surface types has been made, 
			 * they are displayed onto the Grid
			 * */
			newLS.displayLandGridOnConsole(this.landArray);	
			/* After the user has built the landGrid, he is notified of what
			budget he has left before he starts building other buildings and amenities
			 */
			System.out.println("Your current budget is " + Budget.StartingBudget);

			System.out.println("You are going to be building the buildings and amenities on your city grid. Below are your choices");
			/* Now looping over the previously made landSurface grid and adding buildings and amenities 
			to specific grid positions by giving the user options every single time
			*/
			for(int i=0;i < this.landArray.length; i++){
				for(int j=0; j < this.landArray[i].length;j++){
					System.out.println("What do you want to build? Following are your menu options. Please input the numbers and not the string with the no.");
					System.out.println("0. Apartment, 1. House,2. Factory,3. FireStation 4. Hospital 5. Park, -1. keep the preexisting land");
					Scanner s = new Scanner(System.in);
					/* Taking the user input as to what he wants to build
					 * */
					int userChoice = s.nextInt(); 
					if(userChoice == 0 ||userChoice==1 || userChoice==2 || userChoice==3 || userChoice==4 || userChoice==5  || userChoice==-1 ){
						System.out.println();
						System.out.println();
						System.out.println();
						/*Notifying user what row and column range he has so that he is no confusion 
						 * as to what grid positions he may choose 
						 * */
						System.out.println("Choose row between from 0 to not more than " + (gridheight-1)); 
						Scanner s1 = new Scanner(System.in);
						int userChoiceRow = s1.nextInt();
						if(userChoiceRow < 0){
							throw new ArrayIndexOutOfBoundsException("The positions in the aray must be greater than zero");
						} else{
							/*in case the user chooses a row greater than the the grid height, 
						       he is warned and asked to choose from within the limits
						       */
							if(userChoiceRow >= gridheight){
								System.out.println("Sorry row number cannot be greater than " + gridheight);
								System.out.println("Keep going with row choices from 0 to not more than " + ( gridheight-1));
							} else {
								System.out.println("Choose column between from 0 to not more than " + (gridwidth-1));
								Scanner s2= new Scanner(System.in);
								int userChoiceCol = s2.nextInt();
								/* in case the user chooses a row greater than the the grid width, 
							        he is warned and asked to choose from within the limits
							        */
								if(userChoiceCol >= gridwidth){
									System.out.println("Sorry column number cannot be greater than" + gridwidth);
									System.out.println("Keep going with column choices between 0 and " + ( gridwidth-1));

								} else {
									/*based on the user's choice a specific building or amenity is made and its' string representation is returned
								 The string representation returned is added to grid position of user's choice
								 Corresponding budget is also updated by calling the update budget method of each class 
								 */

									if(userChoice==0){

										String apartment = buildingsEnum.Apartment.getBuilding();
										this.landArray[userChoiceRow][userChoiceCol] = apartment;
										buildingsEnum.Apartment.UpdateBudget(buildingsEnum.Apartment);

									} else if(userChoice==1){	
										String house =buildingsEnum.House.getBuilding();
										this.landArray[userChoiceRow][userChoiceCol] = house;
										buildingsEnum.House.UpdateBudget(buildingsEnum.House);

									} else if(userChoice==2){	
										String factory = amenityEnum.Factory.getAmenitySymbol();
										this.landArray[userChoiceRow][userChoiceCol] = factory;
										amenityEnum.Factory.UpdateBudget(amenityEnum.Factory);
									} else if(userChoice==3){		
										String firestation = amenityEnum.FireStation.getAmenitySymbol();
										this.landArray[userChoiceRow][userChoiceCol] = firestation;
										amenityEnum.FireStation.UpdateBudget(amenityEnum.FireStation);

									} else if(userChoice==4){		
										String hospital = amenityEnum.Hospital.getAmenitySymbol();
										this.landArray[userChoiceRow][userChoiceCol] = hospital;
										amenityEnum.Hospital.UpdateBudget(amenityEnum.Hospital);
									} else if(userChoice==5){		
										String park =amenityEnum.Park.getAmenitySymbol();
										this.landArray[userChoiceRow][userChoiceCol] = park;	
										amenityEnum.Park.UpdateBudget(amenityEnum.Park);
									} 

									/*in case the user does not want to build anything the landtype that was there
								he chooses -1 the landSurface type object in the grid is retained
								*/
									else if(userChoice==-1){
										this.landArray[i][j] = this.landArray[userChoiceRow][userChoiceCol]; 
										 
									}


								}

							}
							System.out.println();
						}} else{
							System.out.println("Please make a valid choice");
						}
				}
			} 
			return this.landArray; /* a 2D array of land types is returned- with buildings*/
			/* once the budget is finished user cannot build anything and the game should end. 
			 * Hence the System.exit(0) once the budget is not greater than zero
			 * */
		} System.out.println("You have run out of budget unfortunately");
		System.exit(0);
		return this.landArray; // a 2D array of land types is returned- with buildings
	}


	/* This method checks if the landGrid returned by the makeBuildingAmenity() method has an Apartment object or House object.
	This method assumes that in our world, people only live in Buildings that are of type Apartment or House
	It does so by calling their getBuilding() method which returns a string representation of the Apartment object or House object.
	if there is a building or apartment at that position, then 3 people are assigned per house or apartment. 
	This is stored in array and the value corresponding to an object on the  is set to zero if there is no building at a particular position

	 */

	public int[][] addPeopleToEachBuilding(String[][] landArray,BuildingsEnum buildingsEnum,int x, int y){
		this.peopleArray = new int[x][y];
		for(int i=0; i < this.landArray.length;i++){
			for(int j=0; j < this.landArray[i].length;j++){
				if(this.landArray[i][j] == buildingsEnum.Apartment.getBuilding()|| this.landArray[i][j]==buildingsEnum.House.getBuilding()){
					this.peopleArray[i][j] = 3;
				}	 else{
					this.peopleArray[i][j]=0;


					
				}
			}
		}
		return this.peopleArray;
	}
	/*
	 * This method after initial building of city buildings and amenities is done, lets the user choose if he
	 * wants to many other buildings. 
	 */
	public String[][] buildAnyBuildingAtAPosition(BuildingsEnum buildingsEnum, int x, int y,String[][] landArray){

		for(int i= 0; i < this.landArray.length;i++){
			for(int j = 0; j < this.landArray[i].length;j++){
				if(Budget.StartingBudget>0){
					this.landArray[x][y]=buildingsEnum.getBuilding();

					Budget.StartingBudget=buildingsEnum.UpdateBudget(buildingsEnum);
				} else{
					System.out.println("You have run out of budget unfortunately");
					System.exit(0);
				}
			}
		}
		return this.landArray;
	}

	/*
	 * This method is an overloaded version of the method above but it takes in an AmenityEnum object to 
	 * build a city amenity instead of a building
	 */

	public String[][] buildAnyAmenityAtAPosition(AmenityEnum amenityEnum, int x, int y,String[][] landArray){
		for(int i= 0; i < this.landArray.length;i++){
			for(int j = 0; j < this.landArray[i].length;j++){
				if(Budget.StartingBudget>0){
					this.landArray[x][y]=amenityEnum.getAmenitySymbol();

					Budget.StartingBudget = amenityEnum.UpdateBudget(getAmenityEnum());
				} else{
					System.out.println("You have run out of budget unfortunately");
					System.exit(0);
				}
			}
		}
		return this.landArray;
	}

	/* This method demolishes buildings at a position by taking user input a particular position
	   Then checks if there is a building at that position and sets it to empty String representation on the Grid
	   
	 */
	public String[][] demolishBuildings(int x, int y,LandSurfaceEnum landSurfaceEnum){

		for(int i= 0; i < this.landArray.length;i++){
			for(int j = 0; j < this.landArray[i].length;j++){
				if(Budget.StartingBudget>0){
					this.landArray[x][y]=landSurfaceEnum.returnAnyLandSurface(landSurfaceEnum);
					
					Budget.StartingBudget= Budget.StartingBudget-landSurfaceEnum.getLandCost();	
				} else{
					System.out.println("You have run out of budget unfortunately");
					System.exit(0);
				}

			}
		}


		return this.landArray;

	}	

	/*This method is called after the   makeBuildingAmenity() method has been called and determines 
      whether in the landGrid returned by the makeBuildingAmenity() method there is a house/apartment/factory.
	  These are the three object types that have the payTaxes() method.
	  The taxes get calculated according to the respective methods and are incremented to the totalTax for any object on the landGrid
	 This totalTax is returned by this method
	 */
	public double TotalTaxToBePaid(String[][] buildingAmenityArray1,BuildingsEnum buildingsEnum, AmenityEnum amenityEnum){
		double totalTax =0;
		/*
		 * The total tax is calculated according to the whether a grid position has Apartments, Buildings or Factories.
		 */
		for(int i=0; i < this.landArray.length;i++){
			for(int j=0; j < this.landArray[i].length;j++){
				if(this.landArray[i][j].equals(buildingsEnum.Apartment.getBuilding())){
					double apartmentTax = buildingsEnum.payTaxes(buildingsEnum.Apartment);
					totalTax=totalTax+apartmentTax;
				} else  {
					if(this.landArray[i][j].equals(buildingsEnum.House.getBuilding())){
						double houseTax = buildingsEnum.payTaxes(buildingsEnum.House);
						totalTax=totalTax+houseTax;
					} else {
						if(this.landArray[i][j].equals(amenityEnum.Factory.getAmenitySymbol())){
							double factoryTax = amenityEnum.payTaxes(amenityEnum.Factory);
							totalTax = totalTax+factoryTax;

						}
					}
				}
			}
		}
		return totalTax;
	}
	/*This method is called after the makeBuildingAmenity() method has been called and determines 
	 how many buildings have been made for each building type. 
	The user in the main method supplies any specific type of building  by calling a BuildingsEnum object
	 */
	public int getCountofAnyBuildingMade(String[][] buildingAmenityArray1,BuildingsEnum buildingsEnum){
		int buildingCount = 0;
		for(int i=0;i < this.landArray.length; i++){
			for(int j=0; j < this.landArray[i].length;j++){
				if (this.landArray[i][j]==buildingsEnum.getBuilding()){
					buildingCount++;
				}
			}	
		}
		return buildingCount;
	}
	/*This is an overloaded form of the getCountofAnyBuildingMade() method above but it takes 
	a AmenityEnum object to determine its count
	 */
	public int getCountofAnyBuildingMade(String[][] buildingAmenityArray1,AmenityEnum amenityEnum ){
		int amenityCount = 0;
		for(int i=0;i < this.landArray.length; i++){
			for(int j=0; j < this.landArray[i].length;j++){
				if (this.landArray[i][j]==amenityEnum.getAmenitySymbol()){
					amenityCount++;
				}
			}	
		}
		return amenityCount;
	}

	/*This method gives the details of a specific building at any position on the grid
	This method is not for an AmenityEnum object and only for a BuildingsEnum object
	It loops over the landGrid returned by the makeBuildingAmenity() method and 
	checks if there is a Apartment or Building by calling the BuildingsEnum getBuilding() method and if so
	returns what kind of building it is, its's grid positions and it building and maintenance cost
	 */

	public String getDetailsOfAnyBuilding(String[][] buildingAmenity,BuildingsEnum buildingEnum,int x,int y){
		String buildingDetails="";
		for(int i= 0; i < this.landArray.length;i++){
			for(int j = 0; j < this.landArray[i].length;j++){

				if(this.landArray[x][y].equals(buildingEnum.Apartment.getBuilding())){
					buildingDetails="The building at position (" + x + "," + y + ")" + " is an " +buildingEnum.returnBuildingDetails(buildingEnum.Apartment);

					//return buildingDetails;

				} else if(this.landArray[x][y].equals(buildingEnum.House.getBuilding())){
					buildingDetails="The building at position (" + x + "," + y + ")" + " is a " + buildingEnum.returnBuildingDetails(buildingEnum.House);
					//return buildingDetails;
				}
			}

		}

		return buildingDetails;
	}



}
