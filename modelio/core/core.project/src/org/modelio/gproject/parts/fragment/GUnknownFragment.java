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
package org.modelio.gproject.parts.fragment;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.metamodel.InfrastructureMetamodel;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.storage.nullz.NullRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Non supported part type.
 * 
 * @author cmarin
 * @since > 5.4.0 , 25/08/2023
 */
@objid ("fdedb0fa-995e-4989-b3ae-e6f57070eff6")
public class GUnknownFragment extends AbstractGModelFragment {
    /**
     * Initialize the feature.
     * @param desc the part descriptor
     */
    @objid ("5e9753a1-5590-4554-894f-0fc6948d1db5")
    public  GUnknownFragment(GProjectPartDescriptor desc) {
        super(desc);
    }

    @objid ("945a1ea8-115b-44e4-905b-456ab6f69bd1")
    @Override
    public IRepository getRepository() {
        return NullRepository.getInstance();
    }

    @objid ("07242004-d1d7-44bf-8fa4-1cbee8089cbe")
    @Override
    public IFragmentInfos getInformations() throws IOException {
        return new IFragmentInfos() {
        
            @Override
            public Version getVersion() {
                return getDescriptor().getVersion();
            }
        
            @Override
            public String getName() {
                final String label = getDescriptor().getLabel();
                if (label != null && ! label.isEmpty())
                    return label;
                return getDescriptor().getId();
            }
        
            @Override
            public Version getModelioVersion() {
                return new Version(9999,0,0);
            }
        
            @Override
            public String getDescription() {
                return "Non supported model fragment";
            }
        };
        
    }

    @objid ("8b01f406-9b93-4f9a-ad91-d6306c1b5eb7")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        return new MetamodelVersionDescriptor(new VersionedItem<Void>(InfrastructureMetamodel.NAME, new Version(InfrastructureMetamodel.VERSION)));
    }

    @objid ("ffd54d54-3a7e-4e8c-9588-14754853a4bb")
    @Override
    protected void doInstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        
    }

    @objid ("20896b67-22b3-4465-a4b4-58d2b15c61c6")
    @Override
    protected void doUninstall(IGProject project, IModelioProgress monitor) throws GPartException {
        
    }

    @objid ("1c9577b6-c3bb-42cf-a517-ca7a1de9bf6b")
    @Override
    protected IRepository doMountInitRepository(IGProject project, IModelioProgress aMonitor) throws IOException, FragmentAuthenticationException {
        throw new IOException(CoreProject.I18N.getMessage("UnsupportedFragment.DownCause", getDescriptor().getType()));
    }

    @objid ("4b7f527d-18ba-45f0-b1a3-ae9604dc5820")
    @Override
    protected IAccessManager doInitAccessManager() {
        throw new UnsupportedOperationException();
    }

    @objid ("5fbbcfbf-7feb-412b-8d3b-b4186ea165c7")
    @Override
    protected Collection<MObject> doGetRoots() throws IOException {
        return Collections.emptyList();
    }

}
