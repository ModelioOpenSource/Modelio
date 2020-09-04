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
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("308f5273-ede3-4257-bb0f-8aa076213166")
public class EClass extends ENamedElement {
    @objid ("0acc42e4-a80e-45f5-9a21-71d178d5a8f7")
    private boolean isDeleted = false;

    @objid ("fc7824d6-a268-4460-b67b-ca7fbd855ccb")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createClass();
    }

    @objid ("0f57b5ee-1636-4b11-8e29-57fca5059183")
    public EClass(org.eclipse.uml2.uml.Class element) {
        super(element);
    }

    @objid ("e140f99c-9ae0-4c8d-b8eb-15c54540f55c")
    @Override
    public void attach(Element objingElt) {
        if (!this.isDeleted) {
            ReverseProperties revProp = ReverseProperties.getInstance();
        
            org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        
            Classifier objingClass = (Classifier) objingElt;
        
            if (ecoreOwner != null) {
        
                Object objingOwner =  revProp.getMappedElement(ecoreOwner);
                if (objingOwner instanceof Profile) {
                    objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());  
                } else if (objingOwner instanceof Node) {
                    //rule R44
                    objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage()); 
                } else if (objingOwner instanceof TemplateParameter) {
                    objingClass.setOwnerTemplateParameter((TemplateParameter) objingOwner);  
                } else if (objingOwner instanceof ModelTree) {
                    objingClass.setOwner((ModelTree) objingOwner);              
                } else {
                    objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());   
                }
            } else {
                objingClass.setOwner(ReverseProperties.getInstance().getExternalPackage());   
            }
        }
    }

    @objid ("276d7093-e4b5-44f6-ace1-f057dd1aaf28")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        if (!this.isDeleted && objingElt instanceof Classifier) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            Classifier objingClass = (Classifier) objingElt;
        
            if (objingClass instanceof Class) {
                setAbstract((Class) objingClass);
                setLeaf((Class) objingClass);
                setActive((Class) objingClass);
        
                if (revProp.isRoundtripEnabled()) {
                    setPrimitiveEAnnotation((Class) objingClass);
                    setMainEAnnotation((Class) objingClass);
                    setRootEAnnotation((Class) objingClass);
                }
            }
        }
    }

    @objid ("31a4e074-291a-4136-9013-0a60e5327be8")
    private void setAbstract(Class objingElt) {
        objingElt.setIsAbstract(( (org.eclipse.uml2.uml.Class)getEcoreElement()).isAbstract());
    }

    @objid ("442ffe31-c39f-45a2-b7ce-4e69cdd12011")
    private void setActive(Class objingElt) {
        objingElt.setIsActive(( (org.eclipse.uml2.uml.Class)getEcoreElement()).isActive());
    }

    @objid ("0ef223d1-1dc3-4f48-9ad2-11bef07e2b24")
    private void setLeaf(Class objingElt) {
        objingElt.setIsLeaf(( (org.eclipse.uml2.uml.Class)getEcoreElement()).isLeaf());
    }

    @objid ("3e4201b6-efec-4388-858b-2e039a0bcc00")
    private void setMainEAnnotation(Class objingElt) {
        objingElt.setIsMain(ObjingEAnnotation.isMain(getEcoreElement()));
    }

    @objid ("341ba5b0-379b-45aa-9f48-94406343ae58")
    private void setPrimitiveEAnnotation(Class objingElt) {
        objingElt.setIsElementary(ObjingEAnnotation.isPrimitive( getEcoreElement()));
    }

    @objid ("0c11f958-e3d6-4c87-a405-90e7be412cd3")
    private void setRootEAnnotation(Class objingElt) {
        objingElt.setIsRoot(ObjingEAnnotation.isRoot(getEcoreElement()));
    }

}
