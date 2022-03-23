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
package org.modelio.api.module.lifecycle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("01f40414-0000-30af-0000-000000000000")
public class ModuleException extends Exception {
    @objid ("01f40414-0000-30b4-0000-000000000000")
    private static final long serialVersionUID = 1L;

    @objid ("01f40414-0000-30b8-0000-000000000000")
    public  ModuleException(String message) {
        super(message);
    }

    @objid ("7a57d65b-6111-11e0-bac7-001ec947cd2a")
    public  ModuleException(final String message, final Throwable e) {
        super(message);
        this.initCause(e);
        
    }

}
