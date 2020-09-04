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

package org.modelio.script.macro;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;

/**
 * This class is used as a processor (see the plugin.xml file) to make the
 * injection framework instantiate the MacroCatalog and put it in the context.
 */
@objid ("cb8e7d0e-ad61-4f7f-bbcd-db5e3749894e")
class MacroCatalogInitializer {
    @objid ("8564fe42-f959-4b1e-ad08-f8a5773a8141")
    @Execute
    private static void execute(IEclipseContext context) {
        context.set(org.modelio.script.macro.IMacroService.class,
                ContextInjectionFactory.make(org.modelio.script.macro.MacroService.class, context));
    }

}
