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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("2f33a04f-0f41-4a92-b870-ab0d599f66cc")
public class EArtifact extends ENamedElement {
    @objid ("2ad60c03-4f51-47dd-bf8c-6829e2b65b4a")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createArtifact();
    }

    @objid ("420b77a9-c0b8-46c4-b207-dd11df098c68")
    public EArtifact(org.eclipse.uml2.uml.Artifact element) {
        super(element);
    }

    @objid ("fb0d0bf7-a9d9-472d-9dc6-264d0b965e4f")
    @Override
    public void attach(Element objingElt) {
        //  take the model map
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        //  take the ecore Imported and Importing
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        
        Object objingOwner =  revProp.getMappedElement(ecoreOwner);
        
        if (objingOwner instanceof ModelTree) {
            ((Artifact) objingElt).setOwner((ModelTree)objingOwner);          
        }else {
             ((Artifact) objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage()); 
        }
    }

    @objid ("d6af4cf8-a0c8-4540-8c5d-e89b182a8126")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setFileName((Artifact) objingElt);
    }

    @objid ("473e91e3-b546-40df-88da-0f538f020619")
    private void setFileName(Artifact objingElt) {
        String name = ((org.eclipse.uml2.uml.Artifact)getEcoreElement()).getFileName();
        if (EcoreModelNavigation.isNotNull(name))
            objingElt.setFileName(name);
        else 
            objingElt.setFileName("");
    }

}
