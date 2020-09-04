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

package org.modelio.core.rcp.uiservice;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;

@objid ("b3660045-1abe-4813-9df4-b2b1d0cc5ddc")
public interface IModelioUiService {
    /**
     * Switch to the 'p' perspective. Note that switching to a perspective will
     * force showWelcome(false).
     * 
     * If 'p' is null, a 'default' perspective is
     * guessed based on current application status, and chosen as follows:<ul>
     * <li>the workspace perspective if no project is currently opened</li>
     * <li>the first perspective found otherwise</li>
     * </ul>
     * @param p
     */
    @objid ("52f38234-3817-4d98-ba3a-7e1ff71c4fbb")
    void switchToPerspective(MPerspective p);

    /**
     * Switch to workspace perspective
     */
    @objid ("05653251-6131-4c61-8066-d8437b9ad5f7")
    void switchToWorkspace();

    /**
     * Show/Hide the welcome view. When the welcome view is shown, the
     * application main perspective stack is hidden otherwise it is made
     * visible.
     * @param onOff
     */
    @objid ("dca3dc06-10b2-4790-b107-155e75ff9ba7")
    void showWelcome(boolean onOff);

}
