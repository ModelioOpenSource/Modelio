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

package org.modelio.audit.service.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.audit.service.IAuditService;

/**
 * This class is used as a processor (see the plugin.xml file) to make the injection framework instantiate the
 * ModuleService and put it in the context.
 */
@objid ("7dde1288-45fb-11e2-9b4d-bc305ba4815c")
public class AuditServiceInitializer {
    @objid ("7dde128a-45fb-11e2-9b4d-bc305ba4815c")
    @Execute
    private static void execute(IEclipseContext context) {
        context.set(IAuditService.class, ContextInjectionFactory.make(AuditService.class, context));
    }

}
