package de.pr1meti.ls4pde.server.diagnosis.preproc;

/**
 * Exception indicating that a preprocessor issue was found.
 */
public class PdePreprocessIssueException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = 5373498389725415997L;
	private final PdePreprocessIssue preprocessIssue;

    /**
     * Create a new exception indicating that there was a preprocessing issue.
     *
     * @param newPreprocessIssue Issue encountered.
     */
    public PdePreprocessIssueException(PdePreprocessIssue newPreprocessIssue) {
        super(newPreprocessIssue.getMsg());
        preprocessIssue = newPreprocessIssue;
    }

    /**
     * Get information about the preprocessing issue found.
     *
     * @return Record of the preprocessor issue.
     */
    public PdePreprocessIssue getIssue() {
        return preprocessIssue;
    }

}
