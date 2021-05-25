package package01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

class CalculatorAdvancedTest {
	
	private CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculatorAdvanced.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculatorAdvanced() {
		assertThat(null, not(calculatorAdvanced));
	}

	@ParameterizedTest
	@MethodSource("methodWithParameters")
	void testCalculateAdvanced(Double result, Double currentValue, char action)throws NumberNotInAreaException, NotSupportedOperationException {
		
		calculatorAdvanced.setCurrentValue(currentValue);
		calculatorAdvanced.calculateAdvanced(action);
		assertThat(result, is(calculatorAdvanced.getCurrentValue()));
		
	}
	
	private static Stream<Arguments> methodWithParameters() {
	    return Stream.of(
	    		
	      Arguments.of(1.0, 5.4, '0'),
	      
	      Arguments.of(5.0, 5.4, '1'),
	      Arguments.of(0.0, 0.0, '1'),
	      
	      Arguments.of(4.0, -2.4, '2'),
	      Arguments.of(-8.0, -2.4, '3'),
	      Arguments.of(16.0, 2.4, '4'),
	      Arguments.of(32.0, 2.4, '5'),
	      Arguments.of(64.0, 2.4, '6'),
	      Arguments.of(128.0, 2.4, '7'),
	      Arguments.of(256.0, 2.4, '8'),
	      Arguments.of(512.0, 2.4, '9'),
	      
	      Arguments.of(1.0, 0.0, '!'),
	      Arguments.of(3628800.0, 10.0, '!'),
	      Arguments.of(1.0, 1.0, '!')
	      
	);
	}
	
	
	@ParameterizedTest
	@DisplayName("ExceptionTestForCalculateAdvanced")
	@MethodSource("methodWithParameters2")
	void testCalculateAdvancedException(Class<Exception> exceptionType, Double firstOperand, char action) {
		
		calculatorAdvanced.setCurrentValue(firstOperand);
		Exception exception = assertThrows(exceptionType, ()->calculatorAdvanced.calculateAdvanced(action));
		assertThat(exception, is(instanceOf(exceptionType)));
		
	}
	
	static Stream<Arguments> methodWithParameters2() {
		
		return Stream.of(
				
				Arguments.of(NumberNotInAreaException.class, 11.0, '!'),
				Arguments.of(NumberNotInAreaException.class, -1.0, '!'),
				Arguments.of(NotSupportedOperationException.class, 14.0, 'n')
				
				);
		
	}
	
	
	@ParameterizedTest
	@MethodSource("methodWithParameters3")
	void testHasCharacteristic(Boolean result, Double currentValue, char action) throws NumberNotInAreaException,NotSupportedOperationException {
		
		calculatorAdvanced.setCurrentValue(currentValue);
		assertThat(result, is(calculatorAdvanced.hasCharacteristic(action)));
		
	}
	
	
	private static Stream<Arguments> methodWithParameters3() {
	    return Stream.of(
	    		
	      Arguments.of(true, 1.0, 'A'),
	      Arguments.of(true, 153.0, 'A'),
	      Arguments.of(false, 15.0, 'A'),
	      Arguments.of(true, 1634.0, 'A'),
	      
	      Arguments.of(true, 6.0, 'P'),
	      Arguments.of(true, 28.0, 'P'),
	      Arguments.of(false, 3.0, 'P'),
	      Arguments.of(false, 1.0, 'P')
	     
	);
	}
	
	
	@ParameterizedTest
	@DisplayName("ExceptionTestForHasCharacteristic")
	@MethodSource("methodWithParameters4")
	void testHasCharacteristicException(Class<Exception> exceptionType, Double firstOperand, char action) {
		
		calculatorAdvanced.setCurrentValue(firstOperand);
		Exception exception = assertThrows(exceptionType, ()->calculatorAdvanced.hasCharacteristic(action));
		assertThat(exception, is(instanceOf(exceptionType)));
		
	}
	
	static Stream<Arguments> methodWithParameters4() {
		
		return Stream.of(

				Arguments.of(NumberNotInAreaException.class, 0.0, 'A'),
				Arguments.of(NumberNotInAreaException.class, -1.0, 'P'),
				Arguments.of(NotSupportedOperationException.class, 14.0, 'n')

				);
		
	}

}
