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
package org.modelio.diagram.styles.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("85512460-1926-11e2-92d2-001ec947c8cc")
public interface IDefaultValuesProvider {
    /**
     * Returns a default value for a given type.<br>
     * In most cases, it is NOT possible to infer a default value for a type.It would be like answering the question:
     * what is the default value of a boolean property ?<br>
     * However, for some specific case, safe defaults values are possible, for example a Font.<br>
     * @param type
     * @throws Exception
     * @return the value or null
     */
    @objid ("85512461-1926-11e2-92d2-001ec947c8cc")
    Object getDefaultValue(Class<?> type);

    /**
     * Returns a default value for a given metakey.<br>
     * Usually, it is always possible to infer a default value for a given metakey as a metakeys carry some semantic
     * meaning.
     * @param metaKey
     * @throws Exception
     * @return the value or null
     */
    @objid ("85512467-1926-11e2-92d2-001ec947c8cc")
    Object getDefaultValue(MetaKey metaKey);

}
