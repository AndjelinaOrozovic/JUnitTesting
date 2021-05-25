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


class CalculatorTest {
	
	private Calculator calculator = new Calculator();

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
		calculator.setCurrentValue(Double.valueOf(0));
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculator() {
		assertThat(null, not(calculator));
	}

	@Test
	public void testGetCurrentValue() {
		assertThat(0.0, is(calculator.getCurrentValue()));
	}

	@Test
	public void testSetCurrentValue() {
		calculator.setCurrentValue(Double.valueOf(42));
		assertThat(Double.valueOf(42), is(calculator.getCurrentValue()));
	}

	@ParameterizedTest
	@MethodSource("methodWithParameters")
	void testCalculate(Double result, Double currentValue, Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		
		calculator.setCurrentValue(currentValue);
		calculator.calculate(value, operator);
		assertThat(result, is(closeTo(calculator.getCurrentValue(), 0.000000000000003)));
	
	}
	
	private static Stream<Arguments> methodWithParameters() {
	    return Stream.of(
	    		
	      Arguments.of(6.5, 2.3, 4.2, '+'),
	      Arguments.of(10.0, -4.0, 14.0, '+'),
	      Arguments.of(-3.0, 3.0, -6.0, '+'),
	      Arguments.of(-10.0, -6.0, -4.0, '+'),
	      Arguments.of(0.0, 0.0, 0.0, '+'),
	      
	      Arguments.of(-1.9, 2.3, 4.2, '-'),
	      Arguments.of(-18.0, -4.0, 14.0, '-'),
	      Arguments.of(9.0, 3.0, -6.0, '-'),
	      Arguments.of(10.0, 6.0, -4.0, '-'),
	      Arguments.of(0.0, 0.0, 0.0, '-'),
	      
	      Arguments.of(9.66, 2.3, 4.2, '*'),
	      Arguments.of(-56.0, -4.0, 14.0, '*'),
	      Arguments.of(-18.0, 3.0, -6.0, '*'),
	      Arguments.of(24.0, -6.0, -4.0,'*'),
	      Arguments.of(0.0, 0.0, 0.0, '*'),
	      
	      Arguments.of(0.25, 1.0, 4.0, '/'),
	      Arguments.of(-0.25, -1.0, 4.0, '/'),
	      Arguments.of(-0.25, 1.0, -4.0, '/'),
	      Arguments.of(0.25, -1.0, -4.0,'/'),
	      Arguments.of(0.0,0.0,5.0,'/')
	     
	    );
	}
	
	@ParameterizedTest
	@DisplayName("ExceptionTest")
	@MethodSource("methodWithParameters2")
	void testCalculateException(Class<Exception> exceptionType, Double secondOperand, char operator) {
		
		calculator.setCurrentValue(Double.valueOf(4));
		Exception exception = assertThrows(exceptionType, ()->calculator.calculate(secondOperand, operator));
		assertThat(exception, is(instanceOf(exceptionType)));
		
	}
	
	static Stream<Arguments> methodWithParameters2() {
		
		return Stream.of(
				
				Arguments.of(DivisionByZeroException.class, Double.valueOf(0), '/'),
				Arguments.of(NotSupportedOperationException.class, 1.9, 'n'),
				Arguments.of(NullPointerException.class, null, '+')
				
				);
		
	}
	
}
