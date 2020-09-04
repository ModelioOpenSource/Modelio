/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.xmi.reverse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("412af960-9bd3-4f1b-9bb9-89f474888b44")
public class TotalImportMap {
    @objid ("49187acc-4d97-468c-b156-85ce9b5df395")
    private static TotalImportMap theSingleInstance;

    @objid ("98146d3d-6f0b-45b5-9668-b7e4d406b4ed")
    private Map<Object, Object> modelMap;

    @objid ("bef12e6c-5398-4ee0-88df-7887d83c241c")
    private TotalImportMap() {
        this.modelMap = new HashMap<>();
    }

    @objid ("3df0ae6f-b44e-4197-846f-4c1ff007fea4")
    public static TotalImportMap getInstance() {
        if (theSingleInstance == null)
            theSingleInstance = new TotalImportMap();
        return theSingleInstance;
    }

    @objid ("ddf8e20f-e1bb-4c10-ab97-96af2d40968e")
    public void clear() {
        this.modelMap.clear();
    }

    @objid ("059ca5aa-1d56-4823-8cf8-9f06cf43447c")
    public boolean containsKey(Object key) {
        return  this.modelMap.containsKey(key);
    }

    @objid ("b949f27a-0d0c-410d-a744-b363d6325d1c")
    public boolean containsValue(Object val) {
        return  this.modelMap.containsValue(val);
    }

    @objid ("858b5ec0-063d-4aaa-bda2-251cee9691e0")
    public Object get(Object key) {
        return  this.modelMap.get(key);
    }

    @objid ("750a2abb-f099-49c9-90f5-2180a1e0e532")
    public boolean isEmpty() {
        return  this.modelMap.isEmpty();
    }

    @objid ("43b0093f-bb61-4aac-98d7-83b256dee961")
    public Set<Object> keySet() {
        return  this.modelMap.keySet();
    }

    @objid ("5491292b-e36d-4ff3-b926-7db1b59e7001")
    public Object put(Object key, Object value) {
        return  this.modelMap.put(key, value);
    }

    @objid ("1096fa5e-ee6d-4d66-be84-9311d065cb83")
    public Object remove(Object key) {
        return  this.modelMap.remove(key);
    }

    @objid ("ba14640b-b364-4b87-9ad5-87ee1f62dea1")
    public int size() {
        return  this.modelMap.size();
    }

    @objid ("eac0a812-0db2-4aff-a46f-04ef79053895")
    public Collection<Object> values() {
        return  this.modelMap.values();
    }

}
