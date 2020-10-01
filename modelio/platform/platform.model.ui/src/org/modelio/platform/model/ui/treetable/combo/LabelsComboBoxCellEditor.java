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

package org.modelio.platform.model.ui.treetable.combo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;

/**
 * Notes about design: <br/>
 * Unfortunately, the jface standard ComboBoxCellEditor is not designed for
 * being subclassed.</br>
 * As we need to specialize several of its features, we had to use a modified
 * copy of the original ComboBoxCellEditor (ComboBoxCellEditor3)
 * 
 * @author pvlaemyn
 */
@objid ("6b348c04-1eba-11e2-9382-bc305ba4815c")
public class LabelsComboBoxCellEditor extends ComboBoxCellEditor2 {
    @objid ("87b4fea3-1eba-11e2-9382-bc305ba4815c")
    private List<String> types;

    @objid ("6b34b314-1eba-11e2-9382-bc305ba4815c")
    public LabelsComboBoxCellEditor(Composite parent, String[] labels, int style) {
        this(parent, labels, false, style);
    }

    @objid ("6b34da25-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetValue(Object value) {
        int index = this.types.indexOf(value);
        if (index == -1) {
            index = 0;
        }
        super.doSetValue(Integer.valueOf(index));
    }

    @objid ("6b350132-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Object doGetValue() {
        Integer index = (Integer) super.doGetValue();
        
        if ((index >= 0) && (index < this.types.size())) {
            return this.types.get((Integer) super.doGetValue());
        } else {
            return ((CCombo) getControl()).getText();
        }
    }

    @objid ("4fd7663b-ac36-4040-895d-7cfe4b44ba5c")
    public LabelsComboBoxCellEditor(Composite parent, String[] labels, boolean editable, int style) {
        super(parent, labels, style);
        this.types = new ArrayList<>(Arrays.asList(labels));
        ((CCombo) getControl()).setEditable(editable);
        setActivationStyle(ComboBoxCellEditor2.DROP_DOWN_ON_MOUSE_ACTIVATION);
    }

}
