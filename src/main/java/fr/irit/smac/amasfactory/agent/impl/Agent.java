package fr.irit.smac.amasfactory.agent.impl;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;

/**
 * Abstract class used by subclasses implementing an agent
 *
 * @param <M>
 *            the generic type
 */
public abstract class Agent implements IAgent {

    @JsonProperty
    protected String id;

    @JsonProperty
    protected IKnowledge knowledge;

    protected Logger logger;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public IKnowledge getKnowledge() {
        return this.knowledge;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
