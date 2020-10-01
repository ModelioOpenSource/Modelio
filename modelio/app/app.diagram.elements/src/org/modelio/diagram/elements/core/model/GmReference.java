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

package org.modelio.diagram.elements.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link IGmReference} implementation.
 * 
 * @param <T> the target type
 * @since 3.7
 * @author cma
 */
@objid ("9076d700-5dc2-40f7-9290-63ff86e18e13")
public class GmReference<T extends IGmModelRelated> implements IGmReference<T> {
    @objid ("a472f0f7-1937-47b5-874f-20494db1eb39")
    private static final int MINOR_VERSION = 0;

    @objid ("754eda71-5efb-40f2-878f-7499ce66a9e2")
    private static final String MINOR_VERSION_PREFIX = GmReference.class.getSimpleName() + ".";

    /**
     * The suffix of the minor version property to use to read and write the minor version.
     * <p>
     * It is advised to use {@link #readMinorVersion(IDiagramReader, String)} and {@link #writeMinorVersion(IDiagramWriter, String, int)} instead of directly using this constant.
     */
    @objid ("68924b5d-dbb6-4849-ab17-2596e54c8987")
    private static final String MINOR_VERSION_PROPERTY = "version";

    @objid ("177e537f-5984-4eec-bbbb-5366711ce1f9")
    private transient WeakReference<T> delegate;

    @objid ("3a7e4b22-f039-4f95-b772-fbaf1d2ca35b")
    private String targetGraphicClass;

    /**
     * Listeners of all property change events.
     */
    @objid ("ae9ac3d9-a409-456b-af30-381617db3b00")
    private PropertyChangeSupport listeners;

    @objid ("b4105373-f996-491e-a56a-0898a285b822")
    private IGmDiagram ownerDiagram;

    @objid ("5c7dcad6-07e8-4c0d-8af7-e0448ae57091")
    private transient IGmDiagram targetDiagram;

    @objid ("e2dbc792-b27d-4eda-9a87-80de2736bd41")
    private MRef targetDiagramRef;

    @objid ("b4455df1-8937-4ff7-a814-966cee891482")
    private MRef targetElementRef;

    /**
     * For deserialization only
     */
    @objid ("24619476-124a-4894-8c5c-477f9495c814")
    public GmReference() {
        // so that delegate is never null
        this.delegate = new WeakReference<>(null);
    }

    /**
     * Constructor
     * 
     * @param ownerDiagram the diagram owning this reference, the diagram who needs a reference toward <i>delegate</i>.
     * @param target the target graphic model
     */
    @objid ("65f37178-1a26-4c27-846d-85545feba967")
    public GmReference(IGmDiagram ownerDiagram, T target) {
        this.ownerDiagram = ownerDiagram;
        this.targetDiagram = target.getDiagram();
        if (this.targetDiagram == null) {
            // DiagramElements.LOG.warning(new IllegalArgumentException(String.format("%s has no owner diagram", target)));
            this.targetDiagram = ownerDiagram;
        }
        
        this.delegate = new WeakReference<>(target);
        this.targetElementRef = target.getRepresentedRef();
        this.targetDiagramRef = this.targetDiagram.getRepresentedRef();
        this.targetGraphicClass = target.getClass().getName();
        
        if (this.ownerDiagram != null) {
            this.ownerDiagram.addGraphicReference(this);
        }
    }

    @objid ("d2009113-d06f-424c-8905-b81147f58a95")
    public GmReference(IGmObject owner, T target) {
        this(owner.getDiagram(), target);
    }

    @objid ("fd8d45a5-1e8b-495c-8d4e-1c99b8419aac")
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getListeners().addPropertyChangeListener(listener);
    }

    @objid ("8ee37c9e-b822-41db-a4d3-ffae12363687")
    @Override
    public void addReferenceBrokenListener(PropertyChangeListener listener) {
        addReferenceChangeListener(IGmReference.PROP_REFERENCE_BROKEN, listener);
    }

    @objid ("f93f0107-9066-46ad-b1ac-03ab9b6eae04")
    public void addReferenceChangeListener(String propertyName, PropertyChangeListener listener) {
        getListeners().addPropertyChangeListener(propertyName, listener);
    }

    @objid ("d0d661d3-136a-451c-a1fd-79960f324f18")
    @Override
    public void addReferenceResolvedListener(PropertyChangeListener listener) {
        addReferenceChangeListener(IGmReference.PROP_REFERENCE_RESOLVED, listener);
    }

    @objid ("a7c355ff-af85-4b0d-9afc-d390cafdfa73")
    @Override
    public int getMajorVersion() {
        return 0;
    }

    @objid ("d679495e-0da9-4922-a961-068feff81860")
    @SuppressWarnings ("unchecked")
    @Override
    public T getReferencedModel() {
        final IGmModelRelated m = this.delegate.get();
        if (m != null && m.getDiagram() != null) {
            // reference valid, return it
            return (T) m;
        }
        
        // Reference invalid, re-resolve it
        final T resolved = doResolveReference();
        
        // Cache resolved reference
        if (resolved != null) {
            this.delegate = new WeakReference<>(resolved);
            if (this.listeners != null) {
                this.listeners.firePropertyChange(IGmReference.PROP_REFERENCE_RESOLVED, null, resolved);
            }
        }
        return resolved;
    }

    @objid ("691ab76a-acc6-4eb7-a7e9-74d6733aeb88")
    public MRef getTargetDiagramRef() {
        return this.targetDiagramRef;
    }

    @objid ("bc067e60-6f71-4898-965d-e4afca09079f")
    public MRef getTargetElementRef() {
        return this.targetElementRef;
    }

    @objid ("9e05fbf0-b6c8-4319-bad6-2124b6295203")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("e91c06d3-aab3-4a45-9975-238be5a3bfc9")
    @Override
    public void read(IDiagramReader in) {
        int readVersion = readMinorVersion(in, GmReference.MINOR_VERSION_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        
        default:
            assert (false) : readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        
        }
    }

    @objid ("16082c6a-43b1-4b34-a63d-14d96ea8a730")
    @SuppressWarnings ("unchecked")
    public static <T extends IGmModelRelated> GmReference<T> read(IDiagramReader in, String propName) {
        Object obj = in.readProperty(propName);
        if (obj == null) {
            return null;
        } else if (obj instanceof GmReference<?>) {
            return (GmReference<T>) obj;
        } else {
            return new GmReference<>((IGmDiagram) in.getRoot(), (T) obj);
        }
    }

    @objid ("a58e9014-e31b-4595-bf95-0186aff6fd40")
    @Override
    public void refresh() {
        final IGmModelRelated m = this.delegate.get();
        if (m != null && m.getDiagram() != null) {
            // reference already resolved and valid, refresh references from target
            this.targetDiagram = m.getDiagram();
            this.targetDiagramRef = this.targetDiagram.getRepresentedRef();
        } else {
            // try to resolve the reference
            getReferencedModel();
        }
    }

    /**
     * Unregister this reference from the diagram.
     */
    @objid ("080f08d8-951e-4918-b1d8-6f3be95ab135")
    @Override
    public void releaseGmReference() {
        this.ownerDiagram.removeGraphicReference(this);
    }

    /**
     * Remove the element from a collection of GmReferences.
     * 
     * @param coll the collection to modify
     * @param toRemove the element to remove.
     */
    @objid ("ac885826-4cb2-4e9f-9c5c-bca5c0e4548a")
    public static <T extends IGmModelRelated> void removeFrom(Collection<GmReference<T>> coll, T toRemove) {
        for (Iterator<GmReference<T>> it = coll.iterator(); it.hasNext();) {
            GmReference<?> ref = it.next();
            final IGmModelRelated directTarget = ref.delegate.get();
            if (directTarget == toRemove) {
                it.remove();
            } else if (directTarget == null &&
                    ref.targetElementRef.equals(toRemove.getRepresentedRef()) &&
                    ref.targetGraphicClass.equals(toRemove.getClass().getName())) {
                it.remove();
            }
        }
    }

    @objid ("7b7ab170-2f66-4d3d-a900-b42f8a595506")
    @Override
    public void removeReferenceChangeListener(PropertyChangeListener listener) {
        if (this.listeners != null) {
            this.listeners.removePropertyChangeListener(listener);
        }
    }

    @objid ("720f1098-9c88-4465-a67b-acf0e9b9d2c8")
    public void setOwnerDiagram(IGmDiagram newOwnerDiagram) {
        if (this.ownerDiagram != newOwnerDiagram) {
            this.ownerDiagram.removeGraphicReference(this);
            this.ownerDiagram = newOwnerDiagram;
            this.ownerDiagram.addGraphicReference(this);
        }
    }

    @objid ("aed42012-3830-4fe4-a14f-e4ce5e2e9661")
    @Override
    public String toString() {
        T target = this.delegate.get();
        if (target == null || target.getDiagram() == null) {
            // unresolved
            return String.format("GmReference [unresolved, targetDiagramRef=%s, targetElementRef=%s %s]", this.targetDiagramRef, this.targetElementRef, this.targetGraphicClass);
        } else {
            // resolved
            return String.format("GmReference [%s]", target);
        }
    }

    @objid ("bcff4604-1c34-4b36-b66b-32f3471da513")
    @Override
    public void write(IDiagramWriter out) {
        writeMinorVersion(out, GmReference.MINOR_VERSION_PREFIX, GmReference.MINOR_VERSION);
        T targetGm = this.delegate.get();
        
        if(this.targetDiagram == this.ownerDiagram && targetGm != null) {
            out.writeProperty("targetGraphic", targetGm);
        } else {
            out.writeProperty("targetDiagram", this.targetDiagramRef);
            out.writeProperty("targetElement", this.targetElementRef);
            out.writeProperty("targetGraphicClass", this.targetGraphicClass);
        }
    }

    @objid ("d679ba5c-9199-42f2-bd8a-f6c76af004c5")
    protected final IGmDiagram doResolveDiagramReference() {
        if (this.ownerDiagram != null) {
            return findTargetDiagram(this.ownerDiagram);
        }
        return null;
    }

    @objid ("76d67a76-bf62-4bb1-a539-8caa845122eb")
    @SuppressWarnings ("unchecked")
    protected T doResolveReference() {
        if (this.targetDiagram == null || this.targetDiagram.isDisposed()) {
            this.targetDiagram = doResolveDiagramReference();
            if (this.targetDiagram == null) {
                return null;
            }
        }
        
        GmModel representing = null;
        GmModel firstAny = null;
        Collection<GmModel> candidates = this.targetDiagram.getAllGMRelatedTo(this.targetElementRef);
        for (GmModel model : candidates) {
            this.targetGraphicClass = resolveClass(this.targetDiagram, this.targetGraphicClass);
            if (model.getClass().getName().equals(this.targetGraphicClass)) {
                return (T) model;
            } else if (representing == null && model.getRepresentedElement() != null && model.getRepresentedElement().getUuid().equals(this.targetElementRef.uuid)) {
                representing = model;
            } else if (firstAny == null) {
                firstAny = model;
            }
        }
        
        if (representing != null) {
            return (T) representing;
        }
        return (T) firstAny;
    }

    /**
     * Helper to read the graphic model minor version from the {@value #MINOR_VERSION_PROPERTY} property.
     * 
     * @param in a reader to read the version from.
     * @param prefix the prefix : usually the simple name of java class calling this method + ".".
     * @return the read version, defaults to 0 if not found
     */
    @objid ("d156b9ad-21d9-4020-a7fe-1e12e7df2a9f")
    protected static final int readMinorVersion(IDiagramReader in, String prefix) {
        // Read version, defaults to 0 if not found
        Object versionProperty = in.readProperty(prefix + GmReference.MINOR_VERSION_PROPERTY);
        int readVersion = versionProperty == null ? 0 : ((Integer) versionProperty).intValue();
        return readVersion;
    }

    /**
     * Helper method to write the graphic model minor version.
     * 
     * @param out the writer to use
     * @param prefix the prefix to use. Usually the java simple name of the class calling this method. Use the same as the matching {@link #readMinorVersion(String, IDiagramReader)}.
     * @param theMinorVersion the minor version to write
     */
    @objid ("4d7c6ea8-7e2e-4e8f-9655-a47c8ac82c9c")
    protected static final void writeMinorVersion(IDiagramWriter out, String prefix, int theMinorVersion) {
        // Write the version if different of 0.
        if (theMinorVersion != 0) {
            out.writeProperty(prefix + GmReference.MINOR_VERSION_PROPERTY, Integer.valueOf(theMinorVersion));
        }
    }

    @objid ("56eba11e-46ad-41a6-bac2-38d5613bcf3a")
    private IGmDiagram findTargetDiagram(IGmDiagram into) {
        if (into.getRepresentedRef().equals(this.targetDiagramRef)) {
            return into;
        }
        
        for (IGmDiagram subGmDiagram : into.getEmbeddedDiagrams()) {
            final IGmDiagram targetDiag = findTargetDiagram(subGmDiagram);
            if (targetDiag != null) {
                return targetDiag;
            }
        }
        return null;
    }

    @objid ("1806b69f-d12f-4a03-aab7-6697f70691ea")
    private PropertyChangeSupport getListeners() {
        if (this.listeners == null) {
            this.listeners = new PropertyChangeSupport(this);
        }
        return this.listeners;
    }

    @objid ("44d94d2a-9944-49a2-b254-15893776c44f")
    private void read_0(IDiagramReader in) {
        this.ownerDiagram = (IGmDiagram) in.getRoot();
        
        @SuppressWarnings ("unchecked")
        T target = (T) in.readProperty("targetGraphic");
        
        if (target != null) {
            this.targetDiagramRef = target.getDiagram().getRepresentedRef();
            this.targetElementRef = target.getRepresentedRef();
            this.targetGraphicClass = target.getClass().getName();
            this.targetDiagram = target.getDiagram();
            this.delegate = new WeakReference<>(target);
        } else {
            this.targetDiagramRef = (MRef) in.readProperty("targetDiagram");
            this.targetElementRef = (MRef) in.readProperty("targetElement");
            this.targetGraphicClass = (String) in.readProperty("targetGraphicClass");
        }
        if (this.ownerDiagram != null) {
            this.ownerDiagram.addGraphicReference(this);
        }
    }

    /**
     * Deal with namespace migration.
     */
    @objid ("1188c4c8-6130-4b73-8727-cd1fc5c0adbe")
    private String resolveClass(IGmDiagram diagram, String namespace) {
        Class<? extends IPersistent> nodeNamespace = diagram.getGmNodeFactory().resolveClass(namespace);
        if (nodeNamespace != null) {
            return nodeNamespace.getName();
        }
        
        Class<? extends IPersistent> linkNamespace = diagram.getGmLinkFactory().resolveClass(namespace);
        if (linkNamespace != null) {
            return linkNamespace.getName();
        }
        return namespace;
    }

}
