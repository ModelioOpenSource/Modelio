/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.command.standard;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command handler that runs a Jython script.
 * <p>
 * The jython script must be in the {@value #SCRIPT_PARAM} command parameter.
 * <p>
 * The scripts are run with the bindings configured by {@link #configure(ScriptEngine, List, IModule)}.
 * 
 * @since 3.4
 */
@objid ("25280dde-eba9-4511-b609-28e4c8f37449")
public class RunJythonScriptBasicHandler extends DefaultModuleCommandHandler {
    /**
     * Command parameter containing the Jython script to run.
     */
    @objid ("61eb993b-8cc2-4652-abe1-c7b85613c04e")
    public static final String SCRIPT_PARAM = "JY_SCRIPT";

    @objid ("427b5b43-3582-41a0-8620-a1866861e03c")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        Shell parent = Display.getCurrent().getActiveShell();
        
        IModuleContext context = module.getModuleContext();
        ScriptEngine engine = context.getJythonEngine();
        
        Bindings bindings = configure(engine, selectedElements, module);
        
        String script = getParameter(SCRIPT_PARAM);
        
        // Resolve script path in module resources
        Path scriptPath = context.getConfiguration().getModuleResourcesPath().resolve(script);
        try (FileReader reader = new FileReader(scriptPath.toFile())) {
            // Eval script
            engine.eval(reader, bindings);
        } catch (ScriptException | IOException e) {
            context.getLogService().error(e);
        
            MessageDialog.openError(parent, module.getLabel(), e.getLocalizedMessage());
        }
    }

    /**
     * Computes the bindings to use to run the scripts.
     * <p>
     * By defaults creates the following bindings: <ul>
     * <li> selectedElements : the elements selected in Modelio.
     * <li> module : the module owning the command.
     * <li> modelingSession : the modeling session.
     * <li> and all command parameters.
     * </ul>
     * 
     * @param engine the script engine that will be used.
     * @param selectedElements the Modelio selection.
     * @param module the module owning the command
     * @return the bindings to use.
     */
    @objid ("649be871-3ba6-4884-ac4e-05e8ca3d7f5b")
    protected Bindings configure(ScriptEngine engine, List<MObject> selectedElements, IModule module) {
        Bindings bindings = engine.createBindings();
        
        bindings.putAll(getParameters());
        bindings.put("selectedElements", selectedElements);
        bindings.put("module", module);
        bindings.put("modelingSession", module.getModuleContext().getModelingSession());
        return bindings;
    }

    /**
     * Run a Jython script stored in the given command parameter.
     * <p>
     * The Jython script should return a boolean value.
     * Returns false if the script does not return a boolean or fails.
     * <p>
     * Does nothing and returns true if no script is present for the given command parameter.
     * 
     * @param scriptParamName the command parameter where the script is stored.
     * @param selectedElements the current Modelio selection
     * @param module the module owing the command
     * @return the boolean returned by the script.
     */
    @objid ("2c3010c7-482c-4722-8c86-50f0b9c63f0f")
    protected boolean runBooleanScript(String scriptParamName, List<MObject> selectedElements, IModule module) {
        String script = getParameter(scriptParamName);
        
        if (script != null && ! script.isEmpty()) {
            ScriptEngine engine = module.getModuleContext().getJythonEngine();
            Bindings bindings = configure(engine, selectedElements, module);
        
            try {
                Object ret = engine.eval(script, bindings);
                if (ret instanceof Boolean) {
                    return (Boolean)ret;
                } else {
                    return false;
                }
            } catch (ScriptException e) {
                module.getModuleContext().getLogService().info(e);
                return false;
            }
        } else {
            return true;
        }
    }

}
