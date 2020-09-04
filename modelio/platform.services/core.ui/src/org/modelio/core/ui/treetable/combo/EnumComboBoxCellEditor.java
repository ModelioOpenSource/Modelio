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

package org.modelio.core.ui.treetable.combo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.modelio.core.ui.plugin.CoreUi;

/**
 * Notes about design: <br/>
 * Unfortunately, the jface standard ComboBoxCellEditor is not designed for being subclassed. As we need to specialize
 * several of its features, we had to use a modified copy of the original ComboBoxCellEditor (ComboBoxCellEditor2)
 * 
 * @author pvlaemyn
 */
@objid ("6b33c8b2-1eba-11e2-9382-bc305ba4815c")
public class EnumComboBoxCellEditor extends ComboBoxCellEditor2 {
    @objid ("6b33efc1-1eba-11e2-9382-bc305ba4815c")
     Class<?> enumClass;

    /**
     * Creates a new cell editor with a combo containing the enum's possible values parented under the given control.
     * 
     * @param parent the parent control
     * @param enumClass the enumeration providing literal values for the combo box
     * @param style the style bits
     */
    @objid ("6b33efc4-1eba-11e2-9382-bc305ba4815c")
    public EnumComboBoxCellEditor(Composite parent, Class<?> enumClass, int style) {
        super(parent, getLabels(enumClass), style);
        this.enumClass = enumClass;
        ((CCombo) this.getControl()).setEditable(false);
        setActivationStyle(ComboBoxCellEditor2.DROP_DOWN_ON_MOUSE_ACTIVATION);
    }

    @objid ("6b3416d5-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetValue(Object value) {
        Enum<?> enumValue = (Enum<?>) value;
        super.doSetValue(enumValue.ordinal());
    }

    @objid ("6b343de2-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Object doGetValue() {
        return this.enumClass.getEnumConstants()[(Integer) super.doGetValue()];
    }

    @objid ("6b3464f1-1eba-11e2-9382-bc305ba4815c")
    static String[] getLabels(Class<?> enumClass) {
        Object[] constants = enumClass.getEnumConstants();
        String[] labels = new String[constants.length];
        
        for (int i = 0; i < constants.length; i++) {
            labels[i] = CoreUi.I18N.getString("$" + enumClass.getSimpleName() + "." + constants[i].toString());
        }
        return labels;
    }

}
