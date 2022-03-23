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
package org.modelio.linkeditor.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * The configuration data of a link editor instance.
 */
@objid ("49e6a5d6-5145-46cc-81e7-b076274841b5")
public interface ILinkEditorConfiguration {
    @objid ("d14a4a91-52d3-4cd7-8167-16c1e229d8d5")
    int getLeftDepth();

    @objid ("779205a3-4d3d-477e-bd38-82885b568ea2")
    int getRightDepth();

    @objid ("ad0ef1ec-83ad-4d72-a04a-27814a732401")
    Orientation getLayoutOrientation();

    /**
     * @return a non-null filter.
     */
    @objid ("654accab-5e16-4a8b-aa5c-39e58e332a5a")
    ILinkEditorFilter getLinkFilter();

    @objid ("36873e42-9943-4563-b41e-883ad44f4e8d")
    public enum Orientation {
        @objid ("c31eee05-5dd9-4e11-8a78-f69ea729d63c")
        Horizontal,
        @objid ("50ea612c-909f-4a3d-a776-cb10d31a44a8")
        Vertical,
        @objid ("677f5ae2-d6c9-4c53-a878-1439d9422238")
        Auto;

    }

}
