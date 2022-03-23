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
package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Project type enumeration.
 * <p>
 * Each project implementation has its own value.
 */
@objid ("e03829a9-0985-11e2-bed6-001ec947ccaf")
public enum ProjectType {
    /**
     * Local project.
     */
    @objid ("e79139a2-0985-11e2-bed6-001ec947ccaf")
    LOCAL,
    /**
     * Project located on a SVN repository.
     */
    @objid ("ea2cc69a-0985-11e2-bed6-001ec947ccaf")
    SVN,
    /**
     * Project located on a HTTP server.
     */
    @objid ("edce9418-0985-11e2-bed6-001ec947ccaf")
    HTTP;

}
