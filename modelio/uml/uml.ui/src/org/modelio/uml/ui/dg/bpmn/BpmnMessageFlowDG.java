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
package org.modelio.uml.ui.dg.bpmn;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'BpmnMessageFlow' element.
 */
@objid ("c91884cf-6be1-4026-819d-b246bb07f1d0")
public class BpmnMessageFlowDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The graphic model link represented by this class.
     */
    @objid ("57028a04-1032-4918-a607-87c290d2feed")
    public  BpmnMessageFlowDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("2822d023-89ec-4b60-a703-f6dbd2a52dd5")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        return defaultGetGmNodes(role);
    }

}
