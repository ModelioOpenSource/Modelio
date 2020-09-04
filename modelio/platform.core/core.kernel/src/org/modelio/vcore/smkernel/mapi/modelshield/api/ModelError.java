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

package org.modelio.vcore.smkernel.mapi.modelshield.api;

import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of IModelError.
 */
@objid ("002804cc-0000-0009-0000-000000000000")
public class ModelError implements IModelError {
    @objid ("00830022-b76d-1f61-8473-001ec947cd2a")
    private final String ruleId;

    @objid ("e3c11375-d72d-11e1-bf21-002564c97630")
    private final List<Object> linkedObjects;

    @objid ("e3c11379-d72d-11e1-bf21-002564c97630")
    private final MObject element;

    @objid ("00280b38-0000-0005-0000-000000000000")
    public ModelError(final String ruleId, final MObject element, final List<Object> linkedObjects) {
        this.ruleId = ruleId;
        this.element = element;
        this.linkedObjects = linkedObjects;
    }

    @objid ("002804f8-0000-06bb-0000-000000000000")
    @Override
    public MObject getElement() {
        return this.element;
    }

    @objid ("002804f8-0000-05dd-0000-000000000000")
    @Override
    public String getRuleId() {
        return this.ruleId;
    }

    @objid ("00845f94-b76d-1f61-8473-001ec947cd2a")
    @Override
    public List<Object> getLinkedObjects() {
        return this.linkedObjects;
    }

    @objid ("7bf8816c-f3ab-4338-972e-02d00f06c08a")
    public ModelError(final String ruleId, final MObject element, Object... linkedObjects) {
        this.ruleId = ruleId;
        this.element = element;
        this.linkedObjects = Arrays.asList(linkedObjects);
    }

}
