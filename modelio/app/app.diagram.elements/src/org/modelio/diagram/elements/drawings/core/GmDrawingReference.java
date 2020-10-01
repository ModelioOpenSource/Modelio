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

package org.modelio.diagram.elements.drawings.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmReference;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("e70dab48-c336-4551-9569-4c8a4bd21890")
public class GmDrawingReference<T extends IGmDrawing> implements IGmReference<T> {
    @objid ("148a7ba5-289e-48b6-87e9-f1167d709df5")
    private static final int MINOR_VERSION = 0;

    @objid ("db4e5a8b-8c7c-4b7c-a996-813ef6d25d2d")
    private static final String MINOR_VERSION_PREFIX = GmDrawingReference.class.getSimpleName() + ".";

    /**
     * The suffix of the minor version property to use to read and write the minor version.
     * <p>
     * It is advised to use {@link #readMinorVersion(IDiagramReader, String)} and {@link #writeMinorVersion(IDiagramWriter, String, int)} instead of directly using this constant.
     */
    @objid ("ed548ee8-9899-4d59-a641-094ad67a55b3")
    private static final String MINOR_VERSION_PROPERTY = "version";

    @objid ("f3c0ede3-07ae-4bf0-b52d-ca8f903318a9")
    private transient WeakReference<T> delegate;

    @objid ("0ef8a5bf-0b46-493c-ac1a-5ca97e542bdd")
    private String targetElementRef;

    @objid ("635de1e0-efd2-487c-ae3d-7417f5db05c0")
    private String targetGraphicClass;

    /**
     * Listeners of all property change events.
     */
    @objid ("3416ffe1-6eec-47f1-91eb-b04e94c5f9f8")
    private PropertyChangeSupport listeners;

    @objid ("0433c6ed-724f-47c2-a693-15e0db0257ce")
    private IGmDiagram ownerDiagram;

    @objid ("3eb3b0bd-3436-4de0-a214-4ca24b3dd6f1")
    private transient IGmDiagram targetDiagram;

    @objid ("be76d6f7-b022-4f21-8d39-5cb0b7b939df")
    private MRef targetDiagramRef;

    /**
     * For deserialization only
     */
    @objid ("f3d0603a-46cc-423c-a622-fc75a1adbd48")
    public GmDrawingReference() {
        // so that delegate is never null
        this.delegate = new WeakReference<>(null);
    }

    /**
     * Constructor
     * 
     * @param ownerDiagram the diagram owning this reference, the diagram who needs a reference toward <i>delegate</i>.
     * @param target the target graphic model
     */
    @objid ("52e88803-fd0e-4780-84bf-a3ce9d9a592f")
    public GmDrawingReference(IGmDiagram ownerDiagram, T target) {
        this.ownerDiagram = ownerDiagram;
        this.targetDiagram = target.getDiagram();
        
        this.delegate = new WeakReference<>(target);
        this.targetElementRef = target.getIdentifier();
        this.targetDiagramRef = this.targetDiagram.getRepresentedRef();
        this.targetGraphicClass = target.getClass().getName();
        
        if (this.ownerDiagram != null) {
            this.ownerDiagram.addGraphicReference(this);
        }
    }

    @objid ("14a7ccc8-899e-4879-9c12-c4cf3232dce4")
    public GmDrawingReference(IGmObject owner, T target) {
        this(owner.getDiagram(), target);
    }

    @objid ("d6559db9-15a3-4da4-b47a-18392ad2fadf")
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getListeners().addPropertyChangeListener(listener);
    }

    @objid ("59d03003-fca1-44c7-8047-22eea8709e98")
    @Override
    public void addReferenceBrokenListener(PropertyChangeListener listener) {
        addReferenceChangeListener(IGmReference.PROP_REFERENCE_BROKEN, listener);
    }

    @objid ("68ec622d-2eba-4c06-9394-22806b051926")
    public void addReferenceChangeListener(String propertyName, PropertyChangeListener listener) {
        getListeners().addPropertyChangeListener(propertyName, listener);
    }

    @objid ("0c790119-3408-4720-8352-2825f561da04")
    @Override
    public void addReferenceResolvedListener(PropertyChangeListener listener) {
        addReferenceChangeListener(IGmReference.PROP_REFERENCE_RESOLVED, listener);
    }

    @objid ("68ba5cb9-f172-4700-b919-05c3181267ef")
    @Override
    public int getMajorVersion() {
        return 0;
    }

    @objid ("ac5856a9-5712-4f8a-a896-c7155f617fb5")
    @Override
    public T getReferencedModel() {
        final T m = this.delegate.get();
        if (m != null && m.getDiagram() != null) {
            // reference valid, return it
            return m;
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

    @objid ("f6621266-40ce-4cba-9bb6-6d0108013da1")
    public MRef getTargetDiagramRef() {
        return this.targetDiagramRef;
    }

    @objid ("83bb2727-eb7b-4d83-b576-01e4fb2e058a")
    public String getTargetElementRef() {
        return this.targetElementRef;
    }

    @objid ("5c6c2054-3467-4f64-8e0c-f0f90897e83d")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("07dd6c1a-2e03-4d4b-aa10-9c97094b69da")
    @Override
    public void read(IDiagramReader in) {
        int readVersion = readMinorVersion(in, GmDrawingReference.MINOR_VERSION_PREFIX);
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

    @objid ("ebdc1e25-e2f7-42f7-a836-3ce0aa5db04b")
    @SuppressWarnings("unchecked")
    public static <T extends IGmDrawing> GmDrawingReference<T> read(IDiagramReader in, String propName) {
        Object obj = in.readProperty(propName);
        if (obj instanceof GmDrawingReference<?>) {
            return (GmDrawingReference<T>) obj;
        }
        return new GmDrawingReference<>((IGmDiagram)in.getRoot(), (T)obj);
    }

    @objid ("357f29fa-6333-42e4-b2ac-05072109b2a2")
    @Override
    public void refresh() {
        final T m = this.delegate.get();
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
    @objid ("b4d14005-e787-496d-8aec-1b0a1b45d29c")
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
    @objid ("c15cb8e5-b9c4-4376-8c92-f733648a87ce")
    public static <T extends IGmDrawing> void removeFrom(Collection<GmDrawingReference<T>> coll, T toRemove) {
        for (Iterator<GmDrawingReference<T>> it = coll.iterator(); it.hasNext();) {
            GmDrawingReference<?> ref = it.next();
            final IGmDrawing directTarget = ref.delegate.get();
            if (directTarget == toRemove) {
                it.remove();
            } else if (directTarget == null && 
                    ref.targetElementRef.equals(toRemove.getIdentifier()) &&
                    ref.targetGraphicClass.equals(toRemove.getClass().getName())) {
                it.remove();
            }
        }
    }

    @objid ("f8b8aada-e416-4865-a8be-805b58d42ea5")
    @Override
    public void removeReferenceChangeListener(PropertyChangeListener listener) {
        if (this.listeners != null) {
            this.listeners.removePropertyChangeListener(listener);
        }
    }

    @objid ("12af4b74-08b4-4eb6-9960-36ac02d7d333")
    public void setOwnerDiagram(IGmDiagram newOwnerDiagram) {
        if (this.ownerDiagram != newOwnerDiagram) {
            this.ownerDiagram.removeGraphicReference(this);
            this.ownerDiagram = newOwnerDiagram;
            this.ownerDiagram.addGraphicReference(this);
        }
    }

    @objid ("d2da0d9c-ba40-4bae-b35e-69cb723d4d5a")
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

    @objid ("5a990b5d-ebb5-4dad-9b15-4823ab61f301")
    @Override
    public void write(IDiagramWriter out) {
        writeMinorVersion(out, GmDrawingReference.MINOR_VERSION_PREFIX, GmDrawingReference.MINOR_VERSION);
        out.writeProperty("targetDiagram", this.targetDiagramRef);
        out.writeProperty("targetElement", this.targetElementRef);
        out.writeProperty("targetGraphicClass", this.targetGraphicClass);
    }

    @objid ("a7521e5d-7b66-43cf-bb7d-e9c636dfd5a0")
    protected final IGmDiagram doResolveDiagramReference() {
        if (this.ownerDiagram != null) {
            return findTargetDiagram(this.ownerDiagram);
        }
        return null;
    }

    @objid ("5a7f97a1-509a-498f-adc9-f1191edfd383")
    @SuppressWarnings ("unchecked")
    protected T doResolveReference() {
        if (this.targetDiagram == null || this.targetDiagram.isDisposed()) {
            this.targetDiagram = doResolveDiagramReference();
            if (this.targetDiagram == null) {
                return null;
            }
        }
        
        IGmDrawing candidate = this.targetDiagram.getDrawing(this.targetElementRef);
        if (candidate != null && candidate.getClass().getName().equals(this.targetGraphicClass)) {
            
            return (T) candidate;
        }
        return null;
    }

    /**
     * Helper to read the graphic model minor version from the {@value #MINOR_VERSION_PROPERTY} property.
     * 
     * @param in a reader to read the version from.
     * @param prefix the prefix : usually the simple name of java class calling this method + ".".
     * @return the read version, defaults to 0 if not found
     */
    @objid ("77908ac3-bcc1-443f-8958-8bd67043fa36")
    protected static final int readMinorVersion(IDiagramReader in, String prefix) {
        // Read version, defaults to 0 if not found
        Object versionProperty = in.readProperty(prefix + GmDrawingReference.MINOR_VERSION_PROPERTY);
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
    @objid ("b1fdc4cd-c348-4c74-b24f-e1fe1f74630e")
    protected static final void writeMinorVersion(IDiagramWriter out, String prefix, int theMinorVersion) {
        // Write the version if different of 0.
        if (theMinorVersion != 0) {
            out.writeProperty(prefix + GmDrawingReference.MINOR_VERSION_PROPERTY, Integer.valueOf(theMinorVersion));
        }
    }

    @objid ("7717e440-7616-4ceb-bf50-2d1e07a2777f")
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

    @objid ("a7bc67e9-5fd0-4a21-8ad0-fc039f81e292")
    private PropertyChangeSupport getListeners() {
        if (this.listeners == null) {
            this.listeners = new PropertyChangeSupport(this);
        }
        return this.listeners;
    }

    @objid ("0d280f3d-870a-4ca2-a62f-fc31bb5c05b8")
    private void read_0(IDiagramReader in) {
        this.ownerDiagram = (IGmDiagram) in.getRoot();
        this.targetDiagramRef = (MRef) in.readProperty("targetDiagram");
        this.targetElementRef = (String) in.readProperty("targetElement");
        this.targetGraphicClass = (String) in.readProperty("targetGraphicClass");
        if (this.ownerDiagram != null) {
            this.ownerDiagram.addGraphicReference(this);
        }
    }

}
