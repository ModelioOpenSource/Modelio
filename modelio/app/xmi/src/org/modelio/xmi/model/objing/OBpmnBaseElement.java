/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("90c23d80-db23-410c-a534-f02703f3632a")
public class OBpmnBaseElement extends OElement implements IOElement {
    @objid ("97bb050d-e7f1-4cc3-ae81-eec50347bbcc")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("30523249-f31c-45ec-8386-6eb728908963")
    public OBpmnBaseElement(BpmnBaseElement element) {
        super(element);
        String errorMessage =   Xmi.I18N.getMessage("logFile.warning.export.bpmnelement.title",
                element.getName());
        String descMessage =   Xmi.I18N.getMessage("logFile.warning.export.bpmnelement.description",
                element.getName());
        GenerationProperties.getInstance().addWarning( errorMessage, element, descMessage);
    }

    @objid ("d4bde130-b083-4096-ae35-b74fcf0c794d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
    }

    @objid ("7c7a8bde-a7d1-40c6-b4a7-484574b1f359")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
    }

}
