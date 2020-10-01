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

package org.modelio.gproject.ramc.core.packaging.filters;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that allows to configure a model filter.
 * <p>
 * If contradictory methods are called the last call prevails.
 */
@objid ("6018ae8b-918a-4378-84e2-95327eafe42b")
public interface IModelFilterConfigurer {
    /**
     * Add a filter to the given metaclass and all its sub classes.
     * <p>
     * The filter replaces the previously set one.
     * 
     * @param mmClass a metamodel class
     * @param objectFilter a filter to add.
     */
    @objid ("6b0a427a-b9c9-4473-8d5a-140e6f7095ff")
    void setFilter(final Class<? extends MObject> mmClass, final IObjectFilter objectFilter);

}
