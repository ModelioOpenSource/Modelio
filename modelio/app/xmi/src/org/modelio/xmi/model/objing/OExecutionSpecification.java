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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;

@objid ("25a808bf-efa2-477c-a37e-a78fd0b96d53")
public class OExecutionSpecification extends OInteractionFragment {
    @objid ("c3e84639-d2bb-4925-8a72-cf3c18d1b306")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createBehaviorExecutionSpecification();
    }

    @objid ("ccdb0983-451e-422e-87f3-9cb01a83129b")
    public OExecutionSpecification(ExecutionSpecification param) {
        super(param);
    }

    @objid ("2bceb2e1-7c23-4bee-93e8-2d2b8e0de438")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("23dd13bf-803f-416a-a428-97dff62f817e")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
