package main.java.org.weso.snoicd.glue;

import main.java.org.weso.snoicd.glue.jobs.SnoicdGlueJob;
import main.java.org.weso.snoicd.glue.jobs.SnoicdGlueJobExecutorManager;

public class SnoicdGlueImpl implements SnoicdGlue {

    private final SnoicdGlueJobExecutorManager jobsManager;

    public SnoicdGlueImpl() {
        jobsManager = new SnoicdGlueJobExecutorManager();
    }

    @Override
    public void submitJob(SnoicdGlueJob job) throws IllegalArgumentException {
        this.jobsManager.addJob(job.getJobIdentifier(), job);
    }

    @Override
    public void removeAllJobs() {
        this.jobsManager.removeJob("");
    }

    @Override
    public void removeJob(String identifier) throws IllegalArgumentException {
        this.jobsManager.removeJob("");
    }

    @Override
    public void executeAllJobs() {
        this.jobsManager.runAllJobs();
    }

    @Override
    public void executeJob(String identifier) {
        this.jobsManager.runJob(identifier);
    }

    @Override
    public void waitUntillAllJobsFinished() {
        this.jobsManager.waitForAllJobsToFinish();
    }

    @Override
    public void waitUntillJobFinished(String identifier) {
        this.jobsManager.waitForJobToFinish(identifier);
    }

    @Override
    public SnoicdGlueJob getJob(String identifier) {
        return this.jobsManager.getJob(identifier);
    }
}
