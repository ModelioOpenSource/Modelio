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

import java.lang.reflect.Method;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.propertyview.plugin.PropertyViewPlugin;

/**
 * <i>SvnFragment</i> data model.
 * <p>
 * This class provides the list of properties for the <i>SvnFragment</i> metaclass.
 */
@objid ("f15c5bba-e4dd-4f61-8aed-7b03bfad82e7")
public class SvnFragmentPropertyModel extends AbstractFragmentPropertyModel {
    @objid ("4913ab33-0eb6-4981-8bde-50b652a25062")
    private String revision;

    /**
     * Create a new <i>SvnFragment</i> data model from an <i>SvnFragment</i>.
     * @param fragment the fragment of which properties will be display
     */
    @objid ("bd2d0b6c-a562-424f-88d4-36da6aa07cbf")
    public  SvnFragmentPropertyModel(IGModelFragment fragment) {
        super(fragment);
        IFragmentInfos infos;
        try {
            infos = fragment.getInformations();
            this.name = infos.getName();
        
            Method method = infos.getClass().getMethod("getRevision");
            this.revision = Long.toString((long) method.invoke(infos));
        } catch (Exception e) {
            PropertyViewPlugin.LOG.error(e);
        }
        
    }

    /**
     * Get fragment revision
     * @return the fragment revision
     */
    @objid ("15c18829-751d-4dfe-b29a-9777b33e8963")
    public String getRevision() {
        return this.revision;
    }

    /**
     * Set fragment revision
     * @param revision the fragment revision
     */
    @objid ("3ff96878-3491-475c-8074-113c9ba3993b")
    public void setRevision(String revision) {
        this.revision = revision;
    }

    @objid ("6af324eb-4cdc-4899-b0ea-0dc38e79d78c")
    @Override
    public Map<String, String> getPropertyList() {
        Map<String, String> map = super.getPropertyList();
        map.put(PropertyViewPlugin.I18N.getString("fragment.type.label"), PropertyViewPlugin.I18N.getString("fragment.type.label.svn"));
        map.put(PropertyViewPlugin.I18N.getString("fragment.revision.label"), this.getRevision());
        return map;
    }

}
