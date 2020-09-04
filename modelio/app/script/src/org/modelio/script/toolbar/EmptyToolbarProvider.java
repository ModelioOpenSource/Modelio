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

package org.modelio.script.toolbar;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.modelio.core.ui.swt.trimbarcomponent.TrimBarComponent;

/**
 * Class used as an empty streched zone after the macro toolbar, to avoid SWT layout bugs on the windows platform...
 * <p>
 * Relies on a dynamic ToolControl in the e4 model.
 * </p>
 */
@objid ("f690e5c9-6ba7-47d6-9e9a-68ff9dda641a")
public class EmptyToolbarProvider extends TrimBarComponent {
    @objid ("ee4bfdb0-392e-452c-8c14-9ae116f9ac0b")
    public EmptyToolbarProvider() {
        super("PLACEHOLDER");
    }

    /**
     * Initialize the SWT control.
     * @param parent a widget which will be the parent of the new SWT components.
     */
    @objid ("d56402b9-2bb3-4c92-a090-44f1ed536a93")
    @Override
    protected Control createControl(Composite parent) {
        return new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
    }

    @objid ("d393128d-ebf0-4a73-9226-c811181bc398")
    @Override
    protected ToolBar getControl() {
        return (ToolBar) super.getControl();
    }

}
