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

package org.modelio.core.ui.swt.multistring;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.TableItem;

@objid ("8dca8c74-c068-11e1-8c0a-002564c97630")
class StringAdapterModifier implements ICellModifier, KeyListener {
    @objid ("8dca8c75-c068-11e1-8c0a-002564c97630")
    private MultiStringEditionComposite dialog = null;

    @objid ("8dca8c76-c068-11e1-8c0a-002564c97630")
    public StringAdapterModifier(MultiStringEditionComposite dialog) {
        this.dialog = dialog;
    }

    @objid ("8dca8c79-c068-11e1-8c0a-002564c97630")
    @Override
    public boolean canModify(Object element, String property) {
        return true;
    }

    @objid ("8dcc12e5-c068-11e1-8c0a-002564c97630")
    @Override
    public Object getValue(Object element, String property) {
        String value = (String) element;
        if (value == null) {
            value = "";
        }
        return value;
    }

    @objid ("8dcc12ec-c068-11e1-8c0a-002564c97630")
    @Override
    public void keyPressed(KeyEvent e) {
        // Nothing to do
    }

    @objid ("8dcc12f0-c068-11e1-8c0a-002564c97630")
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.keyCode == SWT.F2) {
            ISelection selection = this.dialog.getContentTable().getSelection();
        
            if (selection instanceof IStructuredSelection) {
                List<Object> selectedObjects = ((IStructuredSelection) selection).toList();
        
                if (selectedObjects.size() == 1) {
                    Object selectedObject = selectedObjects.get(0);
        
                    this.dialog.getContentTable().editElement(selectedObject,0);
                }
            }
        }
    }

    @objid ("8dcc12f4-c068-11e1-8c0a-002564c97630")
    @Override
    public void modify(Object element, String property, Object value) {
        if (element instanceof TableItem) {
            TableItem item = (TableItem)element;
            Object data = item.getData();
        
            final int index = this.dialog.getContent().indexOf(data);
            this.dialog.getContent().set(index, (String) value);
            this.dialog.refresh();
        }
    }

}
