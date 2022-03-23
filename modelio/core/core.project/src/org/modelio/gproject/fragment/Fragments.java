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
package org.modelio.gproject.fragment;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.fragment.exml.ExmlFragmentFactory;
import org.modelio.gproject.fragment.ramcfile.RamcFileFragmentFactory;
import org.modelio.gproject.fragment.unsupported.UnsupportedFragmentFactory;
import org.modelio.gproject.fragment.url.UrlFragmentFactory;

/**
 * Fragments instantiation services.
 * <p>
 * Holds factories to build new fragments or instantiate existing ones.
 */
@objid ("61db06bf-985a-11e1-ac83-001ec947ccaf")
public class Fragments {
    /**
     * Additional fragment factories.
     */
    @objid ("aa7c47e1-0eed-11e2-8e4b-001ec947ccaf")
    private static Collection<IFragmentFactory> factories = new ArrayList<>();

    /**
     * Get the factory for instantiating an existing fragment.
     * @param fDesc a fragment descriptor
     * @return the instantiation factory.
     */
    @objid ("49c0ef78-ab3f-11e1-8392-001ec947ccaf")
    public static IFragmentFactory getFactory(final FragmentDescriptor fDesc) {
        switch (fDesc.getType()) {
        case EXML:
            return ExmlFragmentFactory.getInstance();
        case RAMC:
            return RamcFileFragmentFactory.getInstance();
        case EXML_URL:
            return UrlFragmentFactory.getInstance();
        case MDA:
        default:
            for (IFragmentFactory  f: factories) {
                if (f.supports(fDesc))
                    return f;
            }
            
            return UnsupportedFragmentFactory.getInstance();
        }
        
    }

    /**
     * Register the given fragment factory.
     * @param f a fragment factory.
     */
    @objid ("aa7c47e5-0eed-11e2-8e4b-001ec947ccaf")
    public static void registerFactory(IFragmentFactory f) {
        factories.add(f);
    }

}
