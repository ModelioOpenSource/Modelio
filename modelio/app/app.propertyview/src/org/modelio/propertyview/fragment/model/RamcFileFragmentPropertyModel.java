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

import java.io.IOException;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.fragment.ramcfile.RamcFileFragment;
import org.modelio.propertyview.plugin.PropertyViewPlugin;

/**
 * <i>RamcFileFragment</i> data model.
 * <p>
 * This class provides the list of properties for the <i>RamcFileFragment</i>
 * metaclass.
 */
@objid ("4b13f642-8f70-498f-8bc5-7f4a3f71cf59")
public class RamcFileFragmentPropertyModel extends AbstractFragmentPropertyModel {
    @objid ("669af58b-bd74-44d8-8073-a000977022d6")
    private String version;

    /**
     * Create a new <i>RamcFileFragment</i> data model from an <i>RamcFileFragment</i>.
     * 
     * @param fragment the fragment of which properties will be display
     */
    @objid ("785f6be6-0396-4df5-b759-c5e8601b4a28")
    public RamcFileFragmentPropertyModel(RamcFileFragment fragment) {
        super(fragment);
        IModelComponentInfos infos;
        try {
            infos = fragment.getInformations();
            this.name = infos.getName();
            this.version = infos.getVersion().toString();
        } catch (IOException e) {
            PropertyViewPlugin.LOG.error(e);
        }
    }

    /**
     * Get fragment version
     * 
     * @return the fragment revision
     */
    @objid ("c743a3f0-382d-44d1-b492-41e7a2b231c8")
    public String getVersion() {
        return this.version;
    }

    /**
     * Set fragment version
     * 
     * @param version the fragment version
     */
    @objid ("71ae9e75-f041-4b0b-9982-5ab76fcf6c0b")
    public void setVersion(String version) {
        this.version = version;
    }

    @objid ("3f15bef9-e082-488d-9c10-fc27efb0809b")
    @Override
    public Map<String, String> getPropertyList() {
        Map<String,String> map = super.getPropertyList();
        map.put(PropertyViewPlugin.I18N.getString("fragment.type.label"), PropertyViewPlugin.I18N.getString("fragment.type.label.ramc"));
        map.put(PropertyViewPlugin.I18N.getString("fragment.version.label"), this.getVersion());
        return map;
    }

}
