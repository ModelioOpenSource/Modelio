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
import org.modelio.gproject.fragment.url.UrlFragment;
import org.modelio.gproject.fragment.url.UrlFragment.UrlFragmentInfos;
import org.modelio.propertyview.plugin.PropertyViewPlugin;

/**
 * <i>UrlFragment</i> data model.
 * <p>
 * This class provides the list of properties for the <i>UrlFragment</i>
 * metaclass.
 */
@objid ("ed703138-2002-4c29-86ab-9b0d4e26928d")
public class UrlFragmentPropertyModel extends AbstractFragmentPropertyModel {
    @objid ("146241d0-72f0-44be-87c0-9ae0549c5943")
    private String provider;

    @objid ("701037e8-b458-41ac-b993-9afe9847dfe3")
    private String date;

    /**
     * Create a new <i>UrlFragment</i> data model from an <i>UrlFragment</i>.
     * @param fragment the fragment of which properties will be display
     */
    @objid ("67b929ad-d68e-470b-8f43-b4cf87783625")
    public  UrlFragmentPropertyModel(UrlFragment fragment) {
        super(fragment);
        UrlFragmentInfos infos;
        try {
            infos = fragment.getInformations();
            this.name = infos.getName();
            this.provider = infos.getProvider();
            this.date = infos.getDate();
        } catch (IOException e) {
            PropertyViewPlugin.LOG.error(e);
        }
        
    }

    @objid ("5d6a82f9-b276-4590-8884-fa143b2008ce")
    @Override
    public Map<String, String> getPropertyList() {
        Map<String, String> map = super.getPropertyList();
        map.put(PropertyViewPlugin.I18N.getString("fragment.type.label"), PropertyViewPlugin.I18N.getString("fragment.type.label.url"));
        map.put(PropertyViewPlugin.I18N.getString("fragment.provider.label"), this.provider);
        map.put(PropertyViewPlugin.I18N.getString("fragment.date.label"), this.date);
        return map;
    }

}
