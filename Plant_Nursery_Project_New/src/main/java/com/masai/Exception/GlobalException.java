package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	
	
	//VALIDATION RELATED EXCEPTION------------------------------------------------------------------------------------------------------------------
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
	ErrorDetails err=new ErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}

	//PLANT EXCEPTION-----------------------------------------------------------------------------------------------------
	@ExceptionHandler(PlantException.class)
	public ResponseEntity<ErrorDetails> plantExceptionHandler(PlantException se,WebRequest req){
		
		ErrorDetails err = new ErrorDetails();
		
		
		err.setTimestamp(LocalDateTime.now());
      
		err.setMessage(se.getMessage());
		
		err.setDetails(req.getDescription(false));
		
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
		
	}
	
	
	// SEED EXCEPTION-----------------------------------------------------------------------------------------------------------------
	@ExceptionHandler(SeedException.class)
	public ResponseEntity<ErrorDetails> seedsExceptionHandler(SeedException se,WebRequest req){
		
		ErrorDetails err = new ErrorDetails();
		
		
		err.setTimestamp(LocalDateTime.now());
      
		err.setMessage(se.getMessage());
		
		err.setDetails(req.getDescription(false));
		
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
		
	}
	
	
	//OTHER EXCEPTION-----------------------------------------------------------------------------------------
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception se,WebRequest req){
		
		ErrorDetails err = new ErrorDetails();
		
		
		err.setTimestamp(LocalDateTime.now());
      
		err.setMessage(se.getMessage());
		
		err.setDetails(req.getDescription(false));
		
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
