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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ClassifierTemplateParameter;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("91361f3c-fce3-4cd8-9317-d7818ab0b38d")
public class EClassifierTemplateParameter extends EElement {
    @objid ("63e47653-bcdd-4279-8af7-30dff2bed48e")
    private org.eclipse.uml2.uml.ClassifierTemplateParameter ecoreElement = null;

    @objid ("a4b4f148-2607-4336-8938-d878f2b59369")
    @Override
    public Element createObjingElt() {
        return UML2ClassifierTemplateParameter.create().getElement();
    }

    @objid ("02ffe9ab-f6e9-4df7-bf6b-fc6cf465fd34")
    public EClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("29b3c19b-6b8f-4844-b8de-b097eb1bbff7")
    @Override
    public void attach(Element objingElt) {
        Element objingOperation  = (Element) ReverseProperties.getInstance()
                .getMappedElement(this.ecoreElement.getOwner());
        
        if ((objingOperation != null ) && (objingOperation instanceof Operation)) {
            ((Operation) objingOperation)
            .getTemplate().add((TemplateParameter) objingElt);
        }else if (this.ecoreElement.getOwner().getOwner() instanceof  org.eclipse.uml2.uml.Collaboration){
            Collaboration objCollaboration = (Collaboration) ReverseProperties.getInstance()
                    .getMappedElement(this.ecoreElement.getOwner().getOwner());
            objCollaboration.getTemplate().add((TemplateParameter) objingElt);
        } else {
            objingElt.delete();
        }
    }

}
