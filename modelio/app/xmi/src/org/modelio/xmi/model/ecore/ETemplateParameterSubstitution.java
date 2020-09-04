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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("5ac76c88-751c-403b-853f-a7724fc69837")
public class ETemplateParameterSubstitution extends EElement {
    @objid ("6ae5b6e3-4c02-481a-95fd-b410e91f23d8")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createTemplateParameterSubstitution();
    }

    @objid ("1ca3342b-64a1-4a0f-bcb3-1880c3c34417")
    public ETemplateParameterSubstitution(org.eclipse.uml2.uml.TemplateParameterSubstitution element) {
        super(element);
    }

    @objid ("59e0ccff-4fdb-4637-b9c6-e6053db13f7c")
    @Override
    public void attach(Element objingElt) {
        Element objOwner = (Element) ReverseProperties.getInstance().getMappedElement(getEcoreElement().getOwner());
        
        if (objOwner instanceof TemplateBinding){
            ((TemplateBinding)objOwner).getParameterSubstitution().add(((TemplateParameterSubstitution) objingElt));
        }else{
            objingElt.delete();
        }
    }

    @objid ("3c88d005-358f-4f42-a44a-2d80f3c512ed")
    @Override
    public void setProperties(Element objingElt) {
        setValue(objingElt);
        setActual(objingElt);
        setFormal(objingElt);
    }

    @objid ("1b2143bd-9620-4c08-966a-e0f089d109b4")
    private void setFormal(Element objingElt) {
        org.eclipse.uml2.uml.Element formal = ((org.eclipse.uml2.uml.TemplateParameterSubstitution)getEcoreElement()).getFormal();
        if (formal != null){
            Element objFormal = (Element)ReverseProperties.getInstance().getMappedElement(formal);
            if ((objFormal != null) && (objFormal instanceof TemplateParameter)){
                ((TemplateParameterSubstitution) objingElt).setFormalParameter((TemplateParameter) objFormal);
            }
        }
    }

    @objid ("8498eb67-5757-4d46-b6cc-b70c8c0ce662")
    private void setActual(Element objingElt) {
        org.eclipse.uml2.uml.Element actual  =  ((org.eclipse.uml2.uml.TemplateParameterSubstitution)getEcoreElement()).getActual();
        Element objActual = (Element)ReverseProperties.getInstance().getMappedElement(actual);
        if ((objActual != null) && (objActual instanceof UmlModelElement)){
            ((TemplateParameterSubstitution) objingElt).setActual((UmlModelElement) objActual);
        }
    }

    @objid ("4c787212-2a86-420b-bf52-8f5f3a36e753")
    private void setValue(Element objingElt) {
        ((TemplateParameterSubstitution) objingElt).setValue(ObjingEAnnotation.getValue(getEcoreElement()));
    }

}
