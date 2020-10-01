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

package org.modelio.platform.model.ui.swt.labelprovider;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("af436799-3d85-4c1b-9140-352b167188ff")
public abstract class AbstractContainer<T> implements IModelContainer<T> {
    @objid ("9408b514-bb93-4033-bf6b-377edf446461")
    private List<T> contents;

    @objid ("61a400a6-ec1b-4fe3-8039-b6df936a155e")
    private MObject owner;

    @objid ("cba5f5e5-31ac-4609-bdb2-01e6eaa3a06b")
    private Image icon = GENERIC_CONTAINER_ICON;

    @objid ("7763d0bb-2070-4b91-9314-3f602f20c1fb")
    private static final Image GENERIC_CONTAINER_ICON = CoreUi.getImageDescriptor("icons/genericcontainer.png").createImage(true);

    @objid ("c6a60a8a-eb67-4262-b725-5bfac1a567b0")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.owner == null) ? 0 : this.owner.hashCode());
        return result;
    }

    @objid ("5525cfce-78f7-4f90-8d5e-c8ec961fb7d3")
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
        AbstractContainer<?> other = (AbstractContainer<?>) obj;
        if (this.owner == null) {
            if (other.owner != null) {
                return false;
            }
        } else if (!this.owner.equals(other.owner)) {
            return false;
        }
        return true;
    }

    @objid ("8a1f54ec-80b4-4cbe-9c2d-02095caa24db")
    @Override
    public Image getIcon() {
        return this.icon;
    }

    @objid ("dfbaeb53-7efe-46b5-b067-dc19a7a26fe4")
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    @objid ("8016cc60-225b-4be9-8215-2904655b64c6")
    @Override
    public MObject getOwner() {
        return this.owner;
    }

    @objid ("2742d5b0-761d-42cb-b20e-a4a5720f22bd")
    @Override
    public List<T> getContents() {
        return this.contents != null ? this.contents : Collections.emptyList();
    }

    @objid ("8d503a4b-b5cc-4575-bebe-dee5c2d11fee")
    public AbstractContainer(MObject owner, List<T> contents) {
        this.owner = owner;
        this.contents = contents;
    }

    @objid ("334ea458-0863-4fa4-af0d-a7439c78823b")
    @Override
    public String toString() {
        return super.toString() + String.format(" LinkContainer = [owner='%s' nbObjects=%d]", this.owner.toString(), this.getContents().size());
    }

}
