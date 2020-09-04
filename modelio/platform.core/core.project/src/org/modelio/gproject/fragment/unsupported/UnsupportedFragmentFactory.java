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

package org.modelio.gproject.fragment.unsupported;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.fragment.IFragmentFactory;
import org.modelio.gproject.fragment.IProjectFragment;

/**
 * Unsupported fragments fragment factory.
 */
@objid ("6ac7820f-7dfe-4a92-93d5-6a6766a3dc47")
public class UnsupportedFragmentFactory implements IFragmentFactory {
    @objid ("47a617d7-2b23-472a-9723-3a318de25996")
    private static UnsupportedFragmentFactory instance = new UnsupportedFragmentFactory();

    /**
     * Singleton
     */
    @objid ("1fb53977-ac16-4a5c-b491-e3f21de112b5")
    private UnsupportedFragmentFactory() {
    }

    /**
     * @return the factory.
     */
    @objid ("cebeecac-9314-4228-9bd6-9cb978eccf6e")
    public static IFragmentFactory getInstance() {
        return instance;
    }

    @objid ("d882164c-1b2e-49a7-ba4c-5c88829de449")
    @Override
    public IProjectFragment instantiate(FragmentDescriptor fd) {
        return new UnsupportedFragment(fd);
    }

    @objid ("61cad5a7-eec7-4718-bd9c-7517b94728eb")
    @Override
    public boolean supports(FragmentDescriptor desc) {
        return true;
    }

}
