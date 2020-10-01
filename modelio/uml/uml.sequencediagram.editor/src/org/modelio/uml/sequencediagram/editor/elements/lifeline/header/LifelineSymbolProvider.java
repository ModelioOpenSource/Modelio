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

package org.modelio.uml.sequencediagram.editor.elements.lifeline.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;

/**
 * Utility class that computes lifeline symbol.
 */
@objid ("d949b9ce-55b6-11e2-877f-002564c97630")
public class LifelineSymbolProvider {
    /**
     * This class is not instantiable.
     */
    @objid ("d949b9d0-55b6-11e2-877f-002564c97630")
    private LifelineSymbolProvider() {
    }

    /**
     * Get the lifeline label at the following format: "name : representedType [min..max]"
     * 
     * @param lifeline the lifeline
     * @return the computed label
     */
    @objid ("d949b9d3-55b6-11e2-877f-002564c97630")
    public static String computeSimpleLabel(final Lifeline lifeline) {
        /*
            From UML 2.5 spec: 
            Information identifying the lifeline is displayed inside the rectangle in the following format:
            
            <lifelineident> ::= ([<connectable-element-name>[‘[‘ <selector> ‘]’]] [: <class_name>] [decomposition]) | ‘self’
            <selector> ::= <expression>
            <decomposition> ::= ‘ref’ <interactionident> [‘strict’]
            
            where <class-name> is the type referenced by the represented ConnectableElement. 
            Note that, although the syntax allows it, <lifelineident> cannot be empty.
        
         */
        final StringBuilder s = new StringBuilder(60);
        
        if (lifeline.getRepresented() != null) {
            // Compute the label from the instance, the lifeline's name is ignored
            computeSimpleLabel(lifeline, lifeline.getRepresented(), s);
        } else {
            s.append(lifeline.getName());
            s.append(" : ");
        }
        return s.toString();
    }

    /**
     * Get the instance label at the following format: "name : type [min..max]"
     * 
     * @param c the instance
     * @param s Where the computed cardinality is appended.
     * @return the computed label
     */
    @objid ("d949b9dc-55b6-11e2-877f-002564c97630")
    private static String computeSimpleLabel(Lifeline lifeline, final Instance c, final StringBuilder s) {
        final String name = c.getName();
        
        s.append(name);
        
        
        if (lifeline.getSelector().isEmpty()) {
            computeType(c, s);
            computeCard(c, s); // this does not conform to UML 2.5 spec
        } else {
            s.append("[").append(lifeline.getSelector()).append("]");
            computeType(c, s);
        }
        return s.toString();
    }

    /**
     * Depending on min and max multiplicities:
     * <ul>
     * <li>unspecified : returns ""
     * <li>0..1 : returns "" since 3.7.1 (was "[0..1]" before)
     * <li>1..1 : returns ""
     * <li>0..* : returns "[*]"
     * <li>1..* : returns "[1..*]"
     * <li>all other a..b : returns "[a..b]"
     * </ul>
     * 
     * @param c The instance.
     * @param s Where the computed cardinality is appended.
     */
    @objid ("d949b9e7-55b6-11e2-877f-002564c97630")
    private static void computeCard(final Instance c, final StringBuilder s) {
        final String min = c.getMultiplicityMin();
        final String max = c.getMultiplicityMax();
        
        if (min.isEmpty() && max.isEmpty()) {
            return;
        } else if (/*min.equals("1") &&*/ max.equals("1")) {
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

    @objid ("d949b9f0-55b6-11e2-877f-002564c97630")
    private static void computeType(final Instance c, final StringBuilder s) {
        final NameSpace type = c.getBase();
        s.append(" : ");
        if (type != null) {
            s.append(type.getName());
        }
    }

}
