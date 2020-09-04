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
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("3eca7888-1683-4688-ae50-52dd89eb440f")
public class EReclassifyObjectAction extends EActivityNode {
    @objid ("86a176c3-989f-48dd-8493-610a0fed701c")
    @Override
    public Element createObjingElt() {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
        OpaqueAction element = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.getExtension().add(mmServices.getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2RECLASSIFYOBJECTACTION, element.getMClass()));
        } catch (ElementNotUniqueException e) {
            Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("5c29f2bd-54d7-4496-805f-a02fd461abd1")
    public EReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction element) {
        super(element);
    }

}
