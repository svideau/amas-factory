package fr.irit.smac.amasfactory.agent.impl;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.ICommonFeatures;
import fr.irit.smac.libs.tooling.scheduling.IAgentStrategy;

public abstract class OneStepAgent <F extends ICommonFeatures, K extends IKnowledge, S extends ISkill<K>, P extends IFeature<K, S>>
extends Agent<F, K, S, P> implements IAgentStrategy {

}