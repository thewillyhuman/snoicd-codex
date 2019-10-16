package main.java.org.weso.snoicd.glue.jobs;

/**
 * The interface Snoicd glue job.
 */
public interface SnoicdGlueJob extends Runnable {

    /**
     * Checks whether a job is running or not.
     *
     * @return true if the job is running. False otherwise.
     */
    boolean isRunning();

    /**
     * Checks wheter a job has been started. Notice that a
     * job might not been running but been started previously.
     *
     * @return true if the job has been started. False otherwise.
     */
    boolean hasBeenStarted();

    /**
     * Gets the current state of the job.
     *
     * @return the state
     */
    SnoicdGlueJobState getState();

    /**
     * Gets job identifier.
     *
     * @return the job identifier
     */
    String getJobIdentifier();

    /**
     * Gets scheduller.
     *
     * @return the scheduller
     */
    SnoicdGlueJobScheduller getScheduller();
}
