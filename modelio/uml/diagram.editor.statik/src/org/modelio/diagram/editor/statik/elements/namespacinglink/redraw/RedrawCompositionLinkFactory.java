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

package org.modelio.diagram.editor.statik.elements.namespacinglink.redraw;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.CreationFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("35b5b31e-55b7-11e2-877f-002564c97630")
public class RedrawCompositionLinkFactory implements CreationFactory {
    @objid ("35b5b31f-55b7-11e2-877f-002564c97630")
    private MObject sourceElement;

    @objid ("35b73979-55b7-11e2-877f-002564c97630")
    private MObject targetElement;

    @objid ("35b7397c-55b7-11e2-877f-002564c97630")
    @Override
    public RedrawCompositionLinkFactory getNewObject() {
        return this;
    }

    @objid ("35b73981-55b7-11e2-877f-002564c97630")
    @Override
    public Object getObjectType() {
        return null;
    }

    @objid ("35b73986-55b7-11e2-877f-002564c97630")
    public RedrawCompositionLinkFactory(final MObject sourceElement, final MObject targetElement) {
        super();
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
    }

    @objid ("35b73990-55b7-11e2-877f-002564c97630")
    public MObject getSourceElement() {
        return this.sourceElement;
    }

    @objid ("35b73996-55b7-11e2-877f-002564c97630")
    public MObject getTargetElement() {
        return this.targetElement;
    }

}
