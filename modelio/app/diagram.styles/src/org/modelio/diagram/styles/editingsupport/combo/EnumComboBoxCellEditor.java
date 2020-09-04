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

package org.modelio.diagram.styles.editingsupport.combo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.modelio.diagram.styles.core.view.ISymbolViewItem.Choice;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * Notes about design: <br/>
 * Unfortunately, the jface standard ComboBoxCellEditor is not designed for being subclassed. As we need to specialize several of its features, we had to use a modified copy of the original ComboBoxCellEditor (ComboBoxCellEditor2)
 * 
 * @author pvlaemyn
 */
@objid ("8593e610-1926-11e2-92d2-001ec947c8cc")
public class EnumComboBoxCellEditor extends ComboBoxCellEditor2 {
    @objid ("561b0d08-9821-4096-a0b3-fcddcffb2711")
    private final List<Choice> choices;

    @objid ("8593e615-1926-11e2-92d2-001ec947c8cc")
    public EnumComboBoxCellEditor(Composite parent, List<Choice> choices, int style) {
        super(parent, getLabels(choices), style);
        this.choices = choices;
        ((CCombo) getControl()).setEditable(false);
        setActivationStyle(ComboBoxCellEditor2.DROP_DOWN_ON_MOUSE_ACTIVATION);
    }

    @objid ("8593e61c-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetValue(Object value) {
        for (int i = 0; i < this.choices.size(); i++) {
            Choice choice = this.choices.get(i);
            if (Objects.equals(choice.value, value)) {
                super.doSetValue(Integer.valueOf(i));
                return;
            }
        }
        
        // If we reach here it is an error
        String cs = this.choices.stream().map(s -> "\n  - " + s).collect(Collectors.joining());
        DiagramStyles.LOG.error(new IllegalArgumentException(String.valueOf(value) + " not in :" + cs + " ."));
        
        // Fallback to first option
        super.doSetValue(0);
    }

    @objid ("8593e620-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Object doGetValue() {
        return this.choices.get((Integer) super.doGetValue()).value;
    }

    @objid ("8593e625-1926-11e2-92d2-001ec947c8cc")
    static String[] getLabels(List<Choice> choices) {
        String[] labels = new String[choices.size()];
        
        for (int i = 0; i < choices.size(); i++) {
            labels[i] = choices.get(i).label;
        }
        return labels;
    }

}
