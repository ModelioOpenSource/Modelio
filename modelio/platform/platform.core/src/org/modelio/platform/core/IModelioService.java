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
package org.modelio.platform.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents a Modelio service.
 * <p>
 * Modelio services:
 * <ul>
 * <li>are application-scoped.</li>
 * <li>are named.</li>
 * <li>are the unique emitters of Modelio Events</li>
 * 
 * 
 * @author phv
 */
@objid ("005e2892-9877-103b-a520-001ec947cd2a")
public interface IModelioService {
    @objid ("004956b0-989b-103b-a520-001ec947cd2a")
    String getName();

}
