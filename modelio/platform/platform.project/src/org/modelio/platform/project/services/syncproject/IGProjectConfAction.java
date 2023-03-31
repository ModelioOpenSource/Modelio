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
package org.modelio.platform.project.services.syncproject;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.core.IGProjectState.GProjectStateEnum;
import org.modelio.vbasic.progress.IModelioProgress;

@objid ("6e70a13a-a8bd-4e6d-8a6e-b7ae2a5c4966")
public interface IGProjectConfAction {
    /**
     * Get a user friendly localized label for this action.
     * @return a label string.
     */
    @objid ("750c4b0d-63e1-4865-bae0-b240e5f03416")
    String getLabel();

    /**
     * Apply the action to the project.
     * @param monitor a progress monitor supplier if the operation takes time
     * @param project the updated project
     * @param phase the project state. Same as {@link IGProject#getState()}.
     * @return the synchronization failures.
     */
    @objid ("f7182355-cbb3-43ed-8f07-79c715eb4a83")
    Collection<GProblem> apply(IModelioProgress monitor, IGProject project, GProjectStateEnum phase);
}

