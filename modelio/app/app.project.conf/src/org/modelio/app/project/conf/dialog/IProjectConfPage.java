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
package org.modelio.app.project.conf.dialog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

@objid ("e6b81086-48bb-4d61-b44c-098487ca9487")
public interface IProjectConfPage {
    @objid ("ad1d2850-a4a9-4c23-af6c-b365c7263cb1")
    Composite createControls(FormToolkit toolkit, MApplication application, final Composite parent);

    @objid ("108a7c82-cd84-4c89-aa8e-a725f6bfb9fa")
    Control getControl();

    @objid ("1e600340-972f-40bc-95ef-d75623d7a338")
    void setInput(ProjectModel model);

    /**
     * @return the help topic identifier for the page. Returning <i>null</i< is allowed.
     */
    @objid ("6c0ab407-4913-41bf-897f-f1c4baf29fe4")
    String getHelpTopic();

}
