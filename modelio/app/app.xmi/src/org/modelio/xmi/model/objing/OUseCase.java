/* 
 * Copyright 2013-2020 Modeliosoft
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
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("9e606c2c-544c-40b9-a407-ad5480254afe")
public class OUseCase extends ONameSpace {
    @objid ("9924bdf0-be35-4056-b066-b04582be7064")
    private UseCase objingElement;

    @objid ("b8c05dbe-3d08-45a4-b131-2a586fcc54e2")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createUseCase();
    }

    @objid ("06093897-1d76-4940-8c71-6ac27236e6ba")
    public  OUseCase(UseCase param) {
        super(param);
        this.objingElement = param;
        
    }

    @objid ("8c20f1e4-2d8e-41f5-aa73-27529d8e48bd")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        ModelTree objingOwner = this.objingElement.getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
                
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof Package) {
                ((Package) ecoreOwner).getPackagedElements().add((PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof Component) {
                ((org.eclipse.uml2.uml.Component) ecoreOwner).getPackagedElements().add((PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof Class) {
                ( (org.eclipse.uml2.uml.Class) ecoreOwner).getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                ((org.eclipse.uml2.uml.Interface) ecoreOwner).getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
        
    }

    @objid ("f792c3cb-fa40-4d2d-8de6-c6c3cbd957d0")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setSubject((org.eclipse.uml2.uml.UseCase) ecoreElt);
        
    }

    @objid ("b1b76ccc-99c2-4a97-8288-556236dff079")
    private void setSubject(org.eclipse.uml2.uml.UseCase ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(this.objingElement.getOwner());
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Classifier)
            ecoreElt.getSubjects().add( (org.eclipse.uml2.uml.Classifier)  ecoreOwner);
        
    }

}
