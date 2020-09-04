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
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;

/**
 * Image descriptor for images stored or referenced by {@link AbstractResource} model objects.
 * @author cma
 * @since 3.7
 */
@objid ("f23fa464-26e5-4c28-a7d1-06ae64b31657")
public class ModelResourceImageDescriptor extends ImageDescriptor {
    @objid ("5c4444bf-4d3b-4868-939a-c369e8f4f35b")
    private final AbstractResource resource;

    @objid ("8e025016-7afb-495c-92e3-c3a5235beb58")
    public ModelResourceImageDescriptor(AbstractResource resource) {
        super();
        this.resource = Objects.requireNonNull(resource);
    }

    @objid ("1652fec5-70be-493d-96bc-d286ab8a29d1")
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
        ModelResourceImageDescriptor other = (ModelResourceImageDescriptor) obj;
        if (this.resource == null) {
            if (other.resource != null) {
                return false;
            }
        } else if (!this.resource.equals(other.resource)) {
            return false;
        }
        return true;
    }

    @objid ("dace0209-448a-4d46-b46c-383977b25ab0")
    @Override
    public ImageData getImageData() {
        IResourceHandle handle = this.resource.getHandle();
        if (handle == null) {
            return null;
        }
        
        try {
            return new ImageData(handle.read());
        } catch (IOException e) {
            return null;
        }
    }

    @objid ("953732a0-43a2-4834-9b8a-3c970995e706")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.resource == null) ? 0 : this.resource.hashCode());
        return result;
    }

}
