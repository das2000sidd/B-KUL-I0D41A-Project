package amenity;

import budget.Budget;

public enum AmenityEnum {
	/*Factory, FireStation,Hospital,Park are all object of class Amenity 
      Format- EnumName(enumAmenityStringRepresentation,amenityBuildCost,amnityMaintainCost)*/
	Factory("FA",5000,500),FireStation("FS",10000,1000),Hospital("HOSP",10000,1000),Park("P",5000,1000);


	/*
	 * amenity- String representation of and land Surface
	 * amenityCost- Cost of each amenity as mentioned in the enum above
	 * amenityMaintenanceCost - Maintenance cost that is to be paid when each amenity is made
	 */

	private final String amenity; 
	private final int amenityCost;
	private final int amenityMaintenanceCost;

	/* Constructor	that takes in all attributes 
	 * of an amenity to build specific type of amenities
	 * */
	AmenityEnum(String amenityType,int amenityBuildCost,int amenityMaintainCost){
		this.amenity=amenityType;
		this.amenityCost=amenityBuildCost;
		this.amenityMaintenanceCost=amenityMaintainCost;
	}
	/* Getters for attributes of the
	 *  AmneityEnum object
	 *  */
	public String getAmenitySymbol() {
		return amenity;
	}
	public int getAmenityCostEnum() {
		return amenityCost;
	}
	public int getAmenityMaintenanceCostEnum() {
		return amenityMaintenanceCost;
	}
	/*
	 * There is no implemenetation of the default UpdateBudget method. 
	 * It is overloaded using an AmenityEnum object as mentioned below
	 */
	public int UpdateBudget(){
		return 0;
	}

	/*Every time an amenity gets made, update the budget by subtracting the 
cost of the amenity and the maintenance cost of the amenity
The budget is updated after making a amenity by subtracting starting budget from amenity and maintenance cost and adding the tax amount per amenity
The tax deduction only works if amenity Enum called by payTaxes() method is of type Factory
Tax is the incoming money
	 */
	public int UpdateBudget(AmenityEnum amenityEnum){
		Budget.StartingBudget = Budget.StartingBudget- this.getAmenityCostEnum()-this.getAmenityMaintenanceCostEnum() + this.payTaxes(amenityEnum);
		return Budget.StartingBudget;


	}




	/*This method returns the tax paid for amenities if it is a factory 
 and is calculated as 10% of the amenity buying cost.
If the amenity object is not a factory, amount returned is zero
	 */
	public int payTaxes(AmenityEnum amenity) {
		int totalTaxPaid=0;
		if(this.amenity.equals(amenity.Factory.getAmenitySymbol())){
			// TODO Auto-generated method stub
			totalTaxPaid = (int) (this.Factory.getAmenityCostEnum()*0.1);

		} else{
			totalTaxPaid=0;
		}
		return totalTaxPaid;
	}

}