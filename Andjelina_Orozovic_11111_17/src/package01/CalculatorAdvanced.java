package package01;

/**
 * The CalculatorAdvanced program implements an application that 
 * calculates result of some advanced mathematical operations.
 * 
 * @author Andjelina Orozovic, 11111/17
 * @version 1.0
 * @since 2020-12-23
 */
public class CalculatorAdvanced extends Calculator {
	
	/**
	 * This is constructor for class CalculatorAdvanced!
	 */
	public CalculatorAdvanced() {
		super();
		
	}
	
	/**
	 * This method is used to calculate exponential value of number or factorial of number from interval [0, 10]. Exponent can be from interval [0, 9].
	 * Method sets result in currentValue.
	 * @param action is Double type. Can be one of number in interval [0, 10] or character '!'.
	 * @throws NumberNotInAreaException In case of Number not in area!
	 * @throws NotSupportedOperationException In case of Not supported operation!
	 */
	public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException {
		
		Integer currentValue = getCurrentValue().intValue();
		Integer temp = currentValue;
		
		switch(action) {
		
		case '0':
			setCurrentValue(Double.valueOf(1));
			break;
		
		case '1':
			setCurrentValue(Double.valueOf(temp));
			break;
		
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			for(int i = 0; i <= action % 10; i++) {
				 currentValue *= temp;
			}
			setCurrentValue(Double.valueOf(currentValue));
			break;
			
		case '!':
			if(0 <= temp && temp <= 10) {
				int fact = 1;
				for(int i = 1; i <= temp; i++) {
					fact *= i;
				}
				setCurrentValue(Double.valueOf(fact));
			} else throw new NumberNotInAreaException();
			break;
			
		default:
			throw new NotSupportedOperationException();
		}
		
	}
	
	
	/**
	 * This method is used to check if number is Armstrong's number or is Perfect number. Method sets result to currentValue!
	 * @param value is Double type. Can be one of characters: 'A' - for Armstrong's number check, 'P' - for Perfect number check.
	 * @throws NumberNotInAreaException In case of Number not in area!
	 * @throws NotSupportedOperationException In case of Not supported operation!
	 * @return True if number is Armstrong's number (value = 'A') or is Perfect number (value = 'P'), otherwise false.
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		
		Double currentValue = getCurrentValue();
		Integer temp = currentValue.intValue();
		
		if(temp < 1) {
			throw new NumberNotInAreaException();
		} else {
			
			switch(value) {
			
			case 'A':
				return armstrongNumber(temp);
				
			case 'P':
				return perfectNumber(temp);
				
			default:
				throw new NotSupportedOperationException();
			} 
		}
	}
	
	/**
	 * This method is used to check if number is Armstrong's number!
	 * @param temp This is number which is checked whether it is Armstrong's number or not. It must be Integer type.
	 * @return Boolean type. True if number is Armstrong's number, otherwise false.
	 */
	public Boolean armstrongNumber(int temp) {
		
		int temp2 = temp, digits = 0, last = 0, sumA = 0;
		
		while(temp2 > 0) {
			temp2 /= 10;
			digits++;
		}
		
		temp2 = temp;
		
		while(temp2 > 0) {
			last = temp2 % 10;
			for(int i = 2; i <= digits; i++) {
				last *= (temp2 % 10);
			}
			sumA += last;
			temp2 /= 10;
		}
		
		if(temp == sumA ) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * This method is used to check if number is Perfect number!
	 * @param temp This is number which is checked whether it is Perfect number or not. It must be Integer type.
	 * @return Boolean type. True if number is Perfect number, otherwise false.
	 */
	public Boolean perfectNumber(int temp) {
		
		int sum = 0;
		for(int i = 1; i < temp; i++) {
			if(temp % i == 0) {
				sum += i;
			}
		}
		
		if(sum == temp) {
			return true;
		}	
		return false;
		
	}
	
}
