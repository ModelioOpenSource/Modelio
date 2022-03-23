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
package org.modelio.propertyview.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.blob.BlobCopier;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.blob.IBlobProvider;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * E4 Model processor.
 * <p>
 * Listens to projects events to:
 * <ul>
 * <li>register/unregister the blob provider for stereotype icons and images.</li>
 * </ul>
 * </p>
 */
@objid ("87bd29f5-8720-4777-a6c0-46068cfaf571")
@Singleton
public final class PropertyViewProcessor {
    @objid ("701533bf-522d-420a-82f3-84776e1646e0")
    private IBlobProvider blobProvider;

    /**
     * Called when a project is closed.
     */
    @objid ("044fab38-5512-4b54-94e6-c899c135872d")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic (ModelioEventTopics.PROJECT_CLOSED) final GProject closedProject) {
        if (closedProject != null) {
            final ICoreSession session = closedProject.getSession();
            if (session != null) {
                // Unregister blob provider
                if (this.blobProvider != null) {
                    session.getBlobSupport().removeBlobProvider(this.blobProvider);
                }
            }
        }
        
        this.blobProvider = null;
        
    }

    /**
     * Blob provider for Stereotype icon and image.
     */
    @objid ("1327119d-500e-4db3-8ead-6130b38cd9a0")
    private static final class StereotypeIconsBlobProvider implements IBlobProvider {
        @objid ("ab92af6f-8026-41a3-a604-695fdeb85e7a")
        @Override
        public Collection<String> getRelatedBlobs(final MObject obj) {
            final List<String> blobKeys = new ArrayList<>();
            if (obj instanceof Stereotype) {
                if (!((Stereotype) obj).getIcon().isEmpty()) {
                    blobKeys.add(getIconKey(obj));
                }
                if (!((Stereotype) obj).getImage().isEmpty()) {
                    blobKeys.add(getImageKey(obj));
                }
            }
            return blobKeys;
        }

        @objid ("6592bc2e-9c98-4819-80d3-5e7b49df634c")
        @Override
        public void objectCopied(final MObject from, final IRepository fromRepo, final MObject to, final IRepository toRepo) {
            if (from instanceof Stereotype) {
                IBlobInfo toInfo = new BlobInfo(getIconKey(to), "icon for " + to.getName());
                BlobCopier.copy(getIconKey(from), fromRepo, toInfo, toRepo);
            
                toInfo = new BlobInfo(getImageKey(to), "image for " + to.getName());
                BlobCopier.copy(getImageKey(from), fromRepo, toInfo, toRepo);
            }
            
        }

        @objid ("6e475673-16f6-4390-8809-2c837a941064")
        @Override
        public void objectsMoved(final Collection<? extends MObject> objs, final IRepository fromRepo, final IRepository destRepo) {
            for (final MObject obj : objs) {
                if (obj instanceof Stereotype) {
                    String blobKey = getIconKey(obj);
                    BlobCopier.move(blobKey, fromRepo, destRepo);
            
                    blobKey = getImageKey(obj);
                    BlobCopier.move(blobKey, fromRepo, destRepo);
                }
            }
            
        }

        @objid ("a1bb9fcf-ffc7-4a39-ae6f-51fbd383569e")
        private String getIconKey(final MObject obj) {
            return obj.getUuid() + ".icon";
        }

        @objid ("259c524c-a1c8-4275-a8ae-3b46c143108f")
        private String getImageKey(final MObject obj) {
            return obj.getUuid() + ".image";
        }

        @objid ("14376aa8-455a-4dda-a106-d9e2fefa8662")
        public  StereotypeIconsBlobProvider() {
            super();
        }

    }

}
