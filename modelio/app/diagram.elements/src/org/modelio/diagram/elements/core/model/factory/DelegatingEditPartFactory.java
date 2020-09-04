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

package org.modelio.diagram.elements.core.model.factory;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * Implementation of {@link EditPartFactory} delegating all request to its cascaded factories extensions.
 * <p>
 * This factory is dynamically enriched by diagram plugins, so that it ends by being able to process elements from several metamodel.<br/>
 * </p>
 * <p>
 * Therefore, no ordering can be assumed in the delegation mechanism.
 * </p>
 */
@objid ("e988a841-d8fb-4241-97dd-7ba2a9df1520")
public class DelegatingEditPartFactory implements EditPartFactory {
    /**
     * All cascaded factories (if any).
     */
    @objid ("c9202115-b38a-4ec5-a90d-32a899ca2d69")
    private List<EditPartFactory> cascadedFactories = new ArrayList<>();

    /**
     * Instantiate the factory.
     * @param factoryIds identifier of the cascaded factories needed to call the {@link DiagramFactoryRegistry}.
     */
    @objid ("502b04b1-58c6-40c8-987a-16408be3cd01")
    public DelegatingEditPartFactory(List<String> factoryIds) {
        this.cascadedFactories = new ArrayList<>();
        for (String factoryId : factoryIds) {
            EditPartFactory cascadedFactory = DiagramFactoryRegistry.getInstance().getEditPartFactory(factoryId);
            if (cascadedFactory != null) {
                this.cascadedFactories.add(cascadedFactory);
            }
        }
    }

    @objid ("ea52b44c-7949-4347-9ad2-1542b29eedbc")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        // First ask cascaded edit part factories
        for (EditPartFactory factory : this.cascadedFactories) {
            EditPart editPart = factory.createEditPart(context, model);
            if (editPart != null) {
                return editPart;
            }
        }
        return null;
    }

    /**
     * Register a cascaded factory.
     * @param factory the edit part factory.
     */
    @objid ("d8c7ce11-d733-4dff-9ac8-6b4a5485ba5a")
    public void registerFactory(EditPartFactory factory) {
        if (factory != null && !this.cascadedFactories.contains(factory)) {
            this.cascadedFactories.add(factory);
        }
    }

    /**
     * Remove a registered cascaded factory.
     * @param factory the edit part factory.
     */
    @objid ("21962ed9-b082-4ff9-be31-6afc2e985e43")
    public void unregisterFactory(EditPartFactory factory) {
        this.cascadedFactories.remove(factory);
    }

}
