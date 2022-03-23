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
package org.modelio.platform.model.ui.nattable.viewer.config.body;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.LabelStack;
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnOverrideLabelAccumulator;
import org.modelio.platform.model.ui.nattable.parts.data.CellTagHelper;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.choice.IElementChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multirow.IMultiRowElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.IJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.IStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.platform.model.ui.nattable.viewer.model.PropertyTableDataModel;

/**
 * Adds configuration labels for a given cell (by col/row indexes).
 */
@objid ("5563dc10-ec71-4473-8e9f-a499c2c159f8")
public class CellByCellOverrideLabelAccumulator extends ColumnOverrideLabelAccumulator {
    @objid ("2cac4df4-1e77-4513-8380-714491ca26c2")
    private PropertyTableDataModel dataModel;

    /**
     * Create a new accumulator instance.
     * @param layer the layer this accumulator works on.
     * @param dataModel the table's data model.
     */
    @objid ("9a113dbd-e46c-4514-b6a5-9e26962a4bd3")
    public  CellByCellOverrideLabelAccumulator(ILayer layer, PropertyTableDataModel dataModel) {
        super(layer);
        this.dataModel = dataModel;
        
    }

    @objid ("8cc3a48b-a6d8-415b-a101-83afcb91a5c9")
    @Override
    public void accumulateConfigLabels(LabelStack configLabels, int columnPosition, int rowPosition) {
        super.accumulateConfigLabels(configLabels, columnPosition, rowPosition);
        
        final int row = this.dataModel.getObjectAtRow(rowPosition) + 1;
        final int col = this.dataModel.getObjectAtCol(columnPosition);
        
        final IPropertyModel<?> propertyModel = this.dataModel.getPropertyModel();
        final INatValue type = propertyModel.getValueAt(row, col);
        if (type instanceof IStringChoiceNatValue || type instanceof IElementChoiceNatValue) { // Enum-like
            // standard type for rendering
            String choiceId = (row) + "." + col;
            String tag = CellTagHelper.getTypeTag(type.getClass(), choiceId);
            configLabels.addLabel(tag);
        } else if (type instanceof IJavaEnumNatValue) { // Enum type
            // enumeration specific configuration
            Class<? extends Enum<?>> theEnumeration = ((IJavaEnumNatValue) type).getEnumeration();
            String tag = CellTagHelper.getTypeTag(IJavaEnumNatValue.class, theEnumeration.getName());
            configLabels.addLabel(tag);
        } else if (type instanceof IMultiRowElementNatValue) {
            // enumeration specific configuration
            String tag = CellTagHelper.getTypeTag(IMultiRowElementNatValue.class, ((IMultiRowElementNatValue) type).getTagSuffix());
            configLabels.addLabel(tag);
        } else if (type != null) { // Standard type
            String tag = CellTagHelper.getTypeTag(type.getClass());
            configLabels.addLabel(tag);
        }
        
        if (propertyModel.isEditable(row, col)) {
            configLabels.addLabel(BodyConfiguration.EDITABLE);
        }
        
    }

}
