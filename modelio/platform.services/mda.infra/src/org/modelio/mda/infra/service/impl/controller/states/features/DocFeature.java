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

package org.modelio.mda.infra.service.impl.controller.states.features;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.help.internal.HelpPlugin;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Install documentation bundle so that they are accessible from Help system.
 */
@objid ("3a7bd508-6b45-434b-9f19-bccc9a3b15f5")
@SuppressWarnings("restriction")
public class DocFeature extends AbstractFeature {
    @objid ("550670e9-032a-11e2-9fca-001ec947c8cc")
    private final List<Bundle> docBundles = new ArrayList<>();

    /**
     * @param module the module
     */
    @objid ("58adaed7-4c5c-41ec-995f-cebe71bbcaa8")
    public DocFeature(IRTModuleAccess module) {
        super(module);
    }

    @objid ("365b2289-c6a4-41be-ab2a-af8fa056b2b3")
    @Override
    public void enable() {
        installDocs() ;
    }

    @objid ("8cdad17a-d953-4a8b-b4dd-9d337b8a0de6")
    @Override
    public void disable() {
        uninstallDocs();
    }

    @objid ("930fd91e-ce1a-416e-beca-3937c613b26a")
    private void installDocs() {
        for (Path docFile : this.module.getConfiguration().getDocpath()) {
            //MdaInfra.LOG.debug("  adding '%s' documentation.", docFile);
            try {
                BundleContext bundleContext = MdaInfra.getContext();
        
                Bundle bundleDoc = bundleContext.installBundle("reference:file:/" + docFile);
                bundleDoc.start(Bundle.START_TRANSIENT);
        
                this.docBundles.add(bundleDoc);
        
            } catch (Exception e) {
                MdaInfra.LOG.warning(" Failed installing '%s' documentation bundle: %s", docFile, e.toString());
        
                // Ignore DuplicateBundleException: doc already installed
                if (! e.getClass().getSimpleName().equals("DuplicateBundleException")) {
                    // Log and continue
                    MdaInfra.LOG.debug(e);
                }
            }
        }
        
        // Force the help to reload
        if (!this.docBundles.isEmpty()) {
            HelpPlugin.getTocManager().clearCache();
        }
    }

    @objid ("f22df734-674d-43b6-858d-12e0d2990f78")
    protected void uninstallDocs() {
        if (!this.docBundles.isEmpty()) {
            for (Bundle bundleDoc : this.docBundles) {
                //MdaInfra.LOG.debug("  removing '%s' documentation.", bundleDoc.getSymbolicName());
                try {
                    bundleDoc.stop();
                    bundleDoc.uninstall();
                } catch (BundleException | IllegalStateException e) {
                    // ignore java.lang.IllegalStateException: Module has been uninstalled.
                    MdaInfra.LOG.warning("'%s' documentation uninstall failed: %s",bundleDoc.getSymbolicName(), e.toString());
                }
            }
        
            // Empty the local bundle cache
            this.docBundles.clear();
        
            // Force the help to reload
            HelpPlugin.getTocManager().clearCache();
        }
    }

    @objid ("ea6366a9-b7c2-4e45-b7b0-216fb0a52b14")
    @Override
    public String toString() {
        return "Documentation feature";
    }

}
