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

package org.modelio.api.impl.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.script.engine.core.engine.IClassLoaderProvider;

/**
 * Class providing a classloader to the script.engine plugin to make the api plugin accessible with jython.
 * <p>
 * The api plugin can't depend on script.engine, so we have to implement this class here instead.
 * </p>
 * <p>
 * See also plugin.xml for extension point declaration.
 * </p>
 */
@objid ("32788b97-4c1a-4dc5-b46a-aaa630407837")
public class JythonClassLoaderProvider implements IClassLoaderProvider {
    @objid ("64e3c54b-0ffa-4289-9a35-c212ed2179c6")
    @Override
    public ClassLoader getClassLoader() {
        return IModelioServices.class.getClassLoader();
    }

}
