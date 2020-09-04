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

package org.modelio.app.update.repo;

import java.net.URI;
import java.net.URISyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.URIUtil;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.net.UriPathAccess;
import org.modelio.vbasic.version.Version;

/**
 * Utility class that can:
 * <ul>
 * <li> find a module on a Modelio update site
 * <li> download and install a module from a Modelio update site into the Modelio catalog.
 * </ul>
 */
@objid ("d2393d8d-df2d-4faa-9fee-32c05fddd2bb")
public class ModuleUpdateSiteClient {
    @objid ("739a2343-20f9-4c26-993f-b421627de389")
    private final String updateSite;

    @objid ("16e43985-4dcd-402a-98ec-bef8e30dc40f")
    private final IAuthData authData;

    @objid ("11e97318-b155-4818-9659-3880da38ef2e")
    public ModuleUpdateSiteClient(String updateSite) {
        this(updateSite, null);
    }

    @objid ("c40ae9f0-09d0-4155-b4e5-07f06cac372e")
    public ModuleUpdateSiteClient(String updateSite, IAuthData auth) {
        this.updateSite = updateSite;
        this.authData = auth;
    }

    @objid ("d3aa89b5-edb5-44b3-b2dd-8c5eb87cef8f")
    public String getUpdateSite() {
        return this.updateSite;
    }

    @objid ("ab94c772-6c27-4dde-ad99-dd9cd85c8e8b")
    public UriPathAccess downloadModuleArchive(String moduleName, Version moduleVersion) throws URISyntaxException {
        return new UriPathAccess(getModuleArchiveURI(moduleName, moduleVersion), this.authData);
    }

    @objid ("332cd594-ffaf-46d5-baa3-b2c50cba25bb")
    private URI getModuleArchiveURI(String moduleName, Version moduleVersion) throws URISyntaxException {
        return URIUtil.fromString(this.updateSite + "/modules/" + moduleName + "_" + moduleVersion.toString() + ".jmdac");
    }

    @objid ("402212e4-8364-453b-a73b-60a715957134")
    private URI getRAMCArchiveURI(String ramcFilename) throws URISyntaxException {
        return URIUtil.fromString(this.updateSite + "/ramcs/" + ramcFilename);
    }

    @objid ("97a0e720-6c5a-46bb-97a6-b039ae8ceb8f")
    private URI getTemplateArchiveURI(String templateName, Version templateVersion) throws URISyntaxException {
        return URIUtil.fromString(this.updateSite + "/templates/" + templateName + "_" + templateVersion.toString() + ".tmpl");
    }

    @objid ("15a9f670-9046-49a9-b9af-60645b667719")
    public UriPathAccess downloadRAMCArchive(String ramcFilename) throws URISyntaxException {
        return new UriPathAccess(getRAMCArchiveURI(ramcFilename), this.authData);
    }

    @objid ("629d89bf-c81b-4e11-bb3c-80b3d2a000a6")
    public UriPathAccess downloadTemplateArchive(String templateName, Version templateVersion) throws URISyntaxException {
        return new UriPathAccess(getTemplateArchiveURI(templateName, templateVersion), this.authData);
    }

}
