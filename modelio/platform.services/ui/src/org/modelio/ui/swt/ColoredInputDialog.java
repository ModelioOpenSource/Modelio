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

package org.modelio.ui.swt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * Extension of InputDialog setting the text foreground color to red or green according to the validation.
 * 
 * @since 3.8
 */
@objid ("5ab0278a-f6e6-46a7-bedc-cac84172f05c")
public class ColoredInputDialog extends InputDialog {
    /**
     * C'tor.
     * 
     * @param parentShell the parent shell, or <code>null</code> to create a top-level shell
     * @param dialogTitle the dialog title, or <code>null</code> if none
     * @param dialogMessage the dialog message, or <code>null</code> if none
     * @param initialValue the initial input value, or <code>null</code> if none (equivalent to the empty string)
     * @param validator an input validator, or <code>null</code> if none
     */
    @objid ("571df895-48fa-4b68-8836-f0d46eab450c")
    public ColoredInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue, IInputValidator validator) {
        super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
    }

    @objid ("74769984-fbde-496e-8fa5-12335b4445d9")
    @Override
    protected void validateInput() {
        super.validateInput();
        
        if (getOkButton().isEnabled()) {
            getText().setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        } else {
            getText().setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
        }
    }

}
