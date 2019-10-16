package main.java.org.weso.snoicd.glue.jobs;

public abstract class SnoicdGlueAbstractJob implements SnoicdGlueJob {

    private final SnoicdGlueJobState state = new SnoicdGlueJobState();
    private final boolean isJobRunning = false;
    private final boolean hasJoobBeenStarted = false;
    private final String jobIdentifier;

    public SnoicdGlueAbstractJob(String jobIdentifier) {
        this.jobIdentifier = jobIdentifier;
    }

    @Override
    public boolean isRunning() {
        return this.isJobRunning;
    }

    @Override
    public boolean hasBeenStarted() {
        return this.hasJoobBeenStarted;
    }

    @Override
    public SnoicdGlueJobState getState() {
        return this.state;
    }

    @Override
    public String getJobIdentifier() {
        return this.jobIdentifier;
    }
}
