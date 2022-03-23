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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("49a08564-d680-478f-bd1d-f825132b7a56")
public class ETemplateParameter extends EElement {
    @objid ("82712935-b0d5-4a4d-bbb6-95b15c9438d9")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createTemplateParameter();
    }

    @objid ("baa07717-2a64-4b6e-af21-7752e7682074")
    public  ETemplateParameter(org.eclipse.uml2.uml.TemplateParameter element) {
        super(element);
    }

    @objid ("76215145-72eb-41d5-8a9f-0a1a11f4a79a")
    @Override
    public void attach(Element objingElt) {
        Element objOwner = (Element) ReverseProperties.getInstance().getMappedElement(getEcoreElement().getOwner().getOwner());
        
        if (objOwner instanceof NameSpace){
            ((TemplateParameter) objingElt).setParameterized((NameSpace)objOwner);
        }else if (objOwner instanceof Operation){
            ((TemplateParameter) objingElt).setParameterizedOperation((Operation)objOwner);
        }else{
            objingElt.delete();
        }
        
    }

    @objid ("18d244bf-1964-4cd8-b792-c94549987399")
    @Override
    public void setProperties(Element objingElt) {
        setName(objingElt);
        setIsValueParameter(objingElt);
        setDefault(objingElt);
        setParameteredElement(objingElt);
        
    }

    @objid ("eb8f5421-95a5-4e0e-af88-7eb0d3f7f442")
    private void setParameteredElement(Element objElement) {
        org.eclipse.uml2.uml.Element type = ((org.eclipse.uml2.uml.TemplateParameter) getEcoreElement()).getParameteredElement();
        if (type != null){
            Element objType = (Element) ReverseProperties.getInstance().getMappedElement(type);
            if (objType instanceof UmlModelElement){
                ((TemplateParameter) objElement).setType((UmlModelElement) objType);
            }
        }
        
    }

    @objid ("c93b411a-15a3-43e8-899d-703b73da80f7")
    private void setDefault(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreDefault = ((org.eclipse.uml2.uml.TemplateParameter) getEcoreElement()).getDefault();
        if (ecoreDefault != null){
            if (ecoreDefault instanceof org.eclipse.uml2.uml.LiteralString){
                ((TemplateParameter) objingElt).setDefaultValue(((org.eclipse.uml2.uml.LiteralString) ecoreDefault).getValue());
            }else{
                Element objType = (Element) ReverseProperties.getInstance().getMappedElement(ecoreDefault);
                if (objType instanceof UmlModelElement){
                    ((TemplateParameter) objingElt).setDefaultType((UmlModelElement) objType);
                }
            }
        }
        
    }

    @objid ("9e6993f0-68e3-4fcb-81d0-56bed1505ab4")
    private void setIsValueParameter(Element objingElt) {
        ((TemplateParameter) objingElt).setIsValueParameter(ObjingEAnnotation.isValueParameter((getEcoreElement())));
    }

    @objid ("8f0fc8b2-d5e2-408a-a9ff-a66bfac24104")
    private void setName(Element objingElt) {
        ((TemplateParameter) objingElt).setName(ObjingEAnnotation.getName(getEcoreElement()));
    }

}
