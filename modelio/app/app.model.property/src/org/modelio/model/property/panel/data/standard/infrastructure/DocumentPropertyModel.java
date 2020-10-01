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

import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.text.DefaultTextNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Document</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Document</i>
 * metaclass.
 */
@objid ("ac3e6dbc-de36-4983-b664-6d1f1a5ca5d3")
public class DocumentPropertyModel extends AbstractPropertyModel<Document> {
    @objid ("46556a62-bf0f-47a0-ac4d-6429337f5302")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Category", "Reference", "Abstract", "MimeType" };

    /**
     * Instantiate the externDocument type properties view.
     * 
     * @param theEditedElement the current externDocument type.
     */
    @objid ("f29f59d6-503c-4784-b492-336741861c91")
    public DocumentPropertyModel(final Document theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getColumnNumber()
     */
    @objid ("42610c89-1add-4898-9250-e615aecbf9cd")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getRowsNumber()
     */
    @objid ("64a1cc99-86a1-4883-bb39-2e38da8ea3f9")
    @Override
    public int getRowsNumber() {
        return DocumentPropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int, int)
     */
    @objid ("e54f24fd-656c-4988-96f3-b4dfe5f154d2")
    private Object getValue(final int row, final int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(DocumentPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getType();
            case 3:
                if (this.theEditedElement.isEmbedded()) {
                    return ModelProperty.I18N.getString("Document.Embedded");
                } else {
                    IResourceHandle handle = this.theEditedElement.getHandle();
                    if (handle != null) {
                        return Objects.toString(handle.getLocation());
                    } else {
                        return ModelProperty.I18N.getString("Document.NoHandle");
                    }
                }
            case 4:
                return this.theEditedElement.getAbstract();
            case 5:
                return this.theEditedElement.getMimeType();
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
    @objid ("b0905163-5311-46c7-995d-6cc8f661845d")
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
        
                DefaultElementNatValue externDocumentType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(ResourceType.class));
                externDocumentType.setElementFilter(new IMObjectFilter() {
        
                    @Override
                    public boolean accept(final MObject element) {
                        if (!(element instanceof ResourceType)) {
                            return false;
                        }
                        ResourceType docType = (ResourceType) element;
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
                return externDocumentType;
            case 3:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultTextNatValue((String) getValue(row, col), false);
            case 5:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#setValueAt(int,
     * int, java.lang.Object)
     */
    @objid ("0ead073f-4ae6-431d-aa3c-a87e72ef8fe0")
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
                this.theEditedElement.setType((ResourceType) value);
                return;
            case 3:
                if (this.theEditedElement.isEmbedded()) {
                    // Read only
                    return;
                } else {
                    IResourceHandle handle = this.theEditedElement.getHandle();
                    if (handle != null) {
                        this.theEditedElement.setStorageInfo((String) value);
                    }
                }
                return;
            case 4:
                this.theEditedElement.setAbstract((String) value);
                return;
            case 5:
                // Read only
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

    @objid ("71cd3168-5ff3-4d08-9627-6ccac0a14ef0")
    @Override
    public boolean isEditable(int row, int col) {
        if (!super.isEditable(row, col) || row == 5) {
            return false;
        } else if (row == 3) {
            return !this.theEditedElement.isEmbedded();
        } else {
            return true;
        }
    }

}
