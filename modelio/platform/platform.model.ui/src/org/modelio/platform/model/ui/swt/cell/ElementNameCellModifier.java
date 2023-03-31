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
package org.modelio.platform.model.ui.swt.cell;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.Widget;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * Cell modifier used to edit the name of a {@link ModelElement}.
 * <p>
 * In case of exotic selections (e.g. adapters or complex data structure in a viewer), implementers should redefine {@link #getEditedObject(Object)} to do map the selection with the actual edited object.
 * </p>
 */
@objid ("c487cb7a-bff7-4360-85d9-4828b31af116")
public class ElementNameCellModifier implements ICellModifier {
    @objid ("68af960b-bded-4d7c-99b8-3491a3b5ffea")
    @Override
    public boolean canModify(Object object, String property) {
        Object edited = getEditedObject(object);
        return isEditable(edited);
    }

    @objid ("60fe253f-f9ee-43bb-aef7-6134f651d4df")
    @Override
    public Object getValue(Object object, String property) {
        Object edited = getEditedObject(object);
        if (edited instanceof ModelElement) {
            ModelElement modelElement = (ModelElement) edited;
        
            // Special case for ObjectNode
            if (edited instanceof ObjectNode) {
                final ObjectNode theInstanceNode = (ObjectNode) edited;
                if (theInstanceNode.getRepresented() != null) {
                    return theInstanceNode.getRepresented().getName();
                } else if (theInstanceNode.getRepresentedAttribute() != null) {
                    return theInstanceNode.getRepresentedAttribute().getName();
                } else if (theInstanceNode.getRepresentedRole() != null) {
                    return theInstanceNode.getRepresentedRole().getName();
                } else if (theInstanceNode.getRepresentedRealParameter() != null) {
                    return theInstanceNode.getRepresentedRealParameter().getName();
                }
            }
        
            return modelElement.getName();
        }
        return "";
    }

    @objid ("093ceb10-76a2-456f-8cdb-2c7447702134")
    @Override
    public void modify(Object object, String property, Object value) {
        Object edited = getEditedObject(object);
        if (edited instanceof ModelElement) {
            ModelElement modelElement = (ModelElement) edited;
            // Ignore edition if the element becomes invalid during edition
            if (!modelElement.isValid()) {
                return;
            }
        
            ICoreSession session = CoreSession.getSession(modelElement);
        
            // Special case for ObjectNode
            if (modelElement instanceof ObjectNode) {
                final ObjectNode theInstanceNode = (ObjectNode) modelElement;
                ModelElement auxiliary = null;
                if (theInstanceNode.getRepresented() != null) {
                    auxiliary = theInstanceNode.getRepresented();
                } else if (theInstanceNode.getRepresentedAttribute() != null) {
                    auxiliary = theInstanceNode.getRepresentedAttribute();
                } else if (theInstanceNode.getRepresentedRole() != null) {
                    auxiliary = theInstanceNode.getRepresentedRole();
                } else if (theInstanceNode.getRepresentedRealParameter() != null) {
                    auxiliary = theInstanceNode.getRepresentedRealParameter();
                }
        
                if (auxiliary != null) {
                    if (!auxiliary.getName().equals(value)) {
        
                        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Rename a " + modelElement.getMClass().getName())) {
                            auxiliary.setName((String) value);
                            transaction.commit();
                        }
        
                    }
                }
            }
        
            // Special case for CommunicationNode
            if (modelElement instanceof CommunicationNode) {
                final CommunicationNode theInstanceNode = (CommunicationNode) modelElement;
                ModelElement auxiliary = null;
                if (theInstanceNode.getRepresented() != null) {
                    auxiliary = theInstanceNode.getRepresented();
                }
        
                if (auxiliary != null) {
                    if (!auxiliary.getName().equals(value)) {
        
                        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Rename a " + modelElement.getMClass().getName())) {
                            auxiliary.setName((String) value);
                            transaction.commit();
                        }
        
                    }
                }
            }
        
            if (!modelElement.getName().equals(value)) {
        
                try (ITransaction transaction = session.getTransactionSupport().createTransaction("Rename a " + modelElement.getMClass().getName())) {
                    modelElement.setName((String) value);
                    transaction.commit();
                    // view.selectElement(modelElement);
                }
        
            }
        }
        
    }

    @objid ("95859cb2-85bb-490f-b654-84b5d3121fe4")
    private boolean isEditable(Object object) {
        return (object instanceof ModelElement) ? isEditableElement((ModelElement) object) : false;
    }

    @objid ("f4d21589-b457-492e-970f-c4d0d22df27b")
    private boolean isEditableElement(ModelElement element) {
        // if element is a parameter it can only edited if it is an IO parameter and not a return one
        if (element instanceof Parameter) {
            Parameter p = (Parameter) element;
            return (p.getReturned() == null);
        } else if (element instanceof Usage ||
                element instanceof Binding ||
                element instanceof ClassAssociation ||
                element instanceof ElementImport ||
                element instanceof Generalization ||
                element instanceof InterfaceRealization ||
                element instanceof Manifestation ||
                element instanceof PackageImport ||
                element instanceof PackageMerge ||
                element instanceof ProvidedInterface ||
                element instanceof RaisedException ||
                element instanceof RequiredInterface ||
                element instanceof TemplateBinding ||
                element instanceof TemplateParameterSubstitution ||
                element instanceof UseCaseDependency) {
            return false;
        } else {
            return element.getStatus().isModifiable();
        }
        
    }

    /**
     * Convert the raw object given by SWT to the editable value.
     */
    @objid ("f7ccbfd0-e0e4-438e-a6bd-aef946b8b637")
    protected Object getEditedObject(Object object) {
        if (object instanceof Widget) {
            final Widget item = (Widget) object;
            return getEditedObject(item.getData());
        }
        return object;
    }

}
