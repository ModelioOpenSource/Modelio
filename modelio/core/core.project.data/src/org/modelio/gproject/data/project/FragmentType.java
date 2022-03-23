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
 * Fragment type.
 */
@objid ("005cbeda-3f59-1008-84ba-001ec947cd2a")
public enum FragmentType {
    /**
     * Local EXML fragment
     */
    @objid ("0082927c-3f65-1008-84ba-001ec947cd2a")
    EXML,
    /**
     * EXML fragment located on a HTTP server.
     */
    @objid ("15c4a3c1-03f9-11e2-9ef9-001ec947ccaf")
    EXML_URL,
    /**
     * RAMC archive fragment.
     */
    @objid ("00508606-3f70-1008-84ba-001ec947cd2a")
    RAMC,
    /**
     * static.ramc fragment in a module.
     */
    @objid ("2a3413ea-d724-11e1-9f03-001ec947ccaf")
    MDA,
    /**
     * EXML fragment on a Subversion repository.
     */
    @objid ("aa7c47e9-0eed-11e2-8e4b-001ec947ccaf")
    EXML_SVN;

}
