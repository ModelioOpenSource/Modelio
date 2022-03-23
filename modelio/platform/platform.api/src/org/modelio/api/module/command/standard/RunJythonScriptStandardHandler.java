/* 
 * Copyright 2013-2020 Modeliosoft
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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.script.ScriptEngine;
import org.modelio.api.module.IModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command handler that runs a Jython script.
 * <p>
 * The jython script must be in the {@link RunJythonScriptBasicHandler#SCRIPT_PARAM SCRIPT_PARAM} command parameter.
 * <p>
 * Used parameters:<ul>
 * <li>{@value #ACCEPT_PARAM} : Jython script called by {@link #accept(List, IModule)}
 * <li>{@value #IS_ACTIVE_FOR_PARAM} : Jython script called by {@link #isActiveFor(List, IModule)}
 * </ul>
 * <p>
 * The scripts are run with the bindings configured by {@link #configure(ScriptEngine, List, IModule)}.
 * 
 * @since 3.4
 */
@objid ("1c1a4257-f34a-4083-8cb4-38379561c097")
public class RunJythonScriptStandardHandler extends RunJythonScriptBasicHandler {
    /**
     * Command parameter containing the Jython script called by {@link #isActiveFor(List, IModule)}.
     */
    @objid ("f80c5141-b2c7-43cc-81f8-e19992dcbf11")
    public static final String IS_ACTIVE_FOR_PARAM = "isActiveFor";

    /**
     * Command parameter containing the Jython script called by {@link #accept(List, IModule)}.
     */
    @objid ("9dd42606-768b-4ebb-b5cf-01ef18d40669")
    public static final String ACCEPT_PARAM = "accept";

    @objid ("ff7e8cc8-128c-474f-942a-311245e46857")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module)) {
            return runBooleanScript(ACCEPT_PARAM, selectedElements,module );
        }
        return false;
    }

    @objid ("9c1928f7-4b59-4375-b23b-6cd70077e86b")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        if (super.isActiveFor(selectedElements, module)) {
            return runBooleanScript(IS_ACTIVE_FOR_PARAM, selectedElements,module );
        }
        return false;
    }

}
