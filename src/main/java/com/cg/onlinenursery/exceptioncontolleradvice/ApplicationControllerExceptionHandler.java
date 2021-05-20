package com.cg.onlinenursery.exceptioncontolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.onlinenursery.exception.AdminExistsException;
import com.cg.onlinenursery.exception.AdminNotFoundException;
import com.cg.onlinenursery.exception.CustomerIdNotFoundException;
import com.cg.onlinenursery.exception.InvalidDataException;
import com.cg.onlinenursery.exception.OrderIdNotFoundException;
import com.cg.onlinenursery.exception.PlantersIdNotFoundException;
import com.cg.onlinenursery.exception.PlantersShapeNotFoundException;
import com.cg.onlinenursery.exception.PlantsIdNotFoundException;
import com.cg.onlinenursery.exception.SeedsCommonNameNotFoundException;
import com.cg.onlinenursery.exception.SeedsIdNotFoundException;
import com.cg.onlinenursery.exception.TypeofSeedNotFoundException;

@ControllerAdvice
public class ApplicationControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = SeedsIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleMessageIdNotFoundException(SeedsIdNotFoundException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Invalid input value");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TypeofSeedNotFoundException.class)
	public ResponseEntity<?> handleTypeofSeedNotFoundException(TypeofSeedNotFoundException me) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("error", " typeofseed Not found");
		error.put("timestamp", LocalDateTime.now());
		error.put("details", me.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SeedsCommonNameNotFoundException.class)
	public ResponseEntity<?> handleSeedsCommonNameNotFoundException(SeedsCommonNameNotFoundException me) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("error", " common name not found");
		error.put("timestamp", LocalDateTime.now());
		error.put("details", me.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PlantsIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handlePlantsIdNotFoundException(PlantsIdNotFoundException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Invalid  data input");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PlantersIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handlePlantersIdNotFoundException(PlantersIdNotFoundException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong data entered");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PlantersShapeNotFoundException.class)
	public ResponseEntity<?> handlePlantersShapeNotFoundException(PlantersShapeNotFoundException me) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("error", " planterShape not found");
		error.put("timestamp", LocalDateTime.now());
		error.put("details", me.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = OrderIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleOrderIdNotFoundException(OrderIdNotFoundException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong Order id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CustomerIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleCustomerIdNotFoundException(CustomerIdNotFoundException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "invalid data input");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidDataException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleInvalidDataException(InvalidDataException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Data Not Found");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AdminNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleAdminNotFoundException(AdminNotFoundException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong Admin id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AdminExistsException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleAdminExistsException(AdminExistsException me) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "Not Exit Admin");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", me.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}