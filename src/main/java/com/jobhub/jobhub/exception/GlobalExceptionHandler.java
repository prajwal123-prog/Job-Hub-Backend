package com.jobhub.jobhub.exception;

import com.jobhub.jobhub.dto.ApiResponse;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiResponse<Object>> handleDuplicateEmail(DuplicateEmailException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(DuplicateApplicationException.class)
	public ResponseEntity<ApiResponse<Object>> handleDuplicateApplication(DuplicateApplicationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<ApiResponse<Object>> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName;

			if (error instanceof FieldError) {
				fieldName = ((FieldError) error).getField();
			} else {
				// Handles class-level validation, such as @ValidSalaryRange
				fieldName = "salaryRange";
			}

			errors.put(fieldName, error.getDefaultMessage());
		});

		ApiResponse<Map<String, String>> response =
				new ApiResponse<>(false, "Validation failed", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<Object>> handleInvalidRequestBody(
			HttpMessageNotReadableException ex) {

		String message = "Invalid request body. Check your JSON and field values.";

		String details = ex.getMostSpecificCause().getMessage();

		if (details != null && details.contains("JobType")) {
			message = "Invalid job type. Allowed values: FULL_TIME, PART_TIME, INTERNSHIP, CONTRACT";
		} else if (details != null && details.contains("ApplicationStatus")) {
			message = "Invalid application status. Allowed values: APPLIED, REVIEWING, SHORTLISTED, REJECTED, SELECTED";
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.error(message));
	}


	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Object>> handleAccessDenied(
			AccessDeniedException ex) {

		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(ApiResponse.error(
						"You do not have permission to perform this action"));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.error("An unexpected error occurred: " + ex.getMessage()));
	}
}
