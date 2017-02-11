package gameRunner;
import landType.LandSurface;
import landType.LandSurfaceEnum;
import makeBuildingAmenity.BuildingAmenityGrid;
//import amenity.Amenities;
import amenity.AmenityEnum;
import budget.Budget;
//import buildings.Building;
import buildings.BuildingsEnum;
public class CityBuilder extends Budget {
	/*To construct the city first, land surfaces are needed. 
  Hence a LandSurface object is supplied such that 
	 */
	public CityBuilder(LandSurface landSurface) {

	}


	public LandSurface getLandSurface() {
		return landSurface;
	}

	private LandSurfaceEnum landSurfaceEnum;	
	/*landSurface instance variable for 
	 * building the initial landSurface
	 * */
	private LandSurface landSurface; 
	/*instance variable since a City has an BuildingsEnum object*/
	private BuildingsEnum buildingsEnum; 
	/* instance variable since a city is build on an amenity*/
	private BuildingAmenityGrid buildingAmenityGrid; 
	/*
	 * The Buildings and Amenities Grid 2D array made will be  passed to this cityAmenityGrid 2D array object and 
	 * returned by the method that is subsequently referenced for all other methods
	 */
	private String[][] cityAmenityGrid; // 
	private int[][] populationPerBuilding;
	private int totalPopulation;
	/*
	 * A getter method for the buildingAmenityGrid object that has various methods for
	 * modifying the city we build as well as return statistics of city
	 */
	/*
	 * A getter method for the buildingAmenityGrid object
	 */
	public BuildingAmenityGrid getBuildingAmenityGrid() {
		return buildingAmenityGrid;
	}


	/*This method calls the makeBuildingAmenity method of the 
	 * BuildingAmenityGrid object to giver user choices to put
	 *  in various building and amenity objects n the land grid.
	 * 
	 * */

	public String[][] buildTheCityAmenityGrid(String[][] Array2D,int gridheight,int gridwidth){


		buildingAmenityGrid = new BuildingAmenityGrid(buildingsEnum.Apartment);

		this.cityAmenityGrid=  buildingAmenityGrid.makeBuildingAmenity(this.cityAmenityGrid,gridheight,gridwidth);



		return cityAmenityGrid;

	}
	/* This method displays the city amenity grid*/
	public void displayTheCityAtAnyTime(String[][] arrayOfCity){
		for(int i=0; i < this.cityAmenityGrid.length;i++){
			for(int j=0; j < this.cityAmenityGrid[i].length;j++){
				System.out.print(this.cityAmenityGrid[i][j] + "   ");
			}
			System.out.println();
		}
	}

	/*This method destroys a building by calling the demolish buildings method of
	 * BuildingAmenityGrid object in case the user wants to destroy a building
	 * */

	public String[][] demolishBuildingsinCity(int x, int y,LandSurfaceEnum landSurfaceEnum){
		this.cityAmenityGrid = this.getBuildingAmenityGrid().demolishBuildings( x, y,landSurfaceEnum);
		return this.cityAmenityGrid;
	}

	/*
	 * After a building has been destroyed it would not make sense to have that cell in the grid empty
	 * Hence the returnLandSurfaceRepOfAnyEnum method will be called to set a particular type of land in that position of the grid
	 */
	public String returnLandSurfaceRepOfAnyEnum(LandSurfaceEnum landSurfaceEnum){
		return landSurfaceEnum.getLandType();
	}


	/*This method gets the details of a building at a particular position 
	 * by calling the getBuildingDetailsAtParticularPosition()
   method of the BuildingAmenityGrid class
	 */

	public String getBuildingDetails(int gridXAxis,int gridYAxis ){

		String h = this.getBuildingAmenityGrid().getDetailsOfAnyBuilding(this.cityAmenityGrid, this.getBuildingAmenityGrid().getBuildingsEnum(),gridXAxis, gridYAxis);
		return h;

	}



	@Override
	/*
	 * (non-Javadoc)
	 * @see budget.Budget#UpdateBudget()
	 * This method returns the current Budget
	 */
	public int UpdateBudget() {
		// TODO Auto-generated method stub
		return Budget.StartingBudget;
	}


	/*A method where we can get count of each building type made
   by calling the getCountofAnyBuildingMade of the BuildingAmenityGrid class
 and passing in a specific type of BuildingEnum
	 */
	public int getBuildingCount(BuildingsEnum buildingsEnum){
		return this.buildingAmenityGrid.getCountofAnyBuildingMade(this.cityAmenityGrid, buildingsEnum);
	}
	/*A method where we can get count of each amenity type made
by calling the getCountofAnyBuildingMade of the BuildingAmenityGrid class
 and passing in a specific type of amenity enum
	 * */
	public int getAmenityCount(AmenityEnum amenityEnum){
		return this.buildingAmenityGrid.getCountofAnyBuildingMade(this.cityAmenityGrid, amenityEnum);
	}

	/* A method where we can get total tax paid for any building
 by calling the getTaxFromBuildings method of the BuildingAmenityGrid class
 by passing in a specific type of building enum
	 */
	public double getTotalTaxPaid(BuildingsEnum buildingsEnum,AmenityEnum amenityEnum){

		return this.getBuildingAmenityGrid().TotalTaxToBePaid(this.cityAmenityGrid,buildingsEnum, amenityEnum);
	}


	/* This method gives the 2D array with people for all
	 *  buildings that will be used to find total population
	 *  */
	public int[][] PopulationPerBuildingArray(BuildingsEnum buildingsEnum,int x,int y){
		this.populationPerBuilding= this.getBuildingAmenityGrid().addPeopleToEachBuilding(this.cityAmenityGrid,buildingsEnum, x, y);
		return this.populationPerBuilding;
	}
	/* The method calls the buildAnyBuildingAtAPosition() method of the BuildingAmenityGrid
	 * class to let the user build a building of their preference at a position of their choice
	 */
	public String[][] buildAnyBuildingAtPositionOfChoice(BuildingsEnum buildingsEnum, int x, int y){
		return this.buildingAmenityGrid.buildAnyBuildingAtAPosition(buildingsEnum, x, y, this.cityAmenityGrid);
	}
	/*
	 * This method calls the buildAnyAmenityAtAPosition() method of the BuildingAmenityGrid class 
	 * to let the user build an amenity of their choice at a grid position of their choice
	 */

	public String[][] buildAnyAmenityAtPositionOfChoice(AmenityEnum amenityEnum,int x, int y){
		return this.buildingAmenityGrid.buildAnyAmenityAtAPosition(amenityEnum, x, y, this.cityAmenityGrid);
	}

	/*
	 * When a building gets destroyed, the people in that building can be assumed to leave and 
	 * hence the total population has to be substracted from the existing population of that building
	 * This method loops over the grid after building has been destroyed and if there is no building in that position
	 * then the corresponding population number from the population grid is also destroyed.
	 */

	public int modifiedPopulationPostBuildingDemolition(String[][] cityGrid,BuildingsEnum buildingsEnum ){
		for(int i=0; i < this.cityAmenityGrid.length;i++){
			for(int j = 0 ; j < this.cityAmenityGrid[i].length;j++){
				if(this.cityAmenityGrid[i][j]!=(this.buildingsEnum.Apartment.getBuilding()) ||this.cityAmenityGrid[i][j]!=(this.buildingsEnum.House.getBuilding())){
					this.totalPopulation=this.totalPopulation-this.populationPerBuilding[i][j];

				}
			}
		}
		return this.totalPopulation;
	}
	/*
	 * This method is called the first time the city grid made to determine the total population
	 */

	public int gettotalPopulation(int[][] populationArray){
		for(int i=0; i < this.populationPerBuilding.length;i++){
			for(int j=0; j < this.populationPerBuilding[i].length;j++){
				this.totalPopulation=this.totalPopulation+this.populationPerBuilding[i][j];
			}
		}
		return this.totalPopulation;
	}
	/*
	 * This is the method that will display the rules of the game when the program is executed and the user starts the game
	 */
	public static void printDetailsAboutGame(){
		System.out.println("Welcome to the virtual city Building game");
		System.out.println("In this game you first start of by making a rectangular or square grid with following land surface options");
		System.out.println("Following are the types of land surface available to you and their cost");
		System.out.println(LandSurfaceEnum.Forest.toString() +" - " +LandSurfaceEnum.Forest.getLandCost() );
		System.out.println(LandSurfaceEnum.Grass.toString() + " - "+ LandSurfaceEnum.Grass.getLandCost());
		System.out.println(LandSurfaceEnum.Volcano.toString() + " - " +LandSurfaceEnum.Volcano.getLandCost());
		System.out.println(LandSurfaceEnum.Water.toString() + " - " +LandSurfaceEnum.Water.getLandCost() );
		System.out.println("Following are the types of buildings and amenities available to you and their building cost and maintenance cost");
		System.out.println(BuildingsEnum.Apartment.toString() + " - " +BuildingsEnum.Apartment.getPerbuildingCost() +  " , " +BuildingsEnum.Apartment.getPermaintainCost());
		System.out.println(BuildingsEnum.House.toString() + " - " +BuildingsEnum.House.getPerbuildingCost() +  " , " +BuildingsEnum.House.getPermaintainCost());
		System.out.println(AmenityEnum.Factory.toString() + " - " +AmenityEnum.Factory.getAmenityCostEnum() + " , " + AmenityEnum.Factory.getAmenityMaintenanceCostEnum());
		System.out.println(AmenityEnum.FireStation.toString() + " - " +AmenityEnum.FireStation.getAmenityCostEnum() + " , " + AmenityEnum.FireStation.getAmenityMaintenanceCostEnum());
		System.out.println(AmenityEnum.Hospital.toString() + " - " +AmenityEnum.Hospital.getAmenityCostEnum() + " , " + AmenityEnum.Hospital.getAmenityMaintenanceCostEnum());
		System.out.println(AmenityEnum.Park.toString() + " - " +AmenityEnum.Park.getAmenityCostEnum() + " , " + AmenityEnum.Park.getAmenityMaintenanceCostEnum());
		System.out.println("Your total budget is " + Budget.StartingBudget);
		System.out.println("You start of by building the land grid followed by building buildings and amenities on it as well");
		System.out.println("Once your budget is 0 or below zero, the game will be over");
		System.out.println("After you are done building the city, you can inspect various aspects of your city as will be listed");
		System.out.println("Good luck!");

	}
}