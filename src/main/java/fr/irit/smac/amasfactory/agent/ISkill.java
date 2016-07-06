package fr.irit.smac.amasfactory.agent;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface ISkill<K extends IKnowledge> {

    public void setKnowledge(K knowledge);

    public void setLogger(Logger logger);
}
