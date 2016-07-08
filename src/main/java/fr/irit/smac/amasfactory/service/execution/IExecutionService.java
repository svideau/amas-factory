package fr.irit.smac.amasfactory.service.execution;

import java.util.concurrent.Future;

import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.libs.tooling.scheduling.IHookHandler;

/**
 * The Interface IExecutionService exposes methods to control the execution of
 * the multi-agent system.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public interface IExecutionService<A>
    extends IAgentEventListener<A>, IInfraService, IHookHandler {
    /**
     * Launches the continuous execution of the system. The system will execute
     * according to the implemented strategy, until the {@link #pause} method is
     * called.
     * <p>
     * This method should return immediately.
     * <p>
     * The parameter <code>milis</code> can be used by the various
     * implementations in order to introduce specific effects.
     * 
     * @param milis
     *            an optional parameter for controlling the execution speed,
     *            whose exact effect depends on the implementation
     */
    public void run(long milis);

    /**
     * Launches an atomic execution of the system. What is exactly an atomic
     * execution is defined by the implemented strategy.
     * 
     * Implementations should ensure that this method is non-blocking, with the
     * effect of waiting on the returned {@link Future} similar to a blocking
     * method.
     * 
     * @return a {@link Future} which will complete when the step finishes
     */
    public Future<?> step();

    /**
     * Pause the execution of the system if it is currently running. Otherwise
     * it should have no effect.
     * 
     * Implementations should ensure that this method is non-blocking, with the
     * effect of waiting on the returned {@link Future} similar to a blocking
     * method.
     * 
     * @return a {@link Future} which will complete when the system is
     *         effectively paused
     */
    public Future<?> pause();

    /**
     * Shutdown the system definitively.
     * 
     * Implementations should ensure that this method is blocking
     *
     * @throws ShutdownRuntimeException
     *             the shutdown runtime exception
     */
    @Override
    public void shutdown() throws ShutdownRuntimeException;
    
    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService);

}
