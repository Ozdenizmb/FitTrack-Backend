package com.ozdeniz.fittrack.exception;

public class ErrorMessages {

    private ErrorMessages(){}

    public static final String DEFAULT_ERROR_MESSAGE = "An unexpected error occurred! Please contact the api support!";

    public static final String USER_NOT_FOUND = "User Not Found!";

    public static final String INCORRECT_LOGIN = "Incorrect Email or Password Entry";

    public static final String EMAIL_NOT_FOUND = "Email Not Found!";

    public static final String UNAUTHORIZED = "Unauthorized!";

    public static final String FILE_NOT_FOUND = "File Not Found!";

    public static final String FILE_CANNOT_DELETE = "An error occurred while deleting the file!";

    public static final String FILE_CANNOT_WRITE = "An error occurred while uploading the file!";

    public static final String UNSUPPORTED_FILE_TYPE = "Unsupported file. Only PNG, JPEG and JPG supported";

    public static final String TRAINING_NOT_FOUND = "Training Not Found!";

}
