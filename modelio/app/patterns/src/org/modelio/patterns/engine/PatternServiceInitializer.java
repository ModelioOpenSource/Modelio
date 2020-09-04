/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.patterns.engine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.patterns.api.IPatternService;

/**
 * This class is used as a processor (see the plugin.xml file) to make the
 * injection framework instantiate the Pattern service and put it in the context.
 */
@objid ("fdcd50ea-d6c8-4d2a-8ddd-2e856b96a1b6")
class PatternServiceInitializer {
    @objid ("7999b23b-d0c2-4aae-bcee-dfd62cfa2c3a")
    @Execute
    public void execute(IEclipseContext context) {
        context.set(IPatternService.class, ContextInjectionFactory.make(PatternService.class, context));
    }

    /**
     * Default c'tor.
     */
    @objid ("608a9efe-0555-4976-b855-af1e14b29100")
    public PatternServiceInitializer() {
        // Nothing to do
    }

}
