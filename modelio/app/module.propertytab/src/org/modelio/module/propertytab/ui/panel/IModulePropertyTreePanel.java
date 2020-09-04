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

package org.modelio.module.propertytab.ui.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.module.propertytab.model.ModulePropertyModel;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("c88b5a50-1eba-11e2-9382-bc305ba4815c")
public interface IModulePropertyTreePanel {
    @objid ("c88bcf80-1eba-11e2-9382-bc305ba4815c")
    void setInput(IModelioPickingService pickingService, ModulePropertyModel mdacPropertyTable);

    @objid ("c88bcf83-1eba-11e2-9382-bc305ba4815c")
    void stop();

    @objid ("c88bcf84-1eba-11e2-9382-bc305ba4815c")
    void start(ICoreSession session);

    @objid ("c88bf691-1eba-11e2-9382-bc305ba4815c")
    void disableGUI();

    @objid ("c88bf692-1eba-11e2-9382-bc305ba4815c")
    void enableGUI();

    @objid ("c88bf693-1eba-11e2-9382-bc305ba4815c")
    Composite getComposite();

    @objid ("c88bf695-1eba-11e2-9382-bc305ba4815c")
    void refresh();

    @objid ("c88bf696-1eba-11e2-9382-bc305ba4815c")
    void setFocus();

}
