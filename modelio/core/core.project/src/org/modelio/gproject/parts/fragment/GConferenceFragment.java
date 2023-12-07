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
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.core.IGAccessRights;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.metamodel.InfrastructureMetamodel;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.nullz.NullRepository;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.version.ModelioVersion;

/**
 * Temporary skeleton for future conference fragments.
 * 
 * @author cmarin
 * @since 5.4.1 , 19/10/2023
 */
@objid ("fd94a0fb-cbf6-4724-a4b2-34f66c062716")
public class GConferenceFragment extends AbstractGModelFragment {
    @objid ("1ca17315-cf88-43d4-b00b-e1cc48f669e3")
    private IRepository repository;

    /**
     * Initialize the feature.
     * @param desc the part descriptor
     */
    @objid ("f5fbdbf6-3473-4445-8c99-b293b56dd458")
    public  GConferenceFragment(GProjectPartDescriptor desc) {
        super(desc);
    }

    @objid ("61538c46-aa9a-4a0f-9a28-54db4e8737f8")
    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @objid ("2441ce33-8194-4f79-9145-1ceff8b9c4a2")
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
                return ModelioVersion.VERSION;
            }
        
            @Override
            public String getDescription() {
                return "Future conference model fragment";
            }
        };
        
    }

    @objid ("3caabbc6-cbba-4778-a606-b02f1a79e962")
    @Override
    public IGAccessRights getAccessRights() {
        return InvisibleAccessRights.INSTANCE;
    }

    @objid ("bcd95edd-fd85-45e1-acdc-4baf005cb84c")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        return new MetamodelVersionDescriptor(new VersionedItem<Void>(InfrastructureMetamodel.NAME, new Version(InfrastructureMetamodel.VERSION)));
    }

    @objid ("7962fab3-d245-447c-9b75-fb82414e565b")
    @Override
    protected void doInstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        
    }

    @objid ("092b076e-6004-499a-ac19-35996f776844")
    @Override
    protected void doUninstall(IGProject project, IModelioProgress monitor) throws GPartException {
        
    }

    @objid ("16413d42-04be-4ec3-a34f-a4c271c8f3ff")
    @Override
    protected IRepository doMountInitRepository(IGProject project, IModelioProgress aMonitor) throws IOException, FragmentAuthenticationException {
        // instantiate a dummy repository
        this.repository = new EmptyRepository();
        return this.repository;
    }

    @objid ("0fc2d8f4-c39b-449e-b870-089ebc517595")
    @Override
    protected IAccessManager doInitAccessManager() {
        return (SmObjectImpl obj, IModelLoader loader) -> {
        };
        
    }

    @objid ("0f69aa18-377a-4949-8b3d-cb17eb245a0c")
    @Override
    protected Collection<MObject> doGetRoots() throws IOException {
        return Collections.emptyList();
    }

    @objid ("973a61ee-13a7-484b-a30b-224d23e6403d")
    private static final class InvisibleAccessRights implements IGAccessRights {
        @objid ("34c64e5c-4401-4fd5-a5df-587248ec8377")
        public static final IGAccessRights INSTANCE = new InvisibleAccessRights();

        @objid ("14e387e1-505b-467f-9664-cd0c0486436b")
        @Override
        public boolean isVisible() {
            return false;
        }

        @objid ("1c843970-1802-42f0-96f9-e53f9ad1680a")
        @Override
        public boolean isEditable() {
            return false;
        }

    }

    @objid ("e6730645-62b2-4fe7-bd6c-46d00555da9c")
    private static final class EmptyRepository extends NullRepository {
        @objid ("217a9237-f333-48a9-a0be-b23310e92243")
        @Override
        public void addCreatedObject(SmObjectImpl newObject) {
            throw new UnsupportedOperationException();
        }

        @objid ("b1115823-56dd-4313-83c4-590c629423a3")
        @Override
        public void attach(SmObjectImpl newObject) {
            throw new UnsupportedOperationException();
        }

        @objid ("055a84e2-03d8-4532-80f4-a2b37c384e20")
        @Override
        public OutputStream writeBlob(IBlobInfo key) throws IOException {
            throw new UnsupportedOperationException();
        }

    }

}
