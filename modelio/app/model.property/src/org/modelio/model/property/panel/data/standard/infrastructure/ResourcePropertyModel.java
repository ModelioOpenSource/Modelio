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

package org.modelio.model.property.panel.data.standard.infrastructure;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>Resource</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Resource</i>
 * metaclass.
 */
@objid ("2b6b3146-9eaa-4d9c-9368-4670d7b62ed9")
public class ResourcePropertyModel extends AbstractPropertyModel<Resource> {
    @objid ("4e732439-cf0b-43df-94b0-ce8e8249348d")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "ResourceType" };

    /**
     * Instantiate the externResource type properties view.
     * 
     * @param theEditedElement the current externResource type.
     */
    @objid ("914c027a-2e29-482b-a9ff-8e4fc9051813")
    public ResourcePropertyModel(final Resource theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getColumnNumber()
     */
    @objid ("a7c3477b-4f57-4439-bcbc-69b1e21b8377")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getRowsNumber()
     */
    @objid ("86b7e810-a43c-487d-906e-597f96d5d221")
    @Override
    public int getRowsNumber() {
        return ResourcePropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int, int)
     */
    @objid ("251f8f8a-8991-4c41-807e-3a1111a7e249")
    private Object getValue(final int row, final int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(ResourcePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getType();
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
    @objid ("8b095be3-1553-4500-bc1e-d61e506fda27")
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
                final ModelElement context = this.theEditedElement.getSubject();
                final MMetamodel metamodel = context.getMClass().getMetamodel();
                final MClass contextMetaclass = context.getMClass();
                EList<Stereotype> contextStereotypes = context.getExtension();
        
                DefaultElementNatValue externResourceType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(ResourceType.class));
                externResourceType.setElementFilter(new IMObjectFilter() {
        
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
                return externResourceType;
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
    @objid ("b7c725ff-58f2-405a-8c76-f86a5e7cefab")
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
                this.theEditedElement.setType((ResourceType) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
