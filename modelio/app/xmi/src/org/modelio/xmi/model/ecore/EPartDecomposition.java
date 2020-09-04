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
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("97890962-4f23-471e-ab25-7e6807cc24d7")
public class EPartDecomposition extends EInteractionUse {
    @objid ("8881b153-8ff3-423a-b860-80a147c84f4d")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPartDecomposition();
    }

    @objid ("c47d7fdf-5d13-473c-bc03-0f3652e57f6f")
    public EPartDecomposition(org.eclipse.uml2.uml.PartDecomposition element) {
        super(element);
    }

    @objid ("d6a9d8dc-ecc7-4755-87cf-5ceb8a2ad130")
    @Override
    public void attach(Element objingElt) {
        EList<org.eclipse.uml2.uml.Lifeline> ecoreOwner = ((org.eclipse.uml2.uml.PartDecomposition) getEcoreElement()).getCovereds();
        if ((ecoreOwner.size() > 0) && (objingElt instanceof PartDecomposition)){
            ((PartDecomposition) objingElt).setDecomposed((Lifeline) ReverseProperties.getInstance().getMappedElement(ecoreOwner.get(0)));
        }
    }

}
