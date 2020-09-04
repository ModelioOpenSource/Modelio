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
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of BindableInstance elements
 * 
 * @author ebrosse
 */
@objid ("51860942-c847-44a7-8c1d-39094d70a463")
public class OBindableInstance extends OInstance {
    @objid ("9b2a1aac-78d0-40c2-99ad-66e4b3ef2a04")
    private MObject root = null;

    @objid ("1fd4b0ea-b65d-4647-81e7-84e5472af82b")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (!(getObjingElement() instanceof Port)) {
            if (this.root instanceof Instance) {
                return UMLFactory.eINSTANCE.createSlot();
            }
        
            Property part = null;
            ModelElement representedFeature = getObjingElement().getRepresentedFeature();
            if ((representedFeature != null) && (representedFeature instanceof AssociationEnd)) {
                part = (Property) GenerationProperties.getInstance().getMappedElement(representedFeature);
            } else {
                part = UMLFactory.eINSTANCE.createProperty();
            }
        
            part.setIsComposite(true);
            return part;
        }
        return null;
    }

    /**
     * Constructor of OBindableInstance
     * @param param : the exported Modelio BindableInstance
     */
    @objid ("fc43e156-49ad-4e54-ae99-6188ef2db40a")
    public OBindableInstance(final BindableInstance param) {
        super(param);
        this.root = AbstractObjingModelNavigation.getBindableInstanceOwner(param);
    }

    @objid ("7faa03bf-9afb-4909-810d-82a449203d8c")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!(getObjingElement() instanceof Port)) {
            if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
                attachSlot((org.eclipse.uml2.uml.Slot) ecoreElt);
            } else if (ecoreElt instanceof Property) {
                attachProperty((Property) ecoreElt);
            } else {
                ecoreElt.destroy();
            }
        }
    }

    @objid ("3c64e24e-ef70-42e7-b89a-6042d98c6000")
    private void attachProperty(Property ecoreElt) {
        MObject objOwner = getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = null;
        
        if (objOwner instanceof BindableInstance) {
            setOwnerEAnnotation(ecoreElt, objOwner);
        }
        
        ecoreOwner = GenerationProperties.getInstance().getMappedElement(this.root);
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredClassifier) {
            ((org.eclipse.uml2.uml.StructuredClassifier) ecoreOwner).getOwnedAttributes().add(ecoreElt);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Collaboration) {
            ((org.eclipse.uml2.uml.Collaboration) ecoreOwner).getCollaborationRoles().add(ecoreElt);
        } else {
            ecoreElt.destroy();
        }
    }

    @objid ("4f5ed78c-f232-4f14-8487-e4c25f6fb88c")
    private void attachSlot(org.eclipse.uml2.uml.Slot ecoreElt) {
        MObject objingOwner = getObjingElement().getCompositionOwner();
        
        if (objingOwner instanceof BindableInstance) {
            setOwnerEAnnotation(ecoreElt, objingOwner);
        }
        
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof InstanceSpecification) {
                ((InstanceSpecification) ecoreOwner).getSlots().add(ecoreElt);
            } else {
                ecoreElt.destroy();
                String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.haveNotOwner", ecoreElt.getClass().toString());
                GenerationProperties.getInstance().addWarning(message, getObjingElement());
            }
        
            if (!(getObjingElement() instanceof Port)) {
                ObjingEAnnotation.setIsBindableInstance(ecoreElt);
            }
        }
    }

    @objid ("53709c87-7b37-4d5a-8c94-bc79959e56ef")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof Property) {
            AbstractObjingModelNavigation.setRepresentedFeature(getObjingElement(), (Property) ecoreElt);
            setName((org.eclipse.uml2.uml.NamedElement) ecoreElt);
            setBase((Property) ecoreElt);
            setMin((Property) ecoreElt);
            setMax((Property) ecoreElt);
            setExpressionOfValue((Property) ecoreElt);
        } else if (ecoreElt instanceof org.eclipse.uml2.uml.Slot) {
            setRepresentedFeature((org.eclipse.uml2.uml.Slot) ecoreElt);
            setEAnnotationName((org.eclipse.uml2.uml.Slot) ecoreElt);
            setValue((org.eclipse.uml2.uml.Slot) ecoreElt);
        }
    }

    @objid ("c7fe5b8c-ad33-496a-8a19-c08162dbaae3")
    private void setRepresentedFeature(org.eclipse.uml2.uml.Slot ecoreElt) {
        ModelElement objReprensted = getObjingElement().getRepresentedFeature();
        
        if (objReprensted != null) {
            org.eclipse.uml2.uml.Element represented = GenerationProperties.getInstance().getMappedElement(objReprensted);
        
            if (represented instanceof org.eclipse.uml2.uml.StructuralFeature) {
                ecoreElt.setDefiningFeature((org.eclipse.uml2.uml.StructuralFeature) represented);
            }
        }
    }

    @objid ("18f9768d-3c93-4fe1-bcfd-4b925749d176")
    @Override
    public BindableInstance getObjingElement() {
        return (BindableInstance) super.getObjingElement();
    }

}
