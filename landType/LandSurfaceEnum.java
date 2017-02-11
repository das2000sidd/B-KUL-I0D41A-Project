package landType;

import budget.Budget;
import buildings.BuildingsEnum;

public enum LandSurfaceEnum {
	/*Format- EnumName(enumLandSurfaceRepresentation,LandSurfaceCost)*/
	Forest("F",2000),Grass("G",1000),Volcano("V",4000),Water("W",6000);
	/*Forest, Grass,Volcano and Water are all objects of class amenity	*/
	private final String landType;
	private final int landCost;
	/*constructor	that takes in all attributes of a landSurface to build specific type of amenities	*/
	LandSurfaceEnum(String landType,int landCost){
		this.landType=landType;
		this.landCost=landCost;
	}
	/*getters for each of the attributes of a LandSurfaceEnum object*/
	public String getLandType() {
		return landType;
	}

	public int getLandCost() {
		return landCost;
	}
	public String returnAnyLandSurface(LandSurfaceEnum landSurfaceEnum){
		return landSurfaceEnum.getLandType();
	}
	/*
	 * Budget is updated when land is made by taking amount the 
	 * amount corresponding to land cost from the budget
	 */
	public int UpdateBudget(LandSurfaceEnum landSurfaceEnum) {

		Budget.StartingBudget =Budget.StartingBudget-this.getLandCost();
		return Budget.StartingBudget;


	}
}
