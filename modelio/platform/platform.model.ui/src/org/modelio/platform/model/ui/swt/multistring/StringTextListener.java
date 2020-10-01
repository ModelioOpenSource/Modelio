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

package org.modelio.platform.model.ui.swt.multistring;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

@objid ("8dd22d9b-c068-11e1-8c0a-002564c97630")
class StringTextListener implements ModifyListener, KeyListener {
    @objid ("8dd22d9c-c068-11e1-8c0a-002564c97630")
    private MultiStringEditionComposite dialog = null;

    @objid ("8dd22d9d-c068-11e1-8c0a-002564c97630")
    public StringTextListener(MultiStringEditionComposite dialog) {
        this.dialog = dialog;
    }

    @objid ("8dd3b406-c068-11e1-8c0a-002564c97630")
    @Override
    public void keyPressed(KeyEvent e) {
        // Nothing to do
    }

    @objid ("8dd3b40a-c068-11e1-8c0a-002564c97630")
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
            Text addStringText = (Text)e.getSource();
            String text = addStringText.getText();
            this.dialog.addAdapter(text);
            addStringText.setText("");
        }
    }

    @objid ("8dd3b40e-c068-11e1-8c0a-002564c97630")
    @Override
    public void modifyText(ModifyEvent e) {
        Text addStringText = (Text)e.getSource();
        
        String text = addStringText.getText();
        if (text != null && !text.equals("")) {
            this.dialog.getAddParameterButton().setEnabled(true);
        } else {
            this.dialog.getAddParameterButton().setEnabled(false);
        }
    }

}
