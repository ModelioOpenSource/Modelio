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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("91845636-03bd-4f15-8387-62afd67cde77")
public class EAssociationClass extends ENamedElement {
    @objid ("d456c73a-2746-426b-92e1-2d3d91273523")
    private boolean isDeleted = false;

    @objid ("eb3ae857-bd1c-47ea-8036-2e2513eb0ae4")
    private ClassAssociation objingClassAssociation = null;

    @objid ("c56b03ff-6c22-480e-8630-702b1cefda35")
    private Association objingAssociation = null;

    @objid ("425ad82d-fe70-4916-bbf5-f05631ecb995")
    private Class objingClass = null;

    @objid ("08250201-6cf0-4418-9e04-ba733837419e")
    @Override
    public Class createObjingElt() {
        IStandardModelFactory modelFactory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        // Creation of the association class
        this.objingClassAssociation = modelFactory.createClassAssociation();
        // Creation of the Class
        this.objingClass = modelFactory.createClass();
        // Creation of the org.eclipse.uml2.uml.Association
        this.objingAssociation = modelFactory.createAssociation();
        // Set of the class and org.eclipse.uml2.uml.Association
        this.objingClassAssociation.setClassPart(this.objingClass);
        this.objingClassAssociation.setAssociationPart(this.objingAssociation);
        return this.objingClass;
    }

    @objid ("3d05e44f-2a2e-43bc-ae3a-b47145eb4626")
    private void attachClass() {
        if (!this.isDeleted) {
            ReverseProperties revProp = ReverseProperties.getInstance();
        
            org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        
            if (ecoreOwner != null) {
        
                Object objingOwner =  revProp.getMappedElement(ecoreOwner);
        
                if (objingOwner instanceof Profile) {
                    this.objingClass.setOwner(revProp.getExternalPackage());
                }else if (objingOwner instanceof ModelTree) {
                    this.objingClass.setOwner((ModelTree) objingOwner);
                } else if (objingOwner instanceof Class) {
                    this.objingClass.setOwner((Class) objingOwner);
                } else if (objingOwner instanceof Node) {
                    this.objingClass.setOwner((Node) objingOwner);
                } else {
                    this.objingClass.setOwner(revProp.getExternalPackage());
                }
            } else {
                this.objingClass.setOwner(revProp.getExternalPackage());
            }
        }
    }

    @objid ("9bce2204-e396-494c-9cb5-23982ab3bf99")
    private void setClassProperties() {
        if (!this.isDeleted) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            setClassName();
        
            setClassAbstract();
            setClassLeaf();
            setClassActive();
        
            if (revProp.isRoundtripEnabled()) {
                setClassPrimitiveEAnnotation();
                setClassMainEAnnotation();
            }
        }
    }

    @objid ("a4ca60dd-af83-4770-a6e0-51cb812ea041")
    private void setClassName() {
        String name = ((org.eclipse.uml2.uml.AssociationClass)getEcoreElement()).getName();
        if (EcoreModelNavigation.isNotNull(name))
            this.objingClass.setName(name);
        else 
            this.objingClass.setName("");
    }

    @objid ("2838e343-7e02-451f-a071-8443368b14d3")
    private void setClassAbstract() {
        this.objingClass.setIsAbstract(((org.eclipse.uml2.uml.AssociationClass)getEcoreElement()).isAbstract());
    }

    @objid ("e0ffa88a-2d40-42a5-ae54-56c19a38e018")
    private void setClassLeaf() {
        this.objingClass.setIsLeaf(((org.eclipse.uml2.uml.AssociationClass)getEcoreElement()).isLeaf());
    }

    @objid ("5dd62333-9f39-4457-a6f1-71eb3a9b5f56")
    private void setClassActive() {
        this.objingClass.setIsActive(((org.eclipse.uml2.uml.AssociationClass)getEcoreElement()).isActive());
    }

    @objid ("24b4019b-b8e7-4596-b6b0-46b7c84b5dee")
    private void setClassPrimitiveEAnnotation() {
        this.objingClass.setIsElementary(ObjingEAnnotation.isPrimitive((getEcoreElement())));
    }

    @objid ("a242df4b-c9bf-4358-85f7-a70a4f3c814a")
    private void setClassMainEAnnotation() {
        this.objingClass.setIsMain(ObjingEAnnotation.isMain((getEcoreElement())));
    }

    @objid ("6c4f6233-7664-4d41-9d92-16a2af51540a")
    private void setAssociationProperties() {
        setAssocName();
    }

    @objid ("4b3f735d-70a1-4a7c-a78b-009c1b70a887")
    private void setAssocName() {
        String name = ((org.eclipse.uml2.uml.AssociationClass)getEcoreElement()).getName();
        if (EcoreModelNavigation.isNotNull(name))
            this.objingAssociation.setName(name);
        else
            this.objingAssociation.setName("");
    }

    @objid ("c77d95a2-dfa8-45f6-b599-2c71e77960ff")
    private void deleteElements() {
        if (this.objingClass != null)
            this.objingClass.delete();
        
        if (this.objingAssociation != null)
            this.objingAssociation.delete();
        
        if (this.objingClassAssociation != null)
            this.objingClassAssociation.delete();
        
        this.isDeleted = true;
    }

    @objid ("b13a9082-7acf-4a98-86e6-b66541c4bd84")
    public EAssociationClass(org.eclipse.uml2.uml.AssociationClass element) {
        super(element);
    }

    @objid ("2817525e-f000-48d8-9785-aa247ba39efe")
    @Override
    public void attach(Element objingElt) {
        initialize((Class) objingElt);
        attachClass();
        attachAssociation();
    }

    @objid ("ffdcf409-5e05-4eeb-aa8f-3830c2e0f567")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setClassProperties();
        if (this.objingAssociation != null)
            setAssociationProperties();
    }

    @objid ("c0829aa6-298b-4b3d-87fb-e4a5343a21dc")
    private void initialize(Class partialClass) {
        try {
            this.objingClass = partialClass;
            this.objingClassAssociation = this.objingClass.getLinkToAssociation();
            this.objingAssociation = this.objingClassAssociation.getAssociationPart();
        } catch (Exception e) {
            Xmi.LOG.error(e);
            deleteElements();
        }
    }

    @objid ("9d290d53-5888-4f3c-ab7a-6ea6bb88e3b2")
    private void attachAssociation() {
        if (!this.isDeleted) {
            ReverseProperties revProp = ReverseProperties.getInstance();
            int nbEnds = 0;
            for (Object memberEnd : ((org.eclipse.uml2.uml.AssociationClass)getEcoreElement()).getMemberEnds()) {
        
                Object ends = revProp.getMappedElement((org.eclipse.uml2.uml.Element) memberEnd);
                if (ends instanceof List<?>) {
         
                    List<?> alist = (List<?>) ends;
                    AssociationEnd objingAssocEnd = null;
                    for (Object end : alist ){
                        if (end instanceof AssociationEnd){
                            objingAssocEnd = (AssociationEnd) end;
                            break;
                        }
                    }
        
                    if (objingAssocEnd != null) {
                        // Links the AssociationEnd to the org.eclipse.uml2.uml.Association:
                        objingAssocEnd.setAssociation(this.objingAssociation);
                        nbEnds++;
                    }
                }
            }
        
            if (nbEnds != 2) {
                this.objingAssociation.delete();
                this.objingAssociation = null;
            }
        
        }
    }

}
