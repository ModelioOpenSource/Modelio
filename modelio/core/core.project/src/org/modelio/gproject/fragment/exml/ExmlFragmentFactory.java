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

package org.modelio.gproject.fragment.exml;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.fragment.IFragmentFactory;
import org.modelio.gproject.fragment.IProjectFragment;

/**
 * Local EXMl fragment factory.
 */
@objid ("0246b97a-ab3b-11e1-8392-001ec947ccaf")
public class ExmlFragmentFactory implements IFragmentFactory {
    @objid ("49bc2ae3-ab3f-11e1-8392-001ec947ccaf")
    private static ExmlFragmentFactory instance = new ExmlFragmentFactory();

    /**
     * Singleton
     */
    @objid ("49bc2ae4-ab3f-11e1-8392-001ec947ccaf")
    private ExmlFragmentFactory() {
    }

    /**
     * @return the factory.
     */
    @objid ("49bc2ae6-ab3f-11e1-8392-001ec947ccaf")
    public static IFragmentFactory getInstance() {
        return ExmlFragmentFactory.instance;
    }

    @objid ("49bc2aea-ab3f-11e1-8392-001ec947ccaf")
    @Override
    public IProjectFragment instantiate(FragmentDescriptor fd) {
        return new ExmlFragment(fd.getId(), fd.getScope(), fd.getProperties(), GAuthConf.from(fd.getAuthDescriptor()));
    }

    /**
     * Create a local project fragment.
     * 
     * @param name the fragment name
     * @return the project fragment.
     */
    @objid ("6a6c1b1c-d66d-11e1-9f03-001ec947ccaf")
    public static IProjectFragment instantiateLocal(String name) {
        return new ExmlFragment(name, DefinitionScope.LOCAL, new GProperties(), null);
    }

    @objid ("aa79e591-0eed-11e2-8e4b-001ec947ccaf")
    @Override
    public boolean supports(FragmentDescriptor desc) {
        return desc.getType().equals(FragmentType.EXML);
    }

}
