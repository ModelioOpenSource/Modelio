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
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("5adff1b3-6055-4371-83cd-c9bd6c1fe10f")
public class EAssociation extends ENamedElement {
    @objid ("ab148b55-e405-44f7-8d6c-02d9d133caf8")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Association association =  (org.eclipse.uml2.uml.Association) getEcoreElement();
        int endNumber = EcoreModelNavigation.getValidEndNumber(association);
        
        if (endNumber == 2){
            return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createAssociation();
        }else if (endNumber > 2){
            return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createNaryAssociation();
        }
        return null;
    }

    @objid ("3562eaaa-5bad-4c51-87d9-84c227fdc992")
    public EAssociation(org.eclipse.uml2.uml.Association element) {
        super(element);
    }

}
