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
package org.modelio.api.impl.log;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.impl.plugin.ApiImpl;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.plugin.Api;

/**
 * Implementation of the log service.
 */
@objid ("68732128-9ada-47db-bbbc-056fa93ee96e")
public final class LogService implements ILogService {
    @objid ("496c291f-8cf4-4a4e-b7f0-83639f97558e")
    private String author;

    @objid ("29e7fe99-84b2-4a3e-9cfe-ad73665b842f")
    @Override
    public void info(final String msg) {
        ApiImpl.LOG.info("%s - %s", getAuthor(), msg);
    }

    @objid ("099227f4-b8f4-46fc-a217-8270da3de693")
    @Override
    public void warning(final String msg) {
        ApiImpl.LOG.warning("%s - %s", getAuthor(), msg);
    }

    @objid ("c554cb8f-2bc4-4e81-a8d7-404d3908ddad")
    @Override
    public void error(final String msg) {
        ApiImpl.LOG.error("%s - %s", getAuthor(), msg);
    }

    @objid ("10a19340-b7fa-47d6-b6a8-0f51d0bc3668")
    @Override
    public void info(final Throwable t) {
        ApiImpl.LOG.info(getAuthor());
        ApiImpl.LOG.info(t);
        
    }

    @objid ("e8a77a88-88dd-4bf1-8021-86cb18dd1ea9")
    @Override
    public void warning(final Throwable t) {
        ApiImpl.LOG.warning(getAuthor());
        ApiImpl.LOG.warning(t);
        
    }

    @objid ("08a48389-2eb0-4767-bb64-80fbbb578b0a")
    @Override
    public void error(final Throwable t) {
        ApiImpl.LOG.error(getAuthor());
        ApiImpl.LOG.error(t);
        
    }

    /**
     * Get an identifier for the module.
     * <p>
     * Returns in this preference order:
     * <ul>
     * <li>the module name
     * <li>the module identifier (the module model is not available)
     * <li> {@value #API_PLUGIN_ID} (there is no module)
     * <li>
     * @return an identifier for the module
     */
    @objid ("a2f9dac6-b411-4dfb-b0ab-852bf4d22e59")
    private String getAuthor() {
        if (this.author == null)
            return Api.PLUGIN_ID;
        else
            return this.author;
        
    }

    @objid ("d6da1678-e824-49c1-9421-ab6a48e54829")
    public  LogService(String author) {
        this.author = author;
    }

}
