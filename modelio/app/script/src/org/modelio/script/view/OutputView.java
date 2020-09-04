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

package org.modelio.script.view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.modelio.script.plugin.Script;

/**
 * The output area of the ScriptView
 */
@objid ("008c762a-663d-105c-84ef-001ec947cd2a")
class OutputView extends StyledText {
    @objid ("008c8912-663d-105c-84ef-001ec947cd2a")
    public OutputView(Composite parent, int style) {
        super(parent, style | SWT.READ_ONLY);
        setEditable(false);
        
        // Add contextual menu
        createMenu(this);
    }

    @objid ("008c9eac-663d-105c-84ef-001ec947cd2a")
    private void createMenu(final StyledText text) {
        final Menu popupMenu = new Menu(text);
        text.setMenu(popupMenu);
        
        popupMenu.addListener(SWT.Show, new Listener() {
        
            @Override
            public void handleEvent(Event event0) {
                // Remove all items
                for (MenuItem i : popupMenu.getItems()) {
                    i.dispose();
                }
        
                populateMenu(text, popupMenu);
        
            }
        });
    }

    @objid ("008cc558-663d-105c-84ef-001ec947cd2a")
    protected void populateMenu(final StyledText text, final Menu popupMenu) {
        final boolean isSelection = !text.getSelectionText().isEmpty();
        
        final MenuItem copyItem = new MenuItem(popupMenu, SWT.PUSH);
        copyItem.setText(Script.I18N.getString("Menus.Edit.Copy"));
        copyItem.setImage(null);
        copyItem.setAccelerator(SWT.CTRL | 'C');
        copyItem.setEnabled(isSelection);
        copyItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.copy();
            }
        });
        
        new MenuItem(popupMenu, SWT.SEPARATOR);
        
        final MenuItem deleteItem = new MenuItem(popupMenu, SWT.PUSH);
        deleteItem.setText(Script.I18N.getString("Menus.Edit.Clear"));
        deleteItem.setImage(null);
        deleteItem.setAccelerator(SWT.CTRL | SWT.DEL);
        deleteItem.setEnabled(true);
        deleteItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.setText("");
            }
        });
        
        final MenuItem selectAllItem = new MenuItem(popupMenu, SWT.PUSH);
        selectAllItem.setText(Script.I18N.getString("Menus.Edit.SelectAll"));
        selectAllItem.setImage(null);
        selectAllItem.setAccelerator(SWT.CTRL | 'A');
        selectAllItem.setEnabled(true);
        selectAllItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.selectAll();
            }
        });
    }

}
