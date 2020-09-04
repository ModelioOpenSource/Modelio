/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

@objid ("2c7d7125-68c2-452f-8a3a-559dab29a77f")
public class FragmentInfos implements IFragmentInfos {
    @objid ("ad64c241-c775-4dc7-888b-f4b9f768a779")
    private final String description;

    @objid ("00c03014-04ad-4f15-99f7-951818726aa6")
    private final String name;

    @objid ("d6f4c44c-cd97-494e-9883-cf29f3295149")
    private final Version version;

    @objid ("8c245ad9-58eb-460e-85e4-e586bf0681de")
    private final Version modelioVersion;

    @objid ("8a1661ce-6359-478f-97b0-42e3549c8294")
    public FragmentInfos(final String name, final String description, final Version version, final Version modelioVersion) {
        super();
        this.description = description;
        this.name = name;
        this.version = version;
        this.modelioVersion = modelioVersion;
    }

    @objid ("a9bccf84-7685-4b33-85f6-f33f89d5bea4")
    @Override
    public String getDescription() {
        return this.description;
    }

    @objid ("ff6e6c62-040e-4a2d-a7e3-ca75363966bb")
    @Override
    public Version getModelioVersion() {
        return this.modelioVersion;
    }

    @objid ("d1ba27a3-e8c2-4b86-8402-9a6333a64ea7")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("92c2b26e-b0ad-457f-bb7e-12d506131bfa")
    @Override
    public Version getVersion() {
        return this.version;
    }

}
