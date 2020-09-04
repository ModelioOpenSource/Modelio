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

package org.modelio.core.rcp.e4model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.internal.workbench.E4XMIResource;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.model.fragment.MModelFragment;
import org.eclipse.e4.ui.model.fragment.MModelFragments;
import org.eclipse.e4.ui.model.fragment.impl.FragmentPackageImpl;
import org.eclipse.e4.ui.model.internal.ModelUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelio.core.rcp.plugin.CoreRcp;
import org.osgi.framework.Bundle;

/**
 * Service class that loads an E4XMI resource and merges it into the application model.
 * 
 * @see org.eclipse.e4.ui.internal.workbench.ModelAssembler
 */
@objid ("627cf9eb-d3d1-4c99-94a9-6eb37f10f940")
public final class E4XmiLoader {
    /**
     * Load an E4XMI plugin resource and merges it into the application model.
     * <p>
     * Imports won't be resolved.
     * @param application the Eclipse 4 application model.
     * @param plugin an Eclipse plugin.
     * @param pluginEntryPath the .e4xmi file path relative to the plugin.
     * @return the loaded application elements.
     */
    @objid ("f318d1db-1fcd-4925-8d52-b22a78a2dbf5")
    public static List<MApplicationElement> load(MApplication application, Bundle plugin, String pluginEntryPath) {
        URL url = plugin.getEntry(pluginEntryPath);
        URI uri = URI.createURI(url.toString());
        
        String contributorURI = URI.createPlatformPluginURI(plugin.getSymbolicName(), true).toString();
        return E4XmiLoader.load(application, uri, contributorURI);
    }

    /**
     * Load an E4XMI resource and merges it into the application model.
     * <p>
     * Imports won't be resolved.
     * @param application the Eclipse 4 application model.
     * @param uri the E4XMI file URI.
     * @param contributorURI the contributor plugin URI. Used to translate labels starting with '%'.
     * The URI must have the "platform:/plugin/your.plugin.id" format, with no trailing '/'.
     * @return the loaded application elements.
     */
    @objid ("cd37f50a-4b6a-4610-a66d-3f7ca5459012")
    public static List<MApplicationElement> load(MApplication application, URI uri, String contributorURI) {
        Resource applicationResource = ((EObject) application).eResource();
        ResourceSet resourceSet = applicationResource.getResourceSet();
        E4XMIResource e4appResource = (E4XMIResource) applicationResource;
        
        Resource resource;
        try {
            resource = resourceSet.getResource(uri, true);
        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to read '" + uri + "' model extension: " + e.getMessage(), e); //$NON-NLS-1$
        }
        
        EList<?> contents = resource.getContents();
        if (contents.isEmpty()) {
            return Collections.emptyList();
        }
        
        Object extensionRoot = contents.get(0);
        
        if (!(extensionRoot instanceof MModelFragments)) {
            throw new IllegalArgumentException(extensionRoot + " is not a MModelFragments");
        }
        
        List<MApplicationElement> addedElements = new ArrayList<>();
        
        boolean evalImports = false;
        MModelFragments fragmentsContainer = (MModelFragments) extensionRoot;
        List<MModelFragment> fragments = fragmentsContainer.getFragments();
        for (MModelFragment fragment : fragments) {
            List<MApplicationElement> elements = fragment.getElements();
            if (elements.isEmpty()) {
                continue;
            }
        
            for (MApplicationElement el : elements) {
                EObject o = (EObject) el;
        
                // Remember IDs of items
                E4XMIResource r = (E4XMIResource) o.eResource();
                e4appResource.setID(o, r.getID(o));
        
                // Assign contributor URI
                if (contributorURI != null) {
                    el.setContributorURI(contributorURI);
                }
        
                // Remember IDs of subitems
                TreeIterator<EObject> treeIt = EcoreUtil.getAllContents(o, true);
                while (treeIt.hasNext()) {
                    EObject eObj = treeIt.next();
                    r = (E4XMIResource) eObj.eResource();
                    if (contributorURI != null && (eObj instanceof MApplicationElement)) {
                        ((MApplicationElement) eObj).setContributorURI(contributorURI);
                    }
                    e4appResource.setID(eObj, r.getInternalId(eObj));
                }
            }
        
            // Merge the fragment into the MApplication
            List<MApplicationElement> merged = fragment.merge(application);
            if (merged.size() > 0) {
                evalImports = true;
                addedElements.addAll(merged);
            }
        }
        
        if (evalImports) {
            List<MApplicationElement> localImports = fragmentsContainer.getImports();
            if (localImports != null) {
                E4XmiLoader.resolveImports(application, localImports, addedElements);
            }
        }
        return addedElements;
    }

    /**
     * Remove all E4 elements that belong to the given Eclipse bundle.
     * @param application the application model to clean.
     * @param plugin the bundle to remove.
     */
    @objid ("828f508c-f4a2-4127-9867-a6678a148ae0")
    public static void unload(MApplication application, Bundle plugin) {
        String contributorURI = URI.createPlatformPluginURI(plugin.getSymbolicName(), true).toString();
        E4XmiLoader.unload(application, contributorURI);
    }

    /**
     * Remove all E4 elements that belong to the given contributor.
     * @param application the application model to clean.
     * @param contributorURI the contributorURI to remove.
     */
    @objid ("2b7b1b42-218c-4bdf-b86e-3004b1154b65")
    public static void unload(MApplication application, String contributorURI) {
        EObject eobj = (EObject) application;
        Collection<EObject> toDel = new ArrayList<>();
        for (TreeIterator<EObject> it = eobj.eAllContents(); it.hasNext();) {
            EObject child = it.next();
        
            if (child instanceof MApplicationElement) {
                MApplicationElement c = (MApplicationElement) child;
                if (contributorURI.equals(c.getContributorURI())) {
                    toDel.add(child);
                    it.prune();
                }
            }
        }
        
        for (EObject d : toDel) {
            EcoreUtil.delete(d);
        }
    }

    @objid ("677de8b8-e3af-4ed5-bb56-ef7d3a0c01ac")
    private static void resolveImports(MApplication application, List<MApplicationElement> imports, List<MApplicationElement> addedElements) {
        if (imports.isEmpty()) {
            return;
        }
        // now that we have all components loaded, resolve imports
        Map<MApplicationElement, MApplicationElement> importMaps = new HashMap<>();
        for (MApplicationElement importedElement : imports) {
            MApplicationElement realElement = ModelUtils.findElementById(application,
                    importedElement.getElementId());
            if (realElement == null) {
                CoreRcp.LOG.warning("Could not resolve an import element for '" + realElement + "'"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        
            importMaps.put(importedElement, realElement);
        }
        
        TreeIterator<EObject> it = EcoreUtil.getAllContents(addedElements);
        List<Runnable> commands = new ArrayList<>();
        
        // TODO Probably use EcoreUtil.UsageCrossReferencer
        while (it.hasNext()) {
            EObject o = it.next();
        
            EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) o
                    .eCrossReferences().iterator();
            while (featureIterator.hasNext()) {
                EObject importObject = featureIterator.next();
                if (importObject.eContainmentFeature() == FragmentPackageImpl.Literals.MODEL_FRAGMENTS__IMPORTS) {
                    EStructuralFeature feature = featureIterator.feature();
        
                    MApplicationElement el = importMaps.get(importObject);
                    if (el == null) {
                        CoreRcp.LOG.warning("Could not resolve import for " + el); //$NON-NLS-1$
                    }
        
                    final EObject interalTarget = o;
                    final EStructuralFeature internalFeature = feature;
                    final MApplicationElement internalElment = el;
                    final EObject internalImportObject = importObject;
        
                    commands.add(new Runnable() {
        
                        @Override
                        public void run() {
                            if (internalFeature.isMany()) {
                                CoreRcp.LOG.error("Replacing"); //$NON-NLS-1$
                                @SuppressWarnings("unchecked")
                                List<Object> l = (List<Object>) interalTarget.eGet(internalFeature);
                                int index = l.indexOf(internalImportObject);
                                if (index >= 0) {
                                    l.set(index, internalElment);
                                }
                            } else {
                                interalTarget.eSet(internalFeature, internalElment);
                            }
                        }
                    });
                }
            }
        }
        
        for (Runnable cmd : commands) {
            cmd.run();
        }
    }

}
