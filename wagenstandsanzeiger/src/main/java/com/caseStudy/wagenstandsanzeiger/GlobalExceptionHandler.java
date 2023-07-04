package com.caseStudy.wagenstandsanzeiger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class-collection provides all custom error messages and a class ErrorResponse for the structure of the custom JSON Error response.
 **/


class ErrorResponse {
    private String error_code;
    private String error_description;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}

class PathNotFound extends RuntimeException {
    PathNotFound() {
        super("The path cannot be resolved because it does not exist.");
    }
}

class XMLNotFound extends RuntimeException {
    XMLNotFound() {
        super("There is no matching XML-File for your input.");
    }
}

class WrongUserInputRil extends RuntimeException {
    WrongUserInputRil() {
        super("Please enter 2-5 characters in the input for ril100.");
    }
}

class WrongUserInputTrainNumber extends RuntimeException {
    WrongUserInputTrainNumber() {
        super("Please only enter 2-4 numeric digits for the train number.");
    }
}

class WrongUserInputWaggonNumber extends RuntimeException {
    WrongUserInputWaggonNumber() {
        super("Please only enter 1-2 numeric digits for the waggon number.");
    }
}

class NoWaggonMatches extends RuntimeException {
    NoWaggonMatches() {
        super("There is no waggon matching your input.");
    }
}

class NoTrainMatches extends RuntimeException {
    NoTrainMatches() {
        super("There is no train matching your input.");
    }
}
class NoIdentifiers extends RuntimeException {
    NoIdentifiers() {
        super("There are no identifiers in the matching XML-section.");
    }
}
class NoMatches extends RuntimeException {
    NoMatches() {
        super("There are no matches regarding your input.");
    }
}

class IncompleteUrlException extends RuntimeException {
    IncompleteUrlException() {
        super("The URL is incomplete. Please use the schema '/api/v1/auto-complete/{userInput}'.");
    }
}

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoMatches.class)
    public ResponseEntity<ErrorResponse> NoMatches(NoMatches ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoTrainMatches.class)
    public ResponseEntity<ErrorResponse> CSVFileEmpty(NoTrainMatches ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoWaggonMatches.class)
    public ResponseEntity<ErrorResponse> UserInputNotAlphabetic(NoWaggonMatches ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoIdentifiers.class)
    public ResponseEntity<ErrorResponse> NoIdentifiers(NoWaggonMatches ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(WrongUserInputWaggonNumber.class)
    public ResponseEntity<ErrorResponse> NoUserInput(WrongUserInputWaggonNumber ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(WrongUserInputTrainNumber.class)
    public ResponseEntity<ErrorResponse> UserInputTooShort(WrongUserInputTrainNumber ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(WrongUserInputRil.class)
    public ResponseEntity<ErrorResponse> HeaderNotFound(WrongUserInputRil ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(IncompleteUrlException.class)
    public ResponseEntity<ErrorResponse> IncompleteUrlException(IncompleteUrlException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(PathNotFound.class)
    public ResponseEntity<ErrorResponse> IncompleteUrlException(PathNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(XMLNotFound.class)
    public ResponseEntity<ErrorResponse> IncompleteUrlException(XMLNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setError_description(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError_code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorResponse.setError_description("An unexpected error occurred. Further information: " + ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
