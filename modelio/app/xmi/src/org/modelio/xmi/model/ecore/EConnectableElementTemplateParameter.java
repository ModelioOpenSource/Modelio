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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("1c09ea4a-fa77-4a91-9f92-ef3100b6eec8")
public class EConnectableElementTemplateParameter extends EElement {
    @objid ("f6ba83b1-85e2-470d-aa82-1a58afbddb43")
    @Override
    public Element createObjingElt() {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
        TemplateParameter result = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createTemplateParameter();
        
        try {
            result.getExtension().add(mmServices.getStereotype(XMIProperties.modelerModuleName, 
                    IModelerModuleStereotypes.UML2CONNECTABLEELEMENTTEMPLATEPARAMETER, result.getMClass()));
        } catch (ElementNotUniqueException e) {
           Xmi.LOG.warning(e);
        }
        return result;
    }

    @objid ("ea5fc7b4-ab44-4196-8e29-057d5fc401d4")
    public EConnectableElementTemplateParameter(org.eclipse.uml2.uml.ConnectableElementTemplateParameter element) {
        super(element);
    }

}
