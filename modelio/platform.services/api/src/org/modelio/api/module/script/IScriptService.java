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

package org.modelio.api.module.script;

import javax.script.ScriptEngine;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Get the modelio script service, to execute Jython macros.
 */
@objid ("69996214-6bf6-11e0-a371-001ec947cd2a")
public interface IScriptService {
    @objid ("7564e02f-7b13-11e0-bb5d-001ec947cd2a")
    ScriptEngine getScriptEngine(final ClassLoader loader);

}
