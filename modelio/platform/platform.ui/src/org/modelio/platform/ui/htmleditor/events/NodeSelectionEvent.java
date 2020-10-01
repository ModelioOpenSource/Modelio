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

package org.modelio.platform.ui.htmleditor.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
@objid ("eae7d0b1-39d8-47b5-95d6-0bd02fecf492")
public class NodeSelectionEvent {
    @objid ("86ced2c0-7639-43fc-968d-a9bcc721240d")
    private final String selectedHtml;

    @objid ("c0d10f91-2b61-402b-9f14-56e5c9cdd35e")
    public NodeSelectionEvent(String selectedHtml) {
        this.selectedHtml = selectedHtml;
    }

    @objid ("ee0cde1f-a89c-4d12-9bc4-f1e7dea3c0da")
    public String getSelectedHtml() {
        return this.selectedHtml;
    }

}
