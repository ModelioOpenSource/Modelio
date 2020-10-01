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

package org.modelio.edition.dialogs.dialog.panels.operation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Text;

@objid ("3e834685-4c32-419a-b8c8-e6253792cdcf")
public class OperationNameAreaListener implements KeyListener, FocusListener {
    @objid ("40911831-fef3-42b1-b9b5-9444c6c5e614")
    private OperationEditPanel panel = null;

    @objid ("6965be24-dac9-45ba-90a4-12fa3a92f4ea")
    public OperationNameAreaListener(OperationEditPanel panel) {
        this.panel = panel;
    }

    @objid ("54d5d9c9-6561-4f11-bd5e-ab895b4f044c")
    @Override
    public void keyPressed(KeyEvent e) {
        // Do nothing
    }

    @objid ("66e14bb2-65ee-4aa3-b3d9-dbde75dcd483")
    @Override
    public void keyReleased(KeyEvent e) {
        Text nameText = (Text) e.getSource();
        if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
            this.panel.getModel().setName(nameText.getText());
        } else if (e.keyCode == SWT.ESC) {
            nameText.setText(this.panel.getModel().getName());
        }
    }

    @objid ("12039104-43a3-4718-a693-d486ecc5d521")
    @Override
    public void focusGained(FocusEvent e) {
        // Do nothing
    }

    @objid ("765a90a0-c7ef-47de-ae98-1e9e9fcc2172")
    @Override
    public void focusLost(FocusEvent e) {
        Text nameText = (Text) e.getSource();
        this.panel.getModel().setName(nameText.getText());
    }

}
