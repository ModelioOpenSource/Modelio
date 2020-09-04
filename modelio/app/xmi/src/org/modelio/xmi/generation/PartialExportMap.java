/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.xmi.generation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("4a6b5e21-fe6c-43ac-b689-033ccf36c0d7")
public class PartialExportMap {
    @objid ("ec190ced-1b09-43ee-a7e8-676c7aa7a81b")
    private Map<String, org.eclipse.uml2.uml.Element> modelMap;

    @objid ("df18e293-c050-4300-b97c-75e7af0c9b59")
    private static PartialExportMap theSingleInstance;

    @objid ("d26fdced-c353-44d3-b73d-3d36b4bf1769")
    public void clear() {
        this.modelMap.clear();
    }

    @objid ("9069aa2a-0a0b-427f-84ae-c50d7f961c1b")
    public boolean containsKey(String key) {
        return  this.modelMap.containsKey(key);
    }

    @objid ("aaf0d233-2496-48fd-a7b2-b584848a1002")
    public boolean containsValue(org.eclipse.uml2.uml.Element val) {
        return  this.modelMap.containsValue(val);
    }

    @objid ("f5da7079-d363-4f9b-8e6e-40557c308bf8")
    public org.eclipse.uml2.uml.Element get(String key) {
        return  this.modelMap.get(key);
    }

    @objid ("f4652430-7213-4180-a714-2e9704e96994")
    public static PartialExportMap getInstance() {
        if (theSingleInstance == null)
            theSingleInstance = new PartialExportMap();
        return theSingleInstance;
    }

    @objid ("d9ff9a96-323b-49e6-9a31-db873b912e76")
    public boolean isEmpty() {
        return  this.modelMap.isEmpty();
    }

    @objid ("9ce7fce6-f9c7-4e0e-9790-f1c67c91ad7e")
    public Set<String> keySet() {
        return  this.modelMap.keySet();
    }

    @objid ("5ed6a160-16d1-478c-9328-05a2377ed2fe")
    public org.eclipse.uml2.uml.Element put(String key, org.eclipse.uml2.uml.Element value) {
        return  this.modelMap.put(key, value);
    }

    @objid ("fae8d3fd-d5b1-4026-ac48-fd0b749522a0")
    public org.eclipse.uml2.uml.Element remove(String key) {
        return this.modelMap.remove(key);
    }

    @objid ("9c1f907a-1dec-4aae-a5af-1e182af3b510")
    public int size() {
        return  this.modelMap.size();
    }

    @objid ("27981962-9051-43d7-9cab-aea7e7394cf4")
    public Collection<org.eclipse.uml2.uml.Element> values() {
        return  this.modelMap.values();
    }

    @objid ("b82d400d-8517-4f3a-a1fa-61dce1bd2d64")
    private PartialExportMap() {
        this.modelMap = new HashMap<>();
    }

}
