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
package org.modelio.api.module.context.configuration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f71eb47b-f121-11e1-9565-002564c97630")
public interface IConfigParamValidator {
    @objid ("015852d8-f122-11e1-9565-002564c97630")
    boolean isValidValue(String paramName, String value);
}

