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

package org.modelio.core.ui.swt.images;

import java.io.IOException;
import java.io.InputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Image descriptor for an image stored in a Modelio BLOB.
 * @author cma
 * @since 3.7
 */
@objid ("b9531358-99fa-4279-bb89-58dad8be81f7")
public class ModelBlobImageDescriptor extends ImageDescriptor {
    @objid ("2742af00-cd12-4451-8142-23b9253441d2")
    private final String blobKey;

    @objid ("8c799ddc-5c04-4e5e-8fb6-b35e9d456801")
    private final IRepository repository;

    @objid ("0d8bef00-afa1-4a4e-9ea5-61d7a5d62d9c")
    public ModelBlobImageDescriptor(MObject obj, String blobKey) {
        this (CoreSession.getSession(obj).getRepository(obj), blobKey);
    }

    @objid ("016ea926-25fe-481a-8200-efe3ec25cdff")
    public ModelBlobImageDescriptor(IRepository repository, String blobKey) {
        super();
        this.repository = repository;
        this.blobKey = blobKey;
    }

    @objid ("f1df8561-1d75-4ac2-a5cc-3f31ee0ced34")
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
        ModelBlobImageDescriptor other = (ModelBlobImageDescriptor) obj;
        if (this.blobKey == null) {
            if (other.blobKey != null) {
                return false;
            }
        } else if (!this.blobKey.equals(other.blobKey)) {
            return false;
        }
        if (this.repository == null) {
            if (other.repository != null) {
                return false;
            }
        } else if (!this.repository.equals(other.repository)) {
            return false;
        }
        return true;
    }

    @objid ("35eac7e3-9a9f-4ffa-86cd-bc6741d05552")
    @Override
    public ImageData getImageData() {
        try (InputStream is = this.repository.readBlob(this.blobKey);){
            if (is != null) {
                return new ImageData(is);
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @objid ("21dfd07f-a2f8-4eea-91f9-ae7474ae5505")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.blobKey == null) ? 0 : this.blobKey.hashCode());
        result = prime * result + ((this.repository == null) ? 0 : this.repository.hashCode());
        return result;
    }

}
