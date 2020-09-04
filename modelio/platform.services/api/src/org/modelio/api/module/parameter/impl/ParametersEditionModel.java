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

package org.modelio.api.module.parameter.impl;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.api.module.parameter.IParameterGroupModel;

@objid ("5f80c79b-8f96-11dd-bbe0-001ec947ccaf")
public class ParametersEditionModel implements IParameterEditionModel {
    @objid ("c03aa7fd-8f9f-11dd-bbe0-001ec947ccaf")
    protected List<IParameterGroupModel> groups = new ArrayList<>();

    @objid ("c03d0a59-8f9f-11dd-bbe0-001ec947ccaf")
    protected IModule mdac;

    @objid ("c035e347-8f9f-11dd-bbe0-001ec947ccaf")
    public ParametersEditionModel(IModule mdac) {
        this.mdac = mdac;
    }

    @objid ("c035e351-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public void addGroup(IParameterGroupModel group) {
        this.groups.add (group);
    }

    @objid ("c03845a1-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public List<IParameterGroupModel> getGroups() {
        return this.groups;
    }

    @objid ("f33b2665-01c0-4c3b-99ef-dc4bd8fb7249")
    @Override
    public void removeGroup(IParameterGroupModel group) {
        this.groups.remove (group);
    }

}
