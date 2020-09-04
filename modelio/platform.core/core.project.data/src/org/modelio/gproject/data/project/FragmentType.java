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

package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Fragment type.
 */
@objid ("005cbeda-3f59-1008-84ba-001ec947cd2a")
public enum FragmentType {
    /**
     * Local EXML fragment
     */
    EXML,
    /**
     * EXML fragment located on a HTTP server.
     */
    EXML_URL,
    /**
     * RAMC archive fragment.
     */
    RAMC,
    /**
     * static.ramc fragment in a module.
     */
    MDA,
    /**
     * EXML fragment on a Subversion repository.
     */
    EXML_SVN;
}
