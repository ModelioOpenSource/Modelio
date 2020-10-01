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
import org.modelio.gproject.fragment.ramcfile.MdaFragment;
import org.modelio.propertyview.plugin.PropertyViewPlugin;

/**
 * <i>MdaFragment</i> data model.
 * <p>
 * This class provides the list of properties for the <i>MdaFragment</i>
 * metaclass.
 */
@objid ("e43720c1-5e74-4676-9f0c-01b7ae374416")
public class MdaFragmentPropertyModel extends AbstractFragmentPropertyModel {
    @objid ("0f069f93-3a25-411f-8752-2d2cf8eb1243")
    private String version;

    /**
     * Create a new <i>MdaFragment</i> data model from an <i>MdaFragment</i>.
     * 
     * @param fragment the fragment of which properties will be display
     */
    @objid ("7b0b7092-95f5-4d3c-96f9-1c7af8577968")
    public MdaFragmentPropertyModel(MdaFragment fragment) {
        super(fragment);
        try {
            IModelComponentInfos infos = fragment.getInformations();
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
    @objid ("aefa75fe-00e1-4289-99d1-6f34382a9cbb")
    public String getVersion() {
        return this.version;
    }

    /**
     * Set fragment version
     * 
     * @param version the fragment version
     */
    @objid ("1f1f3235-e0db-4d31-b4fe-a01a85661ea4")
    public void setVersion(String version) {
        this.version = version;
    }

    @objid ("5ac813a3-766c-439e-b26e-090dced38434")
    @Override
    public Map<String, String> getPropertyList() {
        Map<String,String> map = super.getPropertyList();
        map.put(PropertyViewPlugin.I18N.getString("fragment.type.label"), PropertyViewPlugin.I18N.getString("fragment.type.label.mda"));
        map.put(PropertyViewPlugin.I18N.getString("fragment.version.label"), this.getVersion());
        return map;
    }

}
