/*
 * 
 */
package fr.irit.smac.amasfactory.agent.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;

/**
 * Abstract class used by subclasses implementing an agent
 *
 * @param <M>
 *            the generic type
 */
public abstract class AbsInfrastructureAgent<M> implements IInfrastructureAgent<M> {

    @JsonProperty
    private String id;

    private IAgentSideInfrastructure<M> infrastructure;

    @JsonProperty
    private IKnowledge knowledge;

    /**
     * Gets the infra.
     *
     * @return the infra
     */
    protected IAgentSideInfrastructure<M> getInfra() {
        return this.infrastructure;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.agent.IInfrastructureAgent#getId()
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Inits the parameters.
     */
    protected abstract void initParameters();

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.agent.IInfrastructureAgent#getInnerKnowledge()
     */
    @Override
    public IKnowledge getInnerKnowledge() {
        return this.knowledge;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.agent.IInfrastructureAgent#init(fr.irit.smac.
     * amasfactory.IAgentSideInfrastructure, java.lang.String)
     */
    @Override
    public void init(IAgentSideInfrastructure<M> infrastructure, String id) {
        this.infrastructure = infrastructure;
        this.id = id;

        this.initParameters();
    }
}