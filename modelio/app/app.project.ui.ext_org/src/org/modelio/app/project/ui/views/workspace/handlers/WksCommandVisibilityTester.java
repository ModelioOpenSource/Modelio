/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.app.project.ui.views.workspace.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.app.project.ui.plugin.AppProjectUi;

/**
 * This tester checks project management related command visibility in the workspace view.<br/>
 * Note that an ultimate command availability check is carried out by the handler that may enable or disable the command (greyed commands)
 */
@objid ("9c3c0fb5-0a8f-46c8-b08b-00d7e541ef30")
public class WksCommandVisibilityTester extends PropertyTester {
    @objid ("c456d452-86a8-4d47-b9bd-1faf4cb9fbb6")
    @Override
    public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
        ISelection selection = (ISelection) receiver;
        
        switch (property) {
        case "createProjectCommandIsVisible":
        case "importProjectCommandIsVisible":
            // Create project is visible on PathEntry (local projects) or on a working set
            // Import project is visible on PathEntry (local projects) or on a working set
            return true;
        
        case "openProjectCommandIsVisible":
        case "closeProjectCommandIsVisible":
            // Open or close project is visible on ProjectSpace, ProjectReference, RemoteProject
            return true;
        
        case "renameProjectCommandIsVisible":
            return true;
        
        case "deleteProjectCommandIsVisible":
            return true;
        
        case "exportProjectCommandIsVisible":
            return true;
        
        
        
        
        default:
            AppProjectUi.LOG.debug("WksCommandVisibilityTester : '" + property + "' is not a known property");
            break;
        }
        return true;
    }

}
