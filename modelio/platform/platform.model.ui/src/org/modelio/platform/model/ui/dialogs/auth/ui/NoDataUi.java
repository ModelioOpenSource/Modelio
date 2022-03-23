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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.modelio.vbasic.auth.IAuthData;

@objid ("cf77c2cd-cd3e-4aae-ab03-834e940fe564")
public class NoDataUi extends AbstractDataUi {
    @objid ("b5db3765-c844-4045-97e7-b4d11a19f197")
    public  NoDataUi(Composite parent) {
        super(parent);
    }

    @objid ("cf4af254-9ed8-4015-a7c5-4511debb99ab")
    @Override
    protected void createContents(Composite parent) {
        final Composite data = new Composite(parent, SWT.NONE);
        data.setLayout(new GridLayout(1, false));
        Label label = new Label(data, SWT.NONE);
        label.setText("No data required");
        label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
        
    }

    @objid ("91af9019-086b-4118-92d8-ba017895c934")
    @Override
    public void show(IAuthData data) {
        // nothing to do
    }

    @objid ("45f84ed8-c5db-4170-a0e5-ddc08b2b0117")
    @Override
    public void collect(IAuthData data) {
        // nothing to do
    }

}
