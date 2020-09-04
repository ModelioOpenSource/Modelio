/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.diagram.editor.sequence.elements.message.label;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;

/**
 * Utility class that computes Message symbol.
 */
@objid ("d95a82c9-55b6-11e2-877f-002564c97630")
public class MessageLabelProvider {
    /**
     * This class is not instantiable.
     */
    @objid ("d95a82cb-55b6-11e2-877f-002564c97630")
    private MessageLabelProvider() {
    }

    /**
     * Get the Message label at the following format: "name : representedType [min..max]"
     * @param m the Message
     * @return the computed label
     */
    @objid ("d95a82ce-55b6-11e2-877f-002564c97630")
    public static String computeSimpleLabel(final Message m, boolean withSequence) {
        final StringBuilder s = new StringBuilder(60);
        
        if (withSequence && !m.getSequence().isEmpty()) {
            s.append(m.getSequence()).append(": ");
        }
        
        if (m.getInvoked() != null) {
            // Compute the label from the operation's name
            s.append(m.getInvoked().getName());
            s.append(" (");
            s.append(m.getArgument());
            s.append(")");
        } else if (m.getSignalSignature() != null) {
            // Compute the label from the signal's name
            s.append(m.getSignalSignature().getName());
        } else {
            // Compute the label from the message's name
            s.append(m.getName());
        }
        return s.toString();
    }

}
