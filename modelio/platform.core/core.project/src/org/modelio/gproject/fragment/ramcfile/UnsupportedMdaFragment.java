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

package org.modelio.gproject.fragment.ramcfile;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Project fragment for broken MDA fragments, not having any valid repository...
 */
@objid ("d63f8db5-feec-4fd1-ae96-618cf65d8914")
public class UnsupportedMdaFragment extends AbstractFragment {
    /**
     * Initialize the MDA fragment
     * 
     * @param id the fragment ID
     * @param definitionScope the definition scope
     * @param properties the properties
     * @param authConf the authentication data
     */
    @objid ("08d11feb-2dc9-4fb7-be22-73c5f2af387c")
    public UnsupportedMdaFragment(final String id, final DefinitionScope definitionScope, final GProperties properties, final GAuthConf authConf) {
        super(id, definitionScope, properties, authConf);
    }

    @objid ("60d70967-302c-4016-803a-5852b020f60f")
    @Override
    protected IRepository doMountInitRepository(final IModelioProgress aMonitor) throws FragmentAuthenticationException, IOException {
        throw new IOException(getId() + " is a broken repository");
    }

    @objid ("71b156d3-3506-4bd0-91fc-c8c6d88f2001")
    @Override
    public FragmentType getType() {
        return FragmentType.MDA;
    }

    @objid ("ce7f0a46-80e5-453f-ac03-490b2fbfba7f")
    @Override
    protected Collection<MObject> doGetRoots() throws IOException {
        return Collections.emptyList();
    }

    @objid ("5f99deef-6d1e-4718-a54c-fe03302bf7cd")
    @Override
    public IRepository getRepository() {
        return null;
    }

    @objid ("0f8d8eb5-fabf-4f70-9308-73f32538d328")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        return null;
    }

    @objid ("d7b54197-9bba-4fea-aaec-35a8b2e2ea91")
    @Override
    public URI getUri() {
        return null;
    }

    @objid ("7ec41255-046d-4d55-b152-d8f730af887b")
    @Override
    protected IAccessManager doInitAccessManager() {
        return null;
    }

    @objid ("a8a99299-1e4b-4806-87a0-1af80afd4fa3")
    @Override
    public IFragmentInfos getInformations() throws IOException {
        throw new IOException(getId() + " is a broken repository");
    }

}
