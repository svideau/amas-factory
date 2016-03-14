package fr.irit.smac.amasfactory.service.agenthandler.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;

public class BasicAgentHandler<A extends IInfrastructureAgent<M>,M> extends AbstractInfraService<A, M> implements IAgentHandlerService<A,M>
{

    private Map<String,A> agentMap;
    private Set<IAgentEventListener<A>> listenerSet;
    
    public BasicAgentHandler()
    {
        super();
        this.agentMap = null;
        this.listenerSet = null;
    }

    @Override
    public void start()
    {
        this.agentMap = Collections.synchronizedMap(new HashMap<String,A>());
        this.listenerSet = Collections.synchronizedSet(new HashSet<IAgentEventListener<A>>());
    }

    @Override
    public void shutdown()
    {  
       this.agentMap = null; 
    }

    @Override
    public Collection<A> getAgents()
    {
        return this.agentMap.values();
    }

    @Override
    public void addAgent(A agent)
    {
        this.agentMap.put(agent.getId(),agent);
        this.notifyAgentAdded(agent);
    }

    @Override
    public void removeAgent(A agent)
    {
        this.agentMap.remove(agent);
        this.notifyAgentRemoved(agent);
    }

    private void notifyAgentAdded(A agent)
    {
        for(IAgentEventListener<A> listener : this.listenerSet) {
            listener.agentAdded(agent);
        }
    }

    private void notifyAgentRemoved(A agent)
    {
        for(IAgentEventListener<A> listener : this.listenerSet) {
            listener.agentRemoved(agent);
        }
    }


    @Override
    public void addAgents(Collection<A> agents)
    {
        for (A a : agents)
        {
            this.addAgent(a);
        }
    }

    @Override
    public void removeAgents(Collection<A> agents)
    {
        for (A a : agents)
        {
            this.removeAgent(a);
        }
        
    }

    @Override
    public void addAgentEventListener(IAgentEventListener<A> listener)
    {
        this.listenerSet.add(listener);
    }

    @Override
    public void removeAgentEventListener(IAgentEventListener<A> listener)
    {
        this.listenerSet.add(listener);
    }

    @Override
    protected void initParameters(JsonElement parameters)
    {
       // nothing to do
    }

    @Override
    public Map<String, A> getAgentMap()
    {
        return Collections.unmodifiableMap(this.agentMap);
    }

    @Override
    public A getAgent(String id)
    {
        return this.agentMap.get(id);
    }

}