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
package org.modelio.platform.mda.infra.service.plugins;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.osgi.container.Module;
import org.modelio.gproject.module.IMetamodelFragmentHandle;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.version.ModelioVersion;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

@objid ("6088c346-5cf2-4c2c-97ee-bda6f52eaa13")
public class PluginModuleHandle implements IModuleHandle, Comparable<Object> {
    @objid ("bdaaf025-0e1c-41ad-a92b-c335fa0f5a86")
    private final Path resourcesPath;

    @objid ("caaabcb0-778e-4f40-ab79-78c3530bc19e")
    private final Bundle plugin;

    @objid ("0a721414-2da0-48d3-8a6b-234b0eab82f9")
    private final IModuleHandle fileHandle;

    @objid ("2b1ec1e5-d03a-4357-a261-b892a3bcbc26")
    @Deprecated
    public  PluginModuleHandle(Bundle plugin, IModuleHandle fileHandle, boolean isMandatory) throws IOException {
        this.plugin = plugin;
        this.fileHandle = fileHandle;
        
        
        try {
            URL pluginURL = plugin.getEntry("/");
            URL entryURL = FileLocator.toFileURL(pluginURL);
            String entryStr = entryURL.toString();
            entryStr = entryStr.replace(" ", "%20");
            URL entryURLfixed = new URL(entryStr);
            URI entryURI = entryURLfixed.toURI();
            this.resourcesPath = Paths.get(entryURI);
        } catch (URISyntaxException e) {
            throw (MalformedURLException) new MalformedURLException(e.getMessage()).initCause(e);
        }
        
    }

    @objid ("149592c8-67cd-4b63-a6bb-b4e9f27fdb59")
    public  PluginModuleHandle(Bundle plugin, IModuleHandle fileHandle) throws IOException {
        this.plugin = plugin;
        this.fileHandle = fileHandle;
        
        
        try {
            URL pluginURL = plugin.getEntry("/");
            URL entryURL = FileLocator.toFileURL(pluginURL);
            String entryStr = entryURL.toString();
            entryStr = entryStr.replace(" ", "%20");
            URL entryURLfixed = new URL(entryStr);
            URI entryURI = entryURLfixed.toURI();
            this.resourcesPath = Paths.get(entryURI);
        } catch (URISyntaxException e) {
            throw (MalformedURLException) new MalformedURLException(e.getMessage()).initCause(e);
        }
        
    }

    @objid ("cac33caa-b40d-4bce-ba91-a95141cbe924")
    @Override
    public Version getBinaryVersion() {
        return ModelioVersion.VERSION;
    }

    @objid ("75d4e841-26ec-42db-abde-8111391ca353")
    @Override
    public List<VersionedItem<?>> getDependencies() {
        return this.fileHandle.getDependencies();
    }

    @objid ("0da65ba7-3970-4376-9add-6f10a5e740cd")
    @Override
    public List<Path> getDocPaths() {
        return this.fileHandle.getDocPaths();
    }

    @objid ("b3481ce6-233f-4c92-9344-44138c6466ed")
    @Override
    public Path getDynamicModelPath() {
        return this.fileHandle.getDynamicModelPath();
    }

    @objid ("3a8a16c3-4a88-4871-9d49-3397621427dc")
    @Override
    public List<Path> getJarPaths() {
        return Collections.emptyList();
    }

    @objid ("79844bcb-0228-4e8f-b0ec-34182b64d59e")
    @Override
    public String getMainClassName() {
        return this.fileHandle.getMainClassName();
    }

    @objid ("527b78bf-3c32-4212-91a3-866299a62afc")
    @Override
    public Path getModelComponentPath() {
        return this.fileHandle.getModelComponentPath();
    }

    @objid ("ed0efb64-c68c-4a5d-9d89-08edfa7849e3")
    @Override
    public Path getModuleInfosPath() {
        return this.fileHandle.getModuleInfosPath();
    }

    @objid ("d04c6478-a40b-45d7-a4de-5839936bab51")
    @Override
    public String getName() {
        return this.fileHandle.getName();
    }

    @objid ("8cdf119f-cf0c-4c22-9a91-1a0729d8233d")
    @Override
    public Path getResourcePath() {
        return this.resourcesPath;
    }

    @objid ("0898a417-b40c-44a9-990a-071d940137ef")
    @Override
    public String getUid() {
        return this.fileHandle.getUid();
    }

    @objid ("f461fd0d-4064-42c3-9969-58d09f0ea5c1")
    @Override
    public Version getVersion() {
        return this.fileHandle.getVersion();
    }

    @objid ("f15f22be-ed3b-4657-b211-cd86657f0220")
    @Override
    public List<VersionedItem<?>> getWeakDependencies() {
        return this.fileHandle.getWeakDependencies();
    }

    @objid ("3d6fa52d-fb8d-4ffd-acad-4daeee60babe")
    @Override
    public Path getArchive() {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("74a8753b-3bed-407a-b929-e996c949a39d")
    @Override
    public Map<String, Path> getStylePaths() {
        return this.fileHandle.getStylePaths();
    }

    @objid ("11ceb43d-5757-4f18-80a1-45142aab98c9")
    @Override
    public List<IMetamodelFragmentHandle> getMetamodelFragments() {
        return this.fileHandle.getMetamodelFragments();
    }

    @objid ("4bed11f6-6e93-46ef-a1de-e5a57a7ccfa0")
    @Override
    public ClassLoader getProvidedClassLoader() {
        final BundleWiring wiring = this.plugin.adapt(BundleWiring.class);
        if (wiring==null) {
            throw new IllegalStateException(String.format("%s is in %s state.", this.plugin, this.plugin.adapt(Module.class).getState()));
        }
        return wiring.getClassLoader();
    }

    @objid ("e0ddf16a-055a-4123-932e-22dfc1164def")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.fileHandle == null ? 0 : this.fileHandle.hashCode());
        
        result = prime * result + (this.plugin == null ? 0 : this.plugin.hashCode());
        result = prime * result + (this.resourcesPath == null ? 0 : this.resourcesPath.hashCode());
        return result;
    }

    @objid ("5bfe524a-5373-4b6b-b19c-cfff3ddd4864")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PluginModuleHandle other = (PluginModuleHandle) obj;
        if (this.fileHandle == null) {
            if (other.fileHandle != null) {
                return false;
            }
        } else if (!this.fileHandle.equals(other.fileHandle)) {
            return false;
        }
        
        if (this.plugin == null) {
            if (other.plugin != null) {
                return false;
            }
        } else if (!this.plugin.equals(other.plugin)) {
            return false;
        }
        if (this.resourcesPath == null) {
            if (other.resourcesPath != null) {
                return false;
            }
        } else if (!this.resourcesPath.equals(other.resourcesPath)) {
            return false;
        }
        return true;
    }

    @objid ("060bc5fe-d998-4c9b-b38b-033db56e9ef6")
    @Override
    public int compareTo(Object obj) {
        if (obj instanceof String){
            return getName().compareTo((String)obj);
        
        } else if (obj instanceof PluginModuleHandle){
            return getVersion().compareTo(((PluginModuleHandle)obj).getVersion());
        }
        return 0;
    }

}
