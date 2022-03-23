/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.uml.communicationdiagram.editor.elements.communicationnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;

/**
 * Utility class that computes communication node symbol.
 */
@objid ("7a514984-55b6-11e2-877f-002564c97630")
public class CommunicationNodeSymbolProvider {
    /**
     * This class is not instantiable.
     */
    @objid ("7a514986-55b6-11e2-877f-002564c97630")
    private  CommunicationNodeSymbolProvider() {
        
    }

    /**
     * Get the communication node label at the following format: "name : representedType [min..max]"
     * @param c the communication node
     * @return the computed label
     */
    @objid ("7a514989-55b6-11e2-877f-002564c97630")
    public static String computeSimpleLabel(final CommunicationNode c) {
        final StringBuilder s = new StringBuilder(60);
        
        if (c.getRepresented() != null) {
            // Compute the label from the instance, the communication node's name is ignored
            computeSimpleLabel(c.getRepresented(), s);
        } else {
            s.append(c.getName());
            s.append(" : ");
        }
        return s.toString();
    }

    /**
     * Get the instance label at the following format: "name : type [min..max]"
     * @param c the instance
     * @param s Where the computed cardinality is appended.
     * @return the computed label
     */
    @objid ("7a52cffd-55b6-11e2-877f-002564c97630")
    private static String computeSimpleLabel(final Instance c, final StringBuilder s) {
        final String name = c.getName();
        
        s.append(name);
        
        computeType(c, s);
        computeCard(c, s);
        return s.toString();
    }

    /**
     * Depending on min and max multiplicities:
     * <ul>
     * <li>unspecified : returns ""
     * <li>0..1 : returns "[0..1]"
     * <li>1..1 : returns ""
     * <li>0..* : returns "[*]"
     * <li>1..* : returns "[1..*]"
     * <li>all other a..b : returns "[a..b]"
     * </ul>
     * @param c The instance.
     * @param s Where the computed cardinality is appended.
     */
    @objid ("7a52d008-55b6-11e2-877f-002564c97630")
    private static void computeCard(final Instance c, final StringBuilder s) {
        final String min = c.getMultiplicityMin();
        final String max = c.getMultiplicityMax();
        
        if (min.isEmpty() && max.isEmpty()) {
            return;
        } else if (min.equals("1") && max.equals("1")) {
            return;
        } else if (min.equals("0") && max.equals("*")) {
            s.append("[*]");
        } else {
            s.append("[");
            s.append(min);
            s.append("..");
            s.append(max);
            s.append("]");
        }
        
    }

    @objid ("7a52d011-55b6-11e2-877f-002564c97630")
    private static void computeType(final Instance c, final StringBuilder s) {
        final NameSpace type = c.getBase();
        s.append(" : ");
        if (type != null) {
            s.append(type.getName());
        }
        
    }

}
