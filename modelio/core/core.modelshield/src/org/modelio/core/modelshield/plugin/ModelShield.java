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
package org.modelio.core.modelshield.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * ModelShield plugin main class.
 * 
 * Contains static services for log and i18n, as well as the plugin ID.
 */
@objid ("00553480-561e-1033-829a-001ec947cd2a")
public class ModelShield implements BundleActivator {
    @objid ("0055fe1a-561e-1033-829a-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.core.modelshield";

    @objid ("005757b0-561e-1033-829a-001ec947cd2a")
    private static BundleContext context;

    @objid ("00579a68-561e-1033-829a-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
    }

    @objid ("0057d082-561e-1033-829a-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("005805b6-561e-1033-829a-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
