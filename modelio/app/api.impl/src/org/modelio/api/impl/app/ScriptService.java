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

package org.modelio.api.impl.app;

import javax.script.ScriptEngine;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.script.IScriptService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.engine.core.engine.ScriptRunnerFactory;

@objid ("52aaaff3-dbd9-43d5-baae-771a71ed86a2")
public class ScriptService implements IScriptService {
    @objid ("493b741a-8763-47dd-aea0-fbf307c756d9")
    private IModuleManagementService moduleService;

    @objid ("10eac8c7-0536-4cba-8b9b-20785dfae4e4")
    private IProjectService projectService;

    @objid ("0d0834b4-0e16-46fd-9f73-596e13be613b")
    @Override
    public ScriptEngine getScriptEngine(final ClassLoader loader) {
        IScriptRunner scriptRunner = ScriptRunnerFactory.getInstance().getScriptRunner("jython");
        scriptRunner.addClassLoader(loader);
        ScriptEngine engine = scriptRunner.getEngine();
        engine.put("moduleService",this.moduleService);
        engine.put("projectService",this.projectService);
        return engine;
    }

    @objid ("4ec808d5-45c0-406f-ab71-c3041b6b872b")
    public ScriptService(IModuleManagementService moduleService, IProjectService projectService) {
        this.moduleService = moduleService;
        this.projectService = projectService;
    }

}
