/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;

@objid ("0e3b35d1-8fa2-11dd-bbe0-001ec947ccaf")
public class EnumParameterModel extends ParameterModel {
    @objid ("da473b9f-ee96-11e0-8487-002564c97630")
    protected Map<String, String> items;

    @objid ("e496973e-feb1-11dd-8b31-0014222a9f79")
    public EnumParameterModel(IModuleUserConfiguration conf, String name, String label, String description, String defaultValue) {
        super(conf, name, label, description, defaultValue);
        this.items = new LinkedHashMap<>();
    }

    @objid ("e4e3237a-9137-11dd-9e51-001ec947ccaf")
    public void addItem(String value, String label) {
        this.items.put(label, value);
    }

    @objid ("e4e32387-9137-11dd-9e51-001ec947ccaf")
    public Collection<String> getLabels() {
        return this.items.keySet();
    }

    @objid ("e4e585d3-9137-11dd-9e51-001ec947ccaf")
    public String getValue(String label) {
        return this.items.get(label);
    }

    @objid ("e4e585e4-9137-11dd-9e51-001ec947ccaf")
    public String getLabel(String value) {
        for (Map.Entry<String, String> e : this.items.entrySet()) {
            if (e.getValue().equals(value)) {
                return e.getKey();
            }
        }
        return "";
    }

}
