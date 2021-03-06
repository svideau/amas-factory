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
package fr.irit.smac.amasfactory.message;

/**
 * A standard message. Can be extends by other messages to add new
 * functionnalities
 */
public class Message implements IMessage {

    final IMessageType messageType;
    protected String   sender;

    public Message(IMessageType messageType, String sender) {
        this.messageType = messageType;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message type: " + messageType.getName();
    }

    @Override
    public IMessageType getMessageType() {
        return messageType;
    }

    @Override
    public String getSender() {
        return sender;
    }
}
