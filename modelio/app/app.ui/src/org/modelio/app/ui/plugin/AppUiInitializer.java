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

package org.modelio.app.ui.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.app.ui.cert.ServerTrustErrorHandler;

/**
 * E4 model processor called by plugin.xml.
 * <p>
 * Initializes the untrusted SSL server handler.
 */
@objid ("d8f31d93-89b0-4cf4-936a-b9dc11ecaf9e")
public class AppUiInitializer {
    @objid ("ca7e63e0-da7c-44b1-895d-7788c3c2700f")
    @Execute
    static void initialize(IEclipseContext context) {
        ContextInjectionFactory.make(ServerTrustErrorHandler.class, context);
    }

}
