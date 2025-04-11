package gr.aueb.cf.schoolapp2.exceptions;

import java.io.Serial;

public class StudentDAOException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public StudentDAOException(String st) {
        super(st);
    }
}
