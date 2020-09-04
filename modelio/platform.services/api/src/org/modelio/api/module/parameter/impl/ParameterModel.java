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

package org.modelio.api.module.parameter.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.parameter.IParameterModel;

@objid ("6e8b3d61-8f96-11dd-bbe0-001ec947ccaf")
public abstract class ParameterModel implements IParameterModel {
    @objid ("c472b13d-8f9f-11dd-bbe0-001ec947ccaf")
    private String name = "";

    @objid ("c472b142-8f9f-11dd-bbe0-001ec947ccaf")
    private String label = "";

    @objid ("c4751398-8f9f-11dd-bbe0-001ec947ccaf")
    private String defaultValue = "";

    @objid ("4511c1d6-feb0-11dd-8b31-0014222a9f79")
    private String description = "";

    @objid ("c48ceb2d-8f9f-11dd-bbe0-001ec947ccaf")
    private IModuleUserConfiguration config = null;

    @objid ("45200ffa-feb0-11dd-8b31-0014222a9f79")
    public ParameterModel(IModuleUserConfiguration conf, String name, String label, String description, String defaultValue) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.config = conf;
        this.defaultValue = defaultValue;
    }

    @objid ("c47c3ab2-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public String getDefaultValue() {
        return this.defaultValue;
    }

    @objid ("c47e9d09-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public String getLabel() {
        return this.label;
    }

    @objid ("c480ff5c-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("c485c417-8f9f-11dd-bbe0-001ec947ccaf")
    @Override
    public void setValue(Object value) throws IllegalArgumentException {
        this.config.setParameterValue(this.name, value.toString());
    }

    @objid ("c48ceb1e-8f9f-11dd-bbe0-001ec947ccaf")
    public String getStringValue() {
        return this.config.getParameterValue(this.name);
    }

    @objid ("557ab14a-feb0-11dd-8b31-0014222a9f79")
    @Override
    public String getDescription() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.description;
    }

    @objid ("320d7157-d079-4398-9b91-20e4ad41021d")
    @Override
    public boolean isLocked() {
        return this.config.isLocked(this.name);
    }

}
