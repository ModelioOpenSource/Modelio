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
package org.modelio.platform.search.engine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("a66b456d-d08a-40c3-bf9d-54f66a40095a")
public interface ISearchCriteria {
    @objid ("f6cc38c4-6aa6-40cf-bf98-862a10b61e55")
    default Object getOption(String key) {
        return null;
    }

    @objid ("110a7eaa-a6d7-4ce1-83cd-3958aed44112")
    default void setOption(String key, Object value) {
        throw new UnsupportedOperationException();
    }

}
