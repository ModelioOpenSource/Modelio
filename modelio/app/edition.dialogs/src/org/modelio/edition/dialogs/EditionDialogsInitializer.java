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

package org.modelio.edition.dialogs;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;

/**
 * This class is used as a processor (see the plugin.xml file) to make the injection framework instantiate the
 * ModuleService and put it in the context.
 * <p>
 * Also initialize the module cache and put it in the context with {@link IModuleCatalog} as key.
 */
@objid ("dbe23471-0f02-11e2-bbe6-001ec947c8cc")
public class EditionDialogsInitializer {
    @objid ("05edb389-111c-11e2-8b0d-001ec947c8cc")
    @Execute
    private static void execute(IEclipseContext context) {
        context.set(EditionDialogService.class, ContextInjectionFactory.make(EditionDialogService.class, context));
    }

}
