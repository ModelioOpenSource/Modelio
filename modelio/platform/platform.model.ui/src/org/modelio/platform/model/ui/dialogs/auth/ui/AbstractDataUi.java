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
package org.modelio.platform.model.ui.dialogs.auth.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

@objid ("bdc3fece-98ea-441d-9d1f-46b87909e06c")
public abstract class AbstractDataUi implements IAuthDataUi {
    @objid ("7a43981e-3d0c-4d8d-acce-0ac9ab2aad5a")
    private Composite top;

    @objid ("bca157a0-1317-42db-812a-280cffa5cacc")
    public  AbstractDataUi(Composite parent) {
        this.top = new Composite(parent, SWT.BORDER);
        this.top.setLayout(new FillLayout());
        createContents(this.top);
        
    }

    @objid ("8a14c1d2-f663-4d61-b712-8558ecbcf28b")
    abstract void createContents(Composite parent);

    @objid ("c68c3d85-68c5-4263-9d9c-67ef03dd7d0a")
    @Override
    public Composite getTopComposite() {
        return this.top;
    }

}
