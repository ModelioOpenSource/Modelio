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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("01f85212-338d-4c60-90d0-4e646cdd7969")
public class ODataFlow extends OModelElement {
    @objid ("44b3ef31-f324-482c-adab-353559e24df3")
    private DataFlow objingElement;

    @objid ("9a4c5945-617c-4afa-b39d-63bd811673a5")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return  UMLFactory.eINSTANCE.createInformationFlow();
    }

    @objid ("e49cd970-f4f8-4d38-9ac0-ec4e9aeeae4b")
    public ODataFlow(DataFlow element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("c030afdd-d20f-45e2-9c75-7321cfe3643c")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
                
        NameSpace objingOrigin = this.objingElement.getOrigin();
                
        if (objingOrigin != null) {
            org.eclipse.uml2.uml.NamedElement ecoreOrigin = (org.eclipse.uml2.uml.NamedElement) genProp
                    .getMappedElement(objingOrigin);
                
            if (ecoreOrigin != null) {
                org.eclipse.uml2.uml.Package ecorePkg = ecoreOrigin.getNearestPackage();
                ecorePkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
                ((org.eclipse.uml2.uml.InformationFlow) ecoreElt).getInformationSources().add(
                        ecoreOrigin);
            }
        }
    }

    @objid ("6629e907-efba-46b6-aa79-571088b87615")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setDestinationElement((org.eclipse.uml2.uml.InformationFlow) ecoreElt);
    }

    @objid ("938e776c-2d35-4e0c-b4ba-a6b885a977ad")
    private void setDestinationElement(org.eclipse.uml2.uml.InformationFlow ecoreIF) {
        GenerationProperties genProp = GenerationProperties.getInstance();
                
        NameSpace objingDestination = this.objingElement.getDestination();
                
        if (objingDestination != null) {
            org.eclipse.uml2.uml.NamedElement ecoreDestination = (org.eclipse.uml2.uml.NamedElement) genProp
                    .getMappedElement(objingDestination);
                
            if (ecoreDestination != null)
                ecoreIF.getInformationTargets().add(
                        ecoreDestination);
        }
    }

}
