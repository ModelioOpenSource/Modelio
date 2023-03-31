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
package org.modelio.gproject.parts;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGPartFactory;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.parts.feature.GFeature;
import org.modelio.gproject.parts.fragment.GExmlFragment;
import org.modelio.gproject.parts.fragment.GHttpFragment;
import org.modelio.gproject.parts.fragment.GRamcFragment;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.gproject.parts.resource.GResource;

/**
 * GPart factory: create Gpart instance from GProjectPartDescriptor.
 * <p>
 * Natively support several types: EXMLFRAGMENT, MODULE, FEATURE, RESOURCE, RAMC
 * </p>
 * <p>
 * Is extensible by registering additionnal {@link IGPartFactory} implementations.
 * </p>
 */
@objid ("5d7300cd-892d-4a99-aee7-a20179f17163")
public class GPartFactory implements IGPartFactory {
    @objid ("9170cb6c-a8ae-4d60-bff4-fd5edefb8c38")
    private static final List<IGPartFactory> FACTORIES = new ArrayList<>();

    @objid ("2386fcef-fffc-4c34-a1cb-37033945903f")
    private static final GPartFactory INSTANCE = new GPartFactory();

    /**
     * This default factory can create ExmlFragment, Module, Feature or Resource parts.
     */
    @objid ("5b752c03-8dcf-4015-8d1d-71d62703a266")
    private static final IGPartFactory DEFAULT_FACTORY = new IGPartFactory() {
            @Override
            public IGPart instantiate(GProjectPartDescriptor d) {
                switch (d.getType()) {
                case EXMLFRAGMENT:
                    return new GExmlFragment(d);
                case HTTPFRAGMENT:
                    return new GHttpFragment(d);
                case RAMC:
                    return new GRamcFragment(d);
                case MODULE:
                    return new GModule(d);
                case FEATURE:
                    return new GFeature(d);
                case RESOURCE:
                    return new GResource(d);
                default:
                    return null;
                }
            }
    
            @Override
            public boolean supports(GProjectPartDescriptor d) {
                switch (d.getType()) {
                case EXMLFRAGMENT:
                case HTTPFRAGMENT:
                case RAMC:
                case MODULE:
                case FEATURE:
                case RESOURCE:
                    return true;
                default:
                    return false;
                }
            }
        };

    @objid ("b1295044-41f0-49ed-9d82-b5cbace781a3")
    private  GPartFactory() {
        
    }

    @objid ("6e84f07f-17e2-4a6d-acbc-bc6e78ac929e")
    public static GPartFactory getInstance() {
        return INSTANCE;
    }

    @objid ("1cacd264-cdf7-47ef-b343-7c4341430898")
    @Override
    public IGPart instantiate(GProjectPartDescriptor d) {
        for (IGPartFactory f : GPartFactory.FACTORIES) {
            if (f.supports(d)) {
                return f.instantiate(d);
            }
        }
        return null;
    }

    @objid ("5aeedbd0-422c-4d04-9636-decdb9358769")
    @Override
    public boolean supports(GProjectPartDescriptor d) {
        for (IGPartFactory f : GPartFactory.FACTORIES) {
            if (f.supports(d)) {
                return true;
            }
        }
        return false;
    }

    @objid ("667f447b-448a-4ce5-9792-1d771a8ab880")
    public static void registerFactory(IGPartFactory factory) {
        GPartFactory.FACTORIES.add(factory);
    }

    @objid ("e70a5415-99ae-49d2-b79c-3c672d34cf4e")
    public static void unregisterFactory(IGPartFactory factory) {
        GPartFactory.FACTORIES.remove(factory);
    }

static {
            FACTORIES.add(DEFAULT_FACTORY);
        }
    
}
