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
package org.modelio.diagram.browser.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.diagram.browser.view.DiagramBrowserView;

@objid ("a9e805e2-54c7-11e2-ae63-002564c97630")
public class CollapseAllHandler {
    /**
     * Collapses all nodes of the DiagramBrowserView's tree, starting with the root.
     * @param part a {@link BrowserView} part.
     */
    @objid ("b1e97f07-54c7-11e2-ae63-002564c97630")
    @Execute
    public static final void execute(MPart part) {
        DiagramBrowserView view = (DiagramBrowserView) part.getObject();
        view.collapseAll();
        
    }

    @objid ("a36fc690-a43f-4fbe-9f8d-356c21e91acd")
    @CanExecute
    public boolean canExecute(MPart part) {
        if (part == null || part.getObject() == null) {
            return false;
        }
        return (part.getObject() instanceof DiagramBrowserView);
    }

}
