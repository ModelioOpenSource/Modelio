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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("fb8a05b3-dc3b-4739-a9bf-3bf676bf069a")
public class OBpmnBehavior extends OElement implements IOElement {
    @objid ("0e2f95a0-94fd-4adf-a72b-de5bebf9db88")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("180d2fa7-d3ed-4256-bfa6-b6b0d44cc156")
    public OBpmnBehavior(BpmnSharedDefinitions element) {
        super(element);
        String errorMessage = Xmi.I18N.getMessage("logFile.warning.export.bpmnelement.title",
                element.getName());
        String descMessage = Xmi.I18N.getMessage("logFile.warning.export.bpmnelement.description",
                element.getName());
        GenerationProperties.getInstance().addWarning(errorMessage, element, descMessage);
    }

    @objid ("f0e8dcc0-f563-42aa-9249-8208fe44859b")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
    }

    @objid ("f7889333-6650-4e27-8129-5e24658c97ad")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
    }

}
