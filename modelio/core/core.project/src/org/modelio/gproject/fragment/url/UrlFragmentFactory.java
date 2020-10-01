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

package org.modelio.gproject.fragment.url;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.fragment.IFragmentFactory;
import org.modelio.gproject.fragment.IProjectFragment;

/**
 * Remote URI fragment factory.
 */
@objid ("e77dec82-03f8-11e2-9ef9-001ec947ccaf")
public class UrlFragmentFactory implements IFragmentFactory {
    @objid ("e77ded0a-03f8-11e2-9ef9-001ec947ccaf")
    private static UrlFragmentFactory instance = new UrlFragmentFactory();

    /**
     * Singleton
     */
    @objid ("e77dec8b-03f8-11e2-9ef9-001ec947ccaf")
    private UrlFragmentFactory() {
    }

    /**
     * @return the factory.
     */
    @objid ("e77decd0-03f8-11e2-9ef9-001ec947ccaf")
    public static IFragmentFactory getInstance() {
        return instance;
    }

    @objid ("e77dec89-03f8-11e2-9ef9-001ec947ccaf")
    @Override
    public IProjectFragment instantiate(FragmentDescriptor fd) {
        return new UrlFragment(fd.getId(), fd.getUri(), fd.getScope(), fd.getProperties(), 
                        GAuthConf.from(fd.getAuthDescriptor()));
    }

    @objid ("aa85d143-0eed-11e2-8e4b-001ec947ccaf")
    @Override
    public boolean supports(FragmentDescriptor desc) {
        return desc.getType().equals(FragmentType.EXML_URL);
    }

}
