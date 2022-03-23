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
package org.modelio.api.module.parameter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("813a10e0-8e2d-11dd-8928-001ec947ccaf")
public enum ModuleParameterType {
    @objid ("1ac93f7e-b807-11de-af68-001ec947cd2a")
    String,
    @objid ("1ac93f7f-b807-11de-af68-001ec947cd2a")
    Integer,
    @objid ("1ac93f80-b807-11de-af68-001ec947cd2a")
    Boolean,
    @objid ("1ac93f81-b807-11de-af68-001ec947cd2a")
    File,
    @objid ("1ac93f82-b807-11de-af68-001ec947cd2a")
    Directory,
    @objid ("1acba1cf-b807-11de-af68-001ec947cd2a")
    Password,
    @objid ("1acba1d0-b807-11de-af68-001ec947cd2a")
    Color;

}
