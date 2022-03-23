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
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.gproject.fragment.exml.ExmlFragment;
import org.modelio.propertyview.plugin.PropertyViewPlugin;

/**
 * <i>ExmlFragment</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ExmlFragment</i>
 * metaclass.
 */
@objid ("1451bf35-b809-4b9b-b541-790dab474d27")
public class ExmlFragmentPropertyModel extends AbstractFragmentPropertyModel {
    /**
     * Create a new <i>ExmlFragment</i> data model from an <i>ExmlFragment</i>.
     * @param fragment the fragment of which properties will be display
     */
    @objid ("9e02e801-3d0a-483c-a366-2d3533bc7b34")
    public  ExmlFragmentPropertyModel(ExmlFragment fragment) {
        super(fragment);
        IFragmentInfos infos;
        try {
            infos = fragment.getInformations();
            this.name = infos.getName();
        } catch (IOException e) {
            PropertyViewPlugin.LOG.error(e);
        }
        
    }

    @objid ("d84822f2-b1a8-481c-a674-90f8c08b2c9f")
    @Override
    public Map<String, String> getPropertyList() {
        Map<String,String> map = super.getPropertyList();
        map.put(PropertyViewPlugin.I18N.getString("fragment.type.label"), PropertyViewPlugin.I18N.getString("fragment.type.label.exml"));
        return map;
    }

}
