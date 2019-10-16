package main.java.org.weso.snoicd.glue.jobs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The type Snoicd job executor manager.
 */
public class SnoicdGlueJobExecutorManager {

    private ConcurrentMap<String, SnoicdGlueJob> jobsPool;
    private ConcurrentMap<String, Thread> threadsPool;

    /**
     * Instantiates a new Snoicd job executor manager.
     */
    public SnoicdGlueJobExecutorManager() {
        jobsPool = new ConcurrentHashMap<>();
        threadsPool = new ConcurrentHashMap<>();
    }

    /**
     * Run all jobs.
     */
    public void runAllJobs() {
        jobsPool.forEach((identifier, snoicdGlueJob) -> runJob(identifier));
    }

    /**
     * Run job.
     *
     * @param identifier the identifier
     */
    public void runJob(String identifier) {
        SnoicdGlueJob job = jobsPool.get(identifier);
        if(!threadsPool.containsKey(identifier)) {
            Thread t = new Thread(job);
            t.start();
            job.getState().setState(SnoicdGlueJobState.State.RUNNING);
            threadsPool.put(identifier, t);
        }
    }

    /**
     * Add job.
     *
     * @param jobIdentifier the job identifier
     * @param jobToAdd      the job to add
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void addJob(String jobIdentifier, SnoicdGlueJob jobToAdd) throws IllegalArgumentException{
        if(!jobsPool.containsKey(jobIdentifier)) {
            jobsPool.put(jobIdentifier, jobToAdd);
        } else {
            throw new IllegalArgumentException("The job submitted already exists in the pool of jobs");
        }
    }

    /**
     * Remove job.
     *
     * @param jobIdentifier the job identifier
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void removeJob(String jobIdentifier) throws IllegalArgumentException {
        if(jobsPool.containsKey(jobIdentifier)) {
            jobsPool.remove(jobIdentifier);
        } else {
            throw new IllegalArgumentException("The identifier submmited does not exists in the jobs pool");
        }
    }

    /**
     * Wait for job to finish.
     *
     * @param identifier the identifier
     */
    public void waitForJobToFinish(String identifier) {
        if(threadsPool.containsKey(identifier)) {
            try {
                jobsPool.get(identifier).getState().setState(SnoicdGlueJobState.State.STOPPING);
                threadsPool.get(identifier).join();
                jobsPool.get(identifier).getState().setState(SnoicdGlueJobState.State.READY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for all jobs to finish.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void waitForAllJobsToFinish() {
        threadsPool.forEach((identifier, thread) -> waitForJobToFinish(identifier));
    }

    /**
     * Gets job.
     *
     * @param identifier the identifier
     * @return the job
     */
    public SnoicdGlueJob getJob(String identifier) {
        return this.jobsPool.get(identifier);
    }

    /**
     * Abort job.
     *
     * @param identifier the identifier
     */
    public void abortJob(String identifier) {
        if(threadsPool.containsKey(identifier)) {
            jobsPool.get(identifier).getState().setState(SnoicdGlueJobState.State.STOPPING);
            threadsPool.get(identifier).interrupt();
            jobsPool.get(identifier).getState().setState(SnoicdGlueJobState.State.READY);
        }
    }

    /**
     * Abort all jobs.
     */
    public void abortAllJobs() {
        threadsPool.forEach((identifier, thread) -> abortJob(identifier));
    }
}
