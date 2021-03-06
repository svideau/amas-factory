/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.amasfactory.agent.impl;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;

/**
 * The implementation of the main skill of an agent
 * 
 * @param <K>
 *            the main knowledge
 */
public abstract class Skill<K extends IKnowledge> implements ISkill<K> {

    protected K knowledge;

    protected Logger logger;

    @Override
    public void setKnowledge(K knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
