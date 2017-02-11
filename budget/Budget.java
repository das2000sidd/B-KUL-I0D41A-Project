package budget;
/* The StartingBudget object will updated in each subclass as different objects are made.*/
public abstract class Budget {
	/*The starting budget*/
	public static int StartingBudget = 1000000;
	public Budget() {

	}
	/*
	 * Abstract method UpdateBudget() that will called in concrete subclasses to update the Budget as objects are made
	 */
	public abstract int UpdateBudget();

}
