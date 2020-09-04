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

package org.modelio.uml.ui.browser.contrib.labelprovider.builders;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.modelio.core.ui.swt.images.ElementStyler;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;

/**
 * This class computes a label for a BpmnItemAwareElement (aka data objects).
 * 
 * The computed label is NNN:TTT:SSS where NNN is the data object name, TTT its 'type' name and SSS its 'state' name. (TTT and SSS) are optional.
 */
@objid ("a00b3211-0409-48e0-aed5-dc3ed9f5d356")
public class BpmnMessageLabelBuilder {
    /**
     * Elaborate a name for the 'referenced' element of the Message. May return null;
     */
    @objid ("3b217b97-ab7a-4133-9f29-9ae391a418dc")
    private static StyledString getReferencedElementName(BpmnMessage m) {
        final StyledString symbol = new StyledString();
        
        ModelElement type = Represents.getTarget(m);
        if (type != null) {
            symbol.append(type.getName(), ElementStyler.getStyler(type));
        } else if (m.getItemRef() != null) {
            BpmnItemDefinition item = m.getItemRef();
            ModelElement ref = Reference.getTarget(item);
            if (ref != null) {
                symbol.append(ref.getName(), ElementStyler.getStyler(ref));
            } else {
                symbol.append(item.getName(), ElementStyler.getStyler(item));
            }
        } else {
            return null;
        }
        return symbol;
    }

    /**
     * Computes a label for a BpmnItemAwareElement
     * @param elt the data object whose name is to be computed
     * @return the computed name
     */
    @objid ("4e29a883-c404-492c-b51b-112fb8d87bc0")
    public static StyledString computeLabel(BpmnMessage m) {
        final StyledString symbol = new StyledString();
        final Styler styler = ElementStyler.getStyler(m);
        
        if (m != null && m.isValid()) {
            symbol.append(m.getName());
        
            // Append represented element name if required
            StyledString referenceName = getReferencedElementName(m);
            if (referenceName != null) {
                symbol.append(" : ");
                symbol.append(referenceName);
            }
        
            // Append state
            ModelElement inState = State.getTarget(m);
            if (inState != null) {
                symbol.append(" [", styler);
                symbol.append(inState.getName(), ElementStyler.getStyler(inState));
                symbol.append("]", styler);
            }
        
        }
        return symbol;
    }

}
