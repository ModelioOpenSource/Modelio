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
package org.modelio.patterns.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.script.engine.core.engine.IClassLoaderProvider;

/**
 * Class loader provider for Jython script engine.
 * <p>
 * Gives the Patterns plugin class loader.
 */
@objid ("bb3981f2-ff43-4da0-875f-66eeda7d0426")
public class PatternsClassLoaderProvider implements IClassLoaderProvider {
    @objid ("cdc92d5b-eff2-43fe-8028-0fd799556fb9")
    @Override
    public ClassLoader getClassLoader() {
        return Patterns.class.getClassLoader();
    }

}
