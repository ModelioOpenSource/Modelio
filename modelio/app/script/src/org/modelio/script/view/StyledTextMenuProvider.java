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
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.modelio.script.plugin.Script;

/**
 * Build a standard contextual menu with cut, copy, paste, select all and delete
 * commands
 * on a {@link StyledText}.
 */
@objid ("007ce2e6-663d-105c-84ef-001ec947cd2a")
class StyledTextMenuProvider {
    @objid ("007cf43e-663d-105c-84ef-001ec947cd2a")
    StyledTextMenuProvider(StyledText text) {
        createMenu(text);
    }

    @objid ("007d1216-663d-105c-84ef-001ec947cd2a")
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

    @objid ("007d38a4-663d-105c-84ef-001ec947cd2a")
    private boolean isClipBoard(final StyledText text) {
        Clipboard c = new Clipboard(text.getDisplay());
        String s = (String) c.getContents(TextTransfer.getInstance(), DND.CLIPBOARD);
        c.dispose();
        return (s != null) && !s.isEmpty();
    }

    @objid ("007d6bd0-663d-105c-84ef-001ec947cd2a")
    protected void populateMenu(final StyledText text, final Menu popupMenu) {
        final boolean isSelection = !text.getSelectionText().isEmpty();
        final boolean isClipBoard = isClipBoard(text);
        final boolean enabled = text.isEnabled() && text.getEditable();
        
        final MenuItem cutItem = new MenuItem(popupMenu, SWT.PUSH);
        cutItem.setText(Script.I18N.getString("Menus.Edit.Cut"));
        cutItem.setImage(null);
        cutItem.setAccelerator(SWT.CTRL | 'X');
        cutItem.setEnabled(isSelection && enabled);
        cutItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.cut();
            }
        });
        
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
        
        final MenuItem pasteItem = new MenuItem(popupMenu, SWT.PUSH);
        pasteItem.setText(Script.I18N.getString("Menus.Edit.Paste"));
        pasteItem.setImage(null);
        pasteItem.setAccelerator(SWT.CTRL | 'V');
        pasteItem.setEnabled(isClipBoard && enabled);
        pasteItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.paste();
            }
        });
        
        final MenuItem deleteItem = new MenuItem(popupMenu, SWT.PUSH);
        deleteItem.setText(Script.I18N.getString("Menus.Edit.Delete"));
        deleteItem.setImage(null);
        // deleteItem.setAccelerator(SWT.DEL );
        deleteItem.setEnabled(enabled);
        deleteItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.invokeAction(ST.DELETE_NEXT);
            }
        });
        
        new MenuItem(popupMenu, SWT.SEPARATOR);
        
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
