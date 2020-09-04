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

package org.modelio.core.ui.panels.search;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.model.search.engine.ISearchCriteria;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("16bc12ac-743a-45b9-807c-45cb8f11b451")
public interface ISearchPanel {
    @objid ("05f2d7ad-ad74-4769-9e48-d86ab0748f2d")
    Control getControl();

    @objid ("ba2d417d-52d5-4d0f-bb98-f66af88f8346")
    ISearchCriteria getCriteria();

    @objid ("059b4f8b-a568-4dcb-8728-ca53d371d696")
    void setCriteria(ISearchCriteria criteria);

    @objid ("2d506cad-7266-42e6-befa-7dcbc18f5d1d")
    void initialize(Composite parent, ICoreSession session, ISearchController controller);

    /**
     * @since Modelio 3.8
     * 
     * Whether or not this search panel should be displayed or not (implementors may analyze any condition they want to decide  upon)
     * @param session @return
     */
    @objid ("79555ee2-5889-45ec-a43c-db504247af54")
    default boolean isActive(ICoreSession session) {
        return true;
    }

}
