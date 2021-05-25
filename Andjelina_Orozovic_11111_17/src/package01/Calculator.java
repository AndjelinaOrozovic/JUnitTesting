package package01;

/**
 * The Calculator program implements an application that 
 * calculates result of basic binary operations.
 * 
 * @author Andjelina Orozovic, 11111/17
 * @version 1.0
 * @since 2020-12-23
 */
public class Calculator {
	
	/**
	 * currentValue This is Double type which represents first parameter of binary operations. 
	 */
	private Double currentValue;
	
	/**
	 * This is constructor for class Calculator! It initialises currentValue to 0.0. 
	 */
	public Calculator() {
		this.currentValue = Double.valueOf(0);
	}
	
	/**
	 * Get method for reading a Double value of currentValue, which is attribute in class Calculator.
	 * @return currentValue is Double type.
	 */
	public Double getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * Set method for setting a currentValue to some Double value.
	 * @param currentValue is Double type.
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	/**
	 * This method is used to calculate result of basic binary operations! Operations can be +, -, * and /. Method sets result in currentValue.
	 * In case of devision by 0 this method throws DevisionByZeroException.
	 * @param value This is Double type parameter which represent second parameter (first parameter is currentValue).
	 * @param operator This is operator which can be +, -, * and /.
	 * @throws NotSupportedOperationException In case of Not supported operation!
	 * @throws DivisionByZeroException In case of Division by 0!
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException, NullPointerException {
		
		if (value == null) {
			
			throw new NullPointerException();
		
		} else {
		
		switch(operator) {
		
		case '+' :
			this.currentValue += value;
			break;
		case '-' :
			this.currentValue -= value;
			break;
		case '*' :
			this.currentValue *= value;
			break;
		case '/':
			if(value == 0.0) { 
				throw new DivisionByZeroException();
			} else 
				this.currentValue /= value;
			break;
		default:
			throw new NotSupportedOperationException();
			
			}
		
		} 
		
	}
	
}
	

