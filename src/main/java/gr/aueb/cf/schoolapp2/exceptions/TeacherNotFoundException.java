package gr.aueb.cf.schoolapp2.exceptions;

import java.io.Serial;

public class TeacherNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
