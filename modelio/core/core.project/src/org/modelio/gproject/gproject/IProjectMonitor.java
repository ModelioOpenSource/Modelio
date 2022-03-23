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
package org.modelio.gproject.gproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * GProject event handler.
 */
@objid ("89539f7a-08b2-11e2-b193-001ec947ccaf")
public interface IProjectMonitor {
    /**
     * Handle a project event.
     * @param ev a project event.
     */
    @objid ("6181ee62-08b6-11e2-b193-001ec947ccaf")
    void handleProjectEvent(GProjectEvent ev);

}
