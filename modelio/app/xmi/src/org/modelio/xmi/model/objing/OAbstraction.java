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
import org.modelio.metamodel.uml.infrastructure.Abstraction;

/**
 * This class is in charge of the org.eclipse.uml2.uml.Abstraction export
 * @author ebrosse
 */
@objid ("903a61ac-4eea-4b5e-9eb3-498f77941080")
public class OAbstraction extends ODependency {
    @objid ("1136ab0a-4e4d-4ab7-a7dc-79fb7a8410e9")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createAbstraction();
    }

    /**
     * A constructor with the exported org.eclipse.uml2.uml.Abstraction
     * 
     * @param abstraction The exported org.eclipse.uml2.uml.Abstraction
     */
    @objid ("8bcfee2b-9d02-42f1-a25a-da258e9d3eb5")
    public OAbstraction(Abstraction abstraction) {
        super(abstraction);
    }

    @objid ("283ebf7b-2edc-4f7b-af65-52eb7ee86073")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("b448cb8b-a581-4801-8a90-bedb5ed4d56a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setAbstractionMapping( (org.eclipse.uml2.uml.Abstraction) ecoreElt);
    }

    @objid ("f6c938be-41c4-46b1-8a86-7f0f5274d832")
    private void setAbstractionMapping(org.eclipse.uml2.uml.Abstraction ecoreAbstraction) {
        org.eclipse.uml2.uml.OpaqueExpression expr = UMLFactory.eINSTANCE.createOpaqueExpression();
        String abstractionName = getObjingElement().getName();
        String mappingName = "";
        
        if (!"".equals(abstractionName))
            mappingName = abstractionName + "_";
        
        mappingName += "Mapping";
        
        expr.setName(mappingName);
        expr.getBodies().add(getObjingElement().getMapping());
        ecoreAbstraction.setMapping(expr);
    }

    @objid ("0dab13ac-784d-4dc0-b279-3df9234cca97")
    @Override
    public Abstraction getObjingElement() {
        return (Abstraction) super.getObjingElement();
    }

}
