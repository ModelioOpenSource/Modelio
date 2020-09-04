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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("a79812f7-1e25-48c1-ba75-45e070efe052")
public class EEnumeration extends EDataType {
    @objid ("94ef5f45-4554-4ac7-bce7-9c2cbe31315b")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createEnumeration();
    }

    @objid ("257e8d9e-f5ed-44da-90a4-89f48e9cfca2")
    public EEnumeration(org.eclipse.uml2.uml.Enumeration element) {
        super(element);
    }

    @objid ("4c0a178e-0491-41e4-a33e-ac05eedf5fa6")
    @Override
    public void attach(Element objingElt) {
        ModelTree objingOwner = EcoreModelNavigation.getNearestEnumerationOwner(getEcoreElement());
        
        if (objingElt instanceof ModelTree){
        
            ModelTree objingDTImport = (ModelTree) objingElt;
        
            if (objingOwner != null){
                if (objingOwner instanceof Profile){
                    objingDTImport.setOwner(ReverseProperties.getInstance().getExternalPackage());
                }else{
                    objingDTImport.setOwner(objingOwner);
                }
            }else{
                objingDTImport.setOwner(ReverseProperties.getInstance().getExternalPackage());
            }
        }
    }

    @objid ("d6dd0ada-8714-4ea9-8634-973c90259dd3")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setElementary((Enumeration) objingElt);
    }

    @objid ("8bf053d4-e752-4dfd-b459-62a3561a2c05")
    private void setElementary(Enumeration objingElt) {
        // Audit Rule number 80 (Warning): "An org.eclipse.uml2.uml.Enumeration is always primitive."
        objingElt.setIsElementary(true);
    }

}
