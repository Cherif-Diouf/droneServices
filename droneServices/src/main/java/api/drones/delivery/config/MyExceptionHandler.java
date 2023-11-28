package api.drones.delivery.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import api.drones.delivery.exception.DroneException;
import api.drones.delivery.exception.NotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> error = new HashMap<>();
		FieldError err = ex.getBindingResult().getFieldErrors().get(0);
		error.put("error", err.getDefaultMessage());
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Map<String, String> handleRequiredArgument(MissingServletRequestParameterException ex){
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundException.class)
	public Map<String, String> handleObjectNotFound(NotFoundException ex){
		Map<String, String> error = new HashMap<>();
		error.put("error",ex.getMessage());
		return error;
	}
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@ExceptionHandler(DroneException.class)
	public Map<String, String> handleDroneException(DroneException ex){
		Map<String, String> error = new HashMap<>();
		error.put("error",ex.getMessage());
		return error;
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> handleEnumValidator(HttpMessageNotReadableException ex){
		Map<String, String> error = new HashMap<>();
		if(ex.getMessage().startsWith("JSON parse error: Cannot deserialize value of type `api.drones.delivery.entities.DroneState`"))
			error.put("error","Invalid drone state");
		if(ex.getMessage().startsWith("JSON parse error: Cannot deserialize value of type `api.drones.delivery.entities.DroneModel`"))
			error.put("error","Invalid drone model");
		return error;
	}
}
