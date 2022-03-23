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
package org.modelio.platform.ui.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Text;

/**
 * Interface for listening to data model changes on a {@link IPanelProvider}.
 * <p>
 * This interface may be implemented by clients.
 * </p>
 * 
 * @see IPanelProvider#addListener(IPanelListener)
 * @see IPanelProvider#removeListener(IPanelListener)
 */
@objid ("70711571-239e-4f7f-9a65-9a903ba67c3e")
@FunctionalInterface
public interface IPanelListener {
    /**
     * Notifies this listener that the panel's data model has changed.
     * @param changedData the changed data model.
     * @param isValidate whether or not this change is terminal or not.
     * For example, typing text in a {@link Text} usually triggers non-validating changes for each
     * letter, and a validating change when enter is pressed.
     */
    @objid ("7d116904-de1f-4307-a41a-cf292f4d03ab")
    void dataChanged(Object changedData, boolean isValidate);

}
