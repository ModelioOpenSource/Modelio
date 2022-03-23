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
package org.modelio.propertyview.fragment.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.propertyview.plugin.PropertyViewPlugin;

/**
 * Generic data model for the fragment property view
 */
@objid ("c41b628a-e881-4c00-a3fa-dce17ee40e7e")
public class AbstractFragmentPropertyModel {
    @objid ("f6caf59a-9c36-4051-9312-bbfaec441c53")
    protected String type;

    @objid ("e7acbe7f-e861-4653-857d-1a47210d93cb")
    protected String name;

    /**
     * Create a new fragment data model from a fragment.
     * @param fragment the fragment of which properties will be display
     */
    @objid ("67aaeadf-fd9f-49c1-9e8f-53a559525cbe")
    public  AbstractFragmentPropertyModel(AbstractFragment fragment) {
        this.type = fragment.getClass().getSimpleName();
    }

    /**
     * Get fragment type
     * @return the fragment type
     */
    @objid ("78e46075-aa84-4423-a050-f920f4feebdd")
    public String getType() {
        return this.type;
    }

    /**
     * Set the frament type
     * @param type the fragment type
     */
    @objid ("d0b837a1-aaad-412a-82a6-a228bbea2c58")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get fragment name
     * @return the fragment name
     */
    @objid ("a9523798-5c69-4cf6-8476-7bce598f52fb")
    public String getName() {
        return this.name;
    }

    /**
     * Set fragment name
     * @param name the fragment name
     */
    @objid ("68703ac3-eed3-4289-8499-55d9e5e712d2")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get all fragment properties in a Map
     * @return a map containing all fragment properties
     */
    @objid ("7d883af0-4437-4ba9-a33b-440bc3b1bf38")
    public Map<String, String> getPropertyList() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(PropertyViewPlugin.I18N.getString("fragment.name.label"), this.getName());
        return map;
    }

}
