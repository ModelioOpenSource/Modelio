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
package org.modelio.xmi.generation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("a2197523-5e0a-4f0b-905a-24f13137889a")
public class TotalExportMap {
    @objid ("51946ba2-0e92-4f98-89c0-c369c8ad1aee")
    private Map<String, org.eclipse.uml2.uml.Element> modelMap;

    @objid ("1f6c7eb1-2d07-4c79-bce7-c797595253a0")
    private static TotalExportMap theSingleInstance;

    @objid ("a2bee7ca-215f-4278-af38-dd64c9190f1d")
    public void clear() {
        this.modelMap.clear();
    }

    @objid ("c2d27c3d-7561-4c8f-b40e-cc0ddce6e4b5")
    public boolean containsKey(String key) {
        return  this.modelMap.containsKey(key);
    }

    @objid ("b9186556-7a55-4dc9-b7ae-5d1f8e6f398c")
    public boolean containsValue(org.eclipse.uml2.uml.Element val) {
        return  this.modelMap.containsValue(val);
    }

    @objid ("66da0db4-8e2c-41c7-9f7e-5b63dea64dd7")
    public org.eclipse.uml2.uml.Element get(String key) {
        return  this.modelMap.get(key);
    }

    @objid ("0c60a1d5-87a3-47e0-889d-54cbeb9fc2a8")
    public static TotalExportMap getInstance() {
        if (theSingleInstance == null)
            theSingleInstance = new TotalExportMap();
        return theSingleInstance;
    }

    @objid ("e1f2f642-b44a-4537-a862-38fb00878613")
    public boolean isEmpty() {
        return  this.modelMap.isEmpty();
    }

    @objid ("cdd6954f-5624-453f-881e-8d1cc72ec589")
    public Set<String> keySet() {
        return  this.modelMap.keySet();
    }

    @objid ("777b9db3-9e84-4c82-b6dc-df16c9a1a5ea")
    public org.eclipse.uml2.uml.Element put(String key, org.eclipse.uml2.uml.Element value) {
        return  this.modelMap.put(key, value);
    }

    @objid ("252d8b6c-25c2-4308-b60d-4094fd891bd0")
    public org.eclipse.uml2.uml.Element remove(String key) {
        return  this.modelMap.remove(key);
    }

    @objid ("5093a59e-ad20-4efd-8f77-48facc37d754")
    public int size() {
        return  this.modelMap.size();
    }

    @objid ("baaaee4b-14fa-4dcf-a2d3-9ac9e6ea7d04")
    public Collection<org.eclipse.uml2.uml.Element> values() {
        return  this.modelMap.values();
    }

    @objid ("68d1c667-df36-44f5-96d7-c7cef873222e")
    private  TotalExportMap() {
        this.modelMap = new HashMap<>();
    }

}
