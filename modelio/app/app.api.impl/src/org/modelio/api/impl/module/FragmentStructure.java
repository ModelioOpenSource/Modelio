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
package org.modelio.api.impl.module;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.project.IFragmentStructure;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link IFragmentStructure} implementation.
 * @since 3.5
 */
@objid ("2020850b-f0b1-4d46-b30e-d6d373ecceca")
public class FragmentStructure implements IFragmentStructure {
    @objid ("f20a7e2b-4f39-4a3e-bfbf-cfc7d176bbf0")
    private final String name;

    @objid ("8f29d0fc-ba99-4439-b3d4-28f625f71843")
    private final String type;

    @objid ("b05274e0-a552-4904-a8cd-2837f489570b")
    private final String remoteLocation;

    @objid ("433a3caf-7421-4cbf-a76c-cb792a1a3e99")
    private final String state;

    @objid ("d7a02dd4-7b0b-4ded-917a-4898f39f072a")
    private final List<Element> roots = new ArrayList<>();

    @objid ("19e5e7da-eb9f-4584-81fe-3924d3f168fd")
     FragmentStructure(IProjectFragment f) {
        this.name = f.getId();
        this.type = String.valueOf(f.getType());
        this.remoteLocation = String.valueOf(f.getUri());
        this.state = String.valueOf(f.getState());
        
        // Root elements
        for (MObject o : f.getRoots()) {
            if (o instanceof Element) {
                this.roots.add((Element) o);
            }
        }
        
    }

    @objid ("2fc1fd2a-92a7-4003-8aee-02b444c18e63")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("f8ed395c-8eb2-49ad-a202-1a37bbf70dd5")
    @Override
    public String getType() {
        return this.type;
    }

    @objid ("fc3e32fa-7d62-4b70-a925-deb93ba46bdf")
    @Override
    public String getRemoteLocation() {
        return this.remoteLocation;
    }

    @objid ("9c01f205-0b19-4e1d-83fc-bf35529d651e")
    @Override
    public String getState() {
        return this.state;
    }

    @objid ("3acf9057-c101-479a-9670-f6a42b128144")
    @Override
    public List<Element> getRoots() {
        return this.roots;
    }

}
