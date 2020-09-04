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

package org.modelio.core.ui.swt.multistring;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

@objid ("8dc905a9-c068-11e1-8c0a-002564c97630")
class ContentTableListener implements ISelectionChangedListener, KeyListener, ControlListener {
    @objid ("8dc905aa-c068-11e1-8c0a-002564c97630")
    private MultiStringEditionComposite dialog = null;

    @objid ("8dc905ab-c068-11e1-8c0a-002564c97630")
    public ContentTableListener(MultiStringEditionComposite dialog) {
        this.dialog = dialog;
    }

    @objid ("8dc905ae-c068-11e1-8c0a-002564c97630")
    @Override
    public void controlMoved(ControlEvent e) {
        // Nothing to do
    }

    @objid ("8dc905b2-c068-11e1-8c0a-002564c97630")
    @Override
    public void controlResized(ControlEvent e) {
        Table table = (Table)e.getSource();
        int tableWidth = table.getSize().x - 5;
        
        TableColumn[] columns = table.getColumns();
        columns[0].setWidth(tableWidth);
    }

    @objid ("8dc905b6-c068-11e1-8c0a-002564c97630")
    @Override
    public void keyPressed(KeyEvent event) {
        // Nothing to do
    }

    @objid ("8dc905ba-c068-11e1-8c0a-002564c97630")
    @Override
    public void keyReleased(KeyEvent event) {
        if (event.keyCode == SWT.DEL) {
            List<String> adapters = this.dialog.getSelectedAdapters();
            this.dialog.removeAdapters(adapters);
        } else if (event.keyCode == SWT.ARROW_UP && (event.stateMask & SWT.CTRL) != 0
                || event.keyCode == 'u' && (event.stateMask & SWT.CTRL) != 0
                || event.keyCode == 'U' && (event.stateMask & SWT.CTRL) != 0) {
            List<String> adapters = this.dialog.getSelectedAdapters();
            this.dialog.moveUp(adapters);
        } else if (event.keyCode == SWT.ARROW_DOWN && (event.stateMask & SWT.CTRL) != 0
                || event.keyCode == 'd' && (event.stateMask & SWT.CTRL) != 0
                || event.keyCode == 'D' && (event.stateMask & SWT.CTRL) != 0) {
            List<String> adapters = this.dialog.getSelectedAdapters();
            this.dialog.moveDown(adapters);
        }
    }

    @objid ("8dc905be-c068-11e1-8c0a-002564c97630")
    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        List<String> adapters = this.dialog.getSelectedAdapters();
        
        boolean enable = adapters.size() > 0;
        
        this.dialog.getMoveUpParameterButton().setEnabled(enable);
        this.dialog.getMoveDownParameterButton().setEnabled(enable);
        this.dialog.getRemoveParameterButton().setEnabled(enable);
    }

}
