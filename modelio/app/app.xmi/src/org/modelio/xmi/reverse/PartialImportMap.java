/* 
 * Copyright 2013-2020 Modeliosoft
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
import org.eclipse.uml2.uml.Element;

@objid ("9f5282d3-a1c9-497b-a939-7e2c87ac531a")
public class PartialImportMap {
    @objid ("772864e2-66ff-4193-a4d6-f3f4bdd6b333")
    private static PartialImportMap theSingleInstance;

    @objid ("0931a9dd-d927-46e3-94d6-1aa3042d56eb")
    private Map<Element, Object> modelMap;

    @objid ("2f5742e1-aae2-4ebb-93b0-c3d22f550fc7")
    private  PartialImportMap() {
        this.modelMap = new HashMap <>();
    }

    @objid ("4bb03743-8444-4c50-a1ac-f5971674ec33")
    public static PartialImportMap getInstance() {
        if (theSingleInstance == null)
            theSingleInstance = new PartialImportMap();
        return theSingleInstance;
    }

    @objid ("fec4bd8a-f120-4e6a-b365-571ec40e64c6")
    public void clear() {
        this.modelMap.clear();
    }

    @objid ("0171b948-4fa0-4926-a0f8-41b0382f2ce6")
    public boolean containsKey(Object key) {
        return this. modelMap.containsKey(key);
    }

    @objid ("f49a392f-b232-44be-9a24-267600f903a7")
    public boolean containsValue(Object val) {
        return  this.modelMap.containsValue(val);
    }

    @objid ("1ced4db2-9f1b-4fbd-98bd-45561aadfcff")
    public Object get(org.eclipse.uml2.uml.Element key) {
        return  this.modelMap.get(key);
    }

    @objid ("8d864fa9-47f0-4b7f-99df-45fd070f9e7f")
    public boolean isEmpty() {
        return  this.modelMap.isEmpty();
    }

    @objid ("5da8e258-686f-4189-9bcf-7e688bf674fd")
    public Set<org.eclipse.uml2.uml.Element> keySet() {
        return  this.modelMap.keySet();
    }

    @objid ("eb3bcb32-cd8d-4e15-ac54-709746629121")
    public Object put(org.eclipse.uml2.uml.Element key, Object value) {
        return  this.modelMap.put(key, value);
    }

    @objid ("ce41cb29-f0ac-42eb-b0c0-a485f96a7c1e")
    public Object remove(Object key) {
        return  this.modelMap.remove(key);
    }

    @objid ("295b0adf-e582-4b68-a64f-1c5643ebbe30")
    public int size() {
        return  this.modelMap.size();
    }

    @objid ("a67de848-afa3-4fd1-b5c9-6d630004c53d")
    public Collection<Object> values() {
        return  this.modelMap.values();
    }

}
