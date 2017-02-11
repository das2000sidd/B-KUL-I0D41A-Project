package landType;

import java.util.InputMismatchException;
import java.util.Scanner;

import budget.Budget;
import buildings.BuildingsEnum;
//THE INITIAL LAND 2D ARRAY GETS BUILT IN THIS CLASS AND BUDGET IS UPDATED
public class LandSurface extends Budget {
	/* for a 2D land grid array gridwidth stores the no of columns
	 * */
	int gridwidth; 
	/*for a 2D land grid array gridheight stores the no of rows
	 * */
	int gridheight; 
	String[][] landGrid;
	/*a LandSurface will have any one of the 4 kinds of land objects specified in LandSurfaceEnum
	 * */
	private LandSurfaceEnum landSurfaceEnum; 
	/* The Constructor landSurface object takes a LandSurfaceEnum object as an arguement
	 * */
	public LandSurface(LandSurfaceEnum string) {
		this.landSurfaceEnum=string;
	}

	/*this method returns the string symbolic representation of each land types in  LandSurfaceEnum
	 * */
	public String getLandSurfaceSign(){
		String landSurfaceSign = this.getLandSurfaceEnum().getLandType();
		return landSurfaceSign;
	}
	/*this method returns the cost of each land types in  LandSurfaceEnum
	 * */
	public int getLandCost(){
		int landCost = this.getLandSurfaceEnum().getLandCost();
		return landCost;
	}
	/*The superclass Budget has the  the static variable StartingBudget representing starting budget and the
	abstract method UpdateBudget() . This gets updated deducting the cost of the land from the budget
	 */
	@Override
	public int UpdateBudget() {
		// TODO Auto-generated method stub
		Budget.StartingBudget = Budget.StartingBudget - this.getLandCost();
		return Budget.StartingBudget;
	}
	/*getter for any LandSurfaceEnum object
	 * */
	public LandSurfaceEnum getLandSurfaceEnum() {
		return landSurfaceEnum;
	}

	/*
	 * getter method that returns string representation of the land surface object
	 */
	public String getLandRepresentationAtAPosition(LandSurfaceEnum landSurfaceEnum){
		return landSurfaceEnum.getLandType();
	}
	/*this grid makes the 2D land array by taking user input as to what 
	 * landSurfaceType they want to build and then updates the budget
	 * */
	public  String[][] make2Darray(LandSurfaceEnum landSurfaceEnum ,int gridheight,int gridwidth){
		/*
		 * Land building loop runs as long as there is enough budget money
		 */

		if(Budget.StartingBudget > 0){
			/*since a 2D array cannot have negative dimensions, 
			 * hence a negative array size exception is being thrown
			 * */
			if(gridheight < 0 || gridwidth < 0 ){
				throw new NegativeArraySizeException("Array dimensions have to be positive numbers");
			} else{
				this.landGrid = new String[gridheight][gridwidth];
				System.out.println("You choose to built a 2D grid array where height is " +gridheight + " and grid width is " + gridwidth );
				System.out.println("You are going to be building the landsurface grid now. Below are your choices. Please input the numbers and not the string with the no.");
				for(int i=0;i < landGrid.length; i++){
					for(int j=0; j < landGrid[i].length;j++){
						System.out.println("0. Forest, 1. Grass, 2. Water, 3.Volcano ");
						System.out.println("Please make a choice between 0/1/2/3");
						Scanner s = new Scanner(System.in);
						int userChoice = s.nextInt();
						/*
						 * If user makes a valid choice then this particular type of LandSurface will be built
						 * */
						if(userChoice ==0 || userChoice ==1 || userChoice ==2 || userChoice ==3){
							/*
							 * Since array indexes run from 0 to designated size-1, the gridheight-1 tells user what choice of numbers he has
							 * for each grid position
							 */
							System.out.println("Choose row number from 0 to not more than " + (gridheight-1));
							try{
								Scanner s1 = new Scanner(System.in);
								int userChoiceRow = s1.nextInt();
								/*
								 * In case user enters a grid height greater than grid size restrictions
								 * then he is notified that he cannot do that
								 */
								if(userChoiceRow >= gridheight){
									System.out.println("Sorry row number cannot be greater than " + gridheight);
									System.out.println("Keep going with row choices between 0 to not more than " + ( gridheight-1));
								} else {
									System.out.println("Choose column from 0 till " + (gridwidth-1));

									Scanner s2= new Scanner(System.in);
									int userChoiceCol = s2.nextInt();
									/*
									 * In case user enters a grid width greater than grid size restrictions
									 * then he is notified that he cannot do that
									 */
									if(userChoiceCol >= gridwidth){
										System.out.println("Sorry column number cannot be greater than" + gridwidth);
										System.out.println("Keep going with column choices between 0 and " + ( gridwidth-1));
									}else {
										/*
										 * Based on user choice a specific type of LandSurface representation String is received by each 
										 * cell of the 2D array
										 * 
										 */
										if(userChoice==0){	
											LandSurface forest = new LandSurface(LandSurfaceEnum.Forest);
											landGrid[userChoiceRow][userChoiceCol] = forest.getLandSurfaceSign();
											Budget.StartingBudget=landSurfaceEnum.UpdateBudget(landSurfaceEnum.Forest);
										} else if(userChoice==1){ 		
											LandSurface grass = new LandSurface(LandSurfaceEnum.Grass);
											landGrid[userChoiceRow][userChoiceCol] = grass.getLandSurfaceSign();
											Budget.StartingBudget=landSurfaceEnum.UpdateBudget(landSurfaceEnum.Grass);

										} else if(userChoice==2){
											LandSurface water = new LandSurface(LandSurfaceEnum.Water);
											landGrid[userChoiceRow][userChoiceCol] = water.getLandSurfaceSign();
											Budget.StartingBudget=landSurfaceEnum.UpdateBudget(landSurfaceEnum.Water);
										} else if(userChoice==3){
											LandSurface volcano = new LandSurface(LandSurfaceEnum.Volcano);
											landGrid[userChoiceRow][userChoiceCol] = volcano.getLandSurfaceSign();
											Budget.StartingBudget=landSurfaceEnum.UpdateBudget(landSurfaceEnum.Volcano);

										} else if(userChoice!=0 ||userChoice!=1 || userChoice!=2 ||userChoice!=3){
											landGrid[userChoiceRow][userChoiceCol] = " ";

										}
									}
								}

							} catch(ArrayIndexOutOfBoundsException e){
								throw new ArrayIndexOutOfBoundsException("Array index values or grid positions on land cannot be negative");
							}	





							System.out.println();
							System.out.println();
							System.out.println();




						} else{
							System.out.println("Sorry user choice of land type cannot be any number other than 0/1/2/3");
						}




					}




				}


			}
			/*
			 * The 2D landGrid made in this method is returned
			 */
		}	else{
			System.out.println("You have run out of budget unfortunately");
			System.exit(0);
		}
		return landGrid;
	}
	/*
	 * Method displayLandGridOnConsole is called by makeBuildingAmenityMethod
	 * of BuildingAmenityGrid class to display the landGrid before further things
	 * are built on that same piece of land
	 */

	public void displayLandGridOnConsole(String[][] twoDArray){
		for(int i=0;i < this.landGrid.length; i++){
			for(int j=0; j < this.landGrid[i].length;j++){
				System.out.print(this.landGrid[i][j] + " ");
			}
			System.out.println();
		}
	}






}







