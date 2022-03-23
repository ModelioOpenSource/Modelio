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
package org.modelio.bpmnxml.nodes;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("10580690-2fe4-4ad9-9ebf-236ee7a98576")
public interface IFinaliseNode<U extends MObject, J> extends IProduction {
    @objid ("7335f5ea-46cd-4226-90c0-5bd01181b560")
    U finalizeElement(U modelioElement, J jaxbElement, ICoreSession session, IDiagramService diagramService);

    @objid ("d14017a4-7fe8-4685-aac8-0b51945ee721")
    J finalizeJaxbElement(U modelioElement, J jaxbElement, IDiagramService diagramService);

    @objid ("a5e715a9-193b-4443-8482-23848b31c7b0")
    void setConfiguration(Map<String, Object> configuration);

}
