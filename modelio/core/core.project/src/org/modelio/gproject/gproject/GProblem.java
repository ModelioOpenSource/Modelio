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

package org.modelio.gproject.gproject;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ResourceDescriptor;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.module.GModule;
import org.modelio.vbasic.files.FileUtils;

@objid ("9f29b0fe-52a0-461a-b02c-7721677082f4")
public class GProblem {
    @objid ("2f844a63-441c-4aaf-ae0b-2404ae24a46c")
    private final String subject;

    @objid ("6f5f5e6c-cddc-4a47-81dd-466401e6f9ed")
    private final Throwable cause;

    @objid ("435e9b91-3471-456e-aefa-9d5b317ac337")
    public GProblem(ModuleDescriptor moduleDescriptor, GModule module, Throwable cause) {
        this.subject = computeSourceIdentifier(moduleDescriptor, module, null, null);
        this.cause = cause;
    }

    @objid ("40901113-f593-4706-8c98-359e9ca12579")
    public GProblem(FragmentDescriptor fd, IProjectFragment f, Throwable cause) {
        this.subject = computeSourceIdentifier(null, null, fd, f);
        this.cause = cause;
    }

    @objid ("db61f40b-1a8c-4718-b31c-2282c016e92e")
    public GProblem(ResourceDescriptor rd, Throwable cause) {
        this.subject = rd.getTargetLocation() + "(" + rd.getId() + ")";
        this.cause = cause;
    }

    @objid ("c58dcbc4-b4f2-4087-98da-695766eb5bf2")
    public GProblem(String source, Throwable cause) {
        this.subject = source;
        this.cause = cause;
    }

    @objid ("cce8794d-0422-4af5-b963-f1d3711a1e25")
    public GProblem(Throwable cause) {
        this.subject = null;
        this.cause = cause;
    }

    @objid ("1aec99e9-98a5-45cb-9398-f793ca52863a")
    public String getSubject() {
        return this.subject;
    }

    @objid ("ca9e5c32-dd51-4c56-982f-900ae7ce1424")
    public String getProblem() {
        if (this.cause instanceof IOException) {
            return (FileUtils.getLocalizedMessage((IOException) this.cause));
        } else if (this.cause instanceof AccessDeniedException) {
            return (this.cause.getLocalizedMessage());
        } else if (this.cause instanceof RuntimeException) {
            return (this.cause.toString());
        } else {
            return (this.cause.getLocalizedMessage());
        }
    }

    @objid ("2db91402-6d03-4768-96bf-43691b6e3bdc")
    private String computeSourceIdentifier(ModuleDescriptor moduleDescriptor, GModule module, FragmentDescriptor fragmentDescriptor, IProjectFragment fragment) {
        String name = "?";
        String version = "?";
        String id = "Project";
        
        if (module != null) {
            name = module.getName();
            version = module.getVersion().toString();
            id = name + " v" + version;
        } else if (moduleDescriptor != null) {
            name = moduleDescriptor.getName();
            version = moduleDescriptor.getVersion().toString();
            id = name + " v" + version;
        } else if (fragment != null) {
            name = fragment.getId();
            id = name;
        } else if (fragmentDescriptor != null) {
            name = fragmentDescriptor.getId();
            id = name;
        }
        return id;
    }

}
