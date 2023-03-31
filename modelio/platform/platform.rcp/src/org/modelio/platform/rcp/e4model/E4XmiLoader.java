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
package org.modelio.platform.rcp.e4model;

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
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.impl.PlaceholderImpl;
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
import org.modelio.platform.rcp.plugin.CoreRcp;
import org.osgi.framework.Bundle;

/**
 * Service class that loads an E4XMI resource and merges it into the application model.
 * 
 * @see org.eclipse.e4.ui.internal.workbench.ModelAssembler
 */
@objid ("627cf9eb-d3d1-4c99-94a9-6eb37f10f940")
@SuppressWarnings ("restriction")
public final class E4XmiLoader {
    /**
     * Load an E4XMI plugin resource and merges it into the application model.
     * <p>
     * Imports won't be resolved.
     * @param application the Eclipse 4 application model.
     * @param e4ModelPath the .e4xmi file path relative to the plugin.
     * @param contributorPlugin an Eclipse plugin.
     * @return the loaded application elements.
     */
    @objid ("f318d1db-1fcd-4925-8d52-b22a78a2dbf5")
    public static List<MApplicationElement> load(final MApplication application, final String e4ModelPath, final Bundle contributorPlugin) {
        final URI e4ModelURI = URI.createURI(contributorPlugin.getEntry(e4ModelPath).toString());
        final String contributorURI = URI.createPlatformPluginURI(contributorPlugin.getSymbolicName(), true).toString();
        return E4XmiLoader.load(application, e4ModelURI, contributorURI);
    }

    /**
     * Load an E4XMI plugin resource and merges it into the application model.
     * <p>
     * Imports won't be resolved.
     * @param application the Eclipse 4 application model.
     * @param e4ModelURI the E4XMI file URI.
     * @param contributorURI the contributor plugin URI. The URI must have the "platform:/plugin/your.plugin.id" format, with no trailing '/'.
     * @return the loaded application elements.
     */
    @objid ("cd37f50a-4b6a-4610-a66d-3f7ca5459012")
    private static List<MApplicationElement> load(final MApplication application, final URI e4ModelURI, final String contributorURI) {
        final Resource applicationResource = ((EObject) application).eResource();
        final ResourceSet resourceSet = applicationResource.getResourceSet();
        final E4XMIResource e4appResource = (E4XMIResource) applicationResource;
        
        Resource resource;
        try {
            resource = resourceSet.getResource(e4ModelURI, true);
        } catch (final RuntimeException e) {
            throw new RuntimeException("Unable to load '" + e4ModelURI + "' model extension: " + e.getMessage(), e); //$NON-NLS-1$
        }
        
        final EList<?> contents = resource.getContents();
        if (contents.isEmpty()) {
            return Collections.emptyList();
        }
        
        final Object extensionRoot = contents.get(0);
        
        if (!(extensionRoot instanceof MModelFragments)) {
            throw new IllegalArgumentException(extensionRoot + " is not a MModelFragments");
        }
        
        final List<MApplicationElement> addedElements = new ArrayList<>();
        
        boolean evalImports = false;
        final MModelFragments fragmentsContainer = (MModelFragments) extensionRoot;
        final List<MModelFragment> fragments = fragmentsContainer.getFragments();
        for (final MModelFragment fragment : fragments) {
            final List<MApplicationElement> elements = fragment.getElements();
            if (elements.isEmpty()) {
                continue;
            }
        
            for (final MApplicationElement el : elements) {
                final EObject o = (EObject) el;
        
                // Remember IDs of items
                E4XMIResource r = (E4XMIResource) o.eResource();
                e4appResource.setID(o, r.getID(o));
        
                // Assign contributor URI
                if (contributorURI != null) {
                    el.setContributorURI(contributorURI);
                }
        
                // Remember IDs of subitems
                final TreeIterator<EObject> treeIt = EcoreUtil.getAllContents(o, true);
                while (treeIt.hasNext()) {
                    final EObject eObj = treeIt.next();
                    r = (E4XMIResource) eObj.eResource();
                    if (contributorURI != null && eObj instanceof MApplicationElement) {
                        ((MApplicationElement) eObj).setContributorURI(contributorURI);
                    }
                    e4appResource.setID(eObj, r.getInternalId(eObj));
                }
            }
        
            // Merge the fragment into the MApplication
            final List<MApplicationElement> merged = fragment.merge(application);
            if (merged.size() > 0) {
                evalImports = true;
                addedElements.addAll(merged);
            }
        }
        
        if (evalImports) {
            final List<MApplicationElement> localImports = fragmentsContainer.getImports();
            if (localImports != null) {
                E4XmiLoader.resolveImports(application, localImports, addedElements);
            }
        }
        return addedElements;
    }

    /**
     * Remove all E4 elements that belong to the given Eclipse bundle.
     * @param application the application model to clean. *
     * @param e4ModelPath the .e4xmi file path relative to the plugin.
     * @param contributorPlugin an Eclipse plugin.
     */
    @objid ("828f508c-f4a2-4127-9867-a6678a148ae0")
    public static void unload(final MApplication application, final String e4ModelPath, final Bundle contributorPlugin) {
        final URI e4ModelURI = URI.createURI(contributorPlugin.getEntry(e4ModelPath).toString());
        final String contributorURI = URI.createPlatformPluginURI(contributorPlugin.getSymbolicName(), true).toString();
        E4XmiLoader.unload(application, e4ModelURI, contributorURI);
        
    }

    /**
     * Remove all E4 elements that belong to the given contributor.
     * @param application the application model to clean.
     * @param e4ModelURI the E4XMI file URI.
     * @param contributorURI the contributor plugin URI.
     */
    @objid ("2b7b1b42-218c-4bdf-b86e-3004b1154b65")
    private static void unload(final MApplication application, final URI e4ModelURI, final String contributorURI) {
        final EObject eobj = (EObject) application;
        final Collection<EObject> toDel = new ArrayList<>();
        
        for (final TreeIterator<EObject> it = eobj.eAllContents(); it.hasNext();) {
            final EObject child = it.next();
        
            if (child instanceof MApplicationElement) {
                final MApplicationElement c = (MApplicationElement) child;
                if (contributorURI.equals(c.getContributorURI())) {
                    toDel.add(child);
                    it.prune();
                }
            }
        }
        
        for (final EObject d : toDel) {
            EcoreUtil.delete(d, true);
        }
        
        // Unload the resource itself
        final Resource applicationResource = ((EObject) application).eResource();
        final ResourceSet resourceSet = applicationResource.getResourceSet();
        
        try {
            final Resource resource = resourceSet.getResource(e4ModelURI, false);
            if (resource != null) {
                resource.unload();
                resourceSet.getResources().remove(resource);
            }
        } catch (final RuntimeException e) {
            throw new RuntimeException("Unable to unload '" + e4ModelURI + "' model extension: " + e.getMessage(), e); //$NON-NLS-1$
        }
        
    }

    @objid ("677de8b8-e3af-4ed5-bb56-ef7d3a0c01ac")
    private static void resolveImports(final MApplication application, final List<MApplicationElement> imports, final List<MApplicationElement> addedElements) {
        if (imports.isEmpty()) {
            return;
        }
        // now that we have all components loaded, resolve imports
        final Map<MApplicationElement, MApplicationElement> importMaps = new HashMap<>();
        for (final MApplicationElement importedElement : imports) {
            final MApplicationElement realElement = ModelUtils.findElementById(application, importedElement.getElementId());
            if (realElement == null) {
                CoreRcp.LOG.warning("Could not resolve an import element for '" + importedElement + "'"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        
            importMaps.put(importedElement, realElement);
        }
        
        final TreeIterator<EObject> it = EcoreUtil.getAllContents(addedElements);
        
        while (it.hasNext()) {
            final EObject target = it.next();
        
            final EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) target.eCrossReferences().iterator();
            while (featureIterator.hasNext()) {
                final EObject importObject = featureIterator.next();
                if (importObject.eContainmentFeature() == FragmentPackageImpl.Literals.MODEL_FRAGMENTS__IMPORTS && importObject instanceof MApplicationElement) {
                    final EStructuralFeature feature = featureIterator.feature();
        
                    final MApplicationElement el = importMaps.get((MApplicationElement) importObject);
                    if (el == null) {
                        CoreRcp.LOG.warning("Could not resolve import for " + importObject); //$NON-NLS-1$
                        continue;
                    }
        
                    if (feature.isMany()) {
                        CoreRcp.LOG.error("Replacing"); //$NON-NLS-1$
                        @SuppressWarnings ("unchecked")
                        final List<Object> l = (List<Object>) target.eGet(feature);
                        final int index = l.indexOf(importObject);
                        if (index >= 0) {
                            l.set(index, el);
                        }
                    } else {
                        target.eSet(feature, el);
                    }
                }
            }
        }
        
    }

    /**
     * Remove all E4 elements that belong to the given Eclipse bundle.
     * @param application the application model to clean. *
     * @param e4ModelPath the .e4xmi file path relative to the plugin.
     * @param contributorPlugin an Eclipse plugin.
     */
    @objid ("afe1698b-00d6-4167-9d59-3d2ef8b4603c")
    public static void setRendered(final MApplication application, final String e4ModelPath, final Bundle contributorPlugin, final boolean value) {
        final URI e4ModelURI = URI.createURI(contributorPlugin.getEntry(e4ModelPath).toString());
        final String contributorURI = URI.createPlatformPluginURI(contributorPlugin.getSymbolicName(), true).toString();
        E4XmiLoader.setVisible(application, e4ModelURI, contributorURI, value);
        
    }

    /**
     * Show/hide all E4 elements that belong to the given contributor.
     * @param application the application model to update.
     * @param e4ModelURI the E4XMI file URI.
     * @param contributorURI the contributor plugin URI.
     */
    @objid ("d6984ffa-cbe6-4c9b-91f9-f670c0205814")
    private static void setVisible(final MApplication application, final URI e4ModelURI, final String contributorURI, final boolean value) {
        final EObject eobj = (EObject) application;
        
        for (final TreeIterator<EObject> it = eobj.eAllContents(); it.hasNext();) {
            final EObject child = it.next();
        
            if (child instanceof MUIElement) {
                final MUIElement c = (MUIElement) child;
        
                /**
                 * For Placolder, check contained Part
                 */
                if(c instanceof PlaceholderImpl) {
                    PlaceholderImpl pls = (PlaceholderImpl) c;
                    if(pls.getRef()  != null && pls.getRef().getContributorURI() != null && pls.getRef().getContributorURI().equals(contributorURI)) {
                        c.setVisible(value);
                        it.prune();
                    }
                }else if (contributorURI.equals(c.getContributorURI())) {
                    // Ignore elements belonging to an MPartDescriptor to avoid ClassCastExceptions in e4 renderers
                    if (!(((EObject) c).eContainer() instanceof MPartDescriptor)) {
                        c.setVisible(value);
                    }
                    it.prune();
                }
            }
        }
        
    }

}
