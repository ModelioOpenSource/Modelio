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

package org.modelio.core.ui.dialogs.auth.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.vbasic.auth.IAuthData;

@objid ("67c0396f-7f0a-4444-a4b6-cf1864675ca5")
public interface IAuthDataUi {
    /**
     * Return the top composite of the ui
     * @return
     */
    @objid ("c8f6fd57-69ab-4d05-988e-dfbc58a2fb73")
    Composite getTopComposite();

    /**
     * Init the UI from 'data' values
     * @param data
     */
    @objid ("0f190770-dbe0-43e1-830e-4d402e0eaa2a")
    void show(IAuthData data);

    /**
     * Collect data values from the UI into 'data'
     * @param data
     */
    @objid ("4fd34d3f-d044-4ddb-bd83-9ac57898f260")
    void collect(IAuthData data);

}
