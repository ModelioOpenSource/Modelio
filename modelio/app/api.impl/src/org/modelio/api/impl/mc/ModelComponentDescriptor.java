/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.impl.mc;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.mc.IModelComponentDescriptor;
import org.modelio.gproject.data.ramc.IModelComponentInfos.ExportedFile;

@objid ("d496f21a-73cf-4add-8047-a8d7576cf2fe")
public class ModelComponentDescriptor implements IModelComponentDescriptor {
    @objid ("36bb8551-ab58-4d47-a42a-64ac617d9d6f")
    private String name = "";

    @objid ("9811473e-20b1-41de-9368-a2b6ec46df54")
    private String version = "";

    @objid ("804960b7-ae83-4d49-9499-66f03375caf7")
    private List<Path> deployedResources;

    @objid ("b66881dd-1698-4096-8f31-08d3bc03115c")
    public ModelComponentDescriptor(final String name, final String version, List<ExportedFile> resources) {
        this.name = name;
        this.version = version;
        this.deployedResources = Collections.unmodifiableList(resources.stream()
                .map(resource -> resource.getPath())
                .collect(Collectors.toList()));
    }

    @objid ("abf77703-4fca-4291-9a90-9959862c97c9")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("c4c82fa7-9527-42f1-8b42-890e5feea432")
    @Override
    public String getVersion() {
        return this.version;
    }

    @objid ("dc27b654-b85c-4e27-9425-ab801ab7673f")
    @Override
    public String toString() {
        return this.name + " " + this.version;
    }

    @objid ("09ab4c3a-7148-4d02-af02-a01f01109be0")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

    @objid ("e954d847-9f75-4680-81e9-0b7e38538c07")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ModelComponentDescriptor other = (ModelComponentDescriptor) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!this.version.equals(other.version)) {
            return false;
        }
        return true;
    }

    @objid ("092b1778-0d64-42e8-9de4-1e3023045796")
    @Override
    public List<Path> getDeployedResources() {
        return this.deployedResources;
    }

    @objid ("629c25b8-9f8b-454d-8707-23ac187e0f84")
    @Override
    public List<Path> getDeployedResources(Filter<Path> filter) throws IOException {
        List<Path> ret = new ArrayList<>();
        for (Path entry : this.deployedResources) {
            if (filter.accept(entry)) {
                ret.add(entry);
            }
        }
        return Collections.unmodifiableList(ret);
    }

}
