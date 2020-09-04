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

package org.modelio.metamodel.impl.mmextensions.infrastructure.blob;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.vcore.session.api.blob.BlobCopier;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.blob.IBlobProvider;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("abd22f03-dbab-4c5b-8ee2-e28af1127c29")
public class InfrastructureMmBlobProvider implements IBlobProvider {
    @objid ("126605f7-4007-40df-8a12-97578836041a")
    @Override
    public Collection<String> getRelatedBlobs(MObject obj) {
        if (obj instanceof AbstractResource) {
            AbstractResource res = (AbstractResource) obj;
            if (res.isEmbedded()) {
                return Collections.singletonList(getBlobKey(obj));
            }
        }
        return Collections.emptyList();
    }

    @objid ("fedc2fb1-6b1f-4127-8c81-e5c95690a262")
    @Override
    public void objectCopied(MObject from, IRepository fromRepo, MObject to, IRepository toRepo) {
        if (from instanceof AbstractResource) {
            AbstractResource res = (AbstractResource) from;
            if (res.isEmbedded()) {
                String fromBlobKey = getBlobKey(from);
                IBlobInfo toBlobInfo = new BlobInfo(new MRef(to), IResourceHandle.BLOB_LOCAL_KEY);
                BlobCopier.copy(fromBlobKey, fromRepo, toBlobInfo, toRepo);
            }
        }
    }

    @objid ("d58965b1-3a05-4a3a-aa5c-a2eb8d670d9a")
    @Override
    public void objectsMoved(Collection<? extends MObject> objs, IRepository fromRepo, IRepository destRepo) {
        for (MObject obj : objs) {
            if (obj instanceof AbstractResource) {
                AbstractResource res = (AbstractResource) obj;
                if (res.isEmbedded()) {
                    String blobKey = getBlobKey(res);
                    BlobCopier.move(blobKey, fromRepo, destRepo);
                }
            }
        }
    }

    @objid ("35325788-6445-499a-9e7d-53db4b4a557e")
    private String getBlobKey(MObject res) {
        return BlobInfo.computeKey(res, IResourceHandle.BLOB_LOCAL_KEY);
    }

}
