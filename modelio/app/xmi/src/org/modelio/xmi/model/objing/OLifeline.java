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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("d825e0f5-dbf1-420a-b91a-cb40617c6dd4")
public class OLifeline extends OModelElement {
    @objid ("3c6e895c-49ef-45ad-954c-6312cd91d53e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createLifeline();
    }

    @objid ("477df7b7-afe1-4707-a46d-549dae06c9a3")
    public OLifeline(Lifeline param) {
        super(param);
    }

    @objid ("73901a99-d8cf-4441-98ad-f6281845071e")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Interaction objingOwner = ((Lifeline) getObjingElement()).getOwner();
        org.eclipse.uml2.uml.Interaction ecoreOwner = (org.eclipse.uml2.uml.Interaction) GenerationProperties.getInstance()
                .getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            ecoreOwner.getLifelines().add((org.eclipse.uml2.uml.Lifeline)ecoreElt);
        }
    }

    @objid ("c9fc9ebf-0714-4ad1-8395-07792739877f")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setSelector((org.eclipse.uml2.uml.Lifeline) ecoreElt);
        setRepresented((org.eclipse.uml2.uml.Lifeline) ecoreElt);
    }

    @objid ("f949947f-6f00-441e-bc72-6a36c36ca324")
    private void setRepresented(org.eclipse.uml2.uml.Lifeline ecoreElt) {
        Lifeline lifeline = (Lifeline) getObjingElement();
        Element objType = lifeline.getRepresented();
        
        if (objType != null) {
            GenerationProperties genProp = GenerationProperties.getInstance();
            org.eclipse.uml2.uml.Element type = genProp.getMappedElement(objType);
            
            if (type instanceof org.eclipse.uml2.uml.ConnectableElement){
                ecoreElt.setRepresents((org.eclipse.uml2.uml.ConnectableElement)type);
            }else{
                String message = Xmi.I18N.getMessage("logFile.warning.unsupportedTypeExport.message");
                String description = Xmi.I18N.getMessage("logFile.warning.unsupportedTypeExport.description", 
                        ((Instance)objType).getName(), 
                        lifeline.getClass().getSimpleName());
                genProp.addWarning(message, lifeline, description);
            }
        
        }
    }

    @objid ("5a61716e-a363-4a2a-9957-84a4905c1139")
    private void setSelector(org.eclipse.uml2.uml.Lifeline lifeline) {
        String selector = ((Lifeline) getObjingElement()).getSelector();
        if (selector != null && selector.trim().length() > 0) {
            org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE
                    .createLiteralString();
            valueSpecification.setValue(selector);
            lifeline.setSelector(valueSpecification);
        }
    }

}
