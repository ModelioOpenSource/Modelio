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

package org.modelio.gproject.fragment.ramcfile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.ramc.ModelComponentArchive;
import org.modelio.gproject.fragment.IFragmentFactory;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.net.UriPathAccess;

/**
 * Ramc fragment factory.
 */
@objid ("3fc7e2d9-cf5e-11e1-a8eb-001ec947ccaf")
public class RamcFileFragmentFactory implements IFragmentFactory {
    @objid ("3fc7e2e0-cf5e-11e1-a8eb-001ec947ccaf")
    private static RamcFileFragmentFactory instance = new RamcFileFragmentFactory();

    @objid ("3fc7e2e1-cf5e-11e1-a8eb-001ec947ccaf")
    private RamcFileFragmentFactory() {
    }

    /**
     * @return the factory.
     */
    @objid ("3fc7e2e2-cf5e-11e1-a8eb-001ec947ccaf")
    public static IFragmentFactory getInstance() {
        return instance;
    }

    /**
     * Instantiate a RAMC fragment directly from an URI.
     * @param uri an URI referring to a .ramc file.
     * @param authData authentication data, may be <code>null</code>.
     * @return the RAMC fragment.
     * @throws java.io.IOException if the RAMC archive couldn't be read.
     */
    @objid ("3932d1e3-45a3-4b15-972a-c72662253210")
    public static IProjectFragment instantiate(URI uri, IAuthData authData) throws IOException {
        FragmentDescriptor fd = getFragmentDescriptor(uri, authData);
        GAuthConf auth = null;
        if (authData != null) {
            auth = new GAuthConf();
            auth.setAuthData(authData);
        }
        return new RamcFileFragment(fd.getId(), fd.getUri(), fd.getScope(), fd.getProperties(), auth);
    }

    /**
     * Instantiate a RAMC fragment directly from an RAMC file path.
     * @param path a file path referring to a .ramc file.
     * @return the RAMC fragment.
     * @throws java.io.IOException if the RAMC archive couldn't be read.
     */
    @objid ("bb85971a-8870-419a-91b3-f719e579f00a")
    public static IProjectFragment instantiate(Path path) throws IOException {
        ModelComponentArchive ar = new ModelComponentArchive(path, true);
        FragmentDescriptor fd = ar.getFragmentDescriptor();
        return new RamcFileFragment(fd.getId(), fd.getUri(), fd.getScope(), fd.getProperties(), null);
    }

    @objid ("3fc7e2e3-cf5e-11e1-a8eb-001ec947ccaf")
    @Override
    public IProjectFragment instantiate(FragmentDescriptor fd) {
        return new RamcFileFragment(fd.getId(), fd.getUri(), fd.getScope(), fd.getProperties(),
                        GAuthConf.from(fd.getAuthDescriptor()));
    }

    @objid ("aa836ee9-0eed-11e2-8e4b-001ec947ccaf")
    @Override
    public boolean supports(FragmentDescriptor desc) {
        return desc.getType().equals(FragmentType.RAMC);
    }

    /**
     * Instantiate a RAMC fragment descriptor directly from a .ramc file URI.
     * @param uri a .ramc file URI.
     * @param authData authentication data
     * @return a RAMC fragment descriptor or <code>null</code> if the RAMC file couldn't be read.
     * @throws java.io.IOException in case of failure
     */
    @objid ("5f964d2e-2d52-4f2b-8b08-5d2e842f67b5")
    private static FragmentDescriptor getFragmentDescriptor(URI uri, IAuthData authData) throws IOException {
        try (UriPathAccess access = new UriPathAccess(uri, authData)){
            ModelComponentArchive ar = new ModelComponentArchive(access.getPath(), true);
            return ar.getFragmentDescriptor();
        }
    }

}
