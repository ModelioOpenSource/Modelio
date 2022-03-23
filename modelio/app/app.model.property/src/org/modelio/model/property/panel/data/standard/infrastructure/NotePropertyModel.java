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
package org.modelio.model.property.panel.data.standard.infrastructure;

import java.util.Arrays;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Note</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Note</i> metaclass.
 */
@objid ("2abb2a4d-f1fe-403f-842c-a679311c175b")
public class NotePropertyModel extends AbstractPropertyModel<Note> {
    @objid ("84c09dcc-2b12-44ab-8ca8-cc69713c9ca2")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "NoteType",
    			"MimeType" };

    /**
     * Instantiate the note type properties view.
     * @param theEditedElement the current note type.
     */
    @objid ("41cfd428-4eee-47fe-88a6-03d7720b84e2")
    public  NotePropertyModel(final Note theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getColumnNumber()
     */
    @objid ("ecf2f5ed-7662-400e-88c7-98cb96f8dc15")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getRowsNumber()
     */
    @objid ("23e7c444-d2b7-46b0-bc92-2b37e27bbbd2")
    @Override
    public int getRowsNumber() {
        return NotePropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("ab936c84-1eb0-4098-a105-fe9c46814adf")
    private Object getValue(final int row, final int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getModel();
            case 3:
                return (this.theEditedElement.getMimeType() != null) && !this.theEditedElement.getMimeType().isEmpty()
                        ? this.theEditedElement.getMimeType() : this.theEditedElement.getModel().getMimeType();
            default:
                return null;
            }
        default:
            return null;
        }
        
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("87c3ebac-8cd9-4176-8c77-3dadd821541e")
    @Override
    public INatValue getValueAt(final int row, final int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2:
                final ModelElement context = this.theEditedElement.getSubject();
                final MMetamodel metamodel = context.getMClass().getMetamodel();
                final MClass contextMetaclass = context.getMClass();
                EList<Stereotype> contextStereotypes = context.getExtension();
        
                DefaultElementNatValue noteType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(NoteType.class));
                noteType.setElementFilter(new IMObjectFilter() {
        
                    @Override
                    public boolean accept(final MObject element) {
                        if (!(element instanceof NoteType)) {
                            return false;
                        }
                        NoteType docType = (NoteType) element;
                        Stereotype ownerStereotype = docType.getOwnerStereotype();
                        if (ownerStereotype != null && contextStereotypes.contains(ownerStereotype)) {
                            String stereotypeMetaclass = ownerStereotype.getBaseClassName();
                            return stereotypeMetaclass != null
                                    && contextMetaclass.hasBase(metamodel.getMClass(stereotypeMetaclass));
                        }
                        MetaclassReference ownerReference = docType.getOwnerReference();
                        if (ownerReference != null) {
                            String referencedMetaclass = ownerReference.getReferencedClassName();
                            return referencedMetaclass != null
                                    && contextMetaclass.hasBase(metamodel.getMClass(referencedMetaclass));
                        }
                        return false;
                    }
                });
                return noteType;
            case 3:
                return new DefaultStringChoiceNatValue((String) getValue(row, col), false, Arrays.asList("text/plain", "text/html", "text/jython"), true);
            default:
                return null;
            }
        default:
            return null;
        }
        
    }

    @objid ("feb788b1-1bfe-48c0-ad2c-becf59d0ecb2")
    @Override
    public boolean isEditable(int row, int col) {
        return super.isEditable(row, col);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#setValueAt(int,
     * int, java.lang.Object)
     */
    @objid ("902025eb-de4e-487f-b3c6-d0a4f51c2e96")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                this.theEditedElement.setName((String) value);
                return;
            case 2:
                this.theEditedElement.setModel((NoteType) value);
                return;
            case 3:
                this.theEditedElement.setMimeType((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
        
    }

}
