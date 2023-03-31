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
package org.modelio.diagram.elements.core.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;

/**
 * Interface defining the DragTrackerProvider. Main (and only) method is to provide a specific drag tracker upon
 * request.
 * 
 * @author fpoyer
 */
@objid ("80a17d76-1dec-11e2-8cad-001ec947c8cc")
public interface IDragTrackerProvider {
    /**
     * Returns a Drag Tracker.
     * @param request current request for which a Drag Tracker is needed.
     * @return a DragTracker.
     */
    @objid ("80a17d78-1dec-11e2-8cad-001ec947c8cc")
    DragTracker getDragTracker(Request request);
}

