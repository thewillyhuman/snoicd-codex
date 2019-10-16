package main.java.org.weso.snoicd.glue;

import main.java.org.weso.snoicd.glue.jobs.SnoicdGlueJob;

/**
 * The interface Snoicd glue.
 */
public interface SnoicdGlue {

    /**
     * Submit job.
     *
     * @param job the job
     * @throws IllegalArgumentException the illegal argument exception
     */
    void submitJob(SnoicdGlueJob job) throws IllegalArgumentException;

    /**
     * Remove all jobs.
     */
    void removeAllJobs();

    /**
     * Remove job.
     *
     * @param identifier of the job to remove
     * @throws IllegalArgumentException the illegal argument exception
     */
    void removeJob(String identifier) throws  IllegalArgumentException;

    /**
     * Execute all jobs.
     */
    void executeAllJobs();

    /**
     * Execute job.
     *
     * @param identifier the identifier
     */
    void executeJob(String identifier);

    /**
     * Wait untill all jobs finished.
     */
    void waitUntillAllJobsFinished();

    /**
     * Wait untill job finished.
     *
     * @param identifier the identifier
     */
    void waitUntillJobFinished(String identifier);

    /**
     * Gets job.
     *
     * @param identifier the identifier
     * @return the job
     */
    SnoicdGlueJob getJob(String identifier);
}
