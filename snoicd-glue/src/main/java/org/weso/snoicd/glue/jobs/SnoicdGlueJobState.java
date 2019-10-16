package main.java.org.weso.snoicd.glue.jobs;

import java.time.Instant;
import java.util.Date;

/**
 * The type Snoicd glue job state.
 */
public class SnoicdGlueJobState {

    private State state; // The current state.
    private int completionPercentage; // The completion percentage of the job.
    private Date lastUpdatedDate; // The date when the state weas last updated.

    /**
     * The enum State.
     */
    public enum State {
        /**
         * Creating state.
         */
        CREATING,
        /**
         * Ready state.
         */
        READY,
        /**
         * Running state.
         */
        RUNNING,
        /**
         * Stopping state.
         */
        STOPPING
    }

    /**
     * Instantiates a new Snoicd glue job state.
     */
    public SnoicdGlueJobState() {
        this.state=State.CREATING;
        updateLastUpdatedDate(); // Update the date
    }

    /**
     * Sets state.
     *
     * @param newState the new state
     */
    public void setState(State newState) {
        this.state = newState;
        updateLastUpdatedDate(); // Update the date.
    }

    /**
     * Sets completion percentage.
     *
     * @param newCompletionPercentage the new completion percentage
     */
    public void setCompletionPercentage(int newCompletionPercentage) {
        this.completionPercentage = newCompletionPercentage;
        updateLastUpdatedDate(); // Update the date.
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
        return this.state;
    }

    /**
     * Gets completion percentage.
     *
     * @return the completion percentage
     */
    public int getCompletionPercentage() {
        return this.completionPercentage;
    }

    /**
     * Gets last updated date.
     *
     * @return the last updated date
     */
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    /**
     * Updates the date stored at the last updated date
     * to the instant when it is called.
     */
    private void updateLastUpdatedDate() {
        this.lastUpdatedDate = Date.from(Instant.now());
    }
}
