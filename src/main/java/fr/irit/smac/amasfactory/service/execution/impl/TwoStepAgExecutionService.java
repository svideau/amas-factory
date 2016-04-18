package fr.irit.smac.amasfactory.service.execution.impl;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.TwoStepsSystemStrategy;
import fr.irit.smac.libs.tooling.scheduling.impl.system.SynchronizedSystemStrategy;

/**
 * TwoStepAgExecutionService is a service which handles the execution of the
 * multi-agent system.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public class TwoStepAgExecutionService<A extends ITwoStepsAgent & IInfrastructureAgent<M>, M>
    extends AbstractInfraService<A, M>implements IExecutionService<A, M> {

    private TwoStepsSystemStrategy systemStrategy;

    private static final Logger LOGGER = Logger.getLogger(SynchronizedSystemStrategy.class.getName());

    @JsonProperty
    private int nbThreads;

    /**
     * Instantiates a new two step ag execution service.
     */
    public TwoStepAgExecutionService() {
        super();
        this.systemStrategy = null;
        this.nbThreads = Integer.MIN_VALUE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        // first create the scheduler instance before registering
        // as agent listener (in order to assure that agentAdded and
        // agentRemoved
        // will not be called while this.systemStrategy = null)

        this.systemStrategy = new TwoStepsSystemStrategy(Collections.emptyList(),
            Executors.newFixedThreadPool(this.nbThreads));

        this.getInfrastructure().getAgentHandler().addAgentEventListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.service.execution.IExecutionService#run(long)
     */
    @Override
    public void run(long milis) {
        this.systemStrategy.run(milis);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.execution.IExecutionService#step()
     */
    @Override
    public Future<?> step() {
        return this.systemStrategy.step();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.execution.IExecutionService#pause()
     */
    @Override
    public Future<?> pause() {
        return this.systemStrategy.step();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() throws ShutdownRuntimeException {
        try {
            this.systemStrategy.shutdown().get();
        }
        catch (InterruptedException | ExecutionException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            throw new ShutdownRuntimeException("Exception during shutdown of TwoStepAgentExecutionService");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener#
     * agentAdded(java.lang.Object)
     */
    @Override
    public void agentAdded(A agent) {
        this.systemStrategy.addAgent(agent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener#
     * agentRemoved(java.lang.Object)
     */
    @Override
    public void agentRemoved(A agent) {
        this.systemStrategy.removeAgent(agent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#addPreStepHook(java.
     * lang.Runnable)
     */
    @Override
    public void addPreStepHook(Runnable task) {
        this.systemStrategy.addPreStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#addPostStepHook(java.
     * lang.Runnable)
     */
    @Override
    public void addPostStepHook(Runnable task) {
        this.systemStrategy.addPostStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#removePreStepHook(java.
     * lang.Runnable)
     */
    @Override
    public void removePreStepHook(Runnable task) {
        this.systemStrategy.removePreStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#removePostStepHook(java
     * .lang.Runnable)
     */
    @Override
    public void removePostStepHook(Runnable task) {
        this.systemStrategy.removePostStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.scheduling.IHookHandler#getPreStepHooks()
     */
    @Override
    public Set<Runnable> getPreStepHooks() {
        return this.getPreStepHooks();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.scheduling.IHookHandler#getPostStepHooks()
     */
    @Override
    public Set<Runnable> getPostStepHooks() {
        return this.getPostStepHooks();
    }
}
