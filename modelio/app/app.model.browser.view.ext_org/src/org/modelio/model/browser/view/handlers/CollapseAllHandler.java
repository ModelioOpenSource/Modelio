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

package org.modelio.model.browser.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.panel.ModelBrowserPanelProvider;

@objid ("7989cee6-43a7-11e2-b513-002564c97630")
public class CollapseAllHandler {
    /**
     * Collapses all nodes of the BrowserView's tree, starting with the root.
     * 
     * @param part a {@link BrowserView} part.
     */
    @objid ("7989cee7-43a7-11e2-b513-002564c97630")
    @Execute
    public static final void execute(MPart part) {
        BrowserView view = (BrowserView) part.getObject();
        ((ModelBrowserPanelProvider) view.getContributedPanel()).collapseAll();
    }

    @objid ("be1827ad-396b-4893-9450-3c72dac3b2ed")
    @CanExecute
    public static final boolean canExecute(final MPart part) {
        if (part == null || !(part.getObject() instanceof BrowserView)) {
            return false;
        }
        return true;
    }

}
