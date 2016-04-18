package fr.irit.smac.amasfactory.impl;

import java.io.IOException;
import java.io.InputStream;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.util.deserializer.impl.AmasFactoryDeserializer;

/**
 * A factory for creating a BasicAmas.
 *
 * @author lemouzy
 */
public class BasicAmasFactory implements IAmasFactory {

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IAmasFactory#createInfrastructure(java.io.
     * InputStream)
     */
    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) throws IOException {

        AmasFactoryDeserializer parser = AmasFactoryDeserializer.getInstance();

        return parser.deserializeInfrastructure(configuration);
    }
}
