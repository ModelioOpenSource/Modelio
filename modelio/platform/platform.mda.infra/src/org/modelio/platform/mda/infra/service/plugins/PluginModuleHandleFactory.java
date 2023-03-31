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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.URIUtil;
import org.modelio.gproject.catalog.ModuleXmlExtractor;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.osgi.framework.Bundle;

@objid ("534f975e-3cc8-47e4-b872-71e3bf03a17a")
public class PluginModuleHandleFactory {
    @objid ("93ec6d5b-caff-458f-99ea-3bbdeb022968")
    private final Collection<IGMetamodelExtension> metamodelFragments;

    @objid ("dbe7b448-3536-4074-88f8-c35baca0248b")
    public  PluginModuleHandleFactory(Collection<IGMetamodelExtension> metamodelFragments) {
        this.metamodelFragments = metamodelFragments;
    }

    @objid ("9edaac53-2849-43d6-ab2e-fd1335db70ba")
    public PluginModuleHandle get(Bundle plugin, IModelioProgress monitor, IConfigurationElement elt) throws IOException {
        Path extractDir = plugin.getDataFile("extracted").toPath();
        
        URL fileURL = FileLocator.toFileURL(plugin.getEntry("module.xml"));
        Path moduleXmlPath;
        try {
            moduleXmlPath = Paths.get(URIUtil.toURI(fileURL));
        } catch (URISyntaxException e) {
            throw (MalformedURLException) new MalformedURLException(e.getMessage()).initCause(e);
        }
        
        final ModuleXmlExtractor moduleXmlExtractor = new ModuleXmlExtractor(moduleXmlPath, extractDir, this.metamodelFragments);
        
        IModuleHandle fileHandle = moduleXmlExtractor.getModuleHandle(monitor);
        return new PluginModuleHandle(plugin, fileHandle, Boolean.parseBoolean(elt.getAttribute(PluginModuleConstants.MODULE_MANDATORY)));
    }

}
