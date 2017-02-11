package buildings;

import budget.Budget;

public enum BuildingsEnum {
	/*Format- EnumName(enumBuildingStringRepresentation,buildingBuildCost,buildingMaintainCost)
	 the constant objects that represent the attributes of each Building object
	 */
	Apartment("A",5000,500), House("H",8000,800); //In brackets they are values

	/*variables for calling each of the Apartment or 	
	 * House objects various attributes
	 * */
	private final String building;
	private final int perbuildingCost;
	private final int permaintainCost;
	/*a buildingsEnum constructor that takes in all the attributes of the two Building objects and can be called
	 to make specific types of building
	 */
	private BuildingsEnum(String buildingType,int buildCost,int maintainCost){
		this.building=buildingType;
		this.perbuildingCost=buildCost;
		this.permaintainCost=maintainCost;
	}
	/*getter method to return the String representation of a building
	 * */
	public String getBuilding() {
		return building;
	}
	/*getter method to return the cost of a building
	 * */
	public int getPerbuildingCost() {
		return perbuildingCost;
	}
	/* getter method to return the maintenance cost of a building
	 * */
	public int getPermaintainCost() {
		return permaintainCost;
	}
	/*Taxes are paid by residents of a building and are counted as 
	 * 10% of the building cost by the payTaxes() method
	 * */
	public int payTaxes(BuildingsEnum buildingsEnum) {
		int taxPaid = (int)((buildingsEnum.getPerbuildingCost())*0.1);
		return taxPaid;

	}
	/*
	 * Method that returns the details of any specific building
	 */
	
	public String returnBuildingDetails(BuildingsEnum buildingsEnum){
		return (buildingsEnum.toString() + " with a building cost of "+buildingsEnum.getPerbuildingCost() + " and a maintenance cost of " +buildingsEnum.getPermaintainCost());
	}



	/*Everytime a building gets made, the budget is updated.
	This is done by reducing the starting budget by cost of the building and the maintenance cost.
	 */
	public int UpdateBudget(BuildingsEnum buildingsEnum) {

		Budget.StartingBudget =Budget.StartingBudget-this.getPerbuildingCost()-this.getPermaintainCost()+this.payTaxes(buildingsEnum);
		return Budget.StartingBudget;


	}







}
