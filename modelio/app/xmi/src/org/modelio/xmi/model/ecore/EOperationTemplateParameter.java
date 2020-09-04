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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.module.modelermodule.api.xmi.standard.parameter.UML2OperationTemplateParameter;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("b4d93993-9137-414a-acd4-1138fb83a0a0")
public class EOperationTemplateParameter extends EElement {
    @objid ("c1051335-bb37-4b8f-ae86-31fbab26ede2")
    @Override
    public Element createObjingElt() {
        return UML2OperationTemplateParameter.create().getElement();
    }

    @objid ("f7cf5ae8-59fc-42ba-ac07-578f3d575ae1")
    public EOperationTemplateParameter(org.eclipse.uml2.uml.OperationTemplateParameter element) {
        super(element);
    }

    @objid ("c57001e7-335d-4ff2-8870-ec691a4692fa")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        Element objingOperation = null;
        try{
            objingOperation = (Element) ReverseProperties.getInstance()
                    .getMappedElement(ecoreOwner);
        }catch (RuntimeException e){
            Xmi.LOG.error(e);
        }
        
        if (objingOperation instanceof Operation) {
        
            ((Operation) objingOperation)
            .getIO().add((Parameter) objingElt);
            ((Parameter) objingElt).setParameterPassing(PassingMode.INOUT);
        
        }else{
            objingElt.delete();
        }
    }

}
