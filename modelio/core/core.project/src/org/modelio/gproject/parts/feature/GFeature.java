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
package org.modelio.gproject.parts.feature;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.parts.AbstractGPart;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Represent a GFeature which is a functionality of Modelio that can be enabled or disable on a per-project basis.
 */
@objid ("53ed07f5-abc8-4835-8900-45e4a651b454")
public class GFeature extends AbstractGPart {
    /**
     * Initialize the feature.
     * @param desc the part descriptor
     */
    @objid ("a2de295b-69c8-4479-b16f-670659eadc91")
    public  GFeature(GProjectPartDescriptor desc) {
        super(desc);
    }

    @objid ("ded7fe55-7b9b-4faa-aac8-898cb25350d6")
    @Override
    public void mount(IModelioProgress monitor) throws GPartException {
        // Set the state to activated
        this.getState().sendStartMount();
        this.getState().sendEndMount(null);
        
    }

    @objid ("cd790b56-e393-4fbe-9b86-1f6c1c551ff2")
    @Override
    public void unmount(IModelioProgress monitor) throws GPartException {
        // Set the state to not-activated
        this.getState().sendUnmount();
        
    }

}
