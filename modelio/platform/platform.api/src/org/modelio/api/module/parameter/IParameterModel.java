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

@objid ("75a2fdc5-8f94-11dd-bbe0-001ec947ccaf")
public interface IParameterModel {
    @objid ("6c13f589-8e2c-11dd-8928-001ec947ccaf")
    String getLabel();

    @objid ("5f4615d0-8e2f-11dd-8928-001ec947ccaf")
    void setValue(Object value) throws IllegalArgumentException;

    @objid ("720e8eb3-8e2c-11dd-8928-001ec947ccaf")
    String getDefaultValue();

    @objid ("b0ac5854-8f94-11dd-bbe0-001ec947ccaf")
    String getName();

    @objid ("697b6e31-018a-11de-a960-0014222a9f79")
    String getDescription();

    @objid ("c1fd9981-2ba9-4b09-a7e0-9d665bbc0e2c")
    boolean isLocked();

}
