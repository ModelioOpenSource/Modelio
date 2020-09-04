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
import org.modelio.api.module.parameter.IParameterGroupModel;
import org.modelio.api.module.parameter.IParameterModel;

@objid ("682c74e9-8f96-11dd-bbe0-001ec947ccaf")
public class ParameterGroupModel implements IParameterGroupModel {
    @objid ("c2747a01-8f9f-11dd-bbe0-001ec947ccaf")
    private String name = "";

    @objid ("c2747a06-8f9f-11dd-bbe0-001ec947ccaf")
    private String label = "";

    @objid ("c28065c5-8f9f-11dd-bbe0-001ec947ccaf")
    private List<IParameterModel> parameters = null;

    @objid ("c276dc5e-8f9f-11dd-bbe0-001ec947ccaf")
    public ParameterGroupModel(String name, String label) {
        this.name = name;
        this.label = label;
        this.parameters = new ArrayList<>();
    }

    @objid ("c2793eb5-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public String getLabel() {
        return this.label;
    }

    @objid ("c2793ec6-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("c27ba118-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public void addParameter(IParameterModel p) {
        this.parameters.add(p);
    }

    @objid ("c27ba121-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public List<IParameterModel> getParameters() {
        return this.parameters;
    }

    @objid ("c418161e-9ba7-45b4-b2c1-fd190d99be80")
    @Override
    public void removeParameter(IParameterModel p) {
        this.parameters.remove(p);
    }

}
