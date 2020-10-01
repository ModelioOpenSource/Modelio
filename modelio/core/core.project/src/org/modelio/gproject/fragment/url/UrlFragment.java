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

package org.modelio.gproject.fragment.url;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentInfos;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.gproject.fragment.VersionHelper;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.version.ModelioVersion;
import org.modelio.vstore.exml.local.ExmlBase;
import org.modelio.vstore.exml.resource.UriExmlResourceProvider;

/**
 * Remote EXML repository fragment.
 * 
 * Its location is specified as an URL.
 */
@objid ("e77decda-03f8-11e2-9ef9-001ec947ccaf")
public class UrlFragment extends AbstractFragment {
    /**
     * Fragment type identifier.
     */
    @objid ("e77decdf-03f8-11e2-9ef9-001ec947ccaf")
    public static final FragmentType TYPE = FragmentType.EXML_URL;

    @objid ("e77deceb-03f8-11e2-9ef9-001ec947ccaf")
    private final URI repoUrl;

    @objid ("e77ded08-03f8-11e2-9ef9-001ec947ccaf")
    private IRepository repository;

    /**
     * Instantiate an URL based EXML fragment.
     * 
     * @param id the fragment name
     * @param url the repository location as a URL
     * @param definitionScope definition scope
     * @param properties the fragment properties.
     * @param authConf authentication configuration
     */
    @objid ("e77dec8d-03f8-11e2-9ef9-001ec947ccaf")
    public UrlFragment(final String id, final URI url, final DefinitionScope definitionScope, final GProperties properties, final GAuthConf authConf) {
        super(id, definitionScope, properties, authConf);
        
        this.repoUrl = url;
    }

    @objid ("e77ded0e-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    protected IRepository doMountInitRepository(final IModelioProgress aMonitor) {
        // Compute a local path to copy the index to.
        final Path localPath = getRuntimeDirectory();
        
        // instantiate the repository from the URL
        final UriExmlResourceProvider resProvider = new UriExmlResourceProvider(this.repoUrl, localPath, getAuthData());
        resProvider.setName(getId());
        
        this.repository = new ExmlBase(resProvider);
        return this.repository;
    }

    @objid ("e77dece1-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    protected void doUnmountPostProcess() {
        this.repository = null;
    }

    @objid ("e77ded04-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @objid ("e77dece0-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    public FragmentType getType() {
        return TYPE;
    }

    @objid ("e77decfa-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    public URI getUri() {
        return this.repoUrl==null ? null : URI.create(this.repoUrl.toString());
    }

    @objid ("e77ded00-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    public Collection<MObject> doGetRoots() {
        final SmMetamodel mm = getProjectMetamodel();
        final Collection<MObject> roots = this.repository.findByClass(mm.getMClass(AbstractProject.class), true);
        return roots;
    }

    @objid ("e77decf4-03f8-11e2-9ef9-001ec947ccaf")
    protected final void assertOpen() throws IllegalStateException {
        if (this.repository == null) {
            throw new IllegalStateException("The '" + getId() + "' fragment is not mount.");
        }
    }

    @objid ("dd53a49c-395a-11e2-a6db-001ec947ccaf")
    @Override
    protected IAccessManager doInitAccessManager() {
        final BasicAccessManager ret = new BasicAccessManager();
        ret.setWriteable(false);
        return ret;
    }

    @objid ("9e5e011e-35c0-47c4-ba4f-9b3f87bdb652")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        final URI mmuri = UriUtils.asDirectoryUri(this.repoUrl).resolve("admin/").resolve(MMVERSION_FILE_NAME);
        
        try (InputStream is = UriConnections.openInputStream(mmuri, getAuthData());
                InputStreamReader in = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return VersionHelper.convert(new MetamodelVersionDescriptor(in));
        
        } catch (FileNotFoundException | NoSuchFileException e) {
            Log.warning("No '"+mmuri+"' metamodel version file. Assume Modelio 3.1 (9020) metamodel version.");
            return new MetamodelVersionDescriptor(new VersionedItem<Void>(StandardMetamodel.NAME, VersionHelper.convert(9020)));
        }
    }

    @objid ("6eb6c578-bbb3-4189-8e0a-06071d706b7b")
    @Override
    public void rename(final String name, final IModelioProgress aMonitor) throws IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("cad02cc5-ecea-4d99-a678-6460fa6b9b69")
    @Override
    public UrlFragmentInfos getInformations() throws IOException {
        Properties p = getPropertyFile();
        if (p != null) {
            return new UrlFragmentInfos(p.getProperty("name"), p.getProperty("description"), new Version(p.getProperty("version")), new Version(p.getProperty("modelioversion")));
        } else {
            return new UrlFragmentInfos(getId(), "", new Version("0.0.0"), ModelioVersion.VERSION);
        }
    }

    @objid ("fe570c78-5d20-4d1b-a966-01d7426d394c")
    private Properties getPropertyFile() throws IOException {
        final URI infosUri = UriUtils.asDirectoryUri(this.repoUrl).resolve("manifest.properties");
        try (InputStream is = UriConnections.openInputStream(infosUri, getAuthData());
                InputStreamReader in = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            final Properties p = new Properties();
            p.load(in);
            return p;
        } catch (FileNotFoundException | NoSuchFileException e) {
            Log.warning("No '"+infosUri+"' infos version file, use default values.");
            return null;
        }
    }

    @objid ("3efd75da-0c7f-446e-a640-516dd04167a0")
    public class UrlFragmentInfos extends FragmentInfos {
        @objid ("b060b084-1473-423d-af5d-6cecc82ff8e8")
        public UrlFragmentInfos(final String name, final String description, final Version version, final Version modelioVersion) {
            super(name, description, version, modelioVersion);
        }

        @objid ("41c8c2f0-ed3d-4799-b90f-d50a4218c6c6")
        public String getProvider() throws IOException {
            Properties p = getPropertyFile();
            if (p != null) {
                return p.getProperty("provider");
            } else {
                return "";
            }
        }

        @objid ("9efe9b09-a21f-45f2-af9c-8e3b5b4ec893")
        public String getDate() throws IOException {
            Properties p = getPropertyFile();
            if (p != null) {
                return p.getProperty("date");
            } else {
                return "";
            }
        }

    }

}
