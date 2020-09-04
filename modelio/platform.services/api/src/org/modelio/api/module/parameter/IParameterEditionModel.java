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

package org.modelio.api.module.parameter;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("37650d3c-8e2c-11dd-8928-001ec947ccaf")
public interface IParameterEditionModel {
    @objid ("601539e1-8e2c-11dd-8928-001ec947ccaf")
    List<IParameterGroupModel> getGroups();

    @objid ("de912bff-ebc0-4e84-ba84-e85725d289a0")
    void addGroup(IParameterGroupModel group);

    @objid ("6ad2c40a-42ef-48cf-abab-28c59220de06")
    void removeGroup(IParameterGroupModel group);

}
