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
package org.modelio.gproject.fragment.ramcfile;

import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;

/**
 * Fragment for the RAMC provided with a module.
 * <p>
 * No Difference from the RAMC file.
 */
@objid ("86e620e9-d84e-4f22-b3bf-bc860cc9b3f8")
public class MdaFragment extends RamcFileFragment {
    /**
     * Initialize the MDA fragment
     * @param id the fragment ID
     * @param uri the .ram file URI
     * @param definitionScope the definition scope
     * @param properties the properties
     * @param authConf the authentication data
     */
    @objid ("351c5428-2114-4712-920f-c6fd12251f5d")
    public  MdaFragment(String id, URI uri, DefinitionScope definitionScope, GProperties properties, GAuthConf authConf) {
        super(id, uri, definitionScope, properties, authConf);
    }

    @objid ("20ee24f2-79e0-4509-af77-268bb61ba81c")
    @Override
    public FragmentType getType() {
        return FragmentType.MDA;
    }

}
