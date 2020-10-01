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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of Interaction
 * @author ebrosse
 */
@objid ("c8a0e3f2-5976-43df-b26b-6e774aa67b88")
public class OInteraction extends OModelElement {
    @objid ("32c6211a-e8ee-4f19-a4ae-2d2b97ef2135")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createInteraction();
    }

    /**
     * Constructor
     * 
     * @param element : the exported Modelio Interaction
     */
    @objid ("24b33337-f5d2-4e64-82d1-7b0783ff0d83")
    public OInteraction(final Interaction element) {
        super(element);
    }

    @objid ("424f9442-d174-400b-9092-a1e95318f9eb")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        ModelElement objingOwner = getObjingElement().getOwner();
        
        if (objingOwner == null) {
            Operation op = getObjingElement().getOwnerOperation();
            objingOwner = op.getOwner();
            String message = Xmi.I18N.getMessage("logFile.warning.moving.interaction",
                                                 getObjingElement().getName(), op.getName(), objingOwner
                                                 .getName());
            GenerationProperties.getInstance().addWarning(message, getObjingElement());
        }
        
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                ownerIsItf.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml. BehavioredClassifier) {
                org.eclipse.uml2.uml. BehavioredClassifier ownerIsBehavioredClassifier = (org.eclipse.uml2.uml.BehavioredClassifier) ecoreOwner;
                ownerIsBehavioredClassifier.getOwnedBehaviors().add((org.eclipse.uml2.uml.Behavior)ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("c335ccb9-588c-42a4-a4bd-3f8e3920a41e")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setReentrant((org.eclipse.uml2.uml.Interaction) ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled())
            setDiagramName(ecoreElt);
    }

    @objid ("a6771db5-d380-4ed4-a34c-294518381aa4")
    private void setReentrant(org.eclipse.uml2.uml.Interaction interaction) {
        interaction.setIsReentrant(getObjingElement().isIsReentrant());
    }

    @objid ("dec20d11-dc73-44f5-84af-dde2cd6959f4")
    private void setDiagramName(org.eclipse.uml2.uml.Element ecoreElt) {
        String diagramName = "";
        
        for (AbstractDiagram diagram : getObjingElement().getProduct()){
            if (diagram instanceof SequenceDiagram){
                diagramName = diagram.getName();
                break;
            }
        }
        
        if (!diagramName.equals("")){
            ObjingEAnnotation.setDiagramName(ecoreElt, diagramName);
        }
    }

    @objid ("89b10c38-64b2-41fc-a182-c716d337f57b")
    @Override
    public Interaction getObjingElement() {
        return (Interaction) super.getObjingElement();
    }

}
