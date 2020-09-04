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

@objid ("2c32feb0-a23e-4353-afad-57673c2cabda")
public class EReadLinkObjectEndAction extends EActivityNode {
    @objid ("973ec6da-3818-47a2-afcd-eb248f3298b7")
    @Override
    public Element createObjingElt() {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
        OpaqueAction element = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.getExtension().add(mmServices.getStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READLINKOBJECTENDQUALIFIERACTION, element.getMClass()));
        } catch (ElementNotUniqueException e) {
            Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("ae076631-d97e-4505-81df-60bf0a015b6d")
    public EReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction element) {
        super(element);
    }

}
